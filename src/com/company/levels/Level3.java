package com.company.levels;

import city.cs.engine.Body;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import com.company.bodies.dynamics.Spikeball;
import com.company.bodies.dynamics.Villain;
import com.company.bodies.statics.Barrier;
import com.company.bodies.statics.Heart;
import com.company.bodies.statics.Platform;
import com.company.bodies.statics.Text;
import com.company.collisions.ArrowHit;
import com.company.collisions.HeroCollisions;
import com.company.main.Game;
import org.jbox2d.common.Vec2;

public class Level3 extends GameLevel{
    public Level3(Game game) {
        super(game);

        // Add hero and life-overlay
        super.getHero().setPosition(new Vec2(0, -12));
        Heart life = super.getHeart();

        // Add event Listeners
        HeroCollisions heroListener = new HeroCollisions(this, super.getHero());
        super.getHero().addCollisionListener(heroListener);

        // ====== Level Design ======

        // Platforms
        new Platform(this).setPosition(new Vec2(0, -15));
        new Platform(this).setPosition(new Vec2(-25, -5));
        new Platform(this).setPosition(new Vec2(25, -5));
        // These two are off-screen, but we will spawn our spikeballs there so they can smoothly roll in
        new Platform(this).setPosition(new Vec2(-35, -5));
        new Platform(this).setPosition(new Vec2(35, -5));


        // Funnel are the two rotated platforms
        Platform funnel = new Platform(this);
        Platform funnel2 = new Platform(this);
        funnel.setPosition(new Vec2(-15, 5));
        funnel.rotateDegrees(-45);
        funnel2.setPosition(new Vec2(15, 5));
        funnel2.rotateDegrees(45);

        // Garbage Collector (aka Ball-despawner)
        Shape floorShape = new BoxShape(32, 0.5f);
        Body ground = new StaticBody(this, floorShape);
        ground.setPosition(new Vec2(0, 20));
        // In previous levels we already defined a sort-of garbage collector that destroys everything it collides with
        // Here we use it to destroy the balls or spears that may fall off-screen to preserve memory
        ArrowHit deSpawner = new ArrowHit(this, super.getHero());
        ground.addCollisionListener(deSpawner);
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
