package com.company.levels;

import city.cs.engine.*;
import com.company.bodies.dynamics.Enemy;
import com.company.bodies.dynamics.finiteStateMachines.FlyingEnemy;
import com.company.bodies.dynamics.finiteStateMachines.WalkingEnemy;
import com.company.bodies.dynamics.Villain;
import com.company.bodies.statics.*;
import com.company.collisions.HeroCollisions;
import com.company.main.Game;
import org.jbox2d.common.Vec2;

import javax.swing.*;

public class Level4 extends GameLevel{
    public Level4(Game game) {
        super(game);

        // Change background image
        game.getView().setBackground(new ImageIcon("data/graphics/forestbckg.png").getImage());


        // Add hero and life-overlay
        super.getHero().setPosition(new Vec2(-28, -7));
        Heart life = super.getHeart();

        // Add event Listeners
        HeroCollisions heroListener = new HeroCollisions(this, super.getHero());
        super.getHero().addCollisionListener(heroListener);

        // ====== Level Design ======

        // Create ground in a for-loop (12 tiles)
        float offset = 0;
        for (int i=0; i<12; i++) {
            new Ground(this).setPosition(new Vec2(-25+offset, -20f));
            offset += 10;
        }

        // Barrier at the far right of the level
        Barrier viewUpdater = new Barrier(this);
        viewUpdater.setPosition(new Vec2(30, -12));

        // Walking Enemy
        Enemy guard = new Enemy(this, new BoxShape(2.5f, 2.5f));
        guard.setPosition(new Vec2(20, -12.5f));

        // Tracker for Enemy
        WalkingEnemy walker = new WalkingEnemy(this, guard);

        // Flying Bat
        Enemy bat = new Enemy(this, new CircleShape(1), true);
        bat.setPosition(new Vec2(30, 0));
        Enemy bat2 = new Enemy(this, new CircleShape(1), true);
        bat2.setPosition(new Vec2(50, -1));

        // Tracker for Bat
        new FlyingEnemy(this, bat);
        new FlyingEnemy(this, bat2);
        bat.setLinearVelocity(new Vec2(-5, 15));
        bat2.setLinearVelocity(new Vec2(-5, 15));

        // Throne
        Throne throne = new Throne(this);
        throne.setPosition(new Vec2(50, -12));
    }
    // Extra Methods
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
        return "Level4";
    }
}
