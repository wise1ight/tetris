package org.teamseven.tetris.block.item;

import org.teamseven.tetris.Board.GameBoard;
import org.teamseven.tetris.block.CurrBlock;
import org.teamseven.tetris.handler.GameHandler;

import java.awt.*;

import static org.teamseven.tetris.Const.HEIGHT;
import static org.teamseven.tetris.Const.WIDTH;

public class BoomBlock extends ItemUnitBlock {

    public BoomBlock() {
        super(Color.LIGHT_GRAY);
    }

    @Override
    public void execute(GameBoard board, CurrBlock curr) {
        return;
    }

    @Override
    public int execute(GameBoard board, CurrBlock curr, int[] pos, GameHandler handler) {
        for (int j = pos[0] - 3; j < pos[0] + 3; j++) {
            for (int i = pos[1] - 3; i < pos[1] + 3; i++) {
                if (i >= 0 && i < WIDTH && j >= 0 && j < HEIGHT) {
                    board.getBoard()[j][i] = null;
                }
            }
        }
        return 0;
    }
}
