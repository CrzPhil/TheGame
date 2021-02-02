package com.company;
import city.cs.engine.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;


public class MouseHandler extends MouseAdapter{
    private static final float RADIUS = 0.5f;
    private static final Shape orbShape = new CircleShape(RADIUS);
    private static final BodyImage orbImage = new BodyImage("data/orb.gif", 2*RADIUS);

    private WorldView view;

    public MouseHandler(WorldView view){
        this.view = view;
    }

    public void mousePressed(MouseEvent e) {
        DynamicBody orb = new DynamicBody(view.getWorld(), orbShape);
        orb.setPosition(view.viewToWorld(e.getPoint()));
        orb.addImage(orbImage);
    }
}
