package com.company.bodies.dynamics;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/*
    Enemy causes damage to Hero, he can kill them with spear.
    Though if they cause damage to him, which destroys them, he will still get a scoreIncrement(),
    in order to move forward in the level.
 */

public class Enemy extends DynamicBody {
    // Distinguish between the types of Enemies
    private boolean isBat;
    private boolean isGuard;

    // Constructor with Images, which change according to if the enemy is facing left or right (Used for Bats)
    public Enemy(World w, Shape shape, boolean left) {
        super(w, shape);
        this.isBat = true;
        // Checker for Direction that the enemy is facing
        if (left) {
            BodyImage enemyImage = new BodyImage("data/graphics/bat.gif");
            AttachedImage attached = new AttachedImage(this, enemyImage, 4, 0, new Vec2(0, 0));
        } else {
            BodyImage rightImage = new BodyImage("data/graphics/rbat.gif");
            AttachedImage attachedRight = new AttachedImage(this, rightImage, 4, 0, new Vec2(0, 0));
        }
    }

    // Used for Walking Guards in Level 4
    public Enemy(World w, Shape shape) {
        super(w, shape);
        this.isGuard = true;
        BodyImage guardWalkingLeft = new BodyImage("data/graphics/enemywalk.gif", 5);
        this.addImage(guardWalkingLeft);
        // AttachedImage attached = new AttachedImage(this, guardWalkingLeft, 5, 0)

    }

    // Getters and Setters
    public boolean isBat() {
        return isBat;
    }
    public boolean isGuard() {
        return isGuard;
    }
}
