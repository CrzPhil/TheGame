package com.company.bodies.dynamics.finiteStateMachines;

import city.cs.engine.BodyImage;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import com.company.bodies.dynamics.Enemy;
import com.company.levels.GameLevel;
import org.jbox2d.common.Vec2;

public class WalkingEnemy implements StepListener {
    private enum State {
        WALK_LEFT, WALK_RIGHT
    }
    private final Enemy guard;
    private State state;

    public WalkingEnemy(GameLevel level, Enemy guard) {
        this.guard = guard;
        // Guard Spawns walking away from Hero.
        state = State.WALK_RIGHT;
        level.addStepListener(this);
    }

    // Returns true if Enemy walks to/past the bound on the right of the level
    private boolean rightBound() {
        return guard.getPosition().x >= 25f;
    }

    // Returns true if Enemy walks to/past the bound on the left of the level
    private boolean leftBound() {
        return guard.getPosition().x <= -10f;
    }


    @Override
    public void preStep(StepEvent stepEvent) {
        if (rightBound()) {
            if (state != State.WALK_LEFT) {
                state = State.WALK_LEFT;
            }
        } else if (leftBound()) {
            if (state != State.WALK_RIGHT) {
                state = State.WALK_RIGHT;
            }
        }

        checkWalk();
    }

    private void checkWalk() {
        switch (state) {
            case WALK_LEFT:
                guard.setLinearVelocity(new Vec2(-6f, guard.getLinearVelocity().y));
                guard.removeAllImages();
                guard.setGuardImage(new BodyImage("data/graphics/enemywalkLeft.gif", 5));
                guard.addImage(guard.getGuardImage());
                break;
            case WALK_RIGHT:
                guard.setLinearVelocity(new Vec2(6f, guard.getLinearVelocity().y));
                guard.removeAllImages();
                guard.setGuardImage(new BodyImage("data/graphics/enemywalk.gif", 5));
                guard.addImage(guard.getGuardImage());
                break;
            default: // nothing to do
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
