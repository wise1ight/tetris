package org.teamseven.tetris.util;

import org.teamseven.tetris.Const;

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
