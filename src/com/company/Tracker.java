package com.company;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import org.jbox2d.common.Vec2;

public class Tracker implements StepListener {
    private MyView view;
    private Hero spartan;

    public Tracker(MyView view, Hero spartan) {
        this.view = view;
        this.spartan = spartan;
    }

    @Override
    public void preStep(StepEvent stepEvent) {

    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
