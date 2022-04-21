package org.teamseven.tetris.block.item;

import org.teamseven.tetris.Board.GameBoard;
import org.teamseven.tetris.block.Block;
import org.teamseven.tetris.block.UnitBlock;

import java.awt.*;

public class ClearBlock extends Block implements ItemBlock {

    public ClearBlock() {
        color = Color.DARK_GRAY;
        shape = new UnitBlock[][]{
                {new UnitBlock(color)}
        };
    }

    @Override
    public void execute(GameBoard board) {

    }
}
