package org.teamseven.tetris.Board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.teamseven.tetris.block.CurrBlock;
import org.teamseven.tetris.block.ZBlock;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    CurrBlock curr;
    Board board;

    @BeforeEach
    void init() {
        curr = new CurrBlock();
        board = new Board();
        curr.setBlock(new ZBlock());
    }

    @Test
    void plane_test() {
        int[][] expected = new int[][]{
                {0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        board.placeBlock(curr);

        assertThat(board.getBoard()).isDeepEqualTo(expected);
    }

    @Test
    void erase_test() {
        CurrBlock curr = new CurrBlock();
        curr.setBlock(new ZBlock());
        Board originalBoard = new Board();

        board.placeBlock(curr);
        board.eraseCurr(curr);
        assertThat(board.getBoard()).isDeepEqualTo(originalBoard.getBoard());
    }

}