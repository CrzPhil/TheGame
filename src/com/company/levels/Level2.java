package com.company.levels;

import city.cs.engine.*;
import com.company.bodies.dynamics.Enemy;
import com.company.bodies.dynamics.Villain;
import com.company.bodies.statics.*;
import com.company.collisions.ArrowHit;
import com.company.collisions.HeroCollisions;
import com.company.main.Game;
import org.jbox2d.common.Vec2;

import javax.swing.*;


public class Level2 extends GameLevel {
    public Level2(Game game) {
        super(game);

        // Change background image
        game.getView().setBackground(new ImageIcon("data/graphics/fantasy.png").getImage());

        // Add hero and life-overlay
        super.getHero().setPosition(new Vec2(-28, -7));

        // Add event Listeners
        HeroCollisions heroListener = new HeroCollisions(this, super.getHero());
        super.getHero().addCollisionListener(heroListener);

        // ====== Level Design ======

        // Ground
        new Ground(this).setPosition(new Vec2(-25, -15f));
        new Ground(this).setPosition(new Vec2(-15, -15f));

        // Platforms
        new Platform(this).setPosition(new Vec2(0, -5));
        new Platform(this).setPosition(new Vec2(-25, 0));
        new Platform(this).setPosition(new Vec2(-10, 10));
        new Platform(this).setPosition(new Vec2(15, 10));
        new Platform(this).setPosition(new Vec2(25, 10));

        // Special Hermes Boots which give Jump-Boost effect on pickup
        SpecialObject Boots = new SpecialObject(this, new BoxShape(1, 1), new BodyImage("data/graphics/hermesboots.png"), super.getHero());
        Boots.setPosition(new Vec2(-15, -8));

        // Enemy Sprites
        Enemy bat = new Enemy(this, new CircleShape(1), true);
        bat.setPosition(new Vec2(0, 10));
        Enemy bat2 = new Enemy(this, new CircleShape(1), false);
        bat2.setPosition(new Vec2(-25, 5));

        // Listener for Bats, makes them fly up and down
        ArrowHit enemyListener = new ArrowHit(this, super.getHero());
        bat.addCollisionListener(enemyListener);
        bat2.addCollisionListener(enemyListener);

        // Checkpoint Flag
        Checkpoint flag = new Checkpoint(this);
        flag.setPosition(new Vec2(25, 13.5f));

        // Add Barrier at end of level to check for completion
        Barrier checker = new Barrier(this);
        checker.setPosition(new Vec2(29, 10));
    }

    @Override
    public boolean isComplete() {
        // If hero killed all the enemies in both levels, the score adds up to 5 and he may proceed to level 3
        return (super.getHero().getScore() >= 5);
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

    @Override
    public String getLevelName() {
        return "Level2";
    }
}
