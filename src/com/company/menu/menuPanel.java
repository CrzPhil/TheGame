package com.company.menu;

import com.company.main.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menuPanel extends JPanel{
    private JButton menuButton;
    private JButton pauseButton;
    private JButton restartButton;
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
    }

    public JPanel getmainPanel() {
        return mainPanel;
    }
}
