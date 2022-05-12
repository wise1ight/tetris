package org.teamseven.tetris.block.item;

import org.junit.jupiter.api.Test;
import org.teamseven.tetris.Board.GameBoard;
import org.teamseven.tetris.block.Block;
import org.teamseven.tetris.block.CurrBlock;
import org.teamseven.tetris.block.IBlock;
import org.teamseven.tetris.handler.GameHandler;
import org.teamseven.tetris.handler.ItemModeHandler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.teamseven.tetris.Const.DEFAULT_SCORE;

class ColorScoreBlockTest {

    CurrBlock curr;
    GameBoard board = new GameBoard();
    ItemModeHandler itemModeHandler = new ItemModeHandler();
//    GameHandler gameHandler = new GameHandler(false);

    @Test
    void test() {
//        curr = new CurrBlock();
//        curr.setBlock(new IBlock());
//        curr.y = 19;
//        board.placeBlock(curr);
//        Block block = new IBlock();
//        InnerItemBlock innerItemBlock = new InnerItemBlock(block, new ColorScoreBlock());
//        curr.setBlock(innerItemBlock);
//        curr.y = 0;
//        board.placeBlock(curr);
//
//        itemModeHandler.hasItem(curr);
//        itemModeHandler.executeItem(board, curr, gameHandler);
//
//        assertThat(gameHandler.getScore()).isEqualTo(DEFAULT_SCORE * 7 * 10);
    }
}