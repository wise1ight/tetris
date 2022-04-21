package org.teamseven.tetris.block.item;

import org.teamseven.tetris.block.Block;
import org.teamseven.tetris.block.UnitBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemBlock extends Block {

    List<int[]> blockLoc;

    public ItemBlock(Block block, UnitBlock unitBlock) {
        this.shape = block.getShape();
        blockLoc = getBlockLoc();
        Random random = new Random();
        int num = random.nextInt(blockLoc.size());

        int[] pos = blockLoc.get(num);
        shape[pos[0]][pos[1]] = unitBlock;
    }

    private List<int[]> getBlockLoc() {
        List<int[]> blockLoc = new ArrayList<>();

        for (int j = 0; j < shape.length; j++) {
            for (int i = 0; i < shape[0].length; i++) {
                if (shape[j][i] != null) {
                    blockLoc.add(new int[]{j, i});
                }
            }
        }
        return blockLoc;
    }
}
