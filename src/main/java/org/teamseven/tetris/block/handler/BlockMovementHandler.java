package org.teamseven.tetris.block.handler;

import org.teamseven.tetris.Board.Board;
import org.teamseven.tetris.block.CurrBlock;

import static org.teamseven.tetris.util.BlockMovementHandlerUtil.isBlocked;
import static org.teamseven.tetris.util.BlockMovementHandlerUtil.outOfBoard;

public class BlockMovementHandler {

    public static void moveDown(Board board, CurrBlock curr) {
        if (canMove(board, curr, new int[]{1, 0})) {
            board.eraseCurr(curr);
            curr.y++;
        } else {
            board.placeBlock(curr);
            curr.newBlock();
        }
        board.placeBlock(curr);
    }

    public static void moveRight(Board board, CurrBlock curr) {
        if (!canMove(board, curr, new int[]{0, 1})) {
            return;
        }
        board.eraseCurr(curr);
        curr.x++;
        board.placeBlock(curr);
    }

    public static void moveLeft(Board board, CurrBlock curr) {
        if (!canMove(board, curr, new int[]{0, -1})) {
            return;
        }
        board.eraseCurr(curr);
        curr.x--;
        board.placeBlock(curr);
    }

    public static void moveEnd(Board board, CurrBlock curr) {
        int[] vec = new int[]{0, 0};

        while (canMove(board, curr, vec)) {
            vec[0]++;
        }
        board.eraseCurr(curr);
        curr.y = vec[0] - 1;
        board.placeBlock(curr);
    }

    private static boolean canMove(Board board, CurrBlock curr, int[] vec) {
        int x = curr.x + vec[1];
        int y = curr.y + vec[0];

        return !outOfBoard(x, y, curr) && !isBlocked(x, y, curr, board.getBoard());
    }
}
