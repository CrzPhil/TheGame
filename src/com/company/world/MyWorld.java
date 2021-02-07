package com.company.world;

import city.cs.engine.*;
import city.cs.engine.Shape;
import com.company.bodies.*;
import com.company.collisions.ArrowHit;
import com.company.collisions.ScrollHit;
import org.jbox2d.common.Vec2;

import java.util.Random;

public class MyWorld extends World {

    private final Hero Spartan;
    private final Villain Sphinx;
    private final Heart life;
    private final Barrier barrierSphinx;
    private final Text scroll;

    private Choice answerOne;
    private Choice answerTwo;
    private Choice answerThree;

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
        scroll = new Text(this);

        ScrollHit scrollHit = new ScrollHit(this);
        scroll.addCollisionListener(scrollHit);

        // Add our Hero
        Spartan = new Hero(this);

        // Add Hero Heart
        life = new Heart(this);

        // Add our Villain
        Sphinx = new Villain(this);

        // Add barrier in front of Villain
        barrierSphinx = new Barrier(this);

        // Add three choices for the riddle
        answerOne = new Choice(this, new BodyImage("data/fire.png", 4), 10, 12, false);
        answerTwo = new Choice(this, new BodyImage("data/time.png", 4), 14, 12, true);
        answerThree = new Choice(this, new BodyImage("data/water.png", 4), 18, 12, false);

        // Add event Listener to Floor so that falling arrows disappear
        ArrowHit arrowListener = new ArrowHit(this, Spartan);
        ground.addCollisionListener(arrowListener);

        // Add event Listener to Spartan for incoming arrows
        Spartan.addCollisionListener(arrowListener);

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

    public Text getScroll() {
        return scroll;
    }

    public Barrier getBarrier() {
        return barrierSphinx;
    }

    public void destroyChoices() {
        if (answerOne != null) {
            answerOne.destroy();
        }
        if (answerTwo != null) {
            answerTwo.destroy();
        }
        if (answerThree != null) {
            answerThree.destroy();
        }
    }
}
