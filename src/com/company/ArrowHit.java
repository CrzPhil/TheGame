package com.company;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.StaticBody;

public class ArrowHit implements CollisionListener {
    final private Hero Spartan;
    public ArrowHit(Hero Spartan) {
        this.Spartan = Spartan;
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {
        // If Arrows hit either Spartan or the Floor they disappear
        if (collisionEvent.getOtherBody() instanceof Hero) {
            collisionEvent.getReportingBody().destroy();
            System.out.println("You've been hit by an arrow!");
            Spartan.takeDamage();
        } else if (collisionEvent.getOtherBody() instanceof StaticBody) {
            collisionEvent.getReportingBody().destroy();
        }
    }
}
