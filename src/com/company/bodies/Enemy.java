package com.company.bodies;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Enemy extends DynamicBody {
    private Shape enemyShape;
    private BodyImage enemyImage = new BodyImage("data/graphics/flyingenemy2.gif");

    public Enemy(World w, Shape shape) {
        super(w, shape);
        this.enemyShape = shape;
        AttachedImage attached = new AttachedImage(this, enemyImage, 4, 0, new Vec2(0, 0));

    }
}
