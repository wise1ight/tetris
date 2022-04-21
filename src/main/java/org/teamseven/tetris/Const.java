package org.teamseven.tetris;

public class Const {

    public static final String SCENE_START_MENU = "startMenu";
    public static final String SCENE_SETTING = "SCENE_SETTING";

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

    public static String SCORE_ROOT = "score/";
    public static String SCORE_FILE = "score.csv";
    public static String DEFAULT_NAME = "AAA";

    public static int[] DOWN = new int[]{1, 0};
    public static int[] RIGHT = new int[]{0, 1};
    public static int[] LEFT = new int[]{0, -1};
    public static int[] PLACE = new int[]{0, 0};

}
