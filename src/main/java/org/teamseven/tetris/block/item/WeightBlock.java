package org.teamseven.tetris.block.item;

import org.teamseven.tetris.Board.GameBoard;
import org.teamseven.tetris.block.Block;
import org.teamseven.tetris.block.CurrBlock;
import org.teamseven.tetris.block.UnitBlock;
import org.teamseven.tetris.handler.GameHandler;

import java.awt.*;

import static org.teamseven.tetris.Const.HEIGHT;
import static org.teamseven.tetris.Const.WIDTH;

public class WeightBlock extends Block implements ItemBlock {

    public WeightBlock() {
        color = Color.PINK;
        shape = new UnitBlock[][] {
                {null                , new UnitBlock(color), new UnitBlock(color), null},
                {new UnitBlock(color), new UnitBlock(color), new UnitBlock(color), new UnitBlock(color)}
        };

    }

    @Override
    public void execute(GameBoard board, CurrBlock curr) {
        board.eraseCurr(curr);
    }

    @Override
    public int execute(GameBoard board, CurrBlock curr, int[] pos, GameHandler handler) {
        return 0;
    }
}
