package org.teamseven.tetris;

public class Const {

    public static final String APPLICATION_TITLE = "Tetris";

    public static final int SCREEN_RESOLUTION_X = 1600;
    public static final int SCREEN_RESOLUTION_Y = 900;

    public static final String PREF_SCREEN_SIZE_KEY = "SCREEN_SIZE";
    public static final String PREF_BTN_LEFT_KEY = "BTN_LEFT";
    public static final String PREF_BTN_RIGHT_KEY = "BTN_RIGHT";
    public static final String PREF_BTN_ROTATE_RIGHT_KEY = "BTN_ROTATE_RIGHT";
    public static final String PREF_BTN_PAUSE_KEY = "BTN_PAUSE";
    public static final String PREF_COLOR_BLINDNESS_KEY = "COLOR_BLINDNESS";

    public enum ScreenSize {
        SMALL, MEDIUM, LARGE;

        public static ScreenSize toEnum (String str) {
            try {
                return valueOf(str);
            } catch (Exception e) {
                return MEDIUM;
            }
        }
    }

    public enum ColorBlindnessType {
        NONE, RED, GREEN, BLUE;

        public static ColorBlindnessType toEnum(String str) {
            try {
                return valueOf(str);
            } catch (Exception e) {
                return NONE;
            }
        }
    }

    public static int HEIGHT = 20;
    public static int WIDTH = 10;

    public static String currentDir = System.getProperty("user.dir");
    public static String SCORE_ROOT = currentDir + "./";
    public static String SCORE_FILE = "score.csv";
    public static String DEFAULT_NAME = "AAA";

    public static int[] DOWN = new int[]{1, 0};
    public static int[] RIGHT = new int[]{0, 1};
    public static int[] LEFT = new int[]{0, -1};
    public static int[] PLACE = new int[]{0, 0};


    public final static int ONE_LINE = 1;
    public final static int TWO_LINES = 2;
    public final static int THREE_LINES = 3;
    public final static int FOUR_LINES = 4;

    public static int DEFAULT_SCORE = 100;
}
