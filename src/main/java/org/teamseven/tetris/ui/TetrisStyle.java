package org.teamseven.tetris.ui;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;

public class TetrisStyle {

    public static final SimpleAttributeSet DEFAULT_STYLE_SET;
    public static final SimpleAttributeSet MAGENTA_STYLE_SET;
    public static final SimpleAttributeSet GREEN_STYLE_SET;
    public static final SimpleAttributeSet YELLOW_STYLE_SET;

    static {
        DEFAULT_STYLE_SET = new SimpleAttributeSet();
        StyleConstants.setFontSize(DEFAULT_STYLE_SET, 18);
        StyleConstants.setFontFamily(DEFAULT_STYLE_SET, "Courier");
        StyleConstants.setBold(DEFAULT_STYLE_SET, true);
        StyleConstants.setForeground(DEFAULT_STYLE_SET, Color.WHITE);
        StyleConstants.setAlignment(DEFAULT_STYLE_SET, StyleConstants.ALIGN_CENTER);

        MAGENTA_STYLE_SET = new SimpleAttributeSet();
        StyleConstants.setFontSize(MAGENTA_STYLE_SET, 18);
        StyleConstants.setFontFamily(MAGENTA_STYLE_SET, "Courier");
        StyleConstants.setBold(MAGENTA_STYLE_SET, true);
        StyleConstants.setForeground(MAGENTA_STYLE_SET, Color.MAGENTA);
        StyleConstants.setAlignment(MAGENTA_STYLE_SET, StyleConstants.ALIGN_CENTER);

        GREEN_STYLE_SET = new SimpleAttributeSet();
        StyleConstants.setFontSize(GREEN_STYLE_SET, 18);
        StyleConstants.setFontFamily(GREEN_STYLE_SET, "Courier");
        StyleConstants.setBold(GREEN_STYLE_SET, true);
        StyleConstants.setForeground(GREEN_STYLE_SET, Color.GREEN);
        StyleConstants.setAlignment(GREEN_STYLE_SET, StyleConstants.ALIGN_CENTER);

        YELLOW_STYLE_SET = new SimpleAttributeSet();
        StyleConstants.setFontSize(YELLOW_STYLE_SET, 18);
        StyleConstants.setFontFamily(YELLOW_STYLE_SET, "Courier");
        StyleConstants.setBold(YELLOW_STYLE_SET, true);
        StyleConstants.setForeground(YELLOW_STYLE_SET, Color.YELLOW);
        StyleConstants.setAlignment(YELLOW_STYLE_SET, StyleConstants.ALIGN_CENTER);
    }
}