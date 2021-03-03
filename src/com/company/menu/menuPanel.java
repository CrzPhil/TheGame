package com.company.menu;

import com.company.main.Game;

import javax.swing.*;
import java.awt.*;

public class menuPanel extends JPanel{
    private JButton menuButton;
    private JButton pauseButton;
    private JButton restartButton;
    private JPanel mainPanel;
    private Game game;

    public menuPanel(Game game) {
        this.game = game;
    }

    public JPanel getmainPanel() {
        return mainPanel;
    }
}
