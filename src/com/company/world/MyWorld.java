package com.company.world;

import city.cs.engine.*;
import city.cs.engine.Shape;
import com.company.bodies.Arrow;
import com.company.bodies.Heart;
import com.company.bodies.Hero;
import com.company.bodies.Villain;
import com.company.collisions.ArrowHit;
import com.company.collisions.ScrollHit;
import org.jbox2d.common.Vec2;

import java.util.Random;

public class MyWorld extends World {

    private final Hero Spartan;
    private final Villain Sphinx;
    private Heart life;

    public MyWorld() {
        super();

        // Ground
        Shape floorShape = new BoxShape(32, 0.5f);
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

        // Text & Riddles
        Shape scrollShape = new BoxShape(8, 3);
        Body scroll = new StaticBody(this, scrollShape);
        scroll.setPosition(new Vec2(-3, 10.5f));
        BodyImage scrollTexture = new BodyImage("data/riddleOne.png");
        AttachedImage scrollAttached = new AttachedImage(scroll, scrollTexture, 8, 0, new Vec2(0, 0));

        ScrollHit scrollHit = new ScrollHit(this);
        scroll.addCollisionListener(scrollHit);

        // Add our Hero
        Spartan = new Hero(this);

        // Add Hero Heart
        life = new Heart(this);

        // Add our Villain
        Sphinx = new Villain(this);

        // Add event Listener to Floor so that falling arrows disappear
        ArrowHit arrowListener = new ArrowHit(this, Spartan);
        ground.addCollisionListener(arrowListener);

        // Add event Listener to Spartan for incoming arrows
        Spartan.addCollisionListener(arrowListener);

        // Arrow Rain
        /*int temp = 0;
        Random rand = new Random();
        int randomInt = rand.nextInt(10);
        int otherInt = rand.nextInt(10);
        while (temp<20) {
            new Arrow(this).setPosition(new Vec2(randomInt, 10));
            temp++;
        }*/
    }

    // Getter for our Hero
    public Hero getHero() {
        return Spartan;
    }

    public Villain getSphinx() {
        return Sphinx;
    }

    public Heart getHeart() {
        return life;
    }
}
