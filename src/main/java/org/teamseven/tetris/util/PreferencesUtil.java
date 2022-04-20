package org.teamseven.tetris.util;

import org.teamseven.tetris.Const;

import java.awt.event.KeyEvent;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class PreferencesUtil {

    private static final Preferences prefs = Preferences.userNodeForPackage(PreferencesUtil.class);

    public static Const.ScreenSize getScreenSize() {
        return Const.ScreenSize.toEnum(prefs.get(Const.PREF_SCREEN_SIZE_KEY, Const.ScreenSize.MEDIUM.name()));
    }

    public static void setScreenSize(Const.ScreenSize screenSize) {
        prefs.put(Const.PREF_SCREEN_SIZE_KEY, screenSize.name());
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

    public static int getPauseBtnCode() {
        return prefs.getInt(Const.PREF_BTN_PAUSE_KEY, KeyEvent.VK_ESCAPE);
    }

    public static void setPauseBtnCode(int keyCode) {
        prefs.putInt(Const.PREF_BTN_PAUSE_KEY, keyCode);
    }

    public static Const.ColorBlindnessType getColorBlindnessType() {
        return Const.ColorBlindnessType.toEnum(
                prefs.get(Const.PREF_COLOR_BLINDNESS_KEY, Const.ColorBlindnessType.NONE.name()));
    }

    public static void setColorBlindnessType(Const.ColorBlindnessType colorBlindnessType) {
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
