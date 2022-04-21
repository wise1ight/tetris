package org.teamseven.tetris.block.item;

import org.teamseven.tetris.Board.GameBoard;
import org.teamseven.tetris.block.CurrBlock;
import org.teamseven.tetris.handler.GameHandler;

public interface ItemBlock {
    void execute(GameBoard board, CurrBlock curr);
    int execute(GameBoard board, CurrBlock curr, int[] pos, GameHandler handler);
}
