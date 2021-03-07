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
            updateLife();
            collisionEvent.getReportingBody().destroy();
        }
        // If Arrows hit either Spartan or the Floor they disappear
        if (collisionEvent.getOtherBody() instanceof Arrow && collisionEvent.getReportingBody() instanceof Hero) {
            collisionEvent.getOtherBody().destroy();
            System.out.println("You've been hit by an arrow!");
            Spartan.takeDamage();
            updateLife();
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

    // Method to update Health overlay according to Hero's health
    public void updateLife() {
        if (Spartan.getHealth() == 2) {
            world.getHeart().setHeartImage(new BodyImage("data/graphics/halfHeart.png", 4));
        } else if (Spartan.getHealth() == 1) {
            world.getHeart().setHeartImage(new BodyImage("data/graphics/lastHeart.png", 4));
        }
        // If Spartan dies, loss message is displayed, and spartan immobilised.
        else if (Spartan.getHealth() <= 0) {
            world.getHeart().setHeartImage(new BodyImage("data/graphics/emptyHeart.png", 4));

            // New object of a dead spartan (static) is created.
            BoxShape deadSpartanShape = new BoxShape(2,0.5f);
            StaticBody deadSpartan = new StaticBody(world, deadSpartanShape);
            deadSpartan.addImage(new BodyImage("data/graphics/spartan_dead.png", 5));

            // Puts the new Body where the old Walker was, then transposes walker off-screen.
            deadSpartan.setPosition(new Vec2(world.getHero().getPosition()));
            world.getHero().setPosition(new Vec2(0, -50));

            // Display Loss-message (position updated according to level)
            if (world instanceof Level1) {
                new Text(world, new BodyImage("data/graphics/loss.png"));
            } else if (world instanceof Level2) {
                new Text(world, new BodyImage("data/graphics/loss.png")).setPosition(new Vec2(1,-15));
            }
        }
    }
}
