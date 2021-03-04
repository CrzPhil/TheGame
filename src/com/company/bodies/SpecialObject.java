package com.company.bodies;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class SpecialObject extends StaticBody {

    /*
        This class is for special objects which the hero can pick up.
        Will include things like extra-hearts, hermes-boots, ammo, etc.
     */

    private final Shape specialShape;
    private BodyImage objImage;

    public SpecialObject(World world, Shape specialShape, BodyImage objImage) {
        super(world, specialShape);
        this.objImage = objImage;
        this.specialShape = specialShape;
        // Attach image to SpecialBody to make use of 'Scale' effect
        new AttachedImage(this, objImage, 3, 0, new Vec2(0, 0));
    }

    // Getters & Setters
    public Shape getSpecialShape() {
        return specialShape;
    }

    public BodyImage getObjImage() {
        return objImage;
    }

    public void setObjImage(BodyImage objImage) {
        this.objImage = objImage;
    }
}
