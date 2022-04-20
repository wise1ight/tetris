package org.teamseven.tetris.handler;

import static org.teamseven.tetris.util.GameHandlerUtil.toScore;

public class ScoreHandler {

    private int score;
    private int alpha;

    public void addScoreByEraseLine(int erasedLines) {
        score += toScore(erasedLines);
    }

    public void addScoreByMove(int moveCnt) {
        score += alpha * moveCnt;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public int getScore() {
        return score;
    }
}
