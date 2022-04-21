package org.teamseven.tetris.block.item;

import org.teamseven.tetris.Board.GameBoard;
import org.teamseven.tetris.block.CurrBlock;
import org.teamseven.tetris.handler.GameHandler;

import java.awt.*;

public class LineRemoveBlock extends ItemUnitBlock {

    public LineRemoveBlock() {
        super(Color.BLACK);
    }

    @Override
    public void execute(GameBoard board, CurrBlock curr) {
        return;
    }

    @Override
    public int execute(GameBoard board, CurrBlock curr, int[] pos, GameHandler handler) {
        return board.eraseLine(curr.y + pos[0]);
    }
}
