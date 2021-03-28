/**
 * @author Philippos Maximos Giavridis
 * @version Beta-3.0 (As in Milestone 3)
 */

package com.company.main;

import city.cs.engine.BodyImage;
import city.cs.engine.DebugViewer;
import city.cs.engine.SoundClip;
import com.company.levels.*;
import com.company.panels.MainMenu;
import com.company.panels.Overlay;
import com.company.world.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Main Class that the game is run from.
 *
 */
public class Game {

    // Create World
    private GameLevel world;

    // Create View
    private final MyView view;

    // Mouse Listener
    private MouseHandler mouseHandler;

    // Music
    private SoundClip currentMusic;
    private SoundClip level1Music;
    private SoundClip level3Music;

    // Controller
    Controller heroController;
    // gui is the overlay during the Game with the Volume, Pause/Unpause, etc.
    private final Overlay gui;

    // Panel and layout
    private final JPanel parentPanel = new JPanel();
    private final CardLayout layout = new CardLayout();

    // Menu Screen
    private final MainMenu menuPanel = new MainMenu(parentPanel, layout, this);

    // Control Variable (used in MainMenu class) for Music (to know when to loop(), pause(), or resume())
    private boolean firstStart = true;
    // Control Variable for Tutorial
    private boolean tutorial = false;

    /**
     * Sets default level to a {@link Level1} object.
     * Initialises Mouse-listeners and Key-Listeners, as well as the Frame in which the game takes place.
     * Utilises the CardLayout in order to differentiate between Main Menu screen and Game screen.
     */
    public Game() {

        // initialise level to level1
        world = new Level1(this);

        // View
        view = new MyView(world, 1200, 800, this);
        // view.setZoom(20);
        // view.setGridResolution(1);

        // Mouse Listener
        mouseHandler = new MouseHandler(view, world);
        view.addMouseListener(mouseHandler);

        // Keyboard Listener
        heroController = new Controller(world.getHero());
        view.addKeyListener(heroController);

        // Mouse Listener
        view.addMouseListener(new GiveFocus(view));

        // Create Frame
        JFrame frame = new JFrame("Spartan Hero");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel layout is CardLayout so we can switch from Game to Menu
        parentPanel.setLayout(layout);

        // Add children to parent Panel
        parentPanel.add(menuPanel, "Main Menu");
        parentPanel.add(view, "Spartan Game");

        // Add to Frame and set Default to Main Menu (on start-up)
        frame.add(parentPanel);
        layout.show(parentPanel, "Main Menu");

        // Add Bottom Overlay Menu
        gui = new Overlay(this, parentPanel, layout);
        view.add(gui.getmainPanel(), BorderLayout.NORTH);

        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);

        // Debugger
        // JFrame debugView = new DebugViewer(world, 800, 800);

        // Music Section, we only create 'Game' once, so it's fine to be in constructor
        try {
            level1Music = new SoundClip("data/music/lvl1track.wav");
            level3Music = new SoundClip("data/music/level3.wav");
            currentMusic = level1Music;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }

        world.start();
    }

    /** Main method of the class.
     *
     * @param args
     */
    public static void main(String[] args) {
        new Game();
    }

    /**
     * Updates the level after it is completed.
     * Responsible for the lateral movement through the levels.
     * Transfers the old stats to the new level, updates Listeners and Music.
     */
    // Method for level-Changing according to the current level.
    public void goToNextLevel() {
        if (world instanceof Level1) {

            world.stop();

            // Save old stats for the new Level
            int oldHealth = world.getHero().getHealth();
            int oldScore = world.getHero().getScore();
            int oldCheck = world.getHero().getCheckPoint();

            world = new Level2(this);

            //world now refers to the new level
            view.setWorld(world);

            // Reconfigure Listeners/Controllers for new Hero Object
            mouseHandler.updateListener(view, world);
            view.addMouseListener(new GiveFocus(view));

            // Update HeroController to new Hero
            heroController.updateHero(world.getHero());

            // After capturing old stats before creating new Level, we update the stats with this method
            transferStats(oldHealth, oldScore, oldCheck);

            world.start();
        } else if (world instanceof Level2) {
            world.stop();
            // Get Volume of previous track
            double currentVolume = gui.getSlider1().getValue();
            // Stop previous track
            currentMusic.stop();
            // Update Music field
            currentMusic = level3Music;
            currentMusic.loop();
            // Set Volume
            if (currentVolume > 0) {
                currentMusic.setVolume(currentVolume/100);
            } else {
                // Setting volume to 0 generates an error, so we use the next-best value
                currentMusic.setVolume(0.01f);
            }

            // Save old stats for the new Level
            int oldHealth = world.getHero().getHealth();
            int oldScore = world.getHero().getScore();
            int oldCheck = world.getHero().getCheckPoint();

            world = new Level3(this);

            //world now refers to the new level
            view.setWorld(world);

            // Reconfigure Listeners/Controllers for new Hero Object
            mouseHandler.updateListener(view, world);
            view.addMouseListener(new GiveFocus(view));

            // Update HeroController to new Hero
            heroController.updateHero(world.getHero());

            // After capturing old stats before creating new Level, we update the stats with this method
            transferStats(oldHealth, oldScore, oldCheck);

            // Tracker to simulate timer for Ball-Spawns
            Tracker tracker = new Tracker(this);
            world.addStepListener(tracker);

            world.start();
        } else if (world instanceof Level3) {
            world.stop();

            // Get Volume of previous track
            double currentVolume = gui.getSlider1().getValue();
            // Stop previous track
            currentMusic.stop();
            // Update Music field
            currentMusic = level3Music;
            currentMusic.loop();

            // Set Volume
            if (currentVolume > 0) {
                currentMusic.setVolume(currentVolume/100);
            } else {
                // Setting volume to 0 generates an error, so we use the next-best value
                currentMusic.setVolume(0.01f);
            }

            // Save old stats for the new Level
            int oldHealth = world.getHero().getHealth();
            int oldScore = world.getHero().getScore();
            int oldCheck = world.getHero().getCheckPoint();

            world = new Level4(this);

            //world now refers to the new level
            view.setWorld(world);

            // Reconfigure Listeners/Controllers for new Hero Object
            mouseHandler.updateListener(view, world);
            view.addMouseListener(new GiveFocus(view));

            // Update HeroController to new Hero
            heroController.updateHero(world.getHero());

            // After capturing old stats before creating new Level, we update the stats with this method
            transferStats(oldHealth, oldScore, oldCheck);

            world.start();
        }
    }

    /**
     * Transfers the stats from the previous level over to the next level.
     * @param oldHealth Health from previous level
     * @param oldScore Score from previous level
     * @param oldCheck Checkpoint variable which is either 0 or 1, according to whether the checkpoint in level 2 has been passed or not.
     */
    // Method to transfer stats from previous stage to new stage.
    public void transferStats(int oldHealth, int oldScore, int oldCheck) {
        // Transfer old stats to new Hero and add according images
        world.getHero().setHealth(oldHealth);
        world.getHero().setCheckPoint(oldCheck);
        if (oldHealth == 2) {
            world.getHeart().setHeartImage(new BodyImage("data/graphics/halfHeart.png", 4));
        } else if (oldHealth == 1) {
            world.getHeart().setHeartImage(new BodyImage("data/graphics/lastHeart.png", 4));
        }
        world.getHero().setScore(oldScore);
    }

    // Getters & Setters

    /** Getter for the Level the game is pointing to
     *
     * @return Current Level
     */
    public GameLevel getWorld() {
        return world;
    }

    /**
     * Setter for the Level the game is pointing to
     * @param world New level to be pointed to
     */
    public void setWorld(GameLevel world) {
        this.world = world;
    }

    /**
     * Getter for the current View
     * @return Current View
     */
    public MyView getView() {
        return view;
    }

    /**
     * Getter for the Hero's Key-listener
     * @return Current HeroController Object
     */
    public Controller getHeroController() {
        return heroController;
    }

    /**
     * Getter for the music currently playing
     * @return Current Music
     */
    public SoundClip getCurrentMusic() {
        return currentMusic;
    }

    /**
     * Setter for Current Music
     * @param newMusic New track to be played
     */
    public void setCurrentMusic (SoundClip newMusic) {
        this.currentMusic = newMusic;
    }

    /**
     * Getter for the music from level 1
     * @return Track for level 1
     */
    public SoundClip getLevel1Music() {
        return level1Music;
    }

    /**
     * Getter for the music from level 3
     * @return Track for level 3
     */
    public SoundClip getLevel3Music() {
        return level3Music;
    }

    /**
     * Used for Main Menu to Game transition, in order to be able to stop the Music and resume it.
     * @return Whether it is the first time the player hits Play in the Main Menu.
     */
    public boolean isFirstStart() {
        return firstStart;
    }

    /**
     * Setter for the FirstStart boolean variable
     * @param firstStart Whether it's the first time the Player hits Play in the Main Menu.
     */
    public void setFirstStart(boolean firstStart) {
        this.firstStart = firstStart;
    }

    /**
     * Control variable for whether the player is in the Tutorial.
     * @return Whether player is in the tutorial.
     */
    public boolean isTutorial() {
        return tutorial;
    }

    /**
     * Setter for the tutorial variable
     * @param tutorial Whether player is in the tutorial.
     */
    public void setTutorial(boolean tutorial) {
        this.tutorial = tutorial;
    }
}