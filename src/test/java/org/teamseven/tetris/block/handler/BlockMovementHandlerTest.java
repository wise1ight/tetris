package org.teamseven.tetris.block.handler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.teamseven.tetris.Board.Board;
import org.teamseven.tetris.block.CurrBlock;
import org.teamseven.tetris.block.ZBlock;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.teamseven.tetris.Const.HEIGHT;
import static org.teamseven.tetris.Const.WIDTH;

class BlockMovementHandlerTest {

    Board board = new Board();
    CurrBlock curr;

    @BeforeEach
    void init() {
        curr = new CurrBlock();
        board.setBoard(new int[][]{
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
        });
    }

    @Nested
    @DisplayName("canMove 테스트")
    class CanMoveTest {

        Method canMove;

        @BeforeEach
        void reflectionMethod() throws NoSuchMethodException {
            canMove = BlockMovementHandler.class.getDeclaredMethod("canMove", Board.class, CurrBlock.class, int[].class);
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
}