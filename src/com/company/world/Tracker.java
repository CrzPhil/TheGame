package com.company.world;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import com.company.bodies.dynamics.Hero;
import com.company.bodies.dynamics.Spikeball;
import com.company.main.Game;
import org.jbox2d.common.Vec2;

import java.util.Random;

public class Tracker implements StepListener {
    private MyView view;
    private Game game;
    // Control-variable for preStep method
    private int temp=0;
    // We want to randomly generate balls in one of five locations (left-up;left;middle-up;right-up;right)
    Random r = new Random();

    public Tracker(MyView view, Game game) {
        this.view = view;
        this.game = game;
    }

    public Tracker(MyView view) {
        this.view = view;
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        // This blob of code executes roughly every two seconds, which will help us in spawning spikeBalls in level 3
        temp++;
        if (temp % 120 == 0) {
            // We generate a number between 0(incl)-5(excl) and generate a spike in that location
            int randLoc = r.nextInt(5);
            new Spikeball(game.getWorld(), randLoc);
            temp = 0;
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
