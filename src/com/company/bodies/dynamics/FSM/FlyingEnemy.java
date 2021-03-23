package com.company.bodies.dynamics.FSM;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import com.company.bodies.dynamics.Enemy;
import com.company.levels.GameLevel;
import org.jbox2d.common.Vec2;

public class FlyingEnemy implements StepListener {
    private enum State {
        PASSIVE, AGGRESSIVE
    }
    private Enemy bat;
    private static final float RANGE = 15;
    private State state;
    private GameLevel level;

    public FlyingEnemy(GameLevel level, Enemy bat) {
        this.bat = bat;
        this.state = State.PASSIVE;
        this.level = level;
        level.addStepListener(this);
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        // Passive State makes Bat simply fly
        if (!inRange()) {
            if (state != State.PASSIVE) {
                state = State.PASSIVE;
            }
        } else {
            if (state != State.AGGRESSIVE) {
                state = State.AGGRESSIVE;
            }
        }
        checkFlight();
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }

    private void checkFlight() {
        switch (state) {
            // Normal Flight
            case PASSIVE:
                if (tooLow()) {
                    bat.setLinearVelocity(new Vec2(-5, 15));
                } else if (tooHigh()) {
                    bat.setLinearVelocity(new Vec2(-5, -15));
                }
                break;
            // Aggressive State makes Bat dive-bomb from the sky onto the player
            case AGGRESSIVE:
                // Specify Locations of Hero and Bat as Vectors
                Vec2 batLoc = new Vec2(bat.getPosition());
                Vec2 heroLoc = new Vec2(level.getHero().getPosition());
                // Get the Vector between those two positions
                Vec2 distVector = new Vec2((heroLoc.x - batLoc.x), (heroLoc.y - batLoc.y));
                bat.setLinearVelocity(distVector);
        }
    }

    // Flying Methods
    private boolean tooLow() {
        return bat.getPosition().y <= -5;
    }

    private boolean tooHigh() {
        return bat.getPosition().y >= 20;
    }

    // Methods that will check whether Hero is in Range of bat
    private boolean inRange() {
        // Specify Locations of Hero and Bat as Vectors
        Vec2 batLoc = new Vec2(bat.getPosition());
        Vec2 heroLoc = new Vec2(level.getHero().getPosition());
        // Get the Vector between those two positions
        Vec2 distVector = new Vec2(batLoc.x - heroLoc.x, batLoc.y - heroLoc.y);
        // Get the absolute length of that Vector and then compare it to our RANGE variable
        float distance = distVector.length();

        return distance <= RANGE;
    }

}
