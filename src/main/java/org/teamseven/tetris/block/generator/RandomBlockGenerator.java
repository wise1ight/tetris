package org.teamseven.tetris.block.generator;

import org.teamseven.tetris.block.*;

import java.util.Random;

public class RandomBlockGenerator implements BlockGenerator {

    @Override
    public Block generate() {
        Random random = new Random();
        int index = random.nextInt(7);

        switch (index) {
            case 0:
                return new IBlock();
            case 1:
                return new JBlock();
            case 2:
                return new LBlock();
            case 3:
                return new OBlock();
            case 4:
                return new SBlock();
            case 5:
                return new TBlock();
            case 6:
                return new ZBlock();
        }
        return null;
    }
}
