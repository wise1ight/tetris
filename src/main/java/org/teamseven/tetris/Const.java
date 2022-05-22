package org.teamseven.tetris;

import java.awt.*;

public class Const {

    public static final String APPLICATION_TITLE = "Tetris";
    /*
        Universal Values
         */
    public static final int BASE_FRAME_X = 450;
    public static final int BASE_FRAME_Y = 300;

    public static final int SMALL_INT =2;
    public static final int MEDIUM_INT =3;
    public static final int LARGE_INT =4;


    public static final int SCREEN_SMALL_RESOLUTION_X = BASE_FRAME_X * SMALL_INT;
    public static final int SCREEN_SMALL_RESOLUTION_Y = BASE_FRAME_Y * SMALL_INT;
    public static final int SCREEN_MEDIUM_RESOLUTION_X = BASE_FRAME_X * MEDIUM_INT;
    public static final int SCREEN_MEDIUM_RESOLUTION_Y = BASE_FRAME_Y * MEDIUM_INT;
    public static final int SCREEN_LARGE_RESOLUTION_X = BASE_FRAME_X * LARGE_INT;
    public static final int SCREEN_LARGE_RESOLUTION_Y = BASE_FRAME_Y * LARGE_INT;

    public static final int SCREEN_SMALL_BLOCK_FONT_SIZE = 20;
    public static final int SCREEN_MEDIUM_BLOCK_FONT_SIZE = 33;
    public static final int SCREEN_LARGE_BLOCK_FONT_SIZE = 38;

    public static final Color[] COLORS = {Color.WHITE, Color.MAGENTA, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.BLUE, Color.CYAN, Color.RED};

    public static final String PREF_SCREEN_SIZE_KEY = "SCREEN_SIZE";
    public static final String PREF_BTN_LEFT_KEY = "BTN_LEFT";
    public static final String PREF_BTN_RIGHT_KEY = "BTN_RIGHT";
    public static final String PREF_BTN_ROTATE_RIGHT_KEY = "BTN_ROTATE_RIGHT";
    public static final String PREF_BTN_HARD_DROP_KEY = "BTN_HARD_DROP";
    public static final String PREF_BTN_SOFT_DROP_KEY = "BTN_SOFT_DROP";
    public static final String PREF_BTN_PAUSE_KEY = "BTN_PAUSE";
    public static final String PREF_BTN_EXIT_KEY = "BTN_EXIT";
//new
    public static final String PREF_BTN_LEFT_ONE_KEY = "BTN_LEFT_ONE";
    public static final String PREF_BTN_LEFT_TWO_KEY = "BTN_LEFT_TWO";

    public static final String PREF_BTN_RIGHT_ONE_KEY = "BTN_RIGHT_ONE";
    public static final String PREF_BTN_RIGHT_TWO_KEY = "BTN_RIGHT_TWO";

    public static final String PREF_BTN_ROTATE_RIGHT_ONE_KEY = "BTN_ROTATE_RIGHT_ONE";
    public static final String PREF_BTN_ROTATE_RIGHT_TWO_KEY = "BTN_ROTATE_RIGHT_TWO";

    public static final String PREF_BTN_HARD_DROP_ONE_KEY = "BTN_HARD_DROP_ONE";
    public static final String PREF_BTN_HARD_DROP_TWO_KEY = "BTN_HARD_DROP_TWO";

    public static final String PREF_BTN_SOFT_DROP_ONE_KEY = "BTN_SOFT_DROP_ONE";
    public static final String PREF_BTN_SOFT_DROP_TWO_KEY = "BTN_SOFT_DROP_TWO";
//NEW ---- END ----
    public static final String PREF_COLOR_BLINDNESS_KEY = "COLOR_BLINDNESS";
    public static final String PREF_MODE_KEY = "MODE";

    public static final char BORDER_CHAR = 'X';

    public static int HEIGHT = 20;
    public static int WIDTH = 10;

    public static final String CURRENT_DIR = System.getProperty("user.dir");
    public static final String SCORE_ROOT = CURRENT_DIR + "/";
    public static final String SCORE_NORMAL_FILE = "normal.csv";
    public static final String SCORE_ITEM_FILE = "item.csv";
    public static final String DEFAULT_NAME = "AAA";

    public static int[] DOWN = new int[]{1, 0};
    public static int[] RIGHT = new int[]{0, 1};
    public static int[] LEFT = new int[]{0, -1};
    public static int[] PLACE = new int[]{0, 0};


    public final static int ONE_LINE = 1;
    public final static int TWO_LINES = 2;
    public final static int THREE_LINES = 3;
    public final static int FOUR_LINES = 4;

    public static int DEFAULT_SCORE = 100;

    public static final int INIT_DELAY = 1000;

}
