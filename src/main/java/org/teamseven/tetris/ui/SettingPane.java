package org.teamseven.tetris.ui;

import org.teamseven.tetris.Pipeline;
import org.teamseven.tetris.enums.ColorBlindnessType;
import org.teamseven.tetris.handler.PreferencesHandler;

import javax.swing.*;
import java.awt.*;

public class SettingPane extends JLayeredPane implements IDesign {

    private Panel settingPanel, buttonPanel, titlePanel;
    private Button home, keySetting, scoreBoard, color, difficulty_level, size, reSetting;
    private Button selected;
    private Label title;
    private int sizeInt;

    public SettingPane() {
        sizeInt = Pipeline.getSizeInt();
        setComp();
        setDesign();
        setAction();
        refreshPref();
    }

    @Override
    public void setComp() {
        settingPanel = new Panel();

        titlePanel = new Panel();
        title = new Label("Setting");

        titlePanel.add(title);

        buttonPanel = new Panel();

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
    }

    @Override
    public void setDesign() {
        settingPanel.setSize(Pipeline.getScreenResolutionX(), Pipeline.getScreenResolutionY());
        settingPanel.setBackground(Color.black);
        settingPanel.setLayout(null);
        settingPanel.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 8));

        titlePanel.setBounds(0, sizeInt * 50, Pipeline.getScreenResolutionX(), sizeInt * 40);
        titlePanel.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 25));

        title.setForeground(Color.RED);

        buttonPanel.setBounds(sizeInt * 100, sizeInt * 110, Pipeline.getScreenResolutionX() - sizeInt * 100 *2 , sizeInt * 125);
        buttonPanel.setLayout(new GridLayout(4, 2));
    }

    @Override
    public void setAction() {

    }

    private void refreshPref() {
        if (PreferencesHandler.getColorBlindnessType() == ColorBlindnessType.NONE) {
            color.setLabel("Color Blind Mode : OFF");
        } else {
            color.setLabel("Color Blind Mode : ON");
        }

        difficulty_level.setLabel("Level : " + PreferencesHandler.getMode());
        size.setLabel("Size : " + PreferencesHandler.getScreenSize());
    }
}