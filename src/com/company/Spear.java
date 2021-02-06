package com.company;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Spear extends DynamicBody {

    private static final PolygonShape spearShape = new PolygonShape(-0.13f,-2.33f,
            -0.35f,-2.14f,
            -0.37f,2.32f,
            0.34f,2.33f,
            0.35f,-1.44f,
            0.08f,-2.12f);

    private static final BodyImage spearImage = new BodyImage("data/spear.png", 5f);
    private final AttachedImage arrowAttached = new AttachedImage(this, spearImage, 1f, 0, new Vec2(0,0));


    public Spear(MyWorld world) {
        super(world, spearShape);

    }
}
