package org.teamseven.tetris.block;

import java.awt.Color;

public class IBlock extends Block {

    public IBlock() {
        color = Color.CYAN;
        shape = new UnitBlock[][] {
                {new UnitBlock(color), new UnitBlock(color), new UnitBlock(color), new UnitBlock(color)}
        };
    }
}
