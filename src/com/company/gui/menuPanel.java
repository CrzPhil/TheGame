package com.company.gui;

import com.company.levels.Level1;
import com.company.levels.Level2;
import com.company.main.Game;
import com.company.main.MouseHandler;
import com.company.world.GiveFocus;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menuPanel extends JPanel{
    private JButton restartButton;
    private JButton pauseButton;
    private JPanel mainPanel;
    private JButton checkpointButton;
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

                updateWorld();

                game.getView().setBackground(new ImageIcon("data/graphics/background.png").getImage());

                game.getWorld().start();
            }
        });

        // When hitting the Checkpoint button, the player is teleported to the location of the last flag he touched
        checkpointButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // First Checkpoint is at level 2, so that's where we will move the Hero on button-press
                if (game.getWorld().getHero().getCheckPoint() == 1) {
                    // Save old stats for the new Level (we know all stats and won't transfer old ones to prohibit cheating)
                    int oldHealth = 3; // Health is reset on hitting the flag
                    int oldScore = 5;  // Score after the first two levels cannot exceed 5
                    int oldCheck = 1;  // First checkpoint on level 2

                    game.getWorld().stop();
                    game.setWorld(new Level2(game));
                    updateWorld();
                    game.getView().setBackground(new ImageIcon("data/graphics/shroomsbckg.png").getImage());
                    // Lastly, move hero to the flag location so he doesn't have to repeat the whole level
                    game.getWorld().getHero().setPosition(new Vec2(25, 13.5f));

                    // Transfer current stats to new Hero
                    game.transferStats(oldHealth, oldScore, oldCheck);
                    // When the Hero gets teleported to the checkpoint, he hits it again so we have to decrement the actual value by one
                    game.getWorld().getHero().setCheckPoint(game.getWorld().getHero().getCheckPoint()-1);

                    game.getWorld().start();
                }
            }
        });
    }
    // Method to quickly update all the Listeners and Pointers to the current Level
    public void updateWorld() {
        game.getView().setWorld(game.getWorld());
        game.getView().addMouseListener(new MouseHandler(game.getView(), game.getWorld()));
        game.getView().addMouseListener(new GiveFocus(game.getView()));
        game.getWorld().getHero().setHealth(3);
        game.getHeroController().updateHero(game.getWorld().getHero());
    }
    public JPanel getmainPanel() {
        return mainPanel;
    }
}
