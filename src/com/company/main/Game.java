package com.company.main;

import city.cs.engine.BodyImage;
import city.cs.engine.SoundClip;
import com.company.levels.GameLevel;
import com.company.levels.Level1;
import com.company.levels.Level2;
import com.company.levels.Level3;
import com.company.gui.menuPanel;
import com.company.world.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class Game {

    // Create World
    private GameLevel world;

    // Create View
    private final MyView view;

    // Music
    private SoundClip currentMusic;
    private SoundClip level1Music;
    private SoundClip level3Music;

    // Controller
    Controller HeroController;
    private menuPanel menu;

    public Game() {

        // initialise level to level1
        world = new Level1(this);

        // View
        view = new MyView(world, 1200, 800, this);
        // view.setZoom(20);
        // view.setGridResolution(1);

        // Mouse Listener
        view.addMouseListener(new MouseHandler(view, world));

        // Keyboard Listener
        HeroController = new Controller(world.getHero());
        view.addKeyListener(HeroController);

        // Mouse Listener
        view.addMouseListener(new GiveFocus(view));

        // Frame
        final JFrame frame = new JFrame("Spartan Hero");
        frame.add(view, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add Bottom Overlay Menu
        menu = new menuPanel(this);
        frame.add(menu.getmainPanel(), BorderLayout.SOUTH);

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
        try {
            level1Music = new SoundClip("data/music/lvl1track.wav");
            level3Music = new SoundClip("data/music/level3.wav");
            currentMusic = level1Music;
            currentMusic.loop();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }

        world.start();
    }

    public static void main(String[] args) {
        new Game();
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
            HeroController.updateHero(world.getHero());

            // After capturing old stats before creating new Level, we update the stats with this method
            transferStats(oldHealth, oldScore, oldCheck);

            // Change background image
            view.setBackground(new ImageIcon("data/graphics/fantasy.png").getImage());

            world.start();
        } else if (world instanceof Level2) {
            world.stop();
            // Get Volume of previous track
            double currentVolume = menu.getSlider1().getValue();
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
            HeroController.updateHero(world.getHero());

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
        return HeroController;
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
