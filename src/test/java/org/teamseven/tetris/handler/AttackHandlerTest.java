package org.teamseven.tetris.handler;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.teamseven.tetris.Board.GameBoard;
import org.teamseven.tetris.block.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AttackHandlerTest {

    AttackHandler attackHandler = new AttackHandler();

    CurrBlock currBlock = new CurrBlock();

    @Nested
    @DisplayName("readyAttack 테스트")
    class ReadyAttackTest {

        @Test
        @DisplayName("한 줄만 지우는 경우 추가 x")
        void eraseLineNumIs1Test() {
            UnitBlock[][] attackLines = initBoard(new int[][]{
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
            });

            UnitBlock[][] preBoard = initBoard(new int[][]{
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
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0}
            });

            currBlock.setBlock(new IBlock());
            currBlock.y = 16;
            currBlock.x = 6;
            List<Integer> eraseIndex = new ArrayList<>();
            eraseIndex.add(16);
            UnitBlock[][] newAttackLines = attackHandler.readyAttack(eraseIndex, attackLines, preBoard, currBlock);

            assertThat(newAttackLines).isNull();
        }

        @Test
        @DisplayName("일반적으로 추가하는 경우")
        void basicAppendTest() {
            UnitBlock[][] attackLines = initBoard(new int[][]{
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0}
            });

            UnitBlock[][] preBoard = initBoard(new int[][]{
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
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0}
            });

            UnitBlock[][] res = initBoard(new int[][]{
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0}
            });

            currBlock.setBlock(new OBlock());
            currBlock.y = 14;
            currBlock.x = 8;
            List<Integer> eraseIndex = new ArrayList<>();
            eraseIndex.add(14);
            eraseIndex.add(15);
            UnitBlock[][] newAttackLines = attackHandler.readyAttack(eraseIndex, attackLines, preBoard, currBlock);

            assertThat(newAttackLines).isDeepEqualTo(res);
        }

        @Test
        @DisplayName("블럭 중간이 삭제되는 경우")
        void removeMiddleOfBlock() {
            UnitBlock[][] attackLines = initBoard(new int[][]{
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
            });

            UnitBlock[][] preBoard = initBoard(new int[][]{
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
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 1}
            });

            UnitBlock[][] res = initBoard(new int[][]{
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0}
            });
            SBlock sBlock = new SBlock();
            sBlock.left_rotate();
            currBlock.setBlock(sBlock);
            currBlock.y = 17;
            currBlock.x = 8;
            List<Integer> eraseIndex = new ArrayList<>();
            eraseIndex.add(18);
            UnitBlock[][] newAttackLines = attackHandler.readyAttack(eraseIndex, attackLines, preBoard, currBlock);

            assertThat(newAttackLines).isDeepEqualTo(res);
        }

        @Test
        @DisplayName("10줄 넘을 경우 아래 부분 자르고 추가해야 함")
        void over10LinesTest() {
            UnitBlock[][] attackLines = initBoard(new int[][]{
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0}
            });

            UnitBlock[][] preBoard = initBoard(new int[][]{
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
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
            });

            UnitBlock[][] res = initBoard(new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 0}
            });
            IBlock block = new IBlock();
            block.left_rotate();
            currBlock.setBlock(block);
            currBlock.y = 16;
            currBlock.x = 9;
            List<Integer> eraseIndex = new ArrayList<>();
            eraseIndex.add(16);
            eraseIndex.add(17);
            eraseIndex.add(18);
            eraseIndex.add(19);
            UnitBlock[][] newAttackLines = attackHandler.readyAttack(eraseIndex, attackLines, preBoard, currBlock);

            assertThat(newAttackLines).isDeepEqualTo(res);
        }

        @Test
        @DisplayName("10줄일 경우 그대로 리턴해야 함")
        void attackLineNumIs10Test() {
            UnitBlock[][] attackLines = initBoard(new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 0}
            });

            UnitBlock[][] preBoard = initBoard(new int[][]{
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
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
            });

            UnitBlock[][] res = initBoard(new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 0}
            });
            IBlock block = new IBlock();
            block.left_rotate();
            currBlock.setBlock(block);
            currBlock.y = 16;
            currBlock.x = 0;
            List<Integer> eraseIndex = new ArrayList<>();
            eraseIndex.add(16);
            eraseIndex.add(17);
            eraseIndex.add(18);
            eraseIndex.add(19);
            UnitBlock[][] newAttackLines = attackHandler.readyAttack(eraseIndex, attackLines, preBoard, currBlock);

            assertThat(newAttackLines).isDeepEqualTo(res);
        }
    }

    @Nested
    @DisplayName("getAttackedBoard 테스트")
    class GetAttackedBoardTest {

        @Test
        @DisplayName("일반적으로 추가하는 경우")
        void basicAppendTest() {
            UnitBlock[][] attackedLines = initBoard(new int[][]{
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0}
            });

            UnitBlock[][] board = initBoard(new int[][]{
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
                    {1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
                    {1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                    {1, 1, 1, 0, 0, 1, 1, 1, 0, 0},
            });

            UnitBlock[][] res = initBoard(new int[][]{
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
                    {1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
                    {1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                    {1, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0}
            });
            GameBoard gameBoard = new GameBoard();
            gameBoard.setBoard(board);
            UnitBlock[][] attackedBoard = attackHandler.getAttackedBoard(attackedLines, gameBoard);

            assertThat(attackedBoard).isDeepEqualTo(res);
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