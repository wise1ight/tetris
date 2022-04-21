package org.teamseven.tetris.handler;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.teamseven.tetris.Const;
import org.teamseven.tetris.enums.ColorBlindnessType;
import org.teamseven.tetris.enums.ScreenSize;
import org.teamseven.tetris.handler.PreferencesHandler;

import java.awt.event.KeyEvent;

public class PreferencesHandlerTest {

    @Test
    public void testSetSmallScreen() {
        PreferencesHandler.setScreenSize(ScreenSize.SMALL);
        Assertions.assertEquals(ScreenSize.SMALL, PreferencesHandler.getScreenSize());
    }

    @Test
    public void testSetMediumScreen() {
        PreferencesHandler.setScreenSize(ScreenSize.MEDIUM);
        Assertions.assertEquals(ScreenSize.MEDIUM, PreferencesHandler.getScreenSize());
    }

    @Test
    public void testSetLargeScreen() {
        PreferencesHandler.setScreenSize(ScreenSize.LARGE);
        Assertions.assertEquals(ScreenSize.LARGE, PreferencesHandler.getScreenSize());
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
    public void testSetHardDropBtn() {
        PreferencesHandler.setHardDropBtnCode(KeyEvent.VK_SPACE);
        Assertions.assertEquals(KeyEvent.VK_SPACE, PreferencesHandler.getHardDropBtnCode());
    }

    @Test
    public void testSetPauseBtn() {
        PreferencesHandler.setPauseBtnCode(KeyEvent.VK_3);
        Assertions.assertEquals(KeyEvent.VK_3, PreferencesHandler.getPauseBtnCode());
    }

    @Test
    public void testSetNoneColorBlindness() {
        PreferencesHandler.setColorBlindnessType(ColorBlindnessType.NONE);
        Assertions.assertEquals(ColorBlindnessType.NONE, PreferencesHandler.getColorBlindnessType());
    }

    @Test
    public void testSetColorBlindness() {
        PreferencesHandler.setColorBlindnessType(ColorBlindnessType.BLINDNESS);
        Assertions.assertEquals(ColorBlindnessType.BLINDNESS, PreferencesHandler.getColorBlindnessType());
    }

    @AfterAll
    static void clearAllPreferences() {
        PreferencesHandler.clear();
    }
}
