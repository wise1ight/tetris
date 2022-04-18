package org.teamseven.tetris.block;

import org.teamseven.tetris.factory.BlockFactory;

public class CurrBlock {
    public int x;
    public int y;
    private Block block;

    public CurrBlock() {
        newBlock();
    }

    public void newBlock() {
        block = BlockFactory.blockGenerator("random").generate();
        x = 3;
        y = 0;
    }

    public int height() {
        return block.height();
    }

    public int width() {
        return block.width();
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }
}

