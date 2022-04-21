package org.teamseven.tetris.ui;

import org.teamseven.tetris.Const;
import org.teamseven.tetris.Pipeline;
import org.teamseven.tetris.score.Score;
import org.teamseven.tetris.handler.ScoreHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.text.AttributedString;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.teamseven.tetris.Const.SCORE_ITEM_FILE;
import static org.teamseven.tetris.Const.SCORE_NORMAL_FILE;

public class ScoreBoardTabbedPane extends JTabbedPane implements  IDesign {
    private JLabel lTime;
    private ScoreBoardPanelTab noItem, item;
    private int[] preferredResolution;  // frame resolution - frame top border
    private JLabel title;

    private JPanel noItemScorePanelTab, itemScorePanelTab;
    private JPanel titlePanel, buttonPanel;
    private JPanel scorePanel1st, scorePanel2st, scorePanel3st, scorePanel4st, scorePanel5st,
                    scorePanel6st, scorePanel7st, scorePanel8st, scorePanel9st, scorePanel10st;
    private  JPanel[] scorePanels;
    private JPanel line1st, line2st, line3st, line4st, line5st, line6st, line7st, line8st, line9st, line10st;

    private CustomButton leftButton, rightButton;

    private int pageNum = 0;
    private int totalPageNum = 0;
    private GridBagConstraints gridBagConstraints;
    private GridBagLayout gridBagLayout;
    private ScoreHandler handler = new ScoreHandler();

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

        noItem = new ScoreBoardPanelTab(preferredResolution,SCORE_NORMAL_FILE);
        item = new ScoreBoardPanelTab(preferredResolution,SCORE_ITEM_FILE);

    }

    @Override
    public void setDesign() {
        this.addTab("noitem",noItem);
        this.addTab("item",item);


    }

    @Override
    public void setAction() {

        this.setFocusable(false);
        Score score3 = new Score(222, "AABB");
        noItem.addScore(score3);


    }
}
