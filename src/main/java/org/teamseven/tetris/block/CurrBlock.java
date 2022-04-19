package org.teamseven.tetris.block;

import org.teamseven.tetris.factory.BlockFactory;

public class CurrBlock {
    public int x;
    public int y;
    private Block block;

    public CurrBlock() {
        block = BlockFactory.blockGenerator("random").generate();
        x = 3;
        y = 0;
    }

    public void newBlock(Block nextBlock) {
        block = nextBlock;
        x = 3;
        y = 0;
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

