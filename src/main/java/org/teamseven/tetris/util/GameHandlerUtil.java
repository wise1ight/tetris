package org.teamseven.tetris.util;

import static org.teamseven.tetris.Const.*;

public class GameHandlerUtil {

    public static int toScore(int eraseLines) {
        switch (eraseLines) {
            case ONE_LINE:
                return 500;
            case TWO_LINES:
                return 1500;
            case THREE_LINES:
                return 3000;
            case FOUR_LINES:
                return 5000;
        }
        return 0;
    }

    public static boolean checkErasedLines(int erasedLines) {
        return erasedLines > 0;
    }

    public static boolean checkBlockCnt(int blockCnt) {
        return blockCnt % WIDTH == 0 && blockCnt / WIDTH > 0;
    }

    public static int getLevel(int blockCnt) {
        return blockCnt / WIDTH;
    }

    public static double basicTetrisSpeedRule(int blockCnt) {
        return Math.pow(0.8 - (getLevel(blockCnt) * 0.007), getLevel(blockCnt)) * 1000;
    }
}
