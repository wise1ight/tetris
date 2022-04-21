package org.teamseven.tetris.factory;

import org.teamseven.tetris.block.generator.BlockGenerator;
import org.teamseven.tetris.block.generator.ItemBlockGenerator;
import org.teamseven.tetris.block.generator.RandomBlockGenerator;
import org.teamseven.tetris.block.generator.RouletteWheelGenerator;

public class BlockFactory {

    public static BlockGenerator blockGenerator(String algorithm) {
        if ("random".equals(algorithm))
            return new RouletteWheelGenerator();
        else if ("item".equals(algorithm)) {
            return new ItemBlockGenerator();
        } else {
        }
            return null;
    }
}
