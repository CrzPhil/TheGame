package com.company.levels;

import city.cs.engine.*;
import com.company.bodies.dynamics.Enemy;
import com.company.bodies.dynamics.finiteStateMachines.FlyingEnemy;
import com.company.bodies.dynamics.finiteStateMachines.WalkingEnemy;
import com.company.bodies.dynamics.Villain;
import com.company.bodies.statics.Barrier;
import com.company.bodies.statics.Ground;
import com.company.bodies.statics.Heart;
import com.company.bodies.statics.Text;
import com.company.collisions.HeroCollisions;
import com.company.main.Game;
import org.jbox2d.common.Vec2;

public class Level4 extends GameLevel{
    public Level4(Game game) {
        super(game);

        // Add hero and life-overlay
        super.getHero().setPosition(new Vec2(-28, -7));
        Heart life = super.getHeart();

        // Add event Listeners
        HeroCollisions heroListener = new HeroCollisions(this, super.getHero());
        super.getHero().addCollisionListener(heroListener);

        // ====== Level Design ======


        // Ground
        float offset = 0;
        for (int i=0; i<6; i++) {
            new Ground(this).setPosition(new Vec2(-25+offset, -20f));
            offset += 10;
        }

        // Walking Enemy
        Enemy guard = new Enemy(this, new BoxShape(2.5f, 2.5f));
        guard.setPosition(new Vec2(20, -12.5f));

        // Tracker for Enemy
        WalkingEnemy walker = new WalkingEnemy(this, guard);

        // Flying Bat
        Enemy bat = new Enemy(this, new CircleShape(1), true);
        bat.setPosition(new Vec2(30, 0));

        // Tracker for Bat
        FlyingEnemy flyBat = new FlyingEnemy(this, bat);
        bat.setLinearVelocity(new Vec2(-5, 15));

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
}
