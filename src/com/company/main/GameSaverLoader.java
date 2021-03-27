package com.company.main;

import com.company.levels.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GameSaverLoader {
    static void save(GameLevel level, String filename) throws IOException {
        boolean append = true;
        FileWriter writer = null;
        try {
            writer = new FileWriter(filename);
            writer.write(level.getLevelName() + "," + level.getHero().getScore() + "," + level.getHero().getHealth() + "\n");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
    static GameLevel load(String filename, Game game) throws IOException {
        FileReader fr = null;
        BufferedReader reader = null;
        try {
            String levelName = "";
            int heroScore = 0;
            int heroHealth = 3;

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
                System.out.println("Name: " + levelName + ", Score: " + heroScore + "Health: " + heroHealth);
                line = reader.readLine();
            }
            System.out.println("...done.");

            // Switch statement for all the levels
            switch (levelName) {
                case "Level1" -> {
                    GameLevel level = new Level1(game);
                    level.getHero().setHealth(heroHealth);
                    level.getHero().setScore(heroScore);
                    game.setWorld(level);
                    return level;
                }
                case "Level2" -> {
                    GameLevel level = new Level2(game);
                    level.getHero().setHealth(heroHealth);
                    level.getHero().setScore(heroScore);
                    game.setWorld(level);
                    return level;
                }
                case "Level3" -> {
                    GameLevel level = new Level3(game);
                    level.getHero().setHealth(heroHealth);
                    level.getHero().setScore(heroScore);
                    game.setWorld(level);
                    return level;
                }
                case "Level4" -> {
                    GameLevel level = new Level4(game);
                    level.getHero().setHealth(heroHealth);
                    level.getHero().setScore(heroScore);
                    game.setWorld(level);
                    return level;
                }
                default -> {
                    return null;
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
    }
}
