package com.company;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {
    private static final float MOVEMENT_SPEED = 6;
    private Hero Gandalf;

    public Controller(Hero Gandalf) {
        this.Gandalf = Gandalf;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            Gandalf.startWalking(-MOVEMENT_SPEED);
        } else if (key == KeyEvent.VK_D) {
            Gandalf.startWalking(MOVEMENT_SPEED);
        } else if (key == KeyEvent.VK_SPACE) {
            Gandalf.jump(5f);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            Gandalf.stopWalking();
        } else if (key == KeyEvent.VK_D) {
            Gandalf.stopWalking();
        }
    }
}
