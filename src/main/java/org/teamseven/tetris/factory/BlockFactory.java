package org.teamseven.tetris.factory;

import org.teamseven.tetris.block.generator.BlockGenerator;
import org.teamseven.tetris.block.generator.ItemBlockGenerator;
import org.teamseven.tetris.block.generator.RandomBlockGenerator;

public class BlockFactory {

    public static BlockGenerator blockGenerator(String algorithm) {
        if ("random".equals(algorithm))
            return new RandomBlockGenerator();
        else if ("item".equals(algorithm)) {
            return new ItemBlockGenerator();
        } else {
        }
            return null;
    }
}
