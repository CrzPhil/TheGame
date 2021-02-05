package com.company;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;


public class MouseHandler extends MouseAdapter {
    /*private static final float RADIUS = 0.5f;
    private static final Shape orbShape = new CircleShape(RADIUS);
    private static final BodyImage orbImage = new BodyImage("data/orb.gif", 2*RADIUS);*/

    private WorldView view;
    private MyWorld world;

    public MouseHandler(WorldView view, MyWorld world){
        this.view = view;
        this.world = world;
    }

    public void mousePressed(MouseEvent e) {
        Arrow arrow = new Arrow(world);
        Point mouse = e.getPoint();
        Vec2 worldPoint = view.viewToWorld(mouse);
        arrow.setPosition(worldPoint);
        ArrowHit arrowHit = new ArrowHit(world.getHero());
        arrow.addCollisionListener(arrowHit);
        //new Arrow(world).setPosition(view.viewToWorld(e.getPoint()));
    }
}
