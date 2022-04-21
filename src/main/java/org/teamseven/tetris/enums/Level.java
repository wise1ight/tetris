package org.teamseven.tetris.enums;

public enum Level {
    EASY(0),
    NORMAL(1),
    HARD(2);

    private final int code;

    Level(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}
