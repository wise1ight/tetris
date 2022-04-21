package org.teamseven.tetris;

import org.teamseven.tetris.ui.MainFrame;

import javax.swing.*;
import java.awt.*;

public class Pipeline {

    private static JFrame mainFrame = null;

    public static void runApp() {
        mainFrame = new MainFrame();
    }

    public static JFrame getMainFrame() {
        return Pipeline.mainFrame;
    }

    public static void replacePane(Component component) {
        Container container = getMainFrame().getContentPane();
        container.removeAll();
        container.add(component);
        container.revalidate();
        container.repaint();
    }
}
