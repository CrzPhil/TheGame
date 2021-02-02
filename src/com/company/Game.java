package com.company;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.plaf.basic.BasicTreeUI;

public class Game {
    // Create World
    private World world;

    // Create View
    private MyView view;

    // Create Characters
    private MyShapes characters;

    public Game() {
        // build the world
        world = new MyWorld();

        // Characters
        characters = new MyShapes(world);

        // View
        view = new MyView(world, 1200, 800);

        // Mouse Listener
        view.addMouseListener(new MouseHandler(view));

        // Frame
        final JFrame frame = new JFrame("Basic World");
        frame.add(view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

        // Debugger
        //JFrame debugView = new DebugViewer(world, 800, 800);

        world.start();

    }



    public static void main(String[] args) {
        new Game();

    }

}
