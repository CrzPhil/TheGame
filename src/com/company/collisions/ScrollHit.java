package com.company.collisions;

import city.cs.engine.BodyImage;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import com.company.world.MyWorld;
import com.company.bodies.Arrow;
import com.company.bodies.Spear;
import org.jbox2d.common.Vec2;

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

            /*// Change Sphinx to attack-image
            BodyImage attackSphinx = new BodyImage("data/sphinx_attack.png", 10f);
            world.getSphinx().setSphinxImage(attackSphinx);*/

            java.security.SecureRandom randomizer = new java.security.SecureRandom();

            int minX = -3;
            int maxX = 25;
            int minY = 12;
            int maxY = 130;

            boolean loop = true;
            int temp = 0;

            while (loop) {
                int randX = randomizer.nextInt(maxX) + minX;
                int randY = randomizer.nextInt(maxY) + minY;
                if (temp % 1000 == 0) {
                    new Arrow(world).setPosition(new Vec2(randX, randY));

                    if (Arrow.getArrowCount() >= 50) {
                        loop = false;
                        Arrow.resetArrows();
                        /*BodyImage normalSphinx = new BodyImage("data/sphinx.png", 10f);
                        world.getSphinx().setSphinxImage(normalSphinx);*/
                    }
                }
                temp++;
            }
        }
    }
}
