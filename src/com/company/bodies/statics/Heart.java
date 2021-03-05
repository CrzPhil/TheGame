package com.company.bodies.statics;

import city.cs.engine.*;
import com.company.levels.GameLevel;
import org.jbox2d.common.Vec2;

/*
    Class to display Hero's lives as an image, which updates as he takes damage.
    Heart is located at the bottom right.
 */

public class Heart extends StaticBody {
    // Make hitbox of heart tiny so that it doesnt impact the game
    private static final BoxShape heartShape = new BoxShape(0.1f, 0.1f);
    BodyImage heartImage = new BodyImage("data/graphics/fullHeart.png", 4);

    public Heart(GameLevel world) {
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
