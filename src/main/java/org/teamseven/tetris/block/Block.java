package org.teamseven.tetris.block;

import java.awt.*;

public abstract class Block {

    protected int[][] shape;
    protected Color color;

    public Block() {
        shape = new int[][]{
                {1, 1},
                {1, 1}
        };
        color = Color.YELLOW;
    }

    public int getShape(int x, int y) {
        return shape[y][x];
    }

    public Color getColor() {
        return color;
    }

    public void rotate() {
        int[][] tmp = new int[2][2];

        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                tmp[i][j] = shape[shape.length - 1 - j][i];
            }
        }
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                shape[i][j] = tmp[i][j];
            }
        }
    }

    public int height() {
        return shape.length;
    }

    public int width() {
        if(shape.length > 0)
            return shape[0].length;
        return 0;
    }
}