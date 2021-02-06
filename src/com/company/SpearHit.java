package com.company;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.StaticBody;

public class SpearHit implements CollisionListener {

    @Override
    public void collide(CollisionEvent collisionEvent) {
        if (collisionEvent.getOtherBody() instanceof StaticBody) {
            collisionEvent.getReportingBody().destroy();
        } else if (collisionEvent.getOtherBody() instanceof Arrow) {
            collisionEvent.getOtherBody().destroy();
            collisionEvent.getReportingBody().destroy();
        }
    }
}
