package com.company;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;

public class MyWorld extends World {

    private Hero Gandalf;

    public MyWorld() {
        super();

        // Ground
        Shape floorShape = new BoxShape(28, 0.5f);
        Body ground = new StaticBody(this, floorShape);
        ground.setPosition(new Vec2(0, -11.5f));

        // Tower
        Shape wallShape = new BoxShape(5, 8);
        Body wall = new StaticBody(this, wallShape);
        wall.setPosition(new Vec2(-20.5f, -4));

        BodyImage wallImage = new BodyImage("data/wall.png");
        AttachedImage wallAttached = new AttachedImage(wall, wallImage, 16, 0, new Vec2(0, 0));

        // wall.addImage(wallAttached.getBodyImage());

        // Add our Hero
        Gandalf = new Hero(this);

        // Add other Bodies
        // Sauron
        Shape sauronShape = new PolygonShape(-2.16f,4.66f,
                2.22f,4.68f,
                2.18f,-4.66f,
                -2.42f,-4.62f);
        DynamicBody sauron = new DynamicBody(this, sauronShape);
        sauron.addImage((new BodyImage("data/sauron.png", 10f)));
        sauron.setPosition(new Vec2(-20.5f, 5f));

        // Kirby
        Shape kirbyShape = new PolygonShape(-2.44f,2.37f,
                2.38f,2.35f,
                2.21f,-2.27f,
                -2.35f,-2.27f);
        DynamicBody kirby = new DynamicBody(this, kirbyShape);
        kirby.addImage(new BodyImage("data/kirby.gif", 5f));
        kirby.setPosition(new Vec2(0, -10));
    }

    // Getter for our Hero
    public Hero getHero() {
        return Gandalf;
    }
}
