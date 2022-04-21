package org.teamseven.tetris.util;

import org.teamseven.tetris.block.CurrBlock;
import org.teamseven.tetris.block.UnitBlock;

import static org.teamseven.tetris.Const.HEIGHT;
import static org.teamseven.tetris.Const.WIDTH;

public class BlockMovementHandlerUtil {

    public static boolean outOfBoard(int x, int y, CurrBlock curr) {
        return !(y <= HEIGHT - curr.height() && x >= 0 && x <= WIDTH - curr.width());
    }

    public static boolean isBlocked(int x, int y, CurrBlock curr, UnitBlock[][] board) {
        for (int j = 0; j < curr.height(); j++) {
            for (int i = 0; i < curr.width(); i++) {
                if (board[y + j][x + i] != null && curr.getBlock().getUnitBlock(i, j) != null) {
                    return true;
                }
            }
        }
        return false;
    }
}
