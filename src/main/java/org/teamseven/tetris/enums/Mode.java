package org.teamseven.tetris.enums;

public enum Mode {
    EASY(0, 1.2, 1 / 1.2),
    NORMAL(1, 1, 1),
    HARD(2, 1 / 1.2, 1.2);

    private final int code;
    private final double blockProb;
    private final double additionalSpeed;

    Mode(int code, double blockProb, double additionalSpeed) {
        this.code = code;
        this.blockProb = blockProb;
        this.additionalSpeed = additionalSpeed;
    }

    public int code() {
        return code;
    }

    public double blockProb() {
        return blockProb;
    }

    public double speedProb() {
        return additionalSpeed;
    }
}
