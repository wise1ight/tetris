package org.teamseven.tetris.handler;

import org.teamseven.tetris.Board.GameBoard;
import org.teamseven.tetris.block.CurrBlock;
import org.teamseven.tetris.block.item.ItemBlock;

public class ItemModeHandler {

    private int save;
    private int[] pos;

    public boolean hasItem(CurrBlock curr) {
        if (curr.getBlock() instanceof ItemBlock) {
            return true;
        }
        for (int j = 0; j < curr.height(); j++) {
            for (int i = 0; i < curr.width(); i++) {
                if (curr.getBlock().getUnitBlock(j, i) instanceof ItemBlock) {
                    pos = new int[]{j, i};
                    return true;
                }
            }
        }
        return false;
    }

    public void executeItem(GameBoard board, CurrBlock curr) {
        if (curr.getBlock() instanceof ItemBlock) {
            ((ItemBlock) curr.getBlock()).execute(board);
        } else if (curr.getBlock().getUnitBlock(pos[1], pos[0]) instanceof ItemBlock) {
            ((ItemBlock) curr.getBlock().getUnitBlock(pos[1], pos[0])).execute(board);
        }
    }

    public boolean isNewItem(int totalErasedLines) {
        if (totalErasedLines != 0 && totalErasedLines % 10 == 0 && save != totalErasedLines) {
            save = totalErasedLines / 10;
            return true;
        }
        return false;
    }
}
