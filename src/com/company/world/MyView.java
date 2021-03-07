package com.company.world;

import city.cs.engine.UserView;
import city.cs.engine.World;
import com.company.main.Game;

import javax.swing.*;
import java.awt.*;

public class MyView extends UserView {
     private Image background;
     private Game game;

     public MyView(World w, int width, int height, Game game) {
         super(w, width, height);
         this.game = game;
         background = new ImageIcon("data/graphics/background.png").getImage();
     }

     // Draw our background image
    @Override
    protected void paintBackground(Graphics2D g) {
        /*super.paintBackground(g);*/
        g.drawImage(background, 0, 0, this);
    }

    @Override
    protected void paintForeground(Graphics2D g) {
         g.setFont(new Font("Courier New", Font.BOLD, 24));
         g.drawString("Score: " + game.getWorld().getHero().getScore(), 30, 45);
    }

    // Setter for the image
    public void setBackground(Image background) {
        this.background = background;
    }
}
