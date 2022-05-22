package org.teamseven.tetris.handler;

import lombok.Getter;
import org.teamseven.tetris.Board.GameBoard;
import org.teamseven.tetris.Const;
import org.teamseven.tetris.block.Block;
import org.teamseven.tetris.block.CurrBlock;
import org.teamseven.tetris.block.IBlock;
import org.teamseven.tetris.block.UnitBlock;
import org.teamseven.tetris.factory.BlockFactory;

import javax.swing.*;

import java.awt.*;

import static org.teamseven.tetris.Const.DOWN;
import static org.teamseven.tetris.util.BoardUtil.isFilled;
import static org.teamseven.tetris.util.GameHandlerUtil.*;


@Getter
public class GameHandler {
    private Timer timer;
    protected boolean pause = false;
    protected int blockCnt;
    protected int erasedLines;
    protected int totalErasedLines;
    protected ScoreHandler scoreHandler = new ScoreHandler();

    protected CurrBlock curr;
    protected Block nextBlock;
    protected GameBoard board;

    private boolean animating;

    public GameHandler() {
        board = new GameBoard();
        curr = new CurrBlock();
        nextBlock = BlockFactory.blockGenerator("random").generate();

        board.placeBlock(curr);
        addBlockCnt();
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public boolean playing() {
        if (curr.isStopped(board, nextBlock)) {
            return nextTurn();
        } else {
            int cnt = curr.move(board, DOWN);
            addScoreByMove(cnt);
            return true;
        }
    }

    protected boolean nextTurn() {
        if (animating()) {
            return true;
        }
        setErasedLines(board.eraseLines());
        addScoreByEraseLine();

        curr.newBlock(nextBlock);
        addBlockCnt();

        if (isFinished()) {
            return false;
        }

        speedUp();
        nextBlock = BlockFactory.blockGenerator("random").generate();
        board.placeBlock(curr);
        return true;
    }

    protected boolean isFinished() {
        return !curr.canMove(board);
    }

    protected boolean animating() {
        if (!animating) {
            for (int j = 0; j < Const.HEIGHT; j++) {
                if (isFilled(board.getBoard()[j])) {
                    for (int i = 0; i < Const.WIDTH; i++) {
                        board.getBoard()[j][i].setColor(Color.WHITE);
                    }
                    animating = true;
                }
            }
            return animating;
        } else {
            animating = false;
        }
        return false;
    }

    protected void speedUp() {
        int delay = timer.getDelay();
        double nextDelay = basicTetrisSpeedRule(blockCnt);
        double alpha = PreferencesHandler.getMode().speedProb();

        if (checkErasedLines(erasedLines)) {
            int time = (int) (timer.getDelay() * Math.pow(0.98, erasedLines));
            timer.setDelay(time);
            erasedLines = 0;
        }
        if (checkBlockCnt(blockCnt)) {
            int time = (int) ((1 - alpha) * delay + alpha * nextDelay);
            scoreHandler.addAlphaScore();
            timer.setDelay(time);
        }
    }

    public UnitBlock[][] getBoard() {
        return board.getBoard();
    }

    public void drop() {
        int cnt = curr.moveEnd(board);
        addScoreByMove(cnt);
        addScoreByEraseLine();
        nextTurn();
    }

    public void move(int[] vec) {
        int cnt = curr.move(board, vec);
        if (vec == DOWN) {
            addScoreByMove(cnt);
        }
    }

    public void rotate() {
        board.eraseCurr(curr);
        curr.rotate(board);
        board.placeBlock(curr);
    }

    public void setTotalErasedLines(int totalErasedLines) {
        this.totalErasedLines = totalErasedLines;
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
        timer.stop();
    }

    public void start() {
        this.pause = false;
        timer.start();
    }
}
