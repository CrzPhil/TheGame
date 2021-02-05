package com.company;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.StaticBody;

public class ArrowHit implements CollisionListener {
    private Hero spartan;
    public ArrowHit(Hero spartan) {
        this.spartan = spartan;
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {
        // If Arrows hit either Spartan or the Floor they disappear
        if (collisionEvent.getOtherBody() instanceof Hero) {
            collisionEvent.getReportingBody().destroy();
            System.out.println("You've been hit by an arrow!");
        } else if (collisionEvent.getOtherBody() instanceof StaticBody) {
            collisionEvent.getReportingBody().destroy();
        }
    }
}
