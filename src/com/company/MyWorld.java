package com.company;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;

public class MyWorld extends World {
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

    }
}
