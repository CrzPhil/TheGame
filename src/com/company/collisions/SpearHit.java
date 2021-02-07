package com.company.collisions;

import city.cs.engine.BodyImage;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.StaticBody;
import com.company.bodies.Arrow;
import com.company.bodies.Choice;
import com.company.bodies.Villain;
import com.company.world.MyWorld;
import org.jbox2d.common.Vec2;

public class SpearHit implements CollisionListener {

    private final MyWorld world;
    public SpearHit(MyWorld world) {
        this.world = world;
    }

    /*
        Spears get destroyed when they hit the floor/walls.
        They also destroy incoming arrows (but also get destroyed themselves).
        When the spear hits the Sphinx (Villain), Sphinx takes damage and spear is destroyed.
     */

    @Override
    public void collide(CollisionEvent collisionEvent) {
        // What happens if any of the choices are Picked for the riddle
        if (collisionEvent.getOtherBody() instanceof Choice) {
            // If choice is correct, all choices are removed, and next sequence begins
            if (((Choice) collisionEvent.getOtherBody()).isRightChoice()) {
                world.destroyChoices();
                // Moves Text out of the view
                world.getScroll().moveText(-3, -70);
                // Removes Barrier in front of Villain
                world.getBarrier().allocateBarrier();
                world.getSphinx().setSphinxImage(new BodyImage("data/sphinx_attack.png", 10f));
            }
            // Wrong Choice -> Damage Taken, choice disappears
            else {
                collisionEvent.getOtherBody().destroy();
                world.getHero().takeDamage();
                System.out.println("You chose wrong, and thus took damage!");

                // Changing Heart Image according to health
                if (world.getHero().getHealth() == 2) {
                    world.getHeart().setHeartImage(new BodyImage("data/halfHeart.png", 4));
                } else if (world.getHero().getHealth() == 1) {
                    world.getHeart().setHeartImage(new BodyImage("data/lastHeart.png", 4));
                }
            }
        }

        // What happens if Spear is fired and hits any given object
        if (collisionEvent.getOtherBody() instanceof StaticBody) {
            collisionEvent.getReportingBody().destroy();
        } else if (collisionEvent.getOtherBody() instanceof Arrow) {
            collisionEvent.getOtherBody().destroy();
            collisionEvent.getReportingBody().destroy();
        } else if (collisionEvent.getOtherBody() instanceof Villain) {
            ((Villain) collisionEvent.getOtherBody()).takeDamage();
            collisionEvent.getReportingBody().destroy();
        }
    }
}
