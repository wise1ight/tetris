package org.teamseven.tetris.ui;

import static org.teamseven.tetris.Const.*;

public class MainFrame extends BaseFrame {

    public MainFrame() {
        this.setFrame(APPLICATION_TITLE);
    }

    @Override
    public void setComp() {
        getContentPane().add(new GameMenuPane());
    }

    @Override
    public void setDesign() {
        setSize(SCREEN_RESOLUTION_X, SCREEN_RESOLUTION_Y);
    }

    @Override
    public void setAction() {
    }

}

