package org.teamseven.tetris.block;

import java.awt.Color;

public class ZBlock extends Block {

    public ZBlock() {
        color = Color.RED;
        shape = new UnitBlock[][] {
                {new UnitBlock(color), new UnitBlock(color), null},
                {null,                 new UnitBlock(color), new UnitBlock(color)}
        };
    }
}
