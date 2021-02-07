package com.company.bodies;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import com.company.world.MyWorld;
import org.jbox2d.common.Vec2;

public class Barrier extends StaticBody {

    private static final BoxShape barrierShape = new BoxShape(1, 7);
    private static final BodyImage blankImage = new BodyImage("data/blank.png");

    public Barrier(MyWorld world) {
        super(world, barrierShape);
        addImage(blankImage);
        setPosition(new Vec2(-15.5f, 5f));
    }

    public void allocateBarrier() {
        setPosition(new Vec2(-15.5f, -20f));
    }
    public void relocateBarrier() {
        setPosition(new Vec2(-15.5f, 5f));
    }

}
