package org.teamseven.tetris.block;

import org.teamseven.tetris.Board.GameBoard;
import org.teamseven.tetris.block.handler.BlockMovementHandler;
import org.teamseven.tetris.factory.BlockFactory;

public class CurrBlock {
    public int x;
    public int y;
    private Block block;
    private BlockMovementHandler handler;

    public CurrBlock() {
        handler = new BlockMovementHandler();
        block = BlockFactory.blockGenerator("random").generate();
        x = 3;
        y = 0;
    }

    public void newBlock(Block nextBlock) {
        block = nextBlock;
        x = 3;
        y = 0;
    }

    public void move(GameBoard board, int[] vec) {
        handler.move(board, this, vec);
    }

    public void moveEnd(GameBoard board) {
        handler.moveEnd(board, this);
    }

    public void rotate(GameBoard board) {
        handler.rotate(this);
    }

    public boolean isStopped(GameBoard board, Block nextBlock) {
        return handler.isStopped(board, this, nextBlock);
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public Block getBlock() {
        return block;
    }

    public int width() {
        return block.width();
    }

    public int height() {
        return block.height();
    }
}

