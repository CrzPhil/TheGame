package com.company.bodies.dynamics;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/*
    Enemy causes damage to Hero, he can kill them with spear.
    Though if they cause damage to him, which destroys them, he will still get a scoreIncrement(),
    in order to move forward in the level.
 */

public class Enemy extends DynamicBody {

    // Constructor with Images, which change according to if the enemy is facing left or right
    public Enemy(World w, Shape shape, boolean left) {
        super(w, shape);
        // Checker for Direction that the enemy is facing
        if (left) {
            BodyImage enemyImage = new BodyImage("data/graphics/bat.gif");
            AttachedImage attached = new AttachedImage(this, enemyImage, 4, 0, new Vec2(0, 0));
        } else {
            BodyImage rightImage = new BodyImage("data/graphics/rbat.gif");
            AttachedImage attachedRight = new AttachedImage(this, rightImage, 4, 0, new Vec2(0, 0));
        }
    }
}
