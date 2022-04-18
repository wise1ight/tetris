package org.teamseven.tetris.util;

import org.teamseven.tetris.block.UnitBlock;

import static org.teamseven.tetris.Const.WIDTH;

public class BoardUtil {

    public static boolean isFilled(UnitBlock[] line) {
        for (int i = 0; i < WIDTH; i++) {
            if (line[i] == null) {
                return false;
            }
        }
        return true;
    }
}
