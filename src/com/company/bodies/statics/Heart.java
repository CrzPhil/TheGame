package com.company.bodies.statics;

import city.cs.engine.*;
import com.company.levels.GameLevel;
import org.jbox2d.common.Vec2;

/*
    Class to display Hero's lives as an image, which updates as he takes damage.
    Heart is located at the bottom right.
 */

public class Heart extends StaticBody {
    BodyImage heartImage = new BodyImage("data/graphics/fullHeart.png", 4);

    public Heart(GameLevel world) {
        super(world);
        addImage(heartImage);
        setPosition(new Vec2(25, -16));
    }

    public void setHeartImage(BodyImage heartImage) {
        removeAllImages();
        this.heartImage = heartImage;
        addImage(heartImage);
    }
}
