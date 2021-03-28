package com.company.panels;

import city.cs.engine.SoundClip;
import com.company.levels.GameLevel;
import com.company.levels.Level1;
import com.company.levels.Tutorial;
import com.company.main.Game;
import com.company.main.GameSaverLoader;
import com.company.main.MouseHandler;
import com.company.world.GiveFocus;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Displays Main Menu Screen with PLAY/SAVE/LOAD/TUTORIAL Options.
 * Changes on button-press with the actual Game-Screen through useage of CardLayout.
 * Belongs to Parent Panel created in {@link Game} class.
 */
public class MainMenu extends JPanel implements ActionListener {
    // Inherited in Constructor, used for "Play" button
    private final Game game;

    // Jingle
    private static SoundClip saved;
    private static SoundClip fail;

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

    /**
     * Adds and positions buttons on the Panel.
     * Styles them with custom font and size.
     * @param parent Parent Panel from the {@link Game} class.
     * @param layout CardLayout used
     * @param game Current Game that it is pointing to.
     */
    public MainMenu(JPanel parent, CardLayout layout, Game game) {
        // Inheritance
        this.parent = parent;
        this.layout = layout;
        this.game = game;

        // Set layout to null so we can manually move around Objects
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

    static {
        try {
            saved = new SoundClip("data/music/save.wav");
            fail = new SoundClip("data/music/wrong.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    /**
     * Listener for Button-Presses.
     * Play Switches to the Game-View and starts the music.
     * If the tutorial was played before hitting Play for the first time, Music is started, otherwise, it is continued.
     * Save calls the GameSaverLoader.save() method and saves the current Game-State in a save file.
     * Load Loads the Contents from that file and lets the player continue from there.
     * Tutorial is a Guide-Level, which introduces the player to controls and movement.
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        // Start/Continue Game
        if (source == play) {
            layout.show(parent, "Spartan Game");
            if (!game.isTutorial()) {
                // Make sure the Music within the game continues where it left off after switching to Main Menu
                if (game.isFirstStart()) {
                    // Hitting play starts music and switches to game screen
                    game.getCurrentMusic().loop();
                    game.setFirstStart(false);
                } else {
                    game.getCurrentMusic().resume();
                    // Resume Gameplay when exiting Menu
                    game.getWorld().start();
                }
            } else {
                game.getWorld().stop();

                game.setWorld(new Level1(game));
                game.setTutorial(false);

                Overlay.worldUpdater(game);

                // Background Reset
                game.getView().setBackground(new ImageIcon("data/graphics/background.png").getImage());

                // Stops current Track and resets to first Track.
                game.getCurrentMusic().stop();
                game.setCurrentMusic(game.getLevel1Music());
                game.getCurrentMusic().loop();

                // Start
                game.getWorld().start();
            }
        }
        // Save Current state of Game
        else if (source == save) {
            try {
                GameSaverLoader.save(game.getWorld(), "save");
                saved.play();
            } catch (IOException ioException) {
                // play a jingle to indicate failure
                fail.play();
                ioException.printStackTrace();
            }
        }
        // Load saved state
        else if (source == load) {
            try {
                GameSaverLoader.load("save", game);
                layout.show(parent, "Spartan Game");
            } catch (IOException ioException) {
                // play a jingle to indicate failure
                fail.play();
                ioException.printStackTrace();
            }
        } else if (source == tutorial) {
            layout.show(parent, "Spartan Game");

            // Switch mode to tutorial to avoid complications when switching to normal gameplay
            game.setTutorial(true);

            // Save previous state
            GameLevel preTutorial = game.getWorld();

            // Interrupt Current Default World
            game.getWorld().stop();

            game.setWorld(new Tutorial(game));

            Overlay.worldUpdater(game);


            game.getWorld().start();
        }
    }

    /**
     * Used to Style the Main Menu with a Banner Picture.
     * @param g Used for super()
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon("data/graphics/Banner.png").getImage(), 0, 0, 1200, 800, null);
    }

    // Create/Load Custom font (https://docs.oracle.com/javase/tutorial/2d/text/fonts.html#logical-fonts)

    /**
     * Loads a custom Pixel-Art font to add more flair to the Main Menu.
     * @return New custom Font.
     */
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

    /**
     * Getter for the Play Button
     * @return Play Button Object
     */
    public JButton getPlay() {
        return play;
    }

    /**
     * Getter for the Parent Panel
     * @return Parent
     */
    public JPanel getParentPanel() {
        return parent;
    }

    /**
     * Getter for the Save Button
     * @return Save Object
     */
    public JButton getSave() {
        return save;
    }

    /**
     * Getter for the Load Button
     * @return Load Object
     */
    public JButton getLoad() {
        return load;
    }

    /**
     * Getter for the Tutorial Button
     * @return Tutorial Object
     */
    public JButton getTutorial() {
        return tutorial;
    }
}
