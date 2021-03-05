package com.company.collisions;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import com.company.bodies.dynamics.Hero;
import com.company.bodies.statics.SpecialObject;
import com.company.levels.GameLevel;

public class HeroCollisions implements CollisionListener {
    final private Hero Spartan;
    final private GameLevel world;

    public HeroCollisions(GameLevel world, Hero Spartan) {
        this.Spartan = Spartan;
        this.world = world;
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {
        if (collisionEvent.getOtherBody() instanceof SpecialObject) {
            // Special object is destroyed after collision
            collisionEvent.getOtherBody().destroy();
            // When Hero picks up the boots, GravityScale is reduced, imitating a jump-boost effect
            Spartan.setGravityScale(0.45f);
        }
    }
}
