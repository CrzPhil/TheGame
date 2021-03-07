package com.company.bodies.statics;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Barrier extends StaticBody {

    /*
        This class is to guard the Villain in-between sequences from taking damage.
        It can be further used as a general blocker to prevent Hero or other objects to
        go out-of-bounds.
     */

    private static final BoxShape barrierShape = new BoxShape(0.5f, 7);
    private static final BodyImage blankImage = new BodyImage("data/graphics/blank.png");

    public Barrier(World world) {
        super(world, barrierShape);
        addImage(blankImage);
        setPosition(new Vec2(-15.5f, 5f));
    }

    // Methods to move barrier elsewhere off-screen without having to destroy and redeploy it again.
    // Allocate moves it away, relocate moves it back. Mainly for first level
    public void allocateBarrier() {
        setPosition(new Vec2(-15.5f, -20f));
    }
    public void relocateBarrier() {
        setPosition(new Vec2(-15.5f, 5f));
    }
}
