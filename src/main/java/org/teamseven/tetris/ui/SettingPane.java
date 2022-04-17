package org.teamseven.tetris.ui;

import javax.swing.*;

public class SettingPane extends JLayeredPane implements IDesign {

    private int[] preferredResolution;  // frame resolution - frame top border
    private CardSwitcher cardSwitcher;

    public SettingPane(CardSwitcher cardSwitcher, int[] preferredResolution) {
        this.preferredResolution = preferredResolution;
        this.cardSwitcher = cardSwitcher;

        setComp();
        setDesign();
        setAction();
    }

    @Override
    public void setComp() {

    }

    @Override
    public void setDesign() {

    }

    @Override
    public void setAction() {

    }
}
