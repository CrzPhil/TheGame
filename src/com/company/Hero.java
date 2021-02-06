package com.company;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Hero extends Walker  {
    private static final Shape heroShape = new PolygonShape(1.62f,-3.46f,
            1.66f,-0.67f,
            1.07f,1.97f,
            0.44f,2.76f,
            -1.38f,3.47f,
            -2.18f,3.39f,
            -2.37f,-3.46f);

    private static BodyImage heroImage = new BodyImage("data/spartan_idle.png", 7f);

    private int health = 3;

    public Hero(World world) {
        super(world, heroShape);
        addImage(heroImage);
        this.health = 100;
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
}
