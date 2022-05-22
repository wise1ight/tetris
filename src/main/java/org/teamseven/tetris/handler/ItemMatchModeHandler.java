package org.teamseven.tetris.handler;

import org.teamseven.tetris.Board.GameBoard;
import org.teamseven.tetris.block.CurrBlock;
import org.teamseven.tetris.block.item.ItemBlock;
import org.teamseven.tetris.block.item.WeightBlock;
import org.teamseven.tetris.factory.BlockFactory;

public class ItemMatchModeHandler extends MatchModeHandler {

    private int[] pos;

    @Override
    protected boolean nextTurn(MatchModeHandler otherPlayer){
        if (hasItem(curr)) {
            executeItem(board, curr, this);
        }
        if (animating()) {
            return true;
        }
        initPreBoard();

        setErasedLines(board.eraseLines());
        addScoreByEraseLine();

        if (nextBlock instanceof WeightBlock) {
            curr.setHandler(new WeightMovementHandler());
        } else {
            curr.setHandler(new BlockMovementHandler());
        }

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
        if (isNewItem()) {
            nextBlock = BlockFactory.blockGenerator("item").generate();
        } else {
            nextBlock = BlockFactory.blockGenerator("random").generate();
        }
        board.placeBlock(curr);
        return true;
    }

    private boolean hasItem(CurrBlock curr) {
        if (curr.getBlock() instanceof ItemBlock) {
            return true;
        }
        for (int j = 0; j < curr.height(); j++) {
            for (int i = 0; i < curr.width(); i++) {
                if (curr.getBlock().getUnitBlock(i, j) instanceof ItemBlock) {
                    pos = new int[]{j, i};
                    return true;
                }
            }
        }
        return false;
    }

    private void executeItem(GameBoard board, CurrBlock curr, GameHandler handler) {
        if (curr.getBlock() instanceof ItemBlock) {
            ((ItemBlock) curr.getBlock()).execute(board, curr);
        } else if (curr.getBlock().getUnitBlock(pos[1], pos[0]) instanceof ItemBlock) {
            int cnt = ((ItemBlock) curr.getBlock().getUnitBlock(pos[1], pos[0])).execute(board, curr, pos, handler);
            handler.setErasedLines(cnt);
            handler.addScoreByEraseLine();
        }
    }

    private boolean isNewItem() {
        if (totalErasedLines != 0 && totalErasedLines / 2 > 0) {
            setTotalErasedLines(0);
            return true;
        }
        return false;
    }
}