package com.company;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Hero extends Walker  {
    private static final Shape gandalfShape = new PolygonShape(-0.93f,1.56f,
            2.33f,1.63f,
            2.32f,-2.47f,
            -1.74f,-2.46f,
            -1.64f,1.32f);

    private static final BodyImage gandalfImage = new BodyImage("data/gandalf.png", 5f);

    private int health;

    public Hero(World world) {
        super(world, gandalfShape);
        addImage(gandalfImage);
        this.health = 100;
        this.setPosition(new Vec2(8, -10));
    }

}
