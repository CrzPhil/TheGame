package com.company.levels;

import org.jbox2d.common.Vec2;

public class Level1 extends GameLevel {

    public Level1() {
        // Create Hero through Base Class
        super();

        getHero().setPosition(new Vec2(8, -10));



    }

    @Override
    public boolean isComplete() {
        return false;
    }
}
