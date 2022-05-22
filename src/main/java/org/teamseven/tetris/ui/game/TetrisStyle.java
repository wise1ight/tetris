package org.teamseven.tetris.ui.game;

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

    static {
        styles = new HashMap<>();

        generateStyles();
    }

    private static void generateStyles() {
        for(ScreenSize screenSize : ScreenSize.values()) {
            int fontSize = getFontSize();
            for(ColorBlindnessType colorBlindnessType : ColorBlindnessType.values()) {
                for(Color color : COLORS) {
                    SimpleAttributeSet styleSet = new SimpleAttributeSet();
                    StyleConstants.setFontSize(styleSet, fontSize);
                    StyleConstants.setFontFamily(styleSet, "courier new");
                    StyleConstants.setBold(styleSet, true);
                    StyleConstants.setForeground(styleSet, getColorForBlindness(colorBlindnessType, color));
                    StyleConstants.setAlignment(styleSet, StyleConstants.ALIGN_CENTER);

                    styles.put(String.format("%s-%s-%s", screenSize, colorBlindnessType, color), styleSet);
                }
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

    public static Color getColorForBlindness(ColorBlindnessType colorBlindnessType, Color color) {
        switch (colorBlindnessType) {
            case BLINDNESS: {
                if (color.equals(Color.MAGENTA))
                    return new Color(204,121,167);
                else if (color.equals(Color.GREEN))
                    return new Color(0,158,115);
                else if (color.equals(Color.YELLOW))
                    return new Color(240,228,66);
                else if (color.equals(Color.ORANGE))
                    return new Color(230,159,0);
                else if (color.equals(Color.BLUE))
                    return new Color(0,114,178);
                else if (color.equals(Color.CYAN))
                    return new Color(86,180,233);
                else if (color.equals(Color.RED))
                    return new Color(213,94,0);
                else
                    return color;
            }
            default:
                return color;
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
