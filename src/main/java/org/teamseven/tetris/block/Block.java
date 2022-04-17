package org.teamseven.tetris.block;

import java.awt.*;

import static org.teamseven.tetris.Const.HEIGHT;
import static org.teamseven.tetris.Const.WIDTH;

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

    public void left_rotate() {
        int[][] tmp = new int[shape[0].length][shape.length];

        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                tmp[shape[i].length - 1 - j][i] = shape[i][j];
            }
        }
        shape = tmp;
    }

    public void right_rotate() {
        int[][] tmp = new int[shape[0].length][shape.length];

        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                tmp[j][shape.length - 1 - i] = shape[i][j];
            }
        }
        shape = tmp;
    }

    public int height() {
        return shape.length;
    }

    public int width() {
        if(shape.length > 0)
            return shape[0].length;
        return 0;
    }

    /*
     * 테스트용 메서드
     */
    public void printBoard() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                System.out.print(shape[i][j] + " ");
            }
            System.out.println();
        }
    }
}