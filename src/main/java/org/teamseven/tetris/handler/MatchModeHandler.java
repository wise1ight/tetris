package org.teamseven.tetris.handler;

import org.teamseven.tetris.block.UnitBlock;
import org.teamseven.tetris.factory.BlockFactory;

import java.util.List;

import static org.teamseven.tetris.Const.HEIGHT;
import static org.teamseven.tetris.Const.WIDTH;

public class MatchModeHandler extends GameHandler {
    private UnitBlock[][] store;
    private UnitBlock[][] preStatus;
    private static final int MAXIMUM_ATTACK_LINES = 10;

    public MatchModeHandler() {
        store = new UnitBlock[MAXIMUM_ATTACK_LINES][MAXIMUM_ATTACK_LINES];
        preStatus = new UnitBlock[HEIGHT][WIDTH];
    }

    @Override
    protected boolean nextTurn() {
        if (animating()) {
            return true;
        }
        setErasedLines(board.eraseLines());
        addScoreByEraseLine();

        curr.newBlock(nextBlock);
        addBlockCnt();

        if (isFinished()) {
            return false;
        }

        speedUp();
        nextBlock = BlockFactory.blockGenerator("random").generate();
        for (int i = 0; i < 2; i++) {
            preStatus[i] = board.getBoard()[i].clone();
        }
        board.clearErasedIndex();
        board.placeBlock(curr);
        return true;
    }

    //공격준비할 줄 만들기
    public UnitBlock[][] readyAttack() {
        List<Integer> eraseIndex = board.getEraseIndex();
        int erasedNum = board.eraseLines();
        if (erasedNum < 2) {
            return store;
        }
        setStore(eraseIndex, erasedNum);
        return store;
    }

    private void setStore(List<Integer> eraseIndex, int erasedNum) {
        for (int i = 0; i < MAXIMUM_ATTACK_LINES - erasedNum; i++) {
            store[i] = store[i + erasedNum];
        }
        for (int i = 0; i < eraseIndex.size(); i++) {
            store[MAXIMUM_ATTACK_LINES - i - 1] = preStatus[eraseIndex.get(i)];
        }
    }

    public void attack() {
        store = new UnitBlock[MAXIMUM_ATTACK_LINES][MAXIMUM_ATTACK_LINES];
    }
}
