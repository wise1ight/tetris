package org.teamseven.tetris.block;

import java.awt.Color;

public class SBlock extends Block {

    public SBlock() {
        color = Color.GREEN;
        shape = new UnitBlock[][] {
                {null,                 new UnitBlock(color), new UnitBlock(color)},
                {new UnitBlock(color), new UnitBlock(color), null}
        };
    }
}
