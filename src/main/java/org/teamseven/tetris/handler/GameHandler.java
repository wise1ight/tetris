package org.teamseven.tetris.handler;

import javax.swing.*;

import static org.teamseven.tetris.util.GameHandlerUtil.*;

public class GameHandler {
    private boolean pause = false;
    private int blockCnt;
    private int erasedLines;
    private int totalErasedLines;
    private ScoreHandler scoreHandler = new ScoreHandler();

    public void speedUp(Timer timer) {
        if (checkBlockCnt(blockCnt)) {
            scoreHandler.addAlphaScore();
            int time = (int) (Math.pow(0.8 - ((getLevel(blockCnt) - 1) * 0.007), getLevel(blockCnt) - 1) * 1000 * Math.pow(0.99, totalErasedLines));
            System.out.println("time1 = " + time);
            timer.setDelay(time);
        }
        if (checkErasedLines(erasedLines)) {
            int delay = timer.getDelay();
            int time = (int)(delay * Math.pow(0.99, erasedLines));
            timer.setDelay(time);
            erasedLines = 0;
            System.out.println("time2 = " + time);
        }
    }

    public void addScoreByMove(int cnt) {
        scoreHandler.addScoreByMove(cnt);
    }

    public void addScoreByEraseLine() {
        scoreHandler.addScoreByEraseLine(erasedLines);
    }

    public int getScore() {
        return scoreHandler.getScore();
    }

    public void setErasedLines(int erasedLines) {
        this.totalErasedLines += erasedLines;
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
