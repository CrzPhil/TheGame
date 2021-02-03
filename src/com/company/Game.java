package com.company;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import javax.swing.*;


public class Game {
    // Create World
    private MyWorld world;

    // Create View
    private MyView view;

    public Game() {
        // build the world
        world = new MyWorld();

        // View
        view = new MyView(world, 1200, 800);
        // view.setZoom(20);

        // Mouse Listener
        view.addMouseListener(new MouseHandler(view));

        // Keyboard Listener
        Controller HeroController = new Controller(world.getHero());
        view.addKeyListener(HeroController);

        view.addMouseListener(new GiveFocus(view));

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
