package org.teamseven.tetris.util;

import static org.teamseven.tetris.Const.WIDTH;

public class BoardUtil {

    public static boolean isFilled(int[] line) {
        for (int i = 0; i < WIDTH; i++) {
            if (line[i] == 0) {
                return false;
            }
        }
        return true;
    }
}
