package org.teamseven.tetris.ui;

import org.teamseven.tetris.Pipeline;

import javax.swing.*;

public abstract class BasePane extends JLayeredPane implements IDesign {

    protected int sizeInt;

    public BasePane() {
        sizeInt = Pipeline.getSizeInt();
        setComp();
        setDesign();
        setAction();
    }
}
