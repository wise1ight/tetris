package org.teamseven.tetris.ui.menu;

import org.teamseven.tetris.Pipeline;
import org.teamseven.tetris.enums.ColorBlindnessType;
import org.teamseven.tetris.enums.Mode;
import org.teamseven.tetris.enums.ScreenSize;
import org.teamseven.tetris.handler.PreferencesHandler;
import org.teamseven.tetris.handler.ScoreMemoryHandler;
import org.teamseven.tetris.ui.IDesign;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingPane extends JLayeredPane implements IDesign {

    private Panel settingPanel, buttonPanel, titlePanel;
    private Button home, keySetting, scoreBoard, color, difficulty_level, size, reSetting, twoPlayerModeKey;
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
        twoPlayerModeKey = new Button("Two Player Mode Key");

        selected = keySetting;

        buttonPanel.add(keySetting);
        buttonPanel.add(twoPlayerModeKey);
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
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pipeline.replacePane(new MainMenuPane());
            }
        });

        keySetting.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pipeline.replacePane(new KeySettingPane());
            }
        });

        twoPlayerModeKey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pipeline.replacePane(new TwoPlayerModeKeyPane());
            }
        });

        color.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                reSetting.setLabel("Reset Setting");
                if (PreferencesHandler.getColorBlindnessType() == ColorBlindnessType.NONE) {
                    color.setLabel("Color Blind Mode : ON");
                    PreferencesHandler.setColorBlindnessType(ColorBlindnessType.BLINDNESS);
                }
                else {
                    color.setLabel("Color Blind Mode : OFF");
                    PreferencesHandler.setColorBlindnessType(ColorBlindnessType.NONE);
                }

            }

        });

        difficulty_level.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                reSetting.setLabel("Reset Setting");
                if ( PreferencesHandler.getMode() == Mode.EASY) {
                    PreferencesHandler.setMode(Mode.NORMAL);
                }
                else if (PreferencesHandler.getMode() == Mode.NORMAL) {
                    PreferencesHandler.setMode(Mode.HARD);
                }
                else if (PreferencesHandler.getMode() == Mode.HARD) {
                    PreferencesHandler.setMode(Mode.EASY);
                }
                difficulty_level.setLabel("Level : " +PreferencesHandler.getMode());


            }

        });

        size.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                reSetting.setLabel("Reset Setting");

                if (PreferencesHandler.getScreenSize() == ScreenSize.SMALL) {
                    PreferencesHandler.setScreenSize(ScreenSize.MEDIUM);
                }
                else if (PreferencesHandler.getScreenSize() == ScreenSize.MEDIUM) {
                    PreferencesHandler.setScreenSize(ScreenSize.LARGE);                }
                else if (PreferencesHandler.getScreenSize() == ScreenSize.LARGE) {
                    PreferencesHandler.setScreenSize(ScreenSize.SMALL);
                }
                size.setLabel("Size : " + PreferencesHandler.getScreenSize());

                Pipeline.applyScreenSize();
                Pipeline.replacePane(new SettingPane());
            }

        });

        scoreBoard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScoreMemoryHandler scoreMemoryHandler = new ScoreMemoryHandler();
                scoreMemoryHandler.clear();
                scoreBoard.setLabel("Reset Completed");

            }

        });
        reSetting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScoreMemoryHandler scoreMemoryHandler = new ScoreMemoryHandler();
                scoreMemoryHandler.clear();
                PreferencesHandler.clear();

                reSetting.setLabel("Reset Completed");
                color.setLabel("Color Blind Mode : OFF");
                difficulty_level.setLabel("Level : EASY" );
                size.setLabel("Size : MIDDLE");

            }
        });
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