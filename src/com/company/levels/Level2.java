package com.company.levels;

import city.cs.engine.Body;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import com.company.bodies.*;
import com.company.main.Game;
import org.jbox2d.common.Vec2;

public class Level2 extends GameLevel {
    private final Heart life;

    public Level2(Game game) {
        super(game);

        // Level Design
        // Add hero
        super.getHero().setPosition(new Vec2(0, 1));

        // Ground
        Shape floorShape = new BoxShape(32, 0.5f);
        Body ground = new StaticBody(this, floorShape);
        ground.setPosition(new Vec2(0, -11.5f));
        life = super.getHeart();
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
