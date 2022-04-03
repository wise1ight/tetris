package org.teamseven.tetris.factory;

import org.teamseven.tetris.block.generator.BlockGenerator;
import org.teamseven.tetris.block.generator.RandomBlockGenerator;

public class BlockFactory {

    public BlockGenerator blockGenerator() {
        return new RandomBlockGenerator();
    }
}
