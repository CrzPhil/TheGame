package com.company.levels;

import city.cs.engine.World;
import com.company.bodies.Hero;

public abstract class GameLevel extends World {
    private Hero Spartan;

    public GameLevel() {
        Spartan = new Hero(this);
    }

    public Hero getHero() {
        return Spartan;
    }

    public abstract boolean isComplete();
}
