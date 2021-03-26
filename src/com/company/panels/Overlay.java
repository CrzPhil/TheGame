package com.company.panels;

import com.company.levels.Level1;
import com.company.levels.Level2;
import com.company.main.Game;
import com.company.main.MouseHandler;
import com.company.world.GiveFocus;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Overlay extends JPanel{
    private JButton restartButton;
    private JButton pauseButton;
    private JPanel mainPanel;
    private JButton checkpointButton;
    private JSlider slider1;
    private final Game game;

    // Control-variable for Pause/Unpause
    private boolean stop = false;

    public Overlay(Game game) {
        this.game = game;

        // Make the JPanel transparent so it looks smoother in-game
        mainPanel.setOpaque(false);
        mainPanel.setBackground(new Color(0, 0, 0, 0));

        // Adjust slider settings for Volume control
        slider1.setValue(100);
        slider1.setMaximum(150);
        slider1.setMinimum(0);

        // Pause button stops and resumes the game on-click
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!stop) {
                    game.getWorld().stop();
                    stop = true;
                    // Pause Music
                    game.getCurrentMusic().pause();
                    // Button Text changes from Pause to Unpause
                    pauseButton.setText("Unpause");
                } else {
                    game.getWorld().start();
                    stop = false;
                    // Resume Music
                    game.getCurrentMusic().resume();
                    pauseButton.setText("Pause");
                }
            }
        });

        // Restart button resets lives, levels and enemies, and music;
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Everything we added in Game:goToNextLevel(); we do here but in reverse
                game.getWorld().stop();

                game.setWorld(new Level1(game));

                updateWorld();

                game.getView().setBackground(new ImageIcon("data/graphics/background.png").getImage());

                // Stops current Track and resets to first Track.
                game.getCurrentMusic().stop();
                game.getCurrentMusic().loop();

                // Start
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
                    // Set Background Image
                    game.getView().setBackground(new ImageIcon("data/graphics/shroomsbckg.png").getImage());
                    // Lastly, move hero to the flag location so he doesn't have to repeat the whole level
                    game.getWorld().getHero().setPosition(new Vec2(25, 13.5f));
                    // Transfer current stats to new Hero
                    game.transferStats(oldHealth, oldScore, oldCheck);
                    // When the Hero gets teleported to the checkpoint, he hits it again so we have to decrement the actual value by one
                    game.getWorld().getHero().setCheckPoint(game.getWorld().getHero().getCheckPoint()-1);
                    // Set Track to Level 2 Track
                    game.getCurrentMusic().stop();
                    game.setCurrentMusic(game.getLevel1Music());
                    game.getCurrentMusic().loop();

                    game.getWorld().start();
                }
            }
        });

        // Volume Adjusting Slider
        slider1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                double volume = slider1.getValue();
                // Using if/else since for some reason an error appears when sound reaches 0
                if (volume > 0) {
                    game.getCurrentMusic().setVolume(volume / 100);
                } else {
                    // Setting volume to 0 generates an error, so we use the next-best value
                    game.getCurrentMusic().setVolume(0.01f);
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

    // Getter for Slider; used for Volume consistency across levels
    public JSlider getSlider1() {
        return slider1;
    }
}