package com.company.bodies.dynamics;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Enemy extends DynamicBody {
    private Shape enemyShape;
    private BodyImage enemyImage = new BodyImage("data/graphics/bat.gif");
    private BodyImage rightImage = new BodyImage("data/graphics/rbat.gif");

    public Enemy(World w, Shape shape, boolean left) {
        super(w, shape);
        this.enemyShape = shape;
        if (left) {
            AttachedImage attached = new AttachedImage(this, enemyImage, 4, 0, new Vec2(0, 0));
        } else {
            AttachedImage attachedRight = new AttachedImage(this, rightImage, 4, 0, new Vec2(0, 0));
        }
    }
}
