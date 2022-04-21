package org.teamseven.tetris.handler;

import org.teamseven.tetris.Board.GameBoard;
import org.teamseven.tetris.block.CurrBlock;

import static org.teamseven.tetris.Const.LEFT;
import static org.teamseven.tetris.Const.RIGHT;

public class StopMovementHandler extends BlockMovementHandler {

    @Override
    public int move(GameBoard board, CurrBlock curr, int[] vec) {
        if (vec == RIGHT || vec == LEFT) {
            return 0;
        }
        return super.move(board, curr, vec);
    }

    @Override
    public void rotate(GameBoard board, CurrBlock curr) {
        return;
    }
}
