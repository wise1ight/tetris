package org.teamseven.tetris;

public class Const {

    public static final String SCENE_START_MENU = "startMenu";
    public static final String SCENE_SETTING = "SCENE_SETTING";

    public static final String PREF_SCREEN_SIZE_KEY = "SCREEN_SIZE";
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
}
