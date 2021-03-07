package com.company.collisions;

import city.cs.engine.*;
import com.company.bodies.dynamics.Arrow;
import com.company.bodies.dynamics.Enemy;
import com.company.bodies.dynamics.Hero;
import com.company.bodies.statics.Barrier;
import com.company.bodies.statics.Text;
import com.company.levels.GameLevel;
import com.company.levels.Level1;
import com.company.levels.Level2;
import org.jbox2d.common.Vec2;

/*
    This class is our Arrow-Listener. It is responsible for removing arrows on-collision with different objects.
    The Hero also takes damage if hit by an object of this class.
    According to the current health-level of the Hero, the Heart is also updated.
    Should he be hit three or more times, he will die and the game ends.
 */

public class ArrowHit implements CollisionListener {
    final private Hero Spartan;
    final private GameLevel world;


    public ArrowHit(GameLevel world, Hero Spartan) {
        this.Spartan = Spartan;
        this.world = world;
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {
        // If the (flying) enemy hits the ground, it bounces back up with reduced gravity to imitate flying
        if (collisionEvent.getReportingBody() instanceof Enemy && collisionEvent.getOtherBody() instanceof StaticBody) {
            ((Enemy) collisionEvent.getReportingBody()).setGravityScale(0.5f);
            ((Enemy) collisionEvent.getReportingBody()).setLinearVelocity(new Vec2(0, 14));
        }
        // If Enemy hits Spartan, he takes damage
        if (collisionEvent.getReportingBody() instanceof Enemy && collisionEvent.getOtherBody() instanceof Hero) {
            Spartan.takeDamage();
            Spartan.incrementScore();
            world.getHeart().updateLife();
            collisionEvent.getReportingBody().destroy();
        }
        // If Arrows hit either Spartan or the Floor they disappear
        if (collisionEvent.getOtherBody() instanceof Arrow && collisionEvent.getReportingBody() instanceof Hero) {
            collisionEvent.getOtherBody().destroy();
            System.out.println("You've been hit by an arrow!");
            Spartan.takeDamage();
            world.getHeart().updateLife();
        }
        // If some other StaticBody is hit by something other than the hero, that object is deleted
        else if (collisionEvent.getReportingBody() instanceof StaticBody && !(collisionEvent.getOtherBody() instanceof Hero)) {
            collisionEvent.getOtherBody().destroy();
        }
        // If text is hit by arrows, the arrows get destroyed
        if (collisionEvent.getOtherBody() instanceof Arrow && collisionEvent.getReportingBody() instanceof Text) {
            collisionEvent.getOtherBody().destroy();
        }
        // If Hero hits the barrier at the end of level, next level is initiated
        if (collisionEvent.getReportingBody() instanceof Hero && collisionEvent.getOtherBody() instanceof Barrier && world.isComplete()) {
            world.getGame().goToNextLevel();
        }
    }
}
