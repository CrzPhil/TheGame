package com.company;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Arrow extends DynamicBody {

    private final Shape arrowShape;
    private final DynamicBody arrow;

    public Arrow(World world) {
        super(world);

        arrowShape = new BoxShape(1, 5);
        arrow = new DynamicBody(world, arrowShape);
        BodyImage arrowImage = new BodyImage("data/enemy_arrow.png", 5);
        AttachedImage arrowAttached = new AttachedImage(arrow, arrowImage, 2.5f, 0, new Vec2(0,0));

    }
}
