package org.teamseven.tetris.block.item;

import org.teamseven.tetris.Board.GameBoard;
import org.teamseven.tetris.block.Block;
import org.teamseven.tetris.block.CurrBlock;
import org.teamseven.tetris.block.UnitBlock;
import org.teamseven.tetris.handler.GameHandler;

import java.awt.*;

import static org.teamseven.tetris.Const.HEIGHT;
import static org.teamseven.tetris.Const.WIDTH;

public class ClearBlock extends Block implements ItemBlock {

    public ClearBlock() {
        color = Color.DARK_GRAY;
        shape = new UnitBlock[][]{
                {new UnitBlock(color)}
        };
    }

    @Override
    public void execute(GameBoard board, CurrBlock curr) {
        board.setBoard(new UnitBlock[HEIGHT][WIDTH]);
    }

    @Override
    public int execute(GameBoard board, CurrBlock curr, int[] pos, GameHandler handler) {
        return 0;
    }
}
