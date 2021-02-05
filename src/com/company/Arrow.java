package com.company;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Arrow extends DynamicBody {

    private static final Shape arrowShape = new PolygonShape(-0.18f, -2.04f,
            -0.44f, -1.17f,
            -0.34f, 2.02f,
            -0.02f, 2.03f,
            0.08f, -1.18f);

    private static final BodyImage arrowImage = new BodyImage("data/enemy_arrow.png", 5f);
    private final AttachedImage arrowAttached = new AttachedImage(this, arrowImage, 1f, 0, new Vec2(0,0));

    public Arrow(MyWorld world) {
        super(world, arrowShape);

        /*DynamicBody arrow = new DynamicBody(world, arrowShape);
        AttachedImage arrowAttached = new AttachedImage(arrow, arrowImage, 1f, 0, new Vec2(0,0));*/

        // this.addCollisionListener(new ArrowHit(world.getHero()));
    }
}
