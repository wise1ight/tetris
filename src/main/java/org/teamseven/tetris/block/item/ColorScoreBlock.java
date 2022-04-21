package org.teamseven.tetris.block.item;

import org.teamseven.tetris.Board.GameBoard;
import org.teamseven.tetris.block.CurrBlock;
import org.teamseven.tetris.handler.GameHandler;

import java.awt.*;

import static org.teamseven.tetris.Const.HEIGHT;
import static org.teamseven.tetris.Const.WIDTH;

public class ColorScoreBlock extends ItemUnitBlock{

    public ColorScoreBlock() {
        super(Color.GRAY);
    }

    @Override
    public void execute(GameBoard board, CurrBlock curr) {

    }

    @Override
    public int execute(GameBoard board, CurrBlock curr, int[] pos, GameHandler handler) {
        int cnt = 0;

        System.out.println("awe");
        for (int j = 0; j < HEIGHT; j++) {
            for (int i = 0; i < WIDTH; i++) {
                if (board.getBoard()[j][i] != null && board.getBoard()[j][i].getColor() == curr.getBlock().getColor()) {
                    cnt++;
                }
            }
        }
        handler.addScoreByMove(cnt * 10);
        color = curr.getBlock().getColor();
        return 0;
    }
}
