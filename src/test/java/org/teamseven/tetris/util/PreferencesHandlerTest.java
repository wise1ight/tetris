package org.teamseven.tetris.util;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.teamseven.tetris.Const;
import org.teamseven.tetris.handler.PreferencesHandler;

import java.awt.event.KeyEvent;

public class PreferencesHandlerTest {

    @Test
    public void testSetSmallScreen() {
        PreferencesHandler.setScreenSize(Const.ScreenSize.SMALL);
        Assertions.assertEquals(Const.ScreenSize.SMALL, PreferencesHandler.getScreenSize());
    }

    @Test
    public void testSetMediumScreen() {
        PreferencesHandler.setScreenSize(Const.ScreenSize.MEDIUM);
        Assertions.assertEquals(Const.ScreenSize.MEDIUM, PreferencesHandler.getScreenSize());
    }

    @Test
    public void testSetLargeScreen() {
        PreferencesHandler.setScreenSize(Const.ScreenSize.LARGE);
        Assertions.assertEquals(Const.ScreenSize.LARGE, PreferencesHandler.getScreenSize());
    }

    @Test
    public void testSetLeftBtn() {
        PreferencesHandler.setLeftBtnCode(KeyEvent.VK_0);
        Assertions.assertEquals(KeyEvent.VK_0, PreferencesHandler.getLeftBtnCode());
    }

    @Test
    public void testSetRightBtn() {
        PreferencesHandler.setRightBtnCode(KeyEvent.VK_1);
        Assertions.assertEquals(KeyEvent.VK_1, PreferencesHandler.getRightBtnCode());
    }

    @Test
    public void testSetRotateRightBtn() {
        PreferencesHandler.setRotateRightBtnCode(KeyEvent.VK_2);
        Assertions.assertEquals(KeyEvent.VK_2, PreferencesHandler.getRotateRightBtnCode());
    }

    @Test
    public void testSetPauseBtn() {
        PreferencesHandler.setPauseBtnCode(KeyEvent.VK_3);
        Assertions.assertEquals(KeyEvent.VK_3, PreferencesHandler.getPauseBtnCode());
    }

    @Test
    public void testSetNoneColorBlindness() {
        PreferencesHandler.setColorBlindnessType(Const.ColorBlindnessType.NONE);
        Assertions.assertEquals(Const.ColorBlindnessType.NONE, PreferencesHandler.getColorBlindnessType());
    }

    @Test
    public void testSetRedColorBlindness() {
        PreferencesHandler.setColorBlindnessType(Const.ColorBlindnessType.RED);
        Assertions.assertEquals(Const.ColorBlindnessType.RED, PreferencesHandler.getColorBlindnessType());
    }

    @Test
    public void testSetGreenColorBlindness() {
        PreferencesHandler.setColorBlindnessType(Const.ColorBlindnessType.GREEN);
        Assertions.assertEquals(Const.ColorBlindnessType.GREEN, PreferencesHandler.getColorBlindnessType());
    }

    @Test
    public void testSetBlueColorBlindness() {
        PreferencesHandler.setColorBlindnessType(Const.ColorBlindnessType.BLUE);
        Assertions.assertEquals(Const.ColorBlindnessType.BLUE, PreferencesHandler.getColorBlindnessType());
    }

    @AfterAll
    static void clearAllPreferences() {
        PreferencesHandler.clear();
    }
}
