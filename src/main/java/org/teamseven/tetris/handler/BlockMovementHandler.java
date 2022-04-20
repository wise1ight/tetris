package org.teamseven.tetris.handler;

import org.teamseven.tetris.Board.GameBoard;
import org.teamseven.tetris.block.Block;
import org.teamseven.tetris.block.CurrBlock;
import org.teamseven.tetris.block.UnitBlock;

import static org.teamseven.tetris.Const.DOWN;
import static org.teamseven.tetris.Const.ROTATE;
import static org.teamseven.tetris.util.BlockMovementHandlerUtil.isBlocked;
import static org.teamseven.tetris.util.BlockMovementHandlerUtil.outOfBoard;

public class BlockMovementHandler {

    public void move(GameBoard board, CurrBlock curr, int[] vec) {
        board.eraseCurr(curr);
        if (!canMove(board, curr, vec)) {
            board.placeBlock(curr);
            return;
        }
        curr.x += vec[1];
        curr.y += vec[0];
        board.placeBlock(curr);
    }

    public boolean isStopped(GameBoard board, CurrBlock curr, Block nextBlock) {
        board.eraseCurr(curr);
        if (canMove(board, curr, DOWN)) {
            board.placeBlock(curr);
            return false;
        }
        board.placeBlock(curr);
        return true;
    }

    public void moveEnd(GameBoard board, CurrBlock curr) {
        int[] vec = new int[]{0, 0};

        board.eraseCurr(curr);
        while (canMove(board, curr, vec)) {
            vec[0]++;
        }
        curr.y += vec[0] - 1;
        board.placeBlock(curr);
    }

    private boolean canMove(GameBoard board, CurrBlock curr, int[] vec) {
        int x = curr.x + vec[1];
        int y = curr.y + vec[0];

        return !outOfBoard(x, y, curr) && !isBlocked(x, y, curr, board.getBoard());
    }

    public void rotate(GameBoard board, CurrBlock curr) {
        UnitBlock[][] shape = curr.getBlock().getShape();
        UnitBlock[][] tmp = new UnitBlock[shape[0].length][shape.length];

        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                tmp[j][shape.length - 1 - i] = shape[i][j];
            }
        }
        curr.getBlock().setShape(tmp);
        if (!canMove(board, curr, ROTATE)) {
            curr.getBlock().setShape(shape);
        }
    }
}
