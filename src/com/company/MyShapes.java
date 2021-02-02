package com.company;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class MyShapes extends DynamicBody {
    public MyShapes(World world) {
        super(world);

        // Gandalf
        Shape gandalfShape = new PolygonShape(-0.93f,1.56f,
                2.33f,1.63f,
                2.32f,-2.47f,
                -1.74f,-2.46f,
                -1.64f,1.32f);
        DynamicBody gandalf = new DynamicBody(world, gandalfShape);
        gandalf.addImage(new BodyImage("data/gandalf.png", 5f));
        gandalf.setPosition(new Vec2(8, -10));

        // Sauron
        Shape sauronShape = new PolygonShape(-2.16f,4.66f,
                2.22f,4.68f,
                2.18f,-4.66f,
                -2.42f,-4.62f);
        DynamicBody sauron = new DynamicBody(world, sauronShape);
        sauron.addImage((new BodyImage("data/sauron.png", 10f)));
        sauron.setPosition(new Vec2(-20.5f, 5f));

        // Kirby
        Shape kirbyShape = new PolygonShape(-2.44f,2.37f,
                2.38f,2.35f,
                2.21f,-2.27f,
                -2.35f,-2.27f);
        DynamicBody kirby = new DynamicBody(world, kirbyShape);
        kirby.addImage(new BodyImage("data/kirby.gif", 5f));
        kirby.setPosition(new Vec2(0, -10));
    }
}
