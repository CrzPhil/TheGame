package com.company.main;

import city.cs.engine.BodyImage;
import city.cs.engine.SoundClip;
import com.company.levels.*;
import com.company.panels.Overlay;
import com.company.world.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Game extends JFrame implements ActionListener {

    // Create World
    private GameLevel world;

    // Create View
    private final MyView view;

    // Create Frame
    private final JFrame frame = new JFrame("Spartan Hero");

    // Music
    private SoundClip currentMusic;
    private SoundClip level1Music;
    private SoundClip level3Music;

    // Controller
    Controller heroController;
    // menu is the overlay during the Game with the Volume, Pause/Unpause, etc. Settings.
    private Overlay gui;

    // Panels
    private final JPanel parentPanel = new JPanel();
    // Menu Screen
    private JPanel menuPanel = new JPanel();

    // Buttons
    JButton play = new JButton("play");
    JButton settings = new JButton("settings");
    JButton exit = new JButton("exit");
    JButton mainMenu = new JButton("main menu");

    private final CardLayout layout = new CardLayout();


    public Game() {

        // initialise level to level1
        world = new Level4(this);

        // View
        view = new MyView(world, 1200, 800, this);
        // view.setZoom(20);
        // view.setGridResolution(1);

        // Mouse Listener
        view.addMouseListener(new MouseHandler(view, world));

        // Keyboard Listener
        heroController = new Controller(world.getHero());
        view.addKeyListener(heroController);

        // Mouse Listener
        view.addMouseListener(new GiveFocus(view));

        // Exit frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel layout is CardLayout so we can switch from Game to Menu
        parentPanel.setLayout(layout);
        createMenu();
        frame.add(parentPanel);
        layout.show(parentPanel, "Main Menu");

        // Add Bottom Overlay Menu
        gui = new Overlay(this);
        view.add(gui.getmainPanel(), BorderLayout.SOUTH);

        frame.setLocationByPlatform(true);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

        // (ONLY FOR TESTING LEVEL 3) Tracker to simulate timer for Ball-Spawns (REMOVE WHEN PLAYING WHOLE GAME)
        /*Tracker tracker = new Tracker(view, this);
        world.addStepListener(tracker);*/

        // Debugger
        // JFrame debugView = new DebugViewer(world, 800, 800);

        // Music Section, we only create 'Game' once, so it's fine to be in constructor
        /*try {
            level1Music = new SoundClip("data/music/lvl1track.wav");
            level3Music = new SoundClip("data/music/level3.wav");
            currentMusic = level1Music;
            currentMusic.loop();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }*/

        world.start();
    }

    public static void main(String[] args) {
        new Game();
    }

    private void createMenu() {
        // Listeners
        play.addActionListener(this);
        settings.addActionListener(this);
        exit.addActionListener(this);
        mainMenu.addActionListener(this);

        // Menu Buttons
        menuPanel.add(play);
        menuPanel.add(settings);
        menuPanel.add(exit);

        // Game GUI
        view.add(mainMenu);

        // Add to parent Panel
        parentPanel.add(menuPanel, "Main Menu");
        parentPanel.add(view, "Spartan Game");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == play) {
            layout.show(parentPanel, "Spartan Game");
        } else if (source == mainMenu) {
            layout.show(parentPanel, "Main Menu");
        } else if (source == exit) {
            System.out.println("Exit");
        }
    }

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
            view.addMouseListener(new MouseHandler(view, world));
            view.addMouseListener(new GiveFocus(view));

            // Update HeroController to new Hero
            heroController.updateHero(world.getHero());

            // After capturing old stats before creating new Level, we update the stats with this method
            transferStats(oldHealth, oldScore, oldCheck);

            // Change background image
            view.setBackground(new ImageIcon("data/graphics/fantasy.png").getImage());

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
            view.addMouseListener(new MouseHandler(view, world));
            view.addMouseListener(new GiveFocus(view));

            // Update HeroController to new Hero
            heroController.updateHero(world.getHero());

            // After capturing old stats before creating new Level, we update the stats with this method
            transferStats(oldHealth, oldScore, oldCheck);

            // Change background image
            view.setBackground(new ImageIcon("data/graphics/shroomsbckg.png").getImage());

            // Tracker to simulate timer for Ball-Spawns
            Tracker tracker = new Tracker(view, this);
            world.addStepListener(tracker);

            world.start();
        }
    }

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
    public GameLevel getWorld() {
        return world;
    }

    public void setWorld(GameLevel world) {
        this.world = world;
    }

    public MyView getView() {
        return view;
    }

    public Controller getHeroController() {
        return heroController;
    }

    public SoundClip getCurrentMusic() {
        return currentMusic;
    }

    public void setCurrentMusic (SoundClip newMusic) {
        this.currentMusic = newMusic;
    }

    public SoundClip getLevel1Music() {
        return level1Music;
    }

    public SoundClip getLevel3Music() {
        return level3Music;
    }
}