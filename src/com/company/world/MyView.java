package com.company.world;

import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.swing.*;
import java.awt.*;

public class MyView extends UserView {
     private final Image background;

     public MyView(World w, int width, int height) {
         super(w, width, height);
         background = new ImageIcon("data/background.png").getImage();
     }

    @Override
    protected void paintBackground(Graphics2D g) {
        /*super.paintBackground(g);*/
        g.drawImage(background, 0, 0, this);
    }
}
