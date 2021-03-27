package com.company.main;

import com.company.levels.*;
import com.company.world.GiveFocus;
import com.company.world.Tracker;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GameSaverLoader {

    public static void save(GameLevel level, String filename) throws IOException {
        boolean append = true;
        FileWriter writer = null;
        try {
            writer = new FileWriter(filename);
            writer.write(level.getLevelName() + "," + level.getHero().getScore() + "," + level.getHero().getHealth() + ","
                    + level.getHero().getPosition().x + ","
                    + level.getHero().getPosition().y + "," + level.getHero().getCheckPoint() + "\n");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
    public static GameLevel load(String filename, Game game) throws IOException {
        FileReader fr = null;
        BufferedReader reader = null;
        try {
            String levelName = "";
            int heroScore = 0;
            int heroHealth = 3;
            float posX = 0;
            float posY = 0;
            int checkPoint = 0;

            System.out.println("Reading " + filename + " ...");
            fr = new FileReader(filename);
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                // file is assumed to contain one name, score pair per line
                String[] tokens = line.split(",");
                levelName = tokens[0];
                heroScore = Integer.parseInt(tokens[1]);
                heroHealth = Integer.parseInt(tokens[2]);
                posX = Float.parseFloat(tokens[3]);
                posY = Float.parseFloat(tokens[4]);
                checkPoint = Integer.parseInt(tokens[5]);
                System.out.println("Name: " + levelName + ", Score: " + heroScore + ", Health: " + heroHealth +
                        ", X-Position: " + posX + ", Y-Position: " + posY + ", Checkpoint: " + checkPoint);
                line = reader.readLine();
            }
            System.out.println("...done.");

            // Switch statement for all the levels
            switch (levelName) {
                case "Level1" -> {
                    // Stop the default world and update Level & View
                    game.getWorld().stop();
                    game.setWorld(new Level1(game));

                    fixWorld(game, heroHealth, heroScore, posX, posY, checkPoint);

                    // Stops current Track and resets to first Track.
                    game.getCurrentMusic().stop();
                    game.setCurrentMusic(game.getLevel1Music());
                    game.getCurrentMusic().loop();

                    game.getWorld().start();

                    break;
                }
                case "Level2" -> {
                    // Stop the default world and update Level & View
                    game.getWorld().stop();
                    game.setWorld(new Level2(game));

                    fixWorld(game, heroHealth, heroScore, posX, posY, checkPoint);

                    // Stops current Track and resets to first Track.
                    game.getCurrentMusic().stop();
                    game.setCurrentMusic(game.getLevel1Music());
                    game.getCurrentMusic().loop();

                    game.getWorld().start();

                    break;
                }
                case "Level3" -> {
                    // Stop the default world and update Level & View
                    game.getWorld().stop();
                    game.setWorld(new Level3(game));

                    fixWorld(game, heroHealth, heroScore, posX, posY, checkPoint);

                    // Stops current Track and resets to first Track.
                    game.getCurrentMusic().stop();
                    game.setCurrentMusic(game.getLevel3Music());
                    game.getCurrentMusic().loop();

                    // Tracker to simulate timer for Ball-Spawns
                    Tracker tracker = new Tracker(game.getView(), game);
                    game.getWorld().addStepListener(tracker);

                    game.getWorld().start();

                    break;
                }
                case "Level4" -> {
                    // Stop the default world and update Level & View
                    game.getWorld().stop();
                    game.setWorld(new Level4(game));

                    fixWorld(game, heroHealth, heroScore, posX, posY, checkPoint);

                    // Stops current Track and sets to level Music.
                    game.getCurrentMusic().stop();
                    game.setCurrentMusic(game.getLevel3Music());
                    game.getCurrentMusic().loop();

                    game.getWorld().start();

                    break;
                }
                default -> {
                    break;
                }
            }

        } finally {
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
        return null;
    }

    private static void fixWorld(Game game, int heroHealth, int heroScore, float posX, float posY, int checkPoint) {
        // Add all listeners
        game.getView().setWorld(game.getWorld());
        game.getView().addMouseListener(new MouseHandler(game.getView(), game.getWorld()));
        game.getView().addMouseListener(new GiveFocus(game.getView()));
        game.getHeroController().updateHero(game.getWorld().getHero());

        // Set stats to saved stats
        game.getWorld().getHero().setHealth(heroHealth);
        game.getWorld().getHero().setScore(heroScore);
        game.getWorld().getHero().setCheckPoint(checkPoint);

        // Update Life overlay
        game.getWorld().getHeart().updateLife();

        // Set Hero position
        game.getWorld().getHero().setPosition(new Vec2(posX, posY));
    }
}
