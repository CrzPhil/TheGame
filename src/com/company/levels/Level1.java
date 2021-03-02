package com.company.levels;

import city.cs.engine.*;
import com.company.bodies.*;
import com.company.collisions.ArrowHit;
import com.company.main.Game;
import org.jbox2d.common.Vec2;

public class Level1 extends GameLevel {

    private final Villain Sphinx;
    private final Heart life;
    private final Barrier barrierSphinx;
    private final Text scroll;

    private final Choice answerOne;
    private final Choice answerTwo;
    private final Choice answerThree;

    public Level1(Game game) {
        super(game);

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

        // Temporary shape to make the outline of tower more pixel-perfect. Not to be confused with Barrier Class
        Shape barrierShape = new BoxShape(1, 4);
        Body barrier = new StaticBody(this, barrierShape);
        barrier.setPosition(new Vec2(-12, -3.5f));

        // Text & Riddles
        scroll = new Text(this, new BodyImage("data/riddleOne.png"));

        // Add our Hero
        super.getHero().setPosition(new Vec2(0, 1));

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
        ArrowHit arrowListener = new ArrowHit(this, super.getHero());
        ground.addCollisionListener(arrowListener);

        // Add event Listener to Spartan for incoming arrows
        super.getHero().addCollisionListener(arrowListener);

    }

    // Getters for Objects
    public Hero getHero() {
        return super.getHero();
    }

    @Override
    public boolean isComplete() {
        return false;
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
