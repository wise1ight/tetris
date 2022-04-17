package org.teamseven.tetris.util;

import org.teamseven.tetris.Board.Board;
import org.teamseven.tetris.block.CurrBlock;

import static org.teamseven.tetris.Const.HEIGHT;
import static org.teamseven.tetris.Const.WIDTH;

public class BlockMovementHandlerUtil {

    public static boolean outOfBoard(int x, int y, CurrBlock curr) {
        System.out.println("x = " + x);
        System.out.println("y = " + y);
        System.out.println("!(y < HEIGHT - curr.height() && x >= 0 && x < WIDTH - curr.width()) = " + !(y < HEIGHT - curr.height() && x >= 0 && x < WIDTH - curr.width()));
        return !(y < HEIGHT - curr.height() && x >= 0 && x < WIDTH - curr.width());
    }

    public static boolean isBlocked(int x, int y, CurrBlock curr, int[][] board) {
        for (int j = 0; j < curr.height(); j++) {
            for (int i = 0; i < curr.width(); i++) {
                if (board[y + j][x + i] == 1 && curr.getBlock().getShape(i, j) == 1) {
                    System.out.println("i = " + i);
                    System.out.println("j = " + j);
                    System.out.println("board[y + j][x + i] = " + board[y + j][x + i]);
                    return true;
                }
            }
        }
        return false;
    }
}
