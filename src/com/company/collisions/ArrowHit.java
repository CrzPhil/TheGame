package com.company.collisions;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.StaticBody;
import com.company.bodies.Arrow;
import com.company.bodies.Hero;

public class ArrowHit implements CollisionListener {
    final private Hero Spartan;
    public ArrowHit(Hero Spartan) {
        this.Spartan = Spartan;
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {
        // If Arrows hit either Spartan or the Floor they disappear
        if (collisionEvent.getOtherBody() instanceof Arrow && collisionEvent.getReportingBody() instanceof Hero) {
            collisionEvent.getOtherBody().destroy();
            System.out.println("You've been hit by an arrow!");
            Spartan.takeDamage();
        } else if (collisionEvent.getReportingBody() instanceof StaticBody && !(collisionEvent.getOtherBody() instanceof Hero)) {
            collisionEvent.getOtherBody().destroy();
        }
    }
}
