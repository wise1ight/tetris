package org.teamseven.tetris.block.item;

import org.teamseven.tetris.Board.GameBoard;
import org.teamseven.tetris.block.CurrBlock;

import java.awt.*;

public class BoomBlock extends ItemUnitBlock {

    public BoomBlock() {
        super(Color.LIGHT_GRAY);
    }

    @Override
    public void execute(GameBoard board, CurrBlock curr) {
//        for
    }

    @Override
    public void execute(GameBoard board, CurrBlock curr, int[] pos) {
        return;
    }
}
