package org.teamseven.tetris.ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.teamseven.tetris.enums.ScreenSize;

public class TetrisStyleTest {

    @Test
    public void isCaseNumberSatisfied() {
        Assertions.assertEquals(ScreenSize.values().length * 1 * TetrisStyle.COLORS.length, TetrisStyle.getNumberOfStyles());
    }
}
