package org.teamseven.tetris.Board;

import lombok.Getter;
import lombok.Setter;
import org.teamseven.tetris.block.CurrBlock;
import org.teamseven.tetris.block.UnitBlock;

import java.util.ArrayList;
import java.util.List;

import static org.teamseven.tetris.Const.HEIGHT;
import static org.teamseven.tetris.Const.WIDTH;
import static org.teamseven.tetris.util.BoardUtil.isFilled;

@Getter
@Setter
public class GameBoard {

    private UnitBlock[][] board;

    private List<Integer> eraseIndex = new ArrayList<>();

    public GameBoard() {
        board = new UnitBlock[HEIGHT][WIDTH];
    }

    public void appendEraseIndex(int y) {
        eraseIndex.add(y);
    }

    public void eraseCurr(CurrBlock curr) {
        for (int i = 0; i < curr.width(); i++) {
            for (int j = 0; j < curr.height(); j++) {
                if (curr.getBlock().getUnitBlock(i, j) != null) {
                    board[j + curr.y][i + curr.x] = null;
                }
            }
        }
    }

    public void placeBlock(CurrBlock curr) {
        for (int j = 0; j < curr.height(); j++) {
            for (int i = 0; i < curr.width(); i++) {
                if (curr.getBlock().getUnitBlock(i, j) != null)
                    board[curr.y + j][curr.x + i] = curr.getBlock().getUnitBlock(i, j);
            }
        }
    }

    public int eraseLines() {
        int erased = 0;

        for (int j = 0; j < HEIGHT; j++) {
            if (isFilled(board[j])) {
                erased += eraseLine(j);
            }
        }
        return erased;
    }

    public int eraseLine(int j) {
        UnitBlock[] blankLine = new UnitBlock[WIDTH];

        for (int i = j; i >= 1; i--) {
            appendEraseIndex(i);
            board[i] = board[i - 1].clone();
        }
        board[0] = blankLine;
        return 1;
    }

    public void clearErasedIndex() {
        eraseIndex = new ArrayList<>();
    }

    /*
     * 테스트용 메서드
     */
    public void printBoard() {
        System.out.println("======================");
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                System.out.print((board[i][j] != null ? 1 : 0) + " ");
            }
            System.out.println();
        }
    }
}
