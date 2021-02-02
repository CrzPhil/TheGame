package com.company;

import city.cs.engine.BodyImage;
import org.jbox2d.common.Vec2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {
    private static final float MOVEMENT_SPEED = 6;
    private Hero Spartan;

    public Controller(Hero Spartan) {
        this.Spartan = Spartan;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            Spartan.startWalking(-MOVEMENT_SPEED);
            Spartan.setHeroImage(new BodyImage("data/spartan_left.png"));
        } else if (key == KeyEvent.VK_D) {
            Spartan.startWalking(MOVEMENT_SPEED);
            Spartan.setHeroImage(new BodyImage("data/spartan_right.png"));
        } else if (key == KeyEvent.VK_SPACE) {
            Spartan.jump(5f);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            Spartan.stopWalking();
            // To make slow-down process not to abrupt, yet not too slippery
            Spartan.setLinearVelocity(new Vec2(-3, 0));
        } else if (key == KeyEvent.VK_D) {
            Spartan.stopWalking();
            Spartan.setLinearVelocity(new Vec2(3, 0));
        }
    }
}
