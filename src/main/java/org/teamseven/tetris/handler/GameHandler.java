package org.teamseven.tetris.handler;

public class GameHandler {
    private boolean pause = false;
    private int blockCnt;
    private int score;
    private int erasedLines;

    public boolean checkBlockCnt() {
        if (blockCnt >= 10) {
            blockCnt = 0;
            return true;
        }
        return false;
    }

    public boolean checkErasedLines(int erasedLines) {
        return erasedLines > 0;
    }

    public int getErasedLines() {
        return erasedLines;
    }

    public void setErasedLines(int erasedLines) {
        this.erasedLines = erasedLines;
    }

    public void addBlockCnt() {
        blockCnt += 1;
    }

    public boolean isPaused() {
        return pause;
    }

    public void pause() {
        this.pause = true;
    }

    public void start() {
        this.pause = false;
    }
}
