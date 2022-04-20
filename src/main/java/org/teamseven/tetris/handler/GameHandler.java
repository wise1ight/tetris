package org.teamseven.tetris.handler;

import javax.swing.*;

import static org.teamseven.tetris.util.GameHandlerUtil.*;

public class GameHandler {
    private boolean pause = false;
    private int blockCnt;
    private int erasedLines;
    private ScoreHandler scoreHandler = new ScoreHandler();

    public void speedUp(Timer timer) {
        int delay = timer.getDelay();
        if (checkBlockCnt(blockCnt)) {
            timer.setDelay((int)(delay * 0.9));
        }
        if (checkErasedLines(erasedLines)) {
            timer.setDelay((int)(delay * Math.pow(0.99, erasedLines)));
            erasedLines = 0;
        }
    }

    public void addScoreByMove(int cnt) {
        scoreHandler.addScoreByMove(cnt);
    }

    public void addScoreByEraseLine() {
        scoreHandler.addScoreByEraseLine(erasedLines);
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
