package org.teamseven.tetris.block.item;

import org.teamseven.tetris.Board.GameBoard;
import org.teamseven.tetris.block.CurrBlock;

public interface ItemBlock {
    void execute(GameBoard board, CurrBlock curr);
    int execute(GameBoard board, CurrBlock curr, int[] pos);
}
