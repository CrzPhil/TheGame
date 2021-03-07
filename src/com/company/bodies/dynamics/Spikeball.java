package com.company.bodies.dynamics;

import city.cs.engine.BodyImage;
import city.cs.engine.CircleShape;
import city.cs.engine.DynamicBody;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

// Spikeball class for level 3 which will damage Hero on-collision
public class Spikeball extends DynamicBody {
    private static final CircleShape spikeBall = new CircleShape(1.23f);

    public Spikeball(World w, int position) {
        super(w, spikeBall);
        // Position will be an int between 0 and 5 and tell us where/with which velocity to spawn the ball accordingly
        BodyImage spikeImage = new BodyImage("data/graphics/spikeball.gif", 6);
        addImage(spikeImage);

        if (position == 0) { // 0 is left
            this.setPosition(new Vec2(-35, -4.3f));
            this.setLinearVelocity(new Vec2(15f, 0));
        } else if (position == 1) {  // 1 is right
            this.setPosition(new Vec2(35, -4.3f));
            this.setLinearVelocity(new Vec2(-15f, 0));
        } else if (position == 2) { // middle
            this.setPosition(new Vec2(0, 20));
            this.setLinearVelocity(new Vec2(0, -4));
        } else if (position == 3) { //top-left
            this.setPosition(new Vec2(-16, 20));
            this.setLinearVelocity(new Vec2(0, 0));
        } else { //top-right
            this.setPosition(new Vec2(16, 20));
            this.setLinearVelocity(new Vec2(0, 0));
        }
    }
    // We need a method to check whether the balls have moved off-screen to destroy(); them and avoid cluttering
    public boolean offScreen() {
        return this.getPosition().y < -20;
    }
}
