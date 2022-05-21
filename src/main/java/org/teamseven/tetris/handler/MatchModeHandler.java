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
    private UnitBlock[][] attackLines;
    private UnitBlock[][] attackedLines;
//    private int attackLinesNum;
    private UnitBlock[][] preBoard;
    private static final int MAXIMUM_ATTACK_LINES = 10;

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

//        initPreBoard();

        if (isFinished()) {
            return false;
        }

        speedUp();
        nextBlock = BlockFactory.blockGenerator("random").generate();

        board.placeBlock(curr);
        return true;
    }

    private UnitBlock[][] appendAttackedLines() {
        UnitBlock[][] board = new UnitBlock[HEIGHT][WIDTH];
        int attackedLinesNum = 0;

        for (int i = 0; i < MAXIMUM_ATTACK_LINES; i++) {
            for (int j = 0; j < MAXIMUM_ATTACK_LINES; j++) {
                if (attackedLines[i][j] != null) {
                    attackedLinesNum++;
                    break;
                }
            }
            board[i + MAXIMUM_ATTACK_LINES] = attackedLines[i];
        }
        for (int i = 0; i < HEIGHT - attackedLinesNum; i++) {
            board[i] = this.board.getBoard()[i + attackedLinesNum].clone();
        }
        return board;
    }
//
    private void initPreBoard() {
        for (int i = 0; i < HEIGHT; i++) {
            preBoard[i] = board.getBoard()[i].clone();
        }
        board.clearErasedIndex();
    }

    //공격준비할 줄 만들기
    private void readyAttack() {
        List<Integer> eraseIndex = board.getEraseIndex();
        if (eraseIndex.size() < 1) {
            return;
        }
        saveAttackLines(eraseIndex);
        board.clearErasedIndex();
    }

    private void saveAttackLines(List<Integer> eraseIndex) {
        int erasedNum = eraseIndex.size();

        for (int i = 0; i < MAXIMUM_ATTACK_LINES - erasedNum; i++) {
            attackLines[i] = attackLines[i + erasedNum].clone();
        }
        for (int i = 0; i < eraseIndex.size(); i++) {
            attackLines[MAXIMUM_ATTACK_LINES - i - 1] = preBoard[eraseIndex.get(i)].clone();
        }
        for (int i = 0; i < curr.height(); i++) {
            for (int j = 0; j < curr.width(); j++) {
                if (curr.getBlock().getUnitBlock(j, i) != null && i + curr.y <= eraseIndex.get(eraseIndex.size() - 1) && i + curr.y >= eraseIndex.get(0)) {
                    int y = MAXIMUM_ATTACK_LINES - (curr.height() - i);
                    int x = j + curr.x;
                    attackLines[y][x] = null;
                }
            }
        }
    }
//        for (int j = curr.y; j < curr.height() + curr.y; j++) {
//            for (int i = curr.x; i < curr.width() + curr.x; i++) {
//                attackLines[j][i]
//            }
//        }
//
//    public void attacked(UnitBlock[][] enemyStore) {
//        this.attackedLines = enemyStore;
//    }
//
    public void clearAttackLines() {
        attackLines = new UnitBlock[MAXIMUM_ATTACK_LINES][MAXIMUM_ATTACK_LINES];
    }

    public UnitBlock[][] attack() {
        return attackLines;
    }
}
