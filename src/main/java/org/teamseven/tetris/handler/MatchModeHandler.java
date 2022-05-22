package org.teamseven.tetris.handler;

import lombok.Getter;
import lombok.Setter;
import org.teamseven.tetris.block.UnitBlock;
import org.teamseven.tetris.factory.BlockFactory;

import java.util.*;

import static org.teamseven.tetris.Const.*;

@Getter
@Setter
public class MatchModeHandler extends GameHandler {
    protected UnitBlock[][] attackLines;
    protected UnitBlock[][] attackedLines;
    protected UnitBlock[][] preBoard;
    //TODO
    //minimum erase line 2로 바꾸기
    protected static final int MINIMUM_ERASE_LINE = 1;
    protected static final int MAXIMUM_ATTACK_LINES = 10;

    public MatchModeHandler() {
        attackLines = new UnitBlock[MAXIMUM_ATTACK_LINES][MAXIMUM_ATTACK_LINES];
        attackedLines = new UnitBlock[MAXIMUM_ATTACK_LINES][MAXIMUM_ATTACK_LINES];
        preBoard = new UnitBlock[HEIGHT][WIDTH];
    }

    public boolean playing(MatchModeHandler attackedPlayer) {
        if (curr.isStopped(board, nextBlock)) {
            return nextTurn(attackedPlayer);

        } else {
            int cnt = curr.move(board, DOWN);
            addScoreByMove(cnt);
            return true;
        }
    }

    protected boolean nextTurn(MatchModeHandler otherPlayer) {
        if (animating()) {
            return true;
        }
        initPreBoard();

        setErasedLines(board.eraseLines());
        addScoreByEraseLine();

        readyAttack();
        attackedLines = otherPlayer.attack();
        otherPlayer.clearAttackLines();
        board.setBoard(appendAttackedLines());

        curr.newBlock(nextBlock);
        addBlockCnt();

        if (isFinished()) {
            return false;
        }

        speedUp();
        nextBlock = BlockFactory.blockGenerator("random").generate();

        board.placeBlock(curr);
        return true;
    }

    public void drop(MatchModeHandler otherPlayer) {
        int cnt = curr.moveEnd(board);
        addScoreByMove(cnt);
        addScoreByEraseLine();
        nextTurn(otherPlayer);
    }

    protected UnitBlock[][] appendAttackedLines() {
        UnitBlock[][] board = new UnitBlock[HEIGHT][WIDTH];
        int attackedLinesNum = 0;
        int k = 0;

        for (int i = 0; i < MAXIMUM_ATTACK_LINES; i++) {
            for (int j = 0; j < MAXIMUM_ATTACK_LINES; j++) {
                if (attackedLines[i][j] != null) {
                    attackedLinesNum++;
                    break;
                }
            }
//            board[i + MAXIMUM_ATTACK_LINES] = attackedLines[i];
        }
        for (int i = 0; i < HEIGHT; i++) {
            if (i < HEIGHT - attackedLinesNum) {
                board[i] = this.board.getBoard()[i + attackedLinesNum].clone();
            } else {
                board[i] = attackedLines[MAXIMUM_ATTACK_LINES - attackedLinesNum + k].clone();
                k++;
            }
        }
        return board;
    }

    protected void initPreBoard() {
        for (int i = 0; i < HEIGHT; i++) {
            preBoard[i] = board.getBoard()[i].clone();
        }
        board.clearErasedIndex();
    }

    //공격준비할 줄 만들기
    protected void readyAttack() {
        List<Integer> eraseIndex = board.getEraseIndex();
        if (eraseIndex.size() < MINIMUM_ERASE_LINE) {
            return;
        }
        saveAttackLines(eraseIndex);
        board.clearErasedIndex();
    }

    protected void saveAttackLines(List<Integer> eraseIndex) {
        int erasedNum = eraseIndex.size();

        for (int i = 0; i < MAXIMUM_ATTACK_LINES - erasedNum; i++) {
            attackLines[i] = attackLines[i + erasedNum].clone();
        }
        for (int i = 0; i < eraseIndex.size(); i++) {
            attackLines[MAXIMUM_ATTACK_LINES - i - MINIMUM_ERASE_LINE] = preBoard[eraseIndex.get(i)].clone();
        }
        for (int i = 0; i < curr.height(); i++) {
            for (int j = 0; j < curr.width(); j++) {
                if (curr.getBlock().getUnitBlock(j, i) != null &&
                        i + curr.y <= eraseIndex.get(eraseIndex.size() - MINIMUM_ERASE_LINE) && i + curr.y >= eraseIndex.get(0)) {
                    int y = MAXIMUM_ATTACK_LINES - (curr.height() - i);
                    int x = j + curr.x;
                    attackLines[y][x] = null;
                }
            }
        }
    }

    public void clearAttackLines() {
        attackLines = new UnitBlock[MAXIMUM_ATTACK_LINES][MAXIMUM_ATTACK_LINES];
    }

    public UnitBlock[][] attack() {
        return attackLines;
    }
}
