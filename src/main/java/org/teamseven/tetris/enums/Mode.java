package org.teamseven.tetris.enums;

public enum Mode {
    EASY(0),
    NORMAL(1),
    HARD(2);

    private final int code;

    Mode(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}
