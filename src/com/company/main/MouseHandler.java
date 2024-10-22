package com.company.main;
import city.cs.engine.*;
import com.company.bodies.dynamics.Spear;
import com.company.collisions.SpearHit;
import com.company.levels.GameLevel;
import org.jbox2d.common.Vec2;

import javax.swing.text.View;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;


/*
    This class is responsible for shooting Spears when clicking the mouse.
 */

/**
 * Used to Spawn spears as projectiles when clicking the Mousebutton.
 * Spears spawn on the Hero and are launched towards whatever the Mouse is aiming at.
 */
public class MouseHandler extends MouseAdapter {
    /**
     * Variable for the flying Speed of the spears
     */
    private static final float spearSpeed = 22.5f;

    private WorldView view;
    private GameLevel world;

    /**
     * Points local view and world to the Level and View used.
     * @param view Updates local View
     * @param world Updates local World -or level-
     */
    public MouseHandler(WorldView view, GameLevel world){
        this.view = view;
        this.world = world;
    }

    /**
     * Called whenever the Mouse is clicked
     * @param e Event, used to find location of Mouseclick
     */
    public void mousePressed(MouseEvent e) {
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

        // Set the position of the spear a little above the Spartan, so as to avoid collision on spawning
        spear.setPosition(new Vec2(world.getHero().getPosition().x, world.getHero().getPosition().y + 3.5f));

        // Rotating Spear in the correct direction (Facing: Top, Left, Right)
        if (mx > (cx - 3.5f) && mx < (cx + 3.5f) ) {
            spear.setAngleDegrees(180);
        } else if (mx < cx) {
            spear.setAngleDegrees(-90);
        } else if (mx > cx) {
            spear.setAngleDegrees(90);
        }
        // Give our spear the calculated Velocity
        spear.setLinearVelocity(shot);

        // Collision Listener for the generated Spears
        SpearHit spearHit = new SpearHit(world);
        spear.addCollisionListener(spearHit);
    }

    /**
     * Used when transitioning through levels, to update values being pointed add.
     * Removes necessity of creating new Objects of this class.
     * @param view New View being pointed to.
     * @param world New world being pointed to.
     */
    public void updateListener(WorldView view, GameLevel world) {
        this.view = view;
        this.world = world;
    }
}
