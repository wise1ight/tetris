package org.teamseven.tetris.ui;

import org.teamseven.tetris.enums.ColorBlindnessType;
import org.teamseven.tetris.enums.ScreenSize;
import org.teamseven.tetris.handler.PreferencesHandler;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static org.teamseven.tetris.Const.*;

public class TetrisStyle {

    private static final Map<String, SimpleAttributeSet> styles;

    public static final Color[] COLORS = {Color.WHITE, Color.MAGENTA, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.BLUE, Color.CYAN, Color.RED};

    static {
        styles = new HashMap<>();

        generateStyles();
    }

    private static void generateStyles() {
        for(ScreenSize screenSize : ScreenSize.values()) {
            int fontSize = getFontSize();

            for(Color color : COLORS) {
                SimpleAttributeSet styleSet = new SimpleAttributeSet();
                StyleConstants.setFontSize(styleSet, fontSize);
                StyleConstants.setFontFamily(styleSet, "Courier");
                StyleConstants.setBold(styleSet, true);
                StyleConstants.setForeground(styleSet, color);
                StyleConstants.setAlignment(styleSet, StyleConstants.ALIGN_CENTER);

                styles.put(String.format("%s-%s-%s", screenSize, ColorBlindnessType.NONE, color), styleSet);
            }
        }
    }

    private static int getFontSize() {
        switch (PreferencesHandler.getScreenSize()) {
            case SMALL:
                return SCREEN_SMALL_BLOCK_FONT_SIZE;
            case LARGE:
                return SCREEN_LARGE_BLOCK_FONT_SIZE;
            default:
                return SCREEN_MEDIUM_BLOCK_FONT_SIZE;
        }
    }

    public static SimpleAttributeSet getStyle(Color color) {
        return styles.get(String.format("%s-%s-%s",
                PreferencesHandler.getScreenSize(), PreferencesHandler.getColorBlindnessType(), color));
    }

    public static int getNumberOfStyles() {
        return styles.size();
    }
}
