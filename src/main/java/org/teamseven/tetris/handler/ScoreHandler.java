package org.teamseven.tetris.handler;

import static org.teamseven.tetris.Const.DEFAULT_SCORE;
import static org.teamseven.tetris.util.GameHandlerUtil.toScore;

public class ScoreHandler {

    private int score;
    private int alphaScore = DEFAULT_SCORE;

    public void addScoreByEraseLine(int erasedLines) {
        score += toScore(erasedLines);
    }

    public void addScoreByMove(int moveCnt) {
        score += alphaScore * moveCnt;
    }

    public void addAlphaScore() {
        this.alphaScore = (int) (alphaScore * 1.1);
    }

    public int getScore() {
        return score;
    }
}
