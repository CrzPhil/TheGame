package com.company.collisions;

import city.cs.engine.*;
import com.company.bodies.dynamics.Arrow;
import com.company.bodies.dynamics.Enemy;
import com.company.bodies.dynamics.Villain;
import com.company.bodies.statics.Barrier;
import com.company.bodies.statics.Choice;
import com.company.bodies.statics.Text;
import com.company.levels.GameLevel;
import com.company.levels.Level1;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/*
    This class is responsible for the collisions of the spears.
    It is also the bulk of the Sequence-simulation, as we haven't learnt about timers yet.
    It checks which choice the player goes for in the first riddle, by checking for collisions which each
    of the three objects.
    If the Hero chooses correctly, he gets a free hit on the Villain.
    Should he choose incorrectly, he loses a life and has to try again until he finds the answer.
    After the Hero fires at the Villain, the barrier is restored while it hails arrows.
    Should the Hero survive the arrow-rain, he can then shoot at the villain again.
    After three hits the villain dies and the Spartan wins and gets to drink Ale and party.
 */

public class SpearHit implements CollisionListener {

    private final GameLevel world;
    public SpearHit(GameLevel world) {
        this.world = world;
    }
    private static SoundClip badSequence;
    private static SoundClip batSqueak;

    /*
        Spears get destroyed when they hit the floor/walls.
        They also destroy incoming arrows (but also get destroyed themselves).
        When the spear hits the Sphinx (Villain), Sphinx takes damage and spear is destroyed.
     */

    // Loading the sounds that play on-collision
    static {
        try {
            badSequence = new SoundClip("data/music/dundundun.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException exc) {
            System.out.println(exc);
        }
    }
    static {
        try {
            batSqueak = new SoundClip("data/music/bat.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {
        // All collisions specific to first level
        if (this.world instanceof Level1) {
            // What happens if any of the choices are Picked for the riddle
            if (collisionEvent.getOtherBody() instanceof Choice) {
                // If choice is correct, all choices are removed, and next sequence begins (read above (l.15) for more info)
                if (((Choice) collisionEvent.getOtherBody()).isRightChoice()) {
                    world.destroyChoices();
                    // Moves Text out of the view
                    world.getScroll().moveText(-3, -70);
                    // Removes Barrier in front of Villain
                    world.getBarrier().allocateBarrier();
                    world.getSphinx().setSphinxImage(new BodyImage("data/graphics/sphinx_attack.png", 10f));
                }
                // Wrong Choice -> Damage Taken, choice disappears
                else {
                    collisionEvent.getOtherBody().destroy();
                    world.getHero().takeDamage();
                    System.out.println("You chose wrong, and took damage!");

                    // Changing Heart Image according to health
                    if (world.getHero().getHealth() == 2) {
                        world.getHeart().setHeartImage(new BodyImage("data/graphics/halfHeart.png", 4));
                    } else if (world.getHero().getHealth() == 1) {
                        world.getHeart().setHeartImage(new BodyImage("data/graphics/lastHeart.png", 4));
                    }
                }
            }
            // If villain is hit by spear, Barrier comes back, and another round of Arrow-Rain is initiated.
            else if (collisionEvent.getOtherBody() instanceof Villain) {
                ((Villain) collisionEvent.getOtherBody()).takeDamage();
                collisionEvent.getReportingBody().destroy();
                if (world.getSphinx().getHealth() > 0) {
                    System.out.println("The Sphinx has only " + world.getSphinx().getHealth() + " lives left!");
                    // Barrier is put in front of Sphinx during arrow-sequence
                    world.getBarrier().relocateBarrier();
                    arrowRain();
                    // Barrier is removed after arrow-sequence
                    world.getBarrier().allocateBarrier();
                } else {
                    // Sphinx gets Sent off and destroyed. Winning message appears.
                    world.getSphinx().setLinearVelocity(new Vec2(-16, 1));
                    world.getSphinx().destroy();
                    world.nullSphinx();
                    new Text(world, new BodyImage("data/graphics/won.png"));

                    // Directional Arrow pointing towards new level
                    Text arrow = new Text(world, new BodyImage("data/graphics/direction.png"));
                    arrow.setPosition(new Vec2(10, 10));
                    // Barrier detecting collision and starting new level
                    Barrier levelUp = new Barrier(world);
                    levelUp.setPosition(new Vec2(30, -10));
                }
            }
        }

        // What happens if Spear is fired and hits any given object
        if (collisionEvent.getOtherBody() instanceof StaticBody) {
            collisionEvent.getReportingBody().destroy();
        } else if (collisionEvent.getOtherBody() instanceof Arrow) {
            collisionEvent.getOtherBody().destroy();
            collisionEvent.getReportingBody().destroy();
        }
        // What happens when Spear hits an enemy, it is destroyed and a sound plays
        if (collisionEvent.getOtherBody() instanceof Enemy) {
            collisionEvent.getOtherBody().destroy();
            batSqueak.play();
        }

    }

    // Method to induce arrow-rain through random x and y coordinates.
    public void arrowRain() {
        // Play ominous sound to indicate start of sequence
        badSequence.play();
        java.security.SecureRandom randomizer = new java.security.SecureRandom();
        // Low and High bound of the x-Axis
        int minX = -3;
        int maxX = 25;
        // Low and High bound of the y-Axis
        int minY = 12;
        int maxY = 130;

        boolean loop = true;
        int temp = 0;

        while (loop) {
            // Random number generation
            int randX = randomizer.nextInt(maxX) + minX;
            int randY = randomizer.nextInt(maxY) + minY;
            /*
             In an attempt to not spawn everything at once, only every 1000th execution spawns an arrow.
             This was not very effective, as it appears like they spawn at once anyways.
             */
            if (temp % 1000 == 0) {
                new Arrow(world).setPosition(new Vec2(randX, randY));

                // Spawn 40 arrows total, then reset Count for next sequence
                if (Arrow.getArrowCount() >= 40) {
                    loop = false;
                    Arrow.resetArrows();
                }
            }
            temp++;
        }
    }

}
