package org.teamseven.tetris.ui.startmenu;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends BaseFrame {
    private JPanel scenes;
    private CardLayout cardLayout;
    private StartMenuPane startMenu;
    private JLayeredPane jl1;
    private CardSwitcher cardSwitcher;

    private int[] resolution = new int[2];           // frame resolution
    private int[] preferredResolution = new int[2];  // frame resolution - frame top border

    public MainFrame() {
        resolution[0] = 1200;
        resolution[1] = 600;
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
        jl1 = new JLayeredPane();
    }

    @Override
    public void setDesign() {
        // temporary scene (delete lator)
        jl1.setOpaque(true);
        JButton jButton1 = new JButton("Open");
        jButton1.setBackground(Color.green);
        jButton1.setBounds(175, 50, 150, 100);
        jl1.add(jButton1, JLayeredPane.DEFAULT_LAYER);
        JButton jButton2 = new JButton("Open");
        jButton2.setBackground(Color.BLUE);
        jButton2.setBounds(300, 100, 150, 100);
        jl1.add(jButton2, JLayeredPane.POPUP_LAYER);
        jl1.setPreferredSize(new Dimension(300, 310));
        jl1.add(new JLabel("test"), 300);
        jl1.add(new JButton("d"), 300);
        jl1.setBackground(Color.orange);

        //set scenes
        scenes.setPreferredSize(new Dimension(preferredResolution[0], preferredResolution[1]));
        scenes.add(startMenu, "startMenu");
        scenes.add(jl1, "jl1");
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

