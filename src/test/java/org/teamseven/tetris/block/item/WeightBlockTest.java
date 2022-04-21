package org.teamseven.tetris.block.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.teamseven.tetris.Board.GameBoard;
import org.teamseven.tetris.block.CurrBlock;
import org.teamseven.tetris.block.UnitBlock;
import org.teamseven.tetris.handler.GameHandler;
import org.teamseven.tetris.handler.WeightMovementHandler;

import java.awt.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.teamseven.tetris.Const.*;

class WeightBlockTest {

    CurrBlock curr;
    GameBoard board = new GameBoard();

    UnitBlock[][] res1 = initBoard(new int[][]{
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
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 1, 0, 0}
    });

    UnitBlock[][] res2 = initBoard(new int[][]{
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
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 0, 0, 0, 0, 0, 0}
    });

    @BeforeEach
    void initMap() {
        curr = new CurrBlock();
        board.setBoard(initBoard(new int[][]{
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
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 1, 1, 0, 0},
                {0, 0, 1, 1, 1, 1, 1, 1, 0, 0}
        }));

    }

    @Test
    @DisplayName("기본 삭제")
    void basic_test() {
        curr.setHandler(new WeightMovementHandler());
        curr.setBlock(new WeightBlock());
        curr.y = 14;
        board.placeBlock(curr);

        curr.move(board, DOWN);
        curr.move(board, DOWN);
        curr.move(board, DOWN);
        curr.move(board, DOWN);
        ((ItemBlock) curr.getBlock()).execute(board, curr);

        assertThat(board.getBoard()).isDeepEqualTo(res1);
    }

    @Test
    @DisplayName("이동 테스트")
    void move_test() {
        curr.setHandler(new WeightMovementHandler());
        curr.setBlock(new WeightBlock());
        curr.y = 14;
        board.placeBlock(curr);

        curr.move(board, RIGHT);
        curr.move(board, DOWN);
        curr.move(board, LEFT);
        curr.move(board, DOWN);
        curr.move(board, DOWN);
        curr.move(board, DOWN);
        ((ItemBlock) curr.getBlock()).execute(board, curr);

        assertThat(board.getBoard()).isDeepEqualTo(res2);
    }

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
}