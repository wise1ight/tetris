package org.teamseven.tetris.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.teamseven.tetris.Const;

public class PreferencesUtilTest {

    @Test
    public void testSetSmallScreen() {
        PreferencesUtil.setScreenSize(Const.ScreenSize.SMALL);
        Assertions.assertEquals(Const.ScreenSize.SMALL, PreferencesUtil.getScreenSize());
    }

    @Test
    public void testSetMediumScreen() {
        PreferencesUtil.setScreenSize(Const.ScreenSize.MEDIUM);
        Assertions.assertEquals(Const.ScreenSize.MEDIUM, PreferencesUtil.getScreenSize());
    }

    @Test
    public void testSetLargeScreen() {
        PreferencesUtil.setScreenSize(Const.ScreenSize.LARGE);
        Assertions.assertEquals(Const.ScreenSize.LARGE, PreferencesUtil.getScreenSize());
    }
}
