package org.teamseven.tetris.block.generator;

import org.teamseven.tetris.block.*;
import org.teamseven.tetris.block.item.*;

import java.awt.*;
import java.util.Random;

public class ItemBlockGenerator extends RouletteWheelGenerator {

    @Override
    public Block generate() {
        Block block = super.generate();
        Random random = new Random();

        int index = random.nextInt(5);

        switch (index) {
            case 0:
                return new ItemBlock(block, new BoomBlock(Color.LIGHT_GRAY));
            case 1:
                return new WeightBlock();
            case 2:
                return new ItemBlock(block, new ClearBlock(Color.DARK_GRAY));
            case 3:
                return new ItemBlock(block, new LineRemoveBlock(Color.lightGray));
            case 4:
                return new SBlock();
        }
        return null;
    }
}
