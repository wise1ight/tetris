package org.teamseven.tetris;

import org.teamseven.tetris.ui.MainFrame;

import javax.swing.*;

public class Pipeline {

    private static JFrame mainFrame = null;

    public static void runApp() {
        mainFrame = new MainFrame();
    }

    public static JFrame getMainFrame() {
        return Pipeline.mainFrame;
    }
}
