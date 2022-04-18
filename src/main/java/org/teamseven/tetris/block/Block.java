package org.teamseven.tetris.block;

import java.awt.*;

public abstract class Block {
    protected UnitBlock[][] shape;
    protected Color color;

    public Color getColor() {
        return color;
    }

    public int height() {
        return shape.length;
    }

    public int width() {
        if (shape.length > 0)
            return shape[0].length;
        return 0;
    }

    public UnitBlock getUnitBlock(int x, int y) {
        return shape[y][x];
    }

    public void setShape(UnitBlock[][] shape) {
        this.shape = shape;
    }

    public void left_rotate() {
        UnitBlock[][] tmp = new UnitBlock[shape[0].length][shape.length];

        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                tmp[shape[i].length - 1 - j][i] = shape[i][j];
            }
        }
        shape = tmp;
    }

    public void right_rotate() {
        UnitBlock[][] tmp = new UnitBlock[shape[0].length][shape.length];

        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                tmp[j][shape.length - 1 - i] = shape[i][j];
            }
        }
        shape = tmp;
    }
}
