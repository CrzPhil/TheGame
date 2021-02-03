package com.company;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Hero extends Walker  {
    private static final Shape heroShape = new PolygonShape(1.76f,-3.3f,
            -1.43f,-3.32f,
            -1.5f,3.19f,
            -0.41f,3.22f,
            2.0f,1.26f);

    private static BodyImage heroImage = new BodyImage("data/spartan_idle.png", 7f);

    private int health;

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
}
