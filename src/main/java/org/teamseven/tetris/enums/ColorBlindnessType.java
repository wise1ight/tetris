package org.teamseven.tetris.enums;

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