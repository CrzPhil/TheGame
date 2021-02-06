package com.company;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;


public class MouseHandler extends MouseAdapter {
    private static final float spearSpeed = 20.8f;

    private WorldView view;
    private MyWorld world;

    public MouseHandler(WorldView view, MyWorld world){
        this.view = view;
        this.world = world;
    }

    public void mousePressed(MouseEvent e) {
        /*Arrow arrow = new Arrow(world);
        Point mouse = e.getPoint();
        Vec2 worldPoint = view.viewToWorld(mouse);
        arrow.setPosition(worldPoint);
        ArrowHit arrowHit = new ArrowHit(world.getHero());
        arrow.addCollisionListener(arrowHit);*/

        Spear spear = new Spear(world);
        Point mouse = e.getPoint();
        Vec2 worldPoint = view.viewToWorld(mouse);

        // X and Y Coordinates of the Mouse when clicked
        float mx = worldPoint.x;
        float my = worldPoint.y;

        // X and Y Coordinates of our Spartan when the mouse is clicked
        float cx = world.getHero().getPosition().x;
        float cy = world.getHero().getPosition().y;

        // Vector of the combined values of Mouse Location and Spartan Location
        Vec2 combined = new Vec2(mx-cx, my-cy);

        // Magnitude of the Vector |v| = Sqrt((mc-cx)^2 + (mc-cy)^2)
        float magnitude = (float) Math.sqrt((Math.pow((mx-cx), 2) + Math.pow((my-cy), 2)));

        // Normalising our Vector by dividing the x and y by the magnitude
        Vec2 normalised = new Vec2(combined.x/magnitude, combined.y/magnitude);

        // Multiplying the normalised vector by the speed of the projectile
        Vec2 shot = new Vec2(normalised.x * spearSpeed, normalised.y * spearSpeed);

        spear.setPosition(new Vec2(world.getHero().getPosition().x + 2, world.getHero().getPosition().y + 3.5f));
        spear.setLinearVelocity(shot);

    }
}
