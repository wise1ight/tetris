package org.teamseven.tetris;

import org.teamseven.tetris.enums.ScreenSize;
import org.teamseven.tetris.handler.PreferencesHandler;
import org.teamseven.tetris.ui.MainFrame;

import javax.swing.*;
import java.awt.*;

import static org.teamseven.tetris.Const.*;

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
        container.requestFocus();
    }

    public static int getSizeInt(){
        return PreferencesHandler.getSizeInt();
    }

    public static int getScreenResolutionX() {
        switch (PreferencesHandler.getScreenSize()) {
            case SMALL:
                return SCREEN_SMALL_RESOLUTION_X;
            case LARGE:
                return SCREEN_LARGE_RESOLUTION_X;
            default:
                return SCREEN_MEDIUM_RESOLUTION_X;
        }
    }

    public static int getScreenResolutionY() {
        switch (PreferencesHandler.getScreenSize()) {
            case SMALL:
                return SCREEN_SMALL_RESOLUTION_Y;
            case LARGE:
                return SCREEN_LARGE_RESOLUTION_Y;
            default:
                return SCREEN_MEDIUM_RESOLUTION_Y;
        }
    }

    public static void changeScreenSize(ScreenSize screenSize) {
        getMainFrame().setSize(getScreenResolutionX(), getScreenResolutionY());
    }
}
