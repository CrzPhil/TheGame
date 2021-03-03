package com.company.levels;

import city.cs.engine.World;
import com.company.bodies.*;
import com.company.main.Game;

public abstract class GameLevel extends World {
    private Hero Spartan;
    private Heart life;
    private final Game game;

    public GameLevel(Game game) {
        Spartan = new Hero(this);
        life = new Heart(this);
        this.game = game;
    }

    public Hero getHero() {
        return Spartan;
    }
    public Heart getHeart() {
        return life;
    }

    public Game getGame() {
        return game;
    }

    public abstract boolean isComplete();

    public abstract Text getScroll();

    public abstract Villain getSphinx();

    public abstract Barrier getBarrier();

    public abstract void destroyChoices();

    public abstract void nullSphinx();
}
