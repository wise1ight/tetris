package org.teamseven.tetris.handler;

import javax.swing.*;

import static org.teamseven.tetris.Const.WIDTH;
import static org.teamseven.tetris.util.GameHandlerUtil.*;

public class GameHandler {
    private boolean pause = false;
    private int blockCnt;
    private int erasedLines;
    private int totalErasedLines;
    private ScoreHandler scoreHandler = new ScoreHandler();
    private boolean itemMode;

    public void speedUp(Timer timer) {
        int delay = timer.getDelay();
        double nextDelay = basicTetrisSpeedRule(blockCnt);
        double alpha = PreferencesHandler.getMode().speedProb();

        if (checkErasedLines(erasedLines)) {
            int time = (int) (timer.getDelay() * (0.98 * erasedLines));
            timer.setDelay(time);
            erasedLines = 0;
        }
        if (checkBlockCnt(blockCnt)) {
            int time = (int) ((1 - alpha) * delay + alpha * nextDelay);
            scoreHandler.addAlphaScore();
            timer.setDelay(time);
        }
    }

    public int getTotalErasedLines() {
        return totalErasedLines;
    }

    public boolean isItemMode() {
        return itemMode;
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
