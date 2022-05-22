package org.teamseven.tetris.handler;

import lombok.Getter;
import lombok.Setter;
import org.teamseven.tetris.block.UnitBlock;
import org.teamseven.tetris.factory.BlockFactory;

import static org.teamseven.tetris.Const.*;

@Getter
@Setter
public class MatchModeHandler extends GameHandler {
    protected UnitBlock[][] attackLines;
    protected UnitBlock[][] attackedLines;
    protected UnitBlock[][] preBoard;

    protected AttackHandler attackHandler;

    protected static final int MAXIMUM_ATTACK_LINES = 10;

    public MatchModeHandler() {
        attackLines = new UnitBlock[MAXIMUM_ATTACK_LINES][MAXIMUM_ATTACK_LINES];
        attackedLines = new UnitBlock[MAXIMUM_ATTACK_LINES][MAXIMUM_ATTACK_LINES];
        preBoard = new UnitBlock[HEIGHT][WIDTH];
        attackHandler = new AttackHandler();
    }

    public boolean playing(MatchModeHandler attackedPlayer) {
        if (curr.isStopped(board)) {
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
        attacked(otherPlayer);

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

    protected void readyAttack() {
        UnitBlock[][] newAttackLines = attackHandler.readyAttack(board.getEraseIndex(), attackLines, preBoard, curr);
        board.clearErasedIndex();
        if (newAttackLines != null) {
            attackLines = newAttackLines;
        }
    }

    protected void attacked(MatchModeHandler otherPlayer) {
        attackedLines = otherPlayer.attack();
        otherPlayer.clearAttackLines();
        UnitBlock[][] attackedBoard = attackHandler.getAttackedBoard(attackedLines, board);
        board.setBoard(attackedBoard);
    }

    public void drop(MatchModeHandler otherPlayer) {
        int cnt = curr.moveEnd(board);
        addScoreByMove(cnt);
        addScoreByEraseLine();
        nextTurn(otherPlayer);
    }

    protected void initPreBoard() {
        for (int i = 0; i < HEIGHT; i++) {
            preBoard[i] = board.getBoard()[i].clone();
        }
        board.clearErasedIndex();
    }

    private void clearAttackLines() {
        attackLines = new UnitBlock[MAXIMUM_ATTACK_LINES][MAXIMUM_ATTACK_LINES];
    }

    private UnitBlock[][] attack() {
        return attackLines;
    }
}
