package com.company.bodies.statics;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Ground extends StaticBody {
    // Adding Ground image with custom scale to fit image to body
    private final BodyImage groundImage = new BodyImage("data/graphics/box-tile.png");
    private final AttachedImage groundAttached = new AttachedImage(this, groundImage, 10, 0, new Vec2(0, 0));
    private static Shape groundShape = new BoxShape(5, 5);

    // Two constructors in case Shape needs to be adjusted
    public Ground(World w, Shape s) {
        super(w, s);
    }
    public Ground(World w) {
        super(w, groundShape);
    }

    // Getters and Setters for Shape
    public Shape getGroundShape() {
        return groundShape;
    }

    public void setGroundShape(Shape groundShape) {
        Ground.groundShape = groundShape;
    }
}
