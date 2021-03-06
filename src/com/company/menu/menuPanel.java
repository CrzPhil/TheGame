package com.company.menu;

import com.company.levels.Level1;
import com.company.main.Game;
import com.company.main.MouseHandler;
import com.company.world.GiveFocus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menuPanel extends JPanel{
    private JButton restartButton;
    private JButton pauseButton;
    private JButton reloadButton;
    private JPanel mainPanel;
    private final Game game;

    // Control-variable for Pause/Unpause
    private boolean stop = false;

    public menuPanel(Game game) {
        this.game = game;

        // Pause button stops and resumes the game on-click
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!stop) {
                    game.getWorld().stop();
                    stop = true;
                    // Button Text changes from Pause to Unpause
                    pauseButton.setText("Unpause");
                } else {
                    game.getWorld().start();
                    stop = false;
                    pauseButton.setText("Pause");
                }
            }
        });

        // Restart button resets lives, levels and enemies
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Everything we added in Game:goToNextLevel(); we do here but in reverse
                game.getWorld().stop();

                game.setWorld(new Level1(game));

                game.getView().setWorld(game.getWorld());

                game.getView().addMouseListener(new MouseHandler(game.getView(), game.getWorld()));
                game.getView().addMouseListener(new GiveFocus(game.getView()));

                game.getHeroController().updateHero(game.getWorld().getHero());

                game.getView().setBackground(new ImageIcon("data/graphics/background.png").getImage());

                game.getWorld().start();
            }
        });
    }

    public JPanel getmainPanel() {
        return mainPanel;
    }
}
