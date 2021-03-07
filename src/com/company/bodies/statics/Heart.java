package com.company.bodies.statics;

import city.cs.engine.*;
import com.company.levels.GameLevel;
import com.company.levels.Level1;
import com.company.levels.Level2;
import org.jbox2d.common.Vec2;

/*
    Class to display Hero's lives as an image, which updates as he takes damage.
    Heart is located at the bottom right.
 */

public class Heart extends StaticBody {
    BodyImage heartImage = new BodyImage("data/graphics/fullHeart.png", 4);
    private GameLevel world;

    public Heart(GameLevel world) {
        super(world);
        this.world = world;
        addImage(heartImage);
        setPosition(new Vec2(25, -16));
    }

    // Setter for Image-overlay
    public void setHeartImage(BodyImage heartImage) {
        removeAllImages();
        this.heartImage = heartImage;
        addImage(heartImage);
    }

    // Method to update Health overlay according to Hero's health
    public void updateLife() {
        if (world.getHero().getHealth() == 2) {
            world.getHeart().setHeartImage(new BodyImage("data/graphics/halfHeart.png", 4));
        } else if (world.getHero().getHealth() == 1) {
            world.getHeart().setHeartImage(new BodyImage("data/graphics/lastHeart.png", 4));
        }
        // If Spartan dies, loss message is displayed, and spartan immobilised.
        else if (world.getHero().getHealth() <= 0) {
            world.getHeart().setHeartImage(new BodyImage("data/graphics/emptyHeart.png", 4));

            // New object of a dead spartan (static) is created.
            BoxShape deadSpartanShape = new BoxShape(2,0.5f);
            StaticBody deadSpartan = new StaticBody(world, deadSpartanShape);
            deadSpartan.addImage(new BodyImage("data/graphics/spartan_dead.png", 5));

            // Puts the new Body where the old Walker was, then transposes walker off-screen.
            deadSpartan.setPosition(new Vec2(world.getHero().getPosition()));
            world.getHero().setPosition(new Vec2(0, -50));

            // Display Loss-message (position updated according to level)
            if (world instanceof Level1) {
                new Text(world, new BodyImage("data/graphics/loss.png"));
            } else if (world instanceof Level2) {
                new Text(world, new BodyImage("data/graphics/loss.png")).setPosition(new Vec2(1,-15));
            }
        }
    }
}
