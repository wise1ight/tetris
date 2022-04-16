package org.teamseven.tetris.block.handler;

import org.teamseven.tetris.Board.Board;
import org.teamseven.tetris.block.CurrBlock;
import org.teamseven.tetris.factory.BlockFactory;

import static org.teamseven.tetris.Const.HEIGHT;
import static org.teamseven.tetris.Const.WIDTH;

public class BlockMovementHandler {

    protected void moveDown(Board board, CurrBlock curr) {
        board.eraseCurr(curr);
        if(curr.y < HEIGHT - curr.height()) curr.y++;
        else {
            board.placeBlock(curr);
            curr.setBlock(BlockFactory.blockGenerator("random").generate());
            curr.x = 3;
            curr.y = 0;
        }
        board.placeBlock(curr);
    }

    protected void moveRight(Board board, CurrBlock curr) {
        board.eraseCurr(curr);
        if(curr.x < WIDTH - curr.width()) curr.x++;
        board.placeBlock(curr);
    }

    protected void moveLeft(Board board, CurrBlock curr) {
        board.eraseCurr(curr);
        if(curr.x > 0) {
            curr.x--;
        }
        board.placeBlock(curr);
    }
}
