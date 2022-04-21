package org.teamseven.tetris.block.item;

import org.teamseven.tetris.block.Block;
import org.teamseven.tetris.block.UnitBlock;

import java.awt.*;

public class WeightBlock extends Block {

    public WeightBlock() {
        color = Color.PINK;
        shape = new UnitBlock[][] {
                {null                , new UnitBlock(color), new UnitBlock(color), null},
                {new UnitBlock(color), new UnitBlock(color), new UnitBlock(color), new UnitBlock(color)}
        };
    }
}
