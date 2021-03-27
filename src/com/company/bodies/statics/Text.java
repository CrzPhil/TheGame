package com.company.bodies.statics;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/*
    This class is to be used to display text as objects across the game.
    moveText() locates the text to wherever it is needed.
 */

public class Text extends StaticBody {

    private static final Shape scrollShape = new BoxShape(8, 3);

    AttachedImage scrollAttached;

    // Constructor, adding AttachedImage with custom text
    public Text(World world, BodyImage image) {
        super(world, scrollShape);
        setPosition(new Vec2(-3, 10.5f));
        scrollAttached = new AttachedImage(this, image, 8, 0, new Vec2(0, 0));
    }

    // Constructor, adding AttachedImage with custom text
    public Text(World world, BodyImage image, boolean transparent) {
        super(world);
        setPosition(new Vec2(-3, 10.5f));
        scrollAttached = new AttachedImage(this, image, 6, 0, new Vec2(0, 0));
    }

    // Used to move the text where needed.
    public void moveText(float x, float y) {
        this.setPosition(new Vec2(x, y));
    }
}
