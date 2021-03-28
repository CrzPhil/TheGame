package com.company.collisions;

import city.cs.engine.*;
import com.company.bodies.dynamics.Enemy;
import com.company.bodies.dynamics.Hero;
import com.company.bodies.dynamics.Spikeball;
import com.company.bodies.statics.*;
import com.company.levels.GameLevel;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;

public class HeroCollisions implements CollisionListener {
    final private GameLevel world;
    private static SoundClip pickupSound;
    private static SoundClip checkPoint;
    private static SoundClip gameOver;

    // Load sound for pickup and Checkpoint
    static {
        try {
            pickupSound = new SoundClip("data/music/pickup.wav");
            checkPoint = new SoundClip("data/music/check.wav");
            gameOver = new SoundClip("data/music/gameOver.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException exc) {
            System.out.println(exc);
        }
    }

    // Constructor
    public HeroCollisions(GameLevel world, Hero Spartan) {
        this.world = world;
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {
        // If Hero collides with a special object like the boots in level 2
        if (collisionEvent.getOtherBody() instanceof SpecialObject) {
            // Special object is destroyed after collision
            collisionEvent.getOtherBody().destroy();
            // When Hero picks up the boots, GravityScale is reduced, imitating a jump-boost effect
            ((SpecialObject) collisionEvent.getOtherBody()).hermesBoots();
            // Play pickup sound
            pickupSound.play();
        }
        // If checkpoint is hit, flag gets updated and sound is played
        if (collisionEvent.getOtherBody() instanceof Checkpoint) {
            checkPoint.play();
            // On checkpoint, Hero's lives are reset to full
            world.getHero().setHealth(3);
            world.updateHealth();
            // Increments checkpoint field by 1, helps track where to relocate him when button is pressed
            world.getHero().hitCheckPoint();
            // Save the position of the old flag to replace it with the other flag which is transparent
            Vec2 oldPos = new Vec2(collisionEvent.getOtherBody().getPosition());
            // Old flag is destroyed
            collisionEvent.getOtherBody().destroy();
            // New flag added, without any hitbox
            new Checkpoint(world, new BodyImage("data/graphics/checkpointcompleted.png", 4)).setPosition(oldPos);
        }
        // If Hero hits the barrier at the end of level, next level is initiated
        if (collisionEvent.getReportingBody() instanceof Hero && collisionEvent.getOtherBody() instanceof Barrier && world.isComplete()) {
            world.getGame().goToNextLevel();
        }
        // If he hits a barrier and the world is not completed (i.e level four, switching of View
        else if (collisionEvent.getReportingBody() instanceof Hero && collisionEvent.getOtherBody() instanceof Barrier) {
            world.getGame().getView().setView(new Vec2(50, 0), 20);
            collisionEvent.getOtherBody().destroy();
            // As the view moves, we have to also reposition the Heart-Overlay
            world.getHeart().setPosition(new Vec2(30, 18));
            world.getGame().getView().setBackground(new ImageIcon("data/graphics/townbckg.png").getImage());
        }
        // If Hero collides with Spike-ball, he takes damage, spike-ball is destroyed
        if (collisionEvent.getOtherBody() instanceof Spikeball) {
            collisionEvent.getOtherBody().destroy();
            world.getHero().takeDamage();
            // Updates Image
            world.getHeart().updateLife();
            if (world.getHero().getHealth() == 0) {
                new Text(world, new BodyImage("data/graphics/dead.png"), true).setPosition(new Vec2(0, 0));
            }
        }
        // If Enemy hits Spartan, he takes damage
        if (collisionEvent.getOtherBody() instanceof Enemy && collisionEvent.getReportingBody() instanceof Hero) {
            world.getHero().takeDamage();
            world.getHero().incrementScore();
            world.getHeart().updateLife();
            collisionEvent.getOtherBody().destroy();
        }
        // For the last level, if Spartan hits the throne, game is over
        if (collisionEvent.getOtherBody() instanceof Throne && collisionEvent.getReportingBody() instanceof Hero) {
            gameOver.play();
            world.stop();
            new Text(world, new BodyImage("data/graphics/end.png"), true).setPosition(new Vec2(50, 0));
            world.getGame().getCurrentMusic().stop();
            world.getGame().setCurrentMusic(world.getGame().getLevel1Music());
            world.getGame().getCurrentMusic().loop();
        }
    }
}
