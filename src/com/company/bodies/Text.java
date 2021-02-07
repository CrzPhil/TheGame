package com.company.bodies;

import city.cs.engine.*;
import com.company.world.MyWorld;
import org.jbox2d.common.Vec2;

public class Text extends StaticBody {

    private static final Shape scrollShape = new BoxShape(8, 3);

    private static final BodyImage scrollTexture = new BodyImage("data/riddleOne.png");

    AttachedImage scrollAttached = new AttachedImage(this, scrollTexture, 8, 0, new Vec2(0, 0));

    public Text(MyWorld world) {
        super(world, scrollShape);
        setPosition(new Vec2(-3, 10.5f));
    }

    public void moveText(float x, float y) {
        this.setPosition(new Vec2(x, y));
    }

}
