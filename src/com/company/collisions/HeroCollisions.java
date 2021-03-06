package com.company.collisions;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.SoundClip;
import city.cs.engine.StaticBody;
import com.company.bodies.dynamics.Hero;
import com.company.bodies.statics.Platform;
import com.company.bodies.statics.SpecialObject;
import com.company.levels.GameLevel;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class HeroCollisions implements CollisionListener {
    final private Hero Spartan;
    final private GameLevel world;
    private static SoundClip pickupSound;
    private static SoundClip grassWalk;

    // Load sound for pickup
    static {
        try {
            pickupSound = new SoundClip("data/music/pickup.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException exc) {
            System.out.println(exc);
        }
    }

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
    }
}
