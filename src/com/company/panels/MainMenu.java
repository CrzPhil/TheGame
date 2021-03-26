package com.company.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel implements ActionListener {
    // Two parent-fields which we need to inherit in the constructor
    private final JPanel parent;
    private final CardLayout layout;

    // Buttons
    private final JButton play = new JButton("Play");
    private final JButton save = new JButton("Save");
    private final JButton load = new JButton("Load");
    private final JButton tutorial = new JButton("Tutorial");

    public MainMenu(JPanel parent, CardLayout layout) {
        // Inheritance
        this.parent = parent;
        this.layout = layout;

        // Listeners
        play.addActionListener(this);
        save.addActionListener(this);
        load.addActionListener(this);
        tutorial.addActionListener(this);

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
        }
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
