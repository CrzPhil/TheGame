package com.company.world;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import com.company.bodies.Hero;
import com.company.world.MyView;

public class Tracker implements StepListener {
    private MyView view;
    private Hero spartan;

    public Tracker(MyView view, Hero spartan) {
        this.view = view;
        this.spartan = spartan;
    }

    public Tracker(MyView view) {
        this.view = view;
    }

    @Override
    public void preStep(StepEvent stepEvent) {

    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
