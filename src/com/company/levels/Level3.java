package com.company.levels;

import city.cs.engine.Body;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import com.company.bodies.dynamics.Villain;
import com.company.bodies.statics.Barrier;
import com.company.bodies.statics.Heart;
import com.company.bodies.statics.Text;
import com.company.collisions.HeroCollisions;
import com.company.main.Game;
import org.jbox2d.common.Vec2;

public class Level3 extends GameLevel{
    public Level3(Game game) {
        super(game);

        // Add hero and life-overlay
        super.getHero().setPosition(new Vec2(-28, -7));
        Heart life = super.getHeart();

        // Add event Listeners
        HeroCollisions heroListener = new HeroCollisions(this, super.getHero());
        super.getHero().addCollisionListener(heroListener);

        // ====== Level Design ======

        // Ground
        Shape floorShape = new BoxShape(5, 5);
        Body ground = new StaticBody(this, floorShape);
        ground.setPosition(new Vec2(-25, -15f));
        Body ground2 = new StaticBody(this, floorShape);
        ground2.setPosition(new Vec2(-15, -15f));

    }


    // GameLevel Methods
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
