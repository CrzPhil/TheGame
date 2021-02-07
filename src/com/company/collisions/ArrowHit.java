package com.company.collisions;

import city.cs.engine.BodyImage;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.StaticBody;
import com.company.bodies.Arrow;
import com.company.bodies.Heart;
import com.company.bodies.Hero;
import com.company.world.MyWorld;

public class ArrowHit implements CollisionListener {
    final private Hero Spartan;
    final private MyWorld world;

    public ArrowHit(MyWorld world, Hero Spartan) {
        this.Spartan = Spartan;
        this.world = world;
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {
        // If Arrows hit either Spartan or the Floor they disappear
        if (collisionEvent.getOtherBody() instanceof Arrow && collisionEvent.getReportingBody() instanceof Hero) {
            collisionEvent.getOtherBody().destroy();
            System.out.println("You've been hit by an arrow!");
            Spartan.takeDamage();
            if (Spartan.getHealth() == 2) {
                world.getHeart().setHeartImage(new BodyImage("data/halfHeart.png", 4));
            } else if (Spartan.getHealth() == 1) {
                world.getHeart().setHeartImage(new BodyImage("data/lastHeart.png", 4));
            }

        } else if (collisionEvent.getReportingBody() instanceof StaticBody && !(collisionEvent.getOtherBody() instanceof Hero)) {
            collisionEvent.getOtherBody().destroy();
        }
    }
}
