package org.teamseven.tetris.block;

import java.awt.Color;

public class JBlock extends Block {

    public JBlock() {
        color = Color.BLUE;
        shape = new UnitBlock[][] {
                {new UnitBlock(color), new UnitBlock(color), new UnitBlock(color)},
                {null,                 null,                 new UnitBlock(color)}
        };
    }
}
