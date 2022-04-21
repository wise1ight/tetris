package org.teamseven.tetris.enums;

public enum ScreenSize {
    SMALL, MEDIUM, LARGE;

    public static ScreenSize toEnum(String str) {
        try {
            return valueOf(str);
        } catch (Exception e) {
            return MEDIUM;
        }
    }
}