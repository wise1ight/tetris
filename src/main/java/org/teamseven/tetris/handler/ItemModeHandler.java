package org.teamseven.tetris.handler;

public class ItemModeHandler {

    public int save;

    public boolean isNewItem(int totalErasedLines) {
        if (totalErasedLines != 0 && totalErasedLines % 10 == 0 && save != totalErasedLines) {
            save = totalErasedLines / 10;
            return true;
        }
        return false;
    }
}
