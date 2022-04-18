package org.teamseven.tetris.block;

import java.awt.Color;

public class OBlock extends Block {

    public OBlock() {
        color = Color.YELLOW;
        shape = new UnitBlock[][] {
                {new UnitBlock(color), new UnitBlock(color)},
                {new UnitBlock(color), new UnitBlock(color)}
        };
    }
}
