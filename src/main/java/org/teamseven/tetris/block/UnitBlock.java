package org.teamseven.tetris.block;

import java.awt.Color;

public class UnitBlock {

    protected Color color;

    public UnitBlock(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    /**
     * Black은 테스트용 색
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof UnitBlock)) {
            return false;
        }

        UnitBlock o = (UnitBlock) obj;

        return this.color.equals(o.color) || Color.BLACK.equals(o.color);
    }
}