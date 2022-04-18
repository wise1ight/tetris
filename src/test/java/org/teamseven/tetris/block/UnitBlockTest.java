package org.teamseven.tetris.block;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class UnitBlockTest {

    UnitBlock[][] initBoard(int[][] bitBoard) {
        UnitBlock[][] board = new UnitBlock[bitBoard.length][bitBoard[0].length];

        for (int j = 0; j < bitBoard.length; j++) {
            for (int i = 0; i < bitBoard[0].length; i++) {
                if (bitBoard[j][i] == 1) {
                    board[j][i] = new UnitBlock(Color.BLACK);
                }
            }
        }
        return board;
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class RotateTest {

        @ParameterizedTest(name = "블럭 회전")
        @MethodSource("blockProvider")
        void rotate(Block block, int[][] expected) {
            UnitBlock[][] board = initBoard(expected);

            block.right_rotate();

            assertThat(block.shape).isDeepEqualTo(board);
        }


        private Stream<Arguments> blockProvider() {
            return Stream.of(
                    Arguments.of(new IBlock(), new int[][] {{1},
                                                            {1},
                                                            {1},
                                                            {1}}),
                    Arguments.of(new JBlock(), new int[][] {{0, 1},
                                                            {0, 1},
                                                            {1, 1}}),
                    Arguments.of(new LBlock(), new int[][] {{1, 1},
                                                            {0, 1},
                                                            {0, 1}}),
                    Arguments.of(new OBlock(), new int[][] {{1, 1},
                                                            {1, 1}}),
                    Arguments.of(new SBlock(), new int[][] {{1, 0},
                                                            {1, 1},
                                                            {0, 1}}),
                    Arguments.of(new TBlock(), new int[][] {{1, 0},
                                                            {1, 1},
                                                            {1, 0}}),
                    Arguments.of(new ZBlock(), new int[][] {{0, 1},
                                                            {1, 1},
                                                            {1, 0}}));
        }
    }
}