package com.company.bodies;

import city.cs.engine.*;
import com.company.world.MyWorld;
import org.jbox2d.common.Vec2;

/*
    Class to display Hero's lives as an image, which updates as he takes damage.
    Heart is located at the bottom right.
 */

public class Heart extends StaticBody {
    private static final BoxShape heartShape = new BoxShape(2, 2);
    BodyImage heartImage = new BodyImage("data/fullHeart.png", 4);

    public Heart(MyWorld world) {
        super(world, heartShape);
        addImage(heartImage);
        setPosition(new Vec2(25, -16));
    }

    public void setHeartImage(BodyImage heartImage) {
        removeAllImages();
        this.heartImage = heartImage;
        addImage(heartImage);
    }
}
