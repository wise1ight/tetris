package org.teamseven.tetris.block.handler;

import org.teamseven.tetris.Board.Board;
import org.teamseven.tetris.block.CurrBlock;
import org.teamseven.tetris.factory.BlockFactory;

import static org.teamseven.tetris.Const.HEIGHT;
import static org.teamseven.tetris.Const.WIDTH;
import static org.teamseven.tetris.util.BlockMovementHandlerUtil.isBlocked;
import static org.teamseven.tetris.util.BlockMovementHandlerUtil.outOfBoard;

public class BlockMovementHandler {

    public static void moveDown(Board board, CurrBlock curr) {
        if(canMove(board, curr, new int[]{0, 1})) {
            board.eraseCurr(curr);
            curr.y++;
        }
        else {
            board.placeBlock(curr);
            curr = new CurrBlock();
        }
        board.placeBlock(curr);
    }

    public static void moveRight(Board board, CurrBlock curr) {
        if (!canMove(board, curr, new int[]{1, 0})) {
            return;
        }
        board.eraseCurr(curr);
        curr.x++;
        board.placeBlock(curr);
    }

    public static void moveLeft(Board board, CurrBlock curr) {
        if (!canMove(board, curr, new int[]{-1, 0})) {
            return;
        }
        board.eraseCurr(curr);
        curr.x--;
        board.placeBlock(curr);
    }

    private static boolean canMove(Board board, CurrBlock curr, int[] vec) {
        int x = curr.x + vec[1];
        int y = curr.y + vec[0];

        return !outOfBoard(x, y, curr) && !isBlocked(x, y, curr, board.getBoard());
    }
}
