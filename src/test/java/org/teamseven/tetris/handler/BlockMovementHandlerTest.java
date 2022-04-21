package org.teamseven.tetris.handler;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.teamseven.tetris.Board.GameBoard;
import org.teamseven.tetris.block.*;
import org.teamseven.tetris.handler.BlockMovementHandler;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.teamseven.tetris.Const.*;

class BlockMovementHandlerTest {

    GameBoard board = new GameBoard();
    CurrBlock curr;
    BlockMovementHandler handler = new BlockMovementHandler();

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

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    @DisplayName("블럭 회전 테스트")
    class RotateTest {

        @ParameterizedTest(name = "블럭 회전")
        @MethodSource("rotatedBlockProvider")
        @DisplayName("회전 성공한 경우")
        void rotate(Block block, int[][] expected) {
            UnitBlock[][] shape = initBoard(expected);
            curr.setBlock(block);

            curr.rotate(board);

            assertThat(curr.getBlock().getShape()).isDeepEqualTo(shape);
        }

        @ParameterizedTest(name = "블럭 회전")
        @MethodSource("rotatedBlockProvider")
        @DisplayName("회전 실패한 경우")
        void not_rotate(Block block) {
            curr.setBlock(block);
            curr.rotate(board);
            UnitBlock[][] expected = block.getShape();
            curr.x = 8;

            curr.rotate(board);

            assertThat(curr.getBlock().getShape()).isDeepEqualTo(expected);
        }


        private Stream<Arguments> rotatedBlockProvider() {
            return Stream.of(
                    Arguments.of(new IBlock()),
                    Arguments.of(new JBlock()),
                    Arguments.of(new LBlock()),
                    Arguments.of(new SBlock()),
                    Arguments.of(new TBlock()),
                    Arguments.of(new ZBlock()));
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

    @Nested
    @DisplayName("canMove 테스트")
    class CanMoveTest {

        Method canMove;

        @BeforeEach
        void reflectionMethod() throws NoSuchMethodException {
            canMove = handler.getClass().getDeclaredMethod("canMove", GameBoard.class, CurrBlock.class, int[].class);
            canMove.setAccessible(true);
        }

        @Test
        @DisplayName("아래 이동 가능한 경우")
        void down_success_case() throws InvocationTargetException, IllegalAccessException {
            boolean res = (boolean) canMove.invoke(handler, board, curr, DOWN);

            assertThat(res).isTrue();
        }

        @Test
        @DisplayName("왼쪽 이동 가능한 경우")
        void left_success_case() throws InvocationTargetException, IllegalAccessException {
            boolean res = (boolean) canMove.invoke(handler, board, curr, LEFT);

            assertThat(res).isTrue();
        }

        @Test
        @DisplayName("오른쪽 이동 가능한 경우")
        void right_success_case() throws InvocationTargetException, IllegalAccessException {
            boolean res = (boolean) canMove.invoke(handler, board, curr, RIGHT);

            assertThat(res).isTrue();
        }

        @Test
        @DisplayName("맵 왼쪽 벗어날 경우")
        void out_of_bound_left() throws InvocationTargetException, IllegalAccessException {
            curr.x = 0;

            boolean res = (boolean) canMove.invoke(handler, board, curr, new int[]{0, -1});

            assertThat(res).isFalse();
        }

        @Test
        @DisplayName("맵 오른쪽 벗어날 경우")
        void out_of_bound_right() throws InvocationTargetException, IllegalAccessException {
            curr.x = WIDTH - curr.width();

            boolean res = (boolean) canMove.invoke(handler, board, curr, new int[]{0, 1});

            assertThat(res).isFalse();
        }

        @Test
        @DisplayName("맵 아래 벗어날 경우")
        void out_of_bound_down() throws InvocationTargetException, IllegalAccessException {
            curr.y = HEIGHT - curr.height();

            boolean res = (boolean) canMove.invoke(handler, board, curr, new int[]{1, 0});

            assertThat(res).isFalse();
        }

        @Test
        @DisplayName("블럭에 막힌 경우")
        void blocked() throws InvocationTargetException, IllegalAccessException {
            curr.setBlock(new ZBlock());
            curr.x = 2;
            curr.y = 15;

            board.placeBlock(curr);

            boolean res = (boolean) canMove.invoke(handler, board, curr, new int[]{1, 0});

            assertThat(res).isFalse();
        }
    }

    @Nested
    @DisplayName("이동 테스트")
    class MoveTest {

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

            handler.move(board, curr, RIGHT);

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

            handler.move(board, curr, RIGHT);

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

            handler.move(board, curr, LEFT);

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

            handler.move(board, curr, LEFT);

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

            handler.move(board, curr, DOWN);

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

            handler.move(board, curr, DOWN);

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

            handler.moveEnd(board, curr);

            assertThat(board.getBoard()).isDeepEqualTo(expected);
        }
    }

    @Nested
    @DisplayName("isStopped 테스트")
    class IsStoppedTest {

        @Test
        @DisplayName("멈춘 경우")
        void return_true() {
            curr.y = 16;

            boolean res = handler.isStopped(board, curr, null);

            assertThat(res).isTrue();
        }

        @Test
        @DisplayName("안 멈춘 경우")
        void return_false() {
            boolean res = handler.isStopped(board, curr, null);

            assertThat(res).isFalse();
        }
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