package org.teamseven.tetris.util;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.teamseven.tetris.Const;

import java.awt.event.KeyEvent;

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

    @Test
    public void testSetLeftBtn() {
        PreferencesUtil.setLeftBtnCode(KeyEvent.VK_0);
        Assertions.assertEquals(KeyEvent.VK_0, PreferencesUtil.getLeftBtnCode());
    }

    @Test
    public void testSetRightBtn() {
        PreferencesUtil.setRightBtnCode(KeyEvent.VK_1);
        Assertions.assertEquals(KeyEvent.VK_1, PreferencesUtil.getRightBtnCode());
    }

    @Test
    public void testSetRotateRightBtn() {
        PreferencesUtil.setRotateRightBtnCode(KeyEvent.VK_2);
        Assertions.assertEquals(KeyEvent.VK_2, PreferencesUtil.getRotateRightBtnCode());
    }

    @Test
    public void testSetPauseBtn() {
        PreferencesUtil.setPauseBtnCode(KeyEvent.VK_3);
        Assertions.assertEquals(KeyEvent.VK_3, PreferencesUtil.getPauseBtnCode());
    }

    @Test
    public void testSetNoneColorBlindness() {
        PreferencesUtil.setColorBlindnessType(Const.ColorBlindnessType.NONE);
        Assertions.assertEquals(Const.ColorBlindnessType.NONE, PreferencesUtil.getColorBlindnessType());
    }

    @Test
    public void testSetRedColorBlindness() {
        PreferencesUtil.setColorBlindnessType(Const.ColorBlindnessType.RED);
        Assertions.assertEquals(Const.ColorBlindnessType.RED, PreferencesUtil.getColorBlindnessType());
    }

    @Test
    public void testSetGreenColorBlindness() {
        PreferencesUtil.setColorBlindnessType(Const.ColorBlindnessType.GREEN);
        Assertions.assertEquals(Const.ColorBlindnessType.GREEN, PreferencesUtil.getColorBlindnessType());
    }

    @Test
    public void testSetBlueColorBlindness() {
        PreferencesUtil.setColorBlindnessType(Const.ColorBlindnessType.BLUE);
        Assertions.assertEquals(Const.ColorBlindnessType.BLUE, PreferencesUtil.getColorBlindnessType());
    }

    @AfterAll
    static void clearAllPreferences() {
        PreferencesUtil.clear();
    }
}
