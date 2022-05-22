package org.teamseven.tetris.handler;

import org.teamseven.tetris.Board.GameBoard;
import org.teamseven.tetris.block.CurrBlock;
import org.teamseven.tetris.block.UnitBlock;

import java.util.List;

import static org.teamseven.tetris.Const.HEIGHT;
import static org.teamseven.tetris.Const.WIDTH;

public class AttackHandler {

    //TODO
    //minimum erase line 2로 바꾸기
    protected static final int MINIMUM_ERASE_LINE = 1;
    public static final int MAXIMUM_ATTACK_LINES = 10;

    public UnitBlock[][] getAttackedBoard(UnitBlock[][] attackedLines, GameBoard gameBoard) {
        UnitBlock[][] board = new UnitBlock[HEIGHT][WIDTH];
        int k = 0;

        int attackedLinesNum = getAttackLinesNum(attackedLines);
        for (int i = 0; i < HEIGHT; i++) {
            if (i < HEIGHT - attackedLinesNum) {
                board[i] = gameBoard.getBoard()[i + attackedLinesNum].clone();
            } else {
                board[i] = attackedLines[MAXIMUM_ATTACK_LINES - attackedLinesNum + k].clone();
                k++;
            }
        }
        return board;
    }

    public UnitBlock[][] readyAttack(GameBoard board, UnitBlock[][] attackLines, UnitBlock[][] preBoard, CurrBlock curr) {
        List<Integer> eraseIndex = board.getEraseIndex();
        if (eraseIndex.size() < MINIMUM_ERASE_LINE) {
            return null;
        }
        UnitBlock[][] newAttackLines = saveAttackLines(eraseIndex, attackLines, preBoard, curr);
        board.clearErasedIndex();
        return newAttackLines;
    }

    private UnitBlock[][] saveAttackLines(List<Integer> eraseIndex, UnitBlock[][] attackLines, UnitBlock[][] preBoard, CurrBlock curr) {
        UnitBlock[][] newAttackLines = new UnitBlock[MAXIMUM_ATTACK_LINES][MAXIMUM_ATTACK_LINES];
        int attackLinesNum = getAttackLinesNum(attackLines);
        int erasedNum = eraseIndex.size() + attackLinesNum > MAXIMUM_ATTACK_LINES ? eraseIndex.size() + attackLinesNum - MAXIMUM_ATTACK_LINES : eraseIndex.size();
        int k = eraseIndex.size();

        if (attackLinesNum == 10) {
            return null;
        }

        for (int i = 0; i < MAXIMUM_ATTACK_LINES - erasedNum; i++) {
            newAttackLines[i] = attackLines[i + erasedNum].clone();
        }
        for (int i = 0; i < eraseIndex.size(); i++) {
            newAttackLines[MAXIMUM_ATTACK_LINES - i - 1] = preBoard[eraseIndex.get(i)].clone();
        }
        for (int i = 0; i < curr.height(); i++) {
            boolean flag = false;
            for (int j = 0; j < curr.width(); j++) {
                if (curr.getBlock().getUnitBlock(j, i) != null && eraseIndex.contains(i + curr.y)) {
                    int y = MAXIMUM_ATTACK_LINES - k;
                    int x = j + curr.x;
                    newAttackLines[y][x] = null;
                    flag = true;
                }
            }
            if (flag) {
                k--;
            }
        }
        return newAttackLines;
    }

    private int getAttackLinesNum(UnitBlock[][] attackLines) {
        int attackedLinesNum = 0;

        for (int i = 0; i < MAXIMUM_ATTACK_LINES; i++) {
            for (int j = 0; j < MAXIMUM_ATTACK_LINES; j++) {
                if (attackLines[i][j] != null) {
                    attackedLinesNum++;
                    break;
                }
            }
        }
        return attackedLinesNum;
    }
}
