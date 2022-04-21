package org.teamseven.tetris.handler;

import org.teamseven.tetris.Board.GameBoard;
import org.teamseven.tetris.block.CurrBlock;

import static org.teamseven.tetris.Const.*;
import static org.teamseven.tetris.util.BlockMovementHandlerUtil.isBlocked;
import static org.teamseven.tetris.util.BlockMovementHandlerUtil.outOfBoard;

public class WeightMovementHandler extends BlockMovementHandler {

    private boolean flag;

    @Override
    public int move(GameBoard board, CurrBlock curr, int[] vec) {
        if (flag && (vec == RIGHT || vec == LEFT)) {
            return 0;
        }
        return super.move(board, curr, vec);
    }

    @Override
    public void rotate(GameBoard board, CurrBlock curr) {
        return;
    }

    @Override
    public boolean canMove(GameBoard board, CurrBlock curr, int[] vec) {
        int x = curr.x + vec[1];
        int y = curr.y + vec[0];

        if (y + 1 == HEIGHT || outOfBoard(x, y, curr)) {
            return false;
        }
        if (y != HEIGHT && isBlocked(x, y, curr, board.getBoard())) {
            flag = true;
        }
        return true;
    }
}
