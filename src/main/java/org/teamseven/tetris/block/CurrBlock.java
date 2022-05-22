package org.teamseven.tetris.block;

import org.teamseven.tetris.Board.GameBoard;
import org.teamseven.tetris.handler.BlockMovementHandler;
import org.teamseven.tetris.factory.BlockFactory;

import static org.teamseven.tetris.Const.PLACE;

public class CurrBlock {
    public int x;
    public int y;
    private Block block;
    private BlockMovementHandler handler;

    public void setHandler(BlockMovementHandler handler) {
        this.handler = handler;
    }

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

    public int move(GameBoard board, int[] vec) {
        return handler.move(board, this, vec);
    }

    public int moveEnd(GameBoard board) {
        return handler.moveEnd(board, this);
    }

    public void rotate(GameBoard board) {
        handler.rotate(board, this);
    }

    public boolean isStopped(GameBoard board) {
        return handler.isStopped(board, this);
    }

    public boolean canMove(GameBoard board) {
        return handler.canMove(board, this, PLACE);
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

