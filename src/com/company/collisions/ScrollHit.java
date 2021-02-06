package com.company.collisions;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import com.company.MyWorld;
import com.company.bodies.Arrow;
import com.company.bodies.Spear;
import org.jbox2d.common.Vec2;

import java.util.Random;

public class ScrollHit implements CollisionListener {

    private static MyWorld world;

    public ScrollHit(MyWorld world) {
        this.world = world;
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {
        if (collisionEvent.getOtherBody() instanceof Spear) {
            /*
            Instead of Destroying and then creating a new object for the second riddle,
            we move it out of the way, then replace the images, and move it back after the sequence.
            */
            collisionEvent.getReportingBody().setPosition(new Vec2(collisionEvent.getReportingBody().getPosition().x - 40,
                    collisionEvent.getReportingBody().getPosition().y));

            // Arrow-Hail Sequence Begins
            /*int temp = 0;
            Random rand = new Random();
            int randomInt = rand.nextInt(20);
            int otherInt = rand.nextInt(10);
            while (temp<100) {
                if (temp % 2 == 0) {
                    new Arrow(world).setPosition(new Vec2(randomInt, 10));
                }
                temp++;*/
            java.security.SecureRandom randomizer = new java.security.SecureRandom();
            int minX = 5;
            int maxX = 20;
            int minY = 12;
            int maxY = 20;

            boolean loop = true;
            int temp = 0;

            while (loop) {
                int randX = randomizer.nextInt(maxX) + minX;
                int randY = randomizer.nextInt(maxY) + minY;
                if (temp % 1000 == 0) {
                    new Arrow(world).setPosition(new Vec2(randX, randY));

                    if (Arrow.getArrowCount() >= 10) {
                        loop = false;
                        Arrow.resetArrows();
                    }
                }
                temp++;
            }
        }
    }
}
