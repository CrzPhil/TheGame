package com.company.bodies.statics;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Platform extends StaticBody {
    // Adding Platform image with custom scale
    private final BodyImage platformImage = new BodyImage("data/graphics/plat.png");
    private final AttachedImage platAttached = new AttachedImage(this, platformImage, 3, 0, new Vec2(0, 0));
    private static Shape platformShape = new BoxShape(4.5f, 1.5f);

    public Platform(World w, Shape s) {
        super(w, s);
    }
    public Platform(World w) {
        super(w, platformShape);
    }

    // Getters and Setters for Shape
    public Shape getPlatformShape() {
        return platformShape;
    }

    public void setPlatformShape(Shape platformShape) {
        this.platformShape = platformShape;
    }
}
