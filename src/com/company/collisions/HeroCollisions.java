package com.company.collisions;

import city.cs.engine.*;
import com.company.bodies.dynamics.Hero;
import com.company.bodies.statics.Checkpoint;
import com.company.bodies.statics.SpecialObject;
import com.company.levels.GameLevel;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class HeroCollisions implements CollisionListener {
    final private Hero Spartan;
    final private GameLevel world;
    private static SoundClip pickupSound;
    private static SoundClip checkPoint;

    // Load sound for pickup and Checkpoint
    static {
        try {
            pickupSound = new SoundClip("data/music/pickup.wav");
            checkPoint = new SoundClip("data/music/check.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException exc) {
            System.out.println(exc);
        }
    }

    // Constructor
    public HeroCollisions(GameLevel world, Hero Spartan) {
        this.Spartan = Spartan;
        this.world = world;
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {
        // If Hero collides with a special object like the boots or coins
        if (collisionEvent.getOtherBody() instanceof SpecialObject) {
            // Special object is destroyed after collision
            collisionEvent.getOtherBody().destroy();
            // When Hero picks up the boots, GravityScale is reduced, imitating a jump-boost effect
            Spartan.setGravityScale(0.45f);
            // Play pickup sound
            pickupSound.play();
        }
        // If checkpoint is hit, flag gets updated and sound is played
        if (collisionEvent.getOtherBody() instanceof Checkpoint) {
            checkPoint.play();
            // Save the position of the old flag to replace it with the other flag which is transparent
            Vec2 oldPos = new Vec2(collisionEvent.getOtherBody().getPosition());
            // Old flag is destroyed
            collisionEvent.getOtherBody().destroy();
            // New flag added, without any hitbox
            new Checkpoint(world, new BodyImage("data/graphics/checkpointcompleted.png", 4)).setPosition(oldPos);
        }
    }
}
