package org.teamseven.tetris.handler;

import org.teamseven.tetris.Const;
import org.teamseven.tetris.enums.ColorBlindnessType;
import org.teamseven.tetris.enums.Mode;
import org.teamseven.tetris.enums.ScreenSize;

import java.awt.event.KeyEvent;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import static org.teamseven.tetris.Const.PREF_MODE_KEY;
import static org.teamseven.tetris.Const.PREF_SCREEN_SIZE_KEY;
import static org.teamseven.tetris.enums.Mode.NORMAL;

public class PreferencesHandler {

    private static final Preferences prefs = Preferences.userNodeForPackage(PreferencesHandler.class);

    public static void setMode(Mode mode) {
        prefs.put(PREF_MODE_KEY, mode.name());
    }

    public static Mode getMode() {
        return Mode.valueOf(prefs.get(PREF_MODE_KEY, NORMAL.name()));
    }

    public static ScreenSize getScreenSize() {
        return ScreenSize.toEnum(prefs.get(PREF_SCREEN_SIZE_KEY, ScreenSize.MEDIUM.name()));
    }

    public static void setScreenSize(ScreenSize screenSize) {
        prefs.put(PREF_SCREEN_SIZE_KEY, screenSize.name());
        try {
            prefs.flush();
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }
    }

    public static int getLeftBtnCode() {
        return prefs.getInt(Const.PREF_BTN_LEFT_KEY, KeyEvent.VK_LEFT);
    }

    public static void setLeftBtnCode(int keyCode) {
        prefs.putInt(Const.PREF_BTN_LEFT_KEY, keyCode);
    }

    public static int getRightBtnCode() {
        return prefs.getInt(Const.PREF_BTN_RIGHT_KEY, KeyEvent.VK_RIGHT);
    }

    public static void setRightBtnCode(int keyCode) {
        prefs.putInt(Const.PREF_BTN_RIGHT_KEY, keyCode);
    }

    public static int getRotateRightBtnCode() {
        return prefs.getInt(Const.PREF_BTN_ROTATE_RIGHT_KEY, KeyEvent.VK_UP);
    }

    public static void setRotateRightBtnCode(int keyCode) {
        prefs.putInt(Const.PREF_BTN_ROTATE_RIGHT_KEY, keyCode);
    }

    public static int getHardDropBtnCode() {
        return prefs.getInt(Const.PREF_BTN_HARD_DROP_KEY, KeyEvent.VK_SPACE);
    }

    public static void setHardDropBtnCode(int keyCode) {
        prefs.putInt(Const.PREF_BTN_HARD_DROP_KEY, keyCode);
    }

    public static int getSoftDropBtnCode() {
        return prefs.getInt(Const.PREF_BTN_SOFT_DROP_KEY, KeyEvent.VK_DOWN);
    }

    public static void setSoftDropBtnCode(int keyCode) {
        prefs.putInt(Const.PREF_BTN_SOFT_DROP_KEY, keyCode);
    }

    public static int getPauseBtnCode() {
        return prefs.getInt(Const.PREF_BTN_PAUSE_KEY, KeyEvent.VK_P);
    }

    public static void setPauseBtnCode(int keyCode) {
        prefs.putInt(Const.PREF_BTN_PAUSE_KEY, keyCode);
    }

    public static int getExitBtnCode() {
        return prefs.getInt(Const.PREF_BTN_EXIT_KEY, KeyEvent.VK_ESCAPE);
    }

    public static void setExitBtnCode(int keyCode) {
        prefs.putInt(Const.PREF_BTN_EXIT_KEY, keyCode);
    }

    public static ColorBlindnessType getColorBlindnessType() {
        return ColorBlindnessType.toEnum(
                prefs.get(Const.PREF_COLOR_BLINDNESS_KEY, ColorBlindnessType.NONE.name()));
    }

    public static void setColorBlindnessType(ColorBlindnessType colorBlindnessType) {
        prefs.put(Const.PREF_COLOR_BLINDNESS_KEY, colorBlindnessType.name());
        try {
            prefs.flush();
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }
    }

    public static void clear() {
        try {
            prefs.clear();
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }
    }

}
