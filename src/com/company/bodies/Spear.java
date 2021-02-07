package com.company.bodies;

import city.cs.engine.*;
import com.company.world.MyWorld;

public class Spear extends DynamicBody {

    private static final PolygonShape spearShape = new PolygonShape(-0.13f,-2.33f,
            -0.35f,-2.14f,
            -0.37f,2.32f,
            0.34f,2.33f,
            0.35f,-1.44f,
            0.08f,-2.12f);

    private static final BodyImage spearImage = new BodyImage("data/spear.png", 5f);

    public Spear(MyWorld world) {
        super(world, spearShape);
        addImage(spearImage);
    }
}
