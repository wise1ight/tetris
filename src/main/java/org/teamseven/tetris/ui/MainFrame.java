package org.teamseven.tetris.ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends BaseFrame {
    private JPanel scenes;
    private CardLayout cardLayout;
    private StartMenuPane startMenu;
    private TetrisPane tetris;
    private CardSwitcher cardSwitcher;

    private int[] resolution = new int[2];           // frame resolution
    private int[] preferredResolution = new int[2];  // frame resolution - frame top border

    public MainFrame() {
        resolution[0] = 1600;
        resolution[1] = 900;
        int[] frameBorderSize = new int[2];       // frame top border
        frameBorderSize[0] = this.getInsets().left + this.getInsets().right;
        frameBorderSize[1] = this.getInsets().top + this.getInsets().bottom;
        preferredResolution[0] = resolution[0] - frameBorderSize[0];
        preferredResolution[1] = resolution[1] - frameBorderSize[1];

        this.setFrame("Tetris");
    }

    @Override
    public void setComp() {
        cardLayout = new CardLayout();
        scenes = new JPanel(cardLayout);
        cardSwitcher = new CardSwitcher(scenes, cardLayout);
        startMenu = new StartMenuPane(cardSwitcher, preferredResolution);
        tetris = new TetrisPane(cardSwitcher, preferredResolution);
    }

    @Override
    public void setDesign() {

        //set scenes
        scenes.setPreferredSize(new Dimension(preferredResolution[0], preferredResolution[1]));
        scenes.add(startMenu, "startMenu");
        scenes.add(tetris, "tetris");
        cardSwitcher.switchCard("startMenu");

        //set MainFrame
        this.add(scenes);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    @Override
    public void setAction() {
    }

}

