package com.company.bodies;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Hero extends Walker  {
    private static final Shape heroShape = new PolygonShape(-0.76f,2.77f,
            0.43f,2.76f,
            1.09f,1.95f,
            1.67f,-0.69f,
            1.69f,-2.25f,
            1.09f,-3.46f,
            -1.57f,-3.46f,
            -1.99f,1.19f);

    private static BodyImage heroImage = new BodyImage("data/spartan_idle.png", 7f);

    private int health;

    public Hero(World world) {
        super(world, heroShape);
        addImage(heroImage);
        this.health = 3;
        this.setPosition(new Vec2(8, -10));
    }

    public void setHeroImage(BodyImage direction) {
        removeAllImages();
        Hero.heroImage = direction;
        addImage(heroImage);
    }

    public void takeDamage() {
        health--;
    }

    public int getHealth() {
        return health;
    }
}
