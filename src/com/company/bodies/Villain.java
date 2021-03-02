package com.company.bodies;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Villain extends DynamicBody {
    private static final PolygonShape villainShape = new PolygonShape(-4.31f,-4.58f,
            4.8f,-4.46f,
            4.8f,4.4f,
            -4.06f,4.44f);

    private static BodyImage sphinxImage = new BodyImage("data/sphinx.png", 10f);

    private int health;

    public Villain(World world) {
        super(world, villainShape);
        addImage(sphinxImage);
        this.setPosition(new Vec2(-20.5f, 5f));
        this.health = 3;
    }

    public void takeDamage() {
        health--;
    }

    public int getHealth() {
        return health;
    }

    public void setSphinxImage(BodyImage sphinxImage) {
        removeAllImages();
        Villain.sphinxImage = sphinxImage;
        addImage(sphinxImage);
    }
}
