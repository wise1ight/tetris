package org.teamseven.tetris.ui;

import org.teamseven.tetris.Pipeline;

import javax.swing.*;

public class ScoreBoardTabbedPane extends JTabbedPane implements IDesign {
    private ScoreBoardPanelTab noItem, item;
    private int[] preferredResolution;  // frame resolution - frame top border

    public ScoreBoardTabbedPane() {
        int[] frameBorderSize = new int[2];       // frame top border
        frameBorderSize[0] = this.getInsets().left + this.getInsets().right;
        frameBorderSize[1] = this.getInsets().top + this.getInsets().bottom;
        preferredResolution = new int[2];
        preferredResolution[0] = Pipeline.getScreenResolutionX() - frameBorderSize[0];
        preferredResolution[1] = Pipeline.getScreenResolutionY() - frameBorderSize[1];

        setComp();
        setDesign();
        setAction();
    }

    @Override
    public void setComp() {
        noItem = new ScoreBoardPanelTab(preferredResolution, false,-1);
        item = new ScoreBoardPanelTab(preferredResolution, true,-1);
    }

    @Override
    public void setDesign() {
        this.addTab("일반 게임 모드",noItem);
        this.addTab("아이템 모드",item);
    }

    @Override
    public void setAction() {

    }
}
