package org.teamseven.tetris.block;

public class CurrBlock {
    public int x;
    public int y;
    private Block block;

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
