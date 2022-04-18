package org.teamseven.tetris.block;

import java.awt.Color;

public class LBlock extends Block {

    public LBlock() {
        color = Color.ORANGE;
        shape = new UnitBlock[][] {
                {new UnitBlock(color), new UnitBlock(color), new UnitBlock(color)},
                {new UnitBlock(color), null,                 null}
        };
    }
}
