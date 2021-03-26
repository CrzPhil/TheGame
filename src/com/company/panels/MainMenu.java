package com.company.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel implements ActionListener {
    // Two parent-fields which we need to inherit in the constructor
    private JPanel parent;
    private CardLayout layout;

    // Buttons
    private JButton play = new JButton("play");
    private JButton settings = new JButton("settings");
    private JButton exit = new JButton("exit");

    public MainMenu(JPanel parent, CardLayout layout) {
        // Inheritance
        this.parent = parent;
        this.layout = layout;

        // Listeners
        play.addActionListener(this);
        settings.addActionListener(this);
        exit.addActionListener(this);

        // Add Buttons
        this.add(play);
        this.add(settings);
        this.add(exit);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == play) {
            layout.show(parent, "Spartan Game");
        } else if (source == exit) {
            System.out.println("Exit");
        }
    }

    // Getters

    public JButton getPlay() {
        return play;
    }

    public JButton getSettings() {
        return settings;
    }

    public JButton getExit() {
        return exit;
    }
}
