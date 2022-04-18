package org.teamseven.tetris.block.handler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.teamseven.tetris.Board.GameBoard;
import org.teamseven.tetris.block.CurrBlock;
import org.teamseven.tetris.block.UnitBlock;
import org.teamseven.tetris.block.ZBlock;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.teamseven.tetris.Const.HEIGHT;
import static org.teamseven.tetris.Const.WIDTH;
import static org.teamseven.tetris.block.handler.BlockMovementHandler.*;

class UnitBlockMovementHandlerTest {

    GameBoard board = new GameBoard();
    CurrBlock curr;

    @BeforeEach
    void init() {
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
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 1, 0, 0, 0, 0}
        }));
    }

    @Nested
    @DisplayName("canMove 테스트")
    class CanMoveTest {

        Method canMove;

        @BeforeEach
        void reflectionMethod() throws NoSuchMethodException {
            canMove = BlockMovementHandler.class.getDeclaredMethod("canMove", GameBoard.class, CurrBlock.class, int[].class);
            canMove.setAccessible(true);
        }

        @Test
        @DisplayName("성공하는 경우")
        void success_case() throws InvocationTargetException, IllegalAccessException {
            boolean res = (boolean) canMove.invoke(canMove, board, curr, new int[]{1, 0});

            assertThat(res).isTrue();
        }

        @Test
        @DisplayName("맵 왼쪽 벗어날 경우")
        void out_of_bound_left() throws InvocationTargetException, IllegalAccessException {
            curr.x = 0;

            boolean res = (boolean) canMove.invoke(canMove, board, curr, new int[]{0, -1});

            assertThat(res).isFalse();
        }

        @Test
        @DisplayName("맵 오른쪽 벗어날 경우")
        void out_of_bound_right() throws InvocationTargetException, IllegalAccessException {
            curr.x = WIDTH - curr.width();

            boolean res = (boolean) canMove.invoke(canMove, board, curr, new int[]{0, 1});

            assertThat(res).isFalse();
        }

        @Test
        @DisplayName("맵 아래 벗어날 경우")
        void out_of_bound_down() throws InvocationTargetException, IllegalAccessException {
            curr.y = HEIGHT - curr.height();

            boolean res = (boolean) canMove.invoke(canMove, board, curr, new int[]{1, 0});

            assertThat(res).isFalse();
        }

        @Test
        @DisplayName("블럭에 막힌 경우")
        void blocked() throws InvocationTargetException, IllegalAccessException {
            curr.setBlock(new ZBlock());
            curr.x = 2;
            curr.y = 15;

            board.placeBlock(curr);

            boolean res = (boolean) canMove.invoke(canMove, board, curr, new int[]{1, 0});

            assertThat(res).isFalse();
        }
    }

    @Nested
    @DisplayName("이동 테스트")
    class moveTest {

        @BeforeEach
        void init() {
            curr.setBlock(new ZBlock());
        }

        @Test
        @DisplayName("오른쪽 이동 테스트")
        void move_right_test() {
            UnitBlock[][] expected = initBoard(new int[][]{
                    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
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
                    {0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
                    {0, 0, 1, 1, 1, 1, 0, 0, 0, 0}
            });

            moveRight(board, curr);

            assertThat(board.getBoard()).isDeepEqualTo(expected);
        }

        @Test
        @DisplayName("오른쪽 이동 실패 테스트")
        void fail_to_move_right_test() {
            UnitBlock[][] expected = initBoard(new int[][]{
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
                    {1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
                    {0, 0, 1, 1, 1, 1, 0, 0, 0, 0}
            });
            curr.x = 0;
            curr.y = 16;
            board.placeBlock(curr);

            moveRight(board, curr);

            assertThat(board.getBoard()).isDeepEqualTo(expected);
        }

        @Test
        @DisplayName("왼쪽 이동 테스트")
        void move_left_test() {
            UnitBlock[][] expected = initBoard(new int[][]{
                    {0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
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
                    {0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
                    {0, 0, 1, 1, 1, 1, 0, 0, 0, 0}
            });

            moveLeft(board, curr);

            assertThat(board.getBoard()).isDeepEqualTo(expected);
        }

        @Test
        @DisplayName("왼쪽 이동 실패 테스트")
        void fail_to_move_left_test() {
            UnitBlock[][] expected = initBoard(new int[][]{
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
                    {1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
                    {0, 0, 1, 1, 1, 1, 0, 0, 0, 0}
            });
            curr.x = 0;
            curr.y = 16;
            board.placeBlock(curr);

            moveLeft(board, curr);

            assertThat(board.getBoard()).isDeepEqualTo(expected);
        }

        @Test
        @DisplayName("아래 이동 테스트")
        void move_down_test() {
            UnitBlock[][] expected = initBoard(new int[][]{
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
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
                    {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
                    {0, 0, 1, 1, 1, 1, 0, 0, 0, 0}
            });

            moveDown(board, curr);

            assertThat(board.getBoard()).isDeepEqualTo(expected);
        }

        @Test
        @DisplayName("아래 이동 실패 테스트")
        void fail_to_move_down_test() {
            UnitBlock[][] expected = initBoard(new int[][]{
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
                    {1, 1, 0, 1, 0, 0, 0, 0, 0, 0},
                    {0, 1, 1, 1, 1, 0, 0, 0, 0, 0},
                    {0, 0, 1, 1, 1, 1, 0, 0, 0, 0}
            });
            curr.x = 0;
            curr.y = 17;
            board.placeBlock(curr);

            moveDown(board, curr);
            board.eraseCurr(curr);

            assertThat(board.getBoard()).isDeepEqualTo(expected);
        }

        @Test
        @DisplayName("밑으로 한 번에 이동 테스트")
        void move_end_test() {
            UnitBlock[][] expected = initBoard(new int[][]{
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
                    {0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
                    {0, 0, 0, 1, 1, 1, 0, 0, 0, 0},
                    {0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
                    {0, 0, 1, 1, 1, 1, 0, 0, 0, 0}
            });

            moveEnd(board, curr);

            assertThat(board.getBoard()).isDeepEqualTo(expected);
        }
    }

    UnitBlock[][] initBoard(int[][] bitBoard) {
        UnitBlock[][] board = new UnitBlock[HEIGHT][WIDTH];

        for (int j = 0; j < HEIGHT; j++) {
            for (int i = 0; i < WIDTH; i++) {
                if (bitBoard[j][i] == 1) {
                    board[j][i] = new UnitBlock(Color.BLACK);
                }
            }
        }
        return board;
    }
}