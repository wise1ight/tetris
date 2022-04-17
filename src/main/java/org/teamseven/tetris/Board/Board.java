package org.teamseven.tetris.Board;

import org.teamseven.tetris.block.CurrBlock;

import static org.teamseven.tetris.Const.HEIGHT;
import static org.teamseven.tetris.Const.WIDTH;

public class Board {

    private final int[][] board;

    public Board() {
        board = new int[HEIGHT][WIDTH];
    }

    public int[][] getBoard() {
        return board;
    }

    public void eraseCurr(CurrBlock curr) {
        for (int i = curr.x; i < curr.x + curr.width(); i++) {
            for (int j = curr.y; j < curr.y + curr.height(); j++) {
                board[j][i] = 0;
            }
        }
    }

    public void placeBlock(CurrBlock curr) {
        for (int j = 0; j < curr.height(); j++) {
            for (int i = 0; i < curr.width(); i++) {
                board[curr.y + j][curr.x + i] = curr.getBlock().getShape(i, j);
            }
        }
    }
}
