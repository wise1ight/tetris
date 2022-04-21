package org.teamseven.tetris.enums;

public enum ColorBlindnessType {
    NONE, BLINDNESS;

    public static ColorBlindnessType toEnum(String str) {
        try {
            return valueOf(str);
        } catch (Exception e) {
            return NONE;
        }
    }
}