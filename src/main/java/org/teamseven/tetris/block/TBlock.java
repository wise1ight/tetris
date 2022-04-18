package org.teamseven.tetris.block;

import java.awt.Color;

public class TBlock extends Block {

    public TBlock() {
        color = Color.MAGENTA;
        shape = new UnitBlock[][] {
                {null,                 new UnitBlock(color), null},
                {new UnitBlock(color), new UnitBlock(color), new UnitBlock(color)}
        };
    }
}
