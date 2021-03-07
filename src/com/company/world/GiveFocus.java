package com.company.world;

import com.company.world.MyView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/*
    Standard method to give View focus when mouse is inside.
 */

public class GiveFocus implements MouseListener {
    private MyView view;

    public GiveFocus(MyView view) {
        this.view = view;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        view.requestFocus();
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
