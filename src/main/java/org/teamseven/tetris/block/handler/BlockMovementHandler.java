package org.teamseven.tetris.block.handler;

import org.teamseven.tetris.block.CurrBlock;
import org.teamseven.tetris.factory.BlockFactory;

import static org.teamseven.tetris.Const.HEIGHT;
import static org.teamseven.tetris.Const.WIDTH;

public class BlockMovementHandler {

    protected void moveDown(CurrBlock curr) {
//        eraseCurr();
        if(curr.y < HEIGHT - curr.height()) curr.y++;
        else {
//            placeBlock();
            curr.setBlock(BlockFactory.blockGenerator("random").generate());
            curr.x = 3;
            curr.y = 0;
        }
//        placeBlock();
    }

    protected void moveRight(CurrBlock curr) {
//        eraseCurr();
        if(curr.x < WIDTH - curr.width()) curr.x++;
//        placeBlock();
    }

    protected void moveLeft(CurrBlock curr) {
//        eraseCurr();
        if(curr.x > 0) {
            curr.x--;
        }
//        placeBlock();
    }
}
