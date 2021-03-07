package com.company.bodies.statics;

import city.cs.engine.*;
import com.company.bodies.dynamics.Hero;
import org.jbox2d.common.Vec2;

public class SpecialObject extends StaticBody {

    /*
        This class is for special objects which the hero can pick up.
        Will include things like extra-hearts, hermes-boots, ammo, etc.
     */

    private final Shape specialShape;
    private BodyImage objImage;
    private final Hero Spartan;

    public SpecialObject(World world, Shape specialShape, BodyImage objImage, Hero Spartan) {
        super(world, specialShape);
        this.Spartan = Spartan;
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

    // Method specific to hermes Boots found in level 2, which reduce gravity for the might spartan
    public void hermesBoots() {
        Spartan.setGravityScale(0.45f);
    }
}
