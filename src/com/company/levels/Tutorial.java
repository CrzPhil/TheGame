package com.company.levels;

import city.cs.engine.BodyImage;
import city.cs.engine.CircleShape;
import com.company.bodies.dynamics.Enemy;
import com.company.bodies.dynamics.Villain;
import com.company.bodies.statics.*;
import com.company.collisions.ArrowHit;
import com.company.collisions.HeroCollisions;
import com.company.main.Game;
import org.jbox2d.common.Vec2;

import javax.swing.*;

public class Tutorial extends GameLevel {

    public Tutorial(Game game) {
        super(game);

        // Set background image
        game.getView().setBackground(new ImageIcon("data/graphics/forestbckg.png").getImage());

        // Position Hero
        super.getHero().setPosition(new Vec2(-28, -7));

        // Add event Listeners
        HeroCollisions heroListener = new HeroCollisions(this, super.getHero());
        super.getHero().addCollisionListener(heroListener);

        // ====== Level Design ======

        // Create ground in a for-loop (6 tiles)
        float offset = 0;
        for (int i=0; i<6; i++) {
            new Ground(this).setPosition(new Vec2(-25+offset, -20f));
            offset += 10;
        }

        // Add Practice-Enemy
        Enemy bat = new Enemy(this, new CircleShape(1), true);
        bat.setPosition(new Vec2(7, 10));

        // Listener
        ArrowHit batFly = new ArrowHit(this, super.getHero());
        bat.addCollisionListener(batFly);

        // Checkpoint Flag
        new Platform(this).setPosition(new Vec2(0, 10));
        Checkpoint practice = new Checkpoint(this);
        practice.setPosition(new Vec2(0, 13.5f));

        // Instructions
        new Text(this, new BodyImage("data/graphics/shootEnemies.png"), true).setPosition(new Vec2(14, -2));
        new Text(this, new BodyImage("data/graphics/touchFlag.png"), true).setPosition(new Vec2(14, 12));
        new Text(this, new BodyImage("data/graphics/move.png"), true).setPosition(new Vec2(-22, -2));
    }

    // Inherited Abstract Methods
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

    @Override
    public String getLevelName() {
        return null;
    }
}
