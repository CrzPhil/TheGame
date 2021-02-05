package com.company;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;

public class MyWorld extends World {

    private Hero Spartan;

    public MyWorld() {
        super();

        // Ground
        Shape floorShape = new BoxShape(28, 0.5f);
        Body ground = new StaticBody(this, floorShape);
        ground.setPosition(new Vec2(0, -11.5f));

        // Tower
        Shape wallShape = new BoxShape(8, 6);
        Body wall = new StaticBody(this, wallShape);
        wall.setPosition(new Vec2(-20.5f, -4.8f));

        BodyImage wallImage = new BodyImage("data/wall.png");
        AttachedImage wallAttached = new AttachedImage(wall, wallImage, 13, 0, new Vec2(0, 0));

        // Barrier is in front of tower in order to allow spikeball to be dispensed outwards
        Shape barrierShape = new BoxShape(1, 4);
        Body barrier = new StaticBody(this, barrierShape);
        barrier.setPosition(new Vec2(-12, -3.5f));

        // Text
        Shape scrollShape = new BoxShape(8, 3);
        Body scroll = new StaticBody(this, scrollShape);
        scroll.setPosition(new Vec2(-3, 8.5f));
        BodyImage scrollTexture = new BodyImage("data/riddleOne.png");
        AttachedImage scrollAttached = new AttachedImage(scroll, scrollTexture, 8, 0, new Vec2(0, 0));

        // Add our Hero
        Spartan = new Hero(this);
        /*ArrowHit t = new ArrowHit(Spartan);
        Spartan.addCollisionListener(t);*/

        // Add other Bodies


        // Sphinx
        Shape villainShape = new PolygonShape(-4.31f,-4.58f,
                4.8f,-4.46f,
                4.8f,4.4f,
                -4.06f,4.44f);
        DynamicBody sphinx = new DynamicBody(this, villainShape);
        sphinx.addImage((new BodyImage("data/sphinx.png", 10f)));
        sphinx.setPosition(new Vec2(-20.5f, 5f));

    }

    // Getter for our Hero
    public Hero getHero() {
        return Spartan;
    }
}
