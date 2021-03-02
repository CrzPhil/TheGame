package com.company.bodies;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

public class Barrier extends StaticBody {

    /*
        This class is to guard the Villain in-between sequences from taking damage.
        It can be further used as a general blocker to prevent Hero or other objects to
        go out-of-bounds.
     */

    private static final BoxShape barrierShape = new BoxShape(1, 7);
    private static final BodyImage blankImage = new BodyImage("data/blank.png");

    public Barrier(World world) {
        super(world, barrierShape);
        addImage(blankImage);
        setPosition(new Vec2(-15.5f, 5f));
    }

    // Methods to move barrier elsewhere off-screen without having to destroy and redeploy it again.

    // Allocate moves it away, relocate moves it back.
    public void allocateBarrier() {
        setPosition(new Vec2(-15.5f, -20f));
    }
    public void relocateBarrier() {
        setPosition(new Vec2(-15.5f, 5f));
    }

}
