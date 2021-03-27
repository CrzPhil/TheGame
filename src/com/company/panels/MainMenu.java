package com.company.panels;

import com.company.main.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MainMenu extends JPanel implements ActionListener {
    // Inherited in Constructor, used for "Play" button
    private Game game;

    // Two parent-fields which we need to inherit in the constructor
    private final JPanel parent;
    private final CardLayout layout;

    // Buttons
    private final JButton play = new JButton("Play");
    private final JButton save = new JButton("Save");
    private final JButton load = new JButton("Load");
    private final JButton tutorial = new JButton("Tutorial");

    // Button Positions & Sizes
    private static final int BUTTON_LOC_X = 480;   // X-Coordinate
    private static final int BUTTON_LOC_Y = 300;   // Y-Coordinate
    private static final int BUTTON_SIZE_X = 240;  // Width
    private static final int BUTTON_SIZE_Y = 100;  // Height


    public MainMenu(JPanel parent, CardLayout layout, Game game) {
        // Inheritance
        this.parent = parent;
        this.layout = layout;
        this.game = game;

        // Set the Layout of this panel to BoxLayout in order to Stack buttons
        // this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setLayout(null);

        // Listeners
        play.addActionListener(this);
        save.addActionListener(this);
        load.addActionListener(this);
        tutorial.addActionListener(this);

        // Position Buttons
        play.setBounds(BUTTON_LOC_X, BUTTON_LOC_Y, BUTTON_SIZE_X, BUTTON_SIZE_Y);
        save.setBounds(BUTTON_LOC_X, BUTTON_LOC_Y + 110, BUTTON_SIZE_X, BUTTON_SIZE_Y);
        load.setBounds(BUTTON_LOC_X, BUTTON_LOC_Y + 220, BUTTON_SIZE_X, BUTTON_SIZE_Y);
        tutorial.setBounds(BUTTON_LOC_X, BUTTON_LOC_Y + 330, BUTTON_SIZE_X, BUTTON_SIZE_Y);

        // Fonts
        play.setFont(loadFont());
        save.setFont(loadFont());
        load.setFont(loadFont());
        tutorial.setFont(loadFont());


        // Add Buttons
        this.add(play);
        this.add(save);
        this.add(load);
        this.add(tutorial);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == play) {
            layout.show(parent, "Spartan Game");
            // Make sure the Music within the game continues where it left off after switching to Main Menu
            if (game.isFirstStart()) {
                // Hitting play starts music and switches to game screen
                game.getCurrentMusic().loop();
                game.setFirstStart(false);
            } else {
                game.getCurrentMusic().resume();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon("data/graphics/Banner.png").getImage(), 0, 0, 1200, 800, null);
    }

    // Create/Load Custom font (https://docs.oracle.com/javase/tutorial/2d/text/fonts.html#logical-fonts)
    public Font loadFont() {
        try {
            //Returned font is of pt size 1
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("data/font/arcadeclassic/arcadefont.TTF"));
            // User deriveFont and set size to 40
            return customFont.deriveFont(40f);

        } catch (IOException | FontFormatException e) {
            // Handle exception
        }
        return null;
    }


    // Getters

    public JButton getPlay() {
        return play;
    }

    public JPanel getParentPanel() {
        return parent;
    }

    public JButton getSave() {
        return save;
    }

    public JButton getLoad() {
        return load;
    }

    public JButton getTutorial() {
        return tutorial;
    }
}
