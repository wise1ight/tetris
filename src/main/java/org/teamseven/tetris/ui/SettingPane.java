package org.teamseven.tetris.ui;

import org.teamseven.tetris.Pipeline;
import org.teamseven.tetris.enums.ColorBlindnessType;
import org.teamseven.tetris.enums.Mode;
import org.teamseven.tetris.enums.ScreenSize;
import org.teamseven.tetris.handler.PreferencesHandler;
import org.teamseven.tetris.handler.ScoreMemoryHandler;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

public class SettingPane extends JLayeredPane implements IDesign {

    private Panel settingPanel, buttonPanel, titlePanel;
    private Button home, keySetting, scoreBoard, color, difficulty_level, size, reSetting;
    private Button selected;
    private int sizeInt;

    public SettingPane() {
        sizeInt = Pipeline.getSizeInt();
        setComp();
        setDesign();
        setAction();
    }

    @Override
    public void setComp() {
        settingPanel = new Panel();
        settingPanel.setSize(Pipeline.getScreenResolutionX(), Pipeline.getScreenResolutionY());
        settingPanel.setBackground(Color.black);
        settingPanel.setLayout(null);
        settingPanel.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 8));

        titlePanel = new Panel();
        titlePanel.setBounds(0, sizeInt * 50, Pipeline.getScreenResolutionX(), sizeInt * 40);
        titlePanel.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 25));
        Label title = new Label("Setting");
        title.setForeground(Color.RED);

        titlePanel.add(title);

        buttonPanel = new Panel();
        buttonPanel.setBounds(sizeInt * 100, sizeInt * 110, Pipeline.getScreenResolutionX() - sizeInt * 100 *2 , sizeInt * 125);
        buttonPanel.setLayout(new GridLayout(4, 2));

        keySetting = new Button("Key");
        scoreBoard = new Button("Reset Scoreboard");
        color = new Button("Color Blind Mode");
        difficulty_level = new Button("Level");
        size = new Button("Size");
        reSetting = new Button("Reset Setting");
        home = new Button("Home");

        selected = keySetting;

        buttonPanel.add(keySetting);
        buttonPanel.add(scoreBoard);
        buttonPanel.add(color);
        buttonPanel.add(difficulty_level);
        buttonPanel.add(size);
        buttonPanel.add(reSetting);
        buttonPanel.add(home);

        settingPanel.add(buttonPanel);
        settingPanel.add(titlePanel);

        this.add(settingPanel);

        if (PreferencesHandler.getColorBlindnessType() == ColorBlindnessType.NONE) {
            color.setLabel("Color Blind Mode : OFF");
        } else {
            color.setLabel("Color Blind Mode : ON");
        }

        difficulty_level.setLabel("Level : " + PreferencesHandler.getMode());
        size.setLabel("Size : " + PreferencesHandler.getScreenSize());
    }

    @Override
    public void setDesign() {

    }

    @Override
    public void setAction() {

    }

    /*
    private void refreshPref() {
        // Screen Size
        switch (PreferencesHandler.getScreenSize()) {
            case SMALL:
                rbSmallSize.setSelected(true);
                break;
            case MEDIUM:
                rbMediumSize.setSelected(true);
                break;
            case LARGE:
                rbLargeSize.setSelected(true);
                break;
        }

        // Game Mode
        switch (PreferencesHandler.getMode()) {
            case EASY:
                rbEasy.setSelected(true);
                break;
            case NORMAL:
                rbNormal.setSelected(true);
                break;
            case HARD:
                rbHard.setSelected(true);
                break;
        }

        // Keyboard
        btnLeft.setText(KeyEvent.getKeyText(PreferencesHandler.getLeftBtnCode()));
        btnRight.setText(KeyEvent.getKeyText(PreferencesHandler.getRightBtnCode()));
        btnRotateRight.setText(KeyEvent.getKeyText(PreferencesHandler.getRotateRightBtnCode()));
        btnHardDrop.setText(KeyEvent.getKeyText(PreferencesHandler.getHardDropBtnCode()));
        btnSoftDrop.setText(KeyEvent.getKeyText(PreferencesHandler.getSoftDropBtnCode()));
        btnPause.setText(KeyEvent.getKeyText(PreferencesHandler.getPauseBtnCode()));
        btnExit.setText(KeyEvent.getKeyText(PreferencesHandler.getExitBtnCode()));

        // Color Blindness
        switch (PreferencesHandler.getColorBlindnessType()) {
            case NONE:
                rbNone.setSelected(true);
                break;
            case BLINDNESS:
                rbColorBlindess.setSelected(true);
                break;
        }
    }

     */
}