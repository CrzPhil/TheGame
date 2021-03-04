package com.company.levels;

import city.cs.engine.*;
import com.company.bodies.*;
import com.company.collisions.ArrowHit;
import com.company.collisions.HeroCollisions;
import com.company.main.Game;
import org.jbox2d.common.Vec2;

import java.beans.beancontext.BeanContext;

public class Level2 extends GameLevel {
    private final Heart life;

    public Level2(Game game) {
        super(game);

        // Add hero and life-overlay
        super.getHero().setPosition(new Vec2(-28, -7));
        life = super.getHeart();

        // Add event Listeners
        HeroCollisions heroListener = new HeroCollisions(this, super.getHero());

        // Add event Listener to Spartan for incoming arrows
        super.getHero().addCollisionListener(heroListener);

        // ====== Level Design ======

        // Ground
        Shape floorShape = new BoxShape(10, 0.5f);
        Body ground = new StaticBody(this, floorShape);
        ground.setPosition(new Vec2(-20, -11.5f));

        // Platforms
        Shape platformShape = new BoxShape(5, 0.5f);
        Body platform = new StaticBody(this, platformShape);
        platform.setPosition(new Vec2(0, -5));
        /*BodyImage platformImage = new BodyImage("data/graphics/platform.png");
        AttachedImage platformAttached = new AttachedImage(platform, platformImage, 6, 0, new Vec2(0, 0));*/
        new StaticBody(this, platformShape).setPosition(new Vec2(-25, 0));
        new StaticBody(this, platformShape).setPosition(new Vec2(-10, 10));
        new StaticBody(this, platformShape).setPosition(new Vec2( 15, 10));

        // Special Hermes Boots
        SpecialObject Boots = new SpecialObject(this, new BoxShape(1, 1), new BodyImage("data/graphics/hermesboots.png"));
        Boots.setPosition(new Vec2(-15, -9));

        // Enemy Sprites
        Enemy bat = new Enemy(this, new CircleShape(1));
        bat.setPosition(new Vec2(0, 10));
        // Listener for Bat, makes it fly up and down
        ArrowHit enemyListener = new ArrowHit(this, super.getHero());
        bat.addCollisionListener(enemyListener);
    }

    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public Text getScroll() {
        return null;
    }
    @Override
    public Villain getSphinx() {
        return null;
    }
    @Override
    public Barrier getBarrier() {
        return null;
    }
    @Override
    public void destroyChoices() {
    }
    @Override
    public void nullSphinx() {
    }
}