package org.teamseven.tetris.ui;

import org.teamseven.tetris.Pipeline;
import org.teamseven.tetris.enums.ColorBlindnessType;
import org.teamseven.tetris.enums.Mode;
import org.teamseven.tetris.enums.ScreenSize;
import org.teamseven.tetris.handler.PreferencesHandler;
import org.teamseven.tetris.handler.ScoreMemoryHandler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SettingPane extends Canvas {
    private static int sizeInt = Pipeline.getSizeInt();


    public Frame settingFrame;
    private static Frame homeFrameIn;
    private Panel settingPanel, buttonPanel, titlePanel;

//    public KeySetting keySettingGUI;


    private Button home, keySetting, scoreBoard, color, difficulty_level, size, reSetting;
    private Button selected;

    private List<Button> buttonList = new ArrayList<>();

    public SettingPane(Frame homeFrame) {
        homeFrameIn = homeFrame;
        prepareSettingGUI(homeFrame);
        setAction();
//        keySettingGUI = new KeySetting(settingFrame);
    }

    private void prepareSettingGUI(Frame homeFrame) {
        settingFrame = new Frame("Seoultech SE4 Tetris");
        settingFrame.setSize(Pipeline.getScreenResolutionX(), Pipeline.getScreenResolutionY());
        settingFrame.setResizable(false);
        settingFrame.setLayout(null);
        settingFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

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
        settingFrame.add(settingPanel);
        settingPanel.add(titlePanel);

        settingFrame.setVisible(false);

        if (PreferencesHandler.getColorBlindnessType() == ColorBlindnessType.NONE) {
            color.setLabel("Color Blind Mode : OFF");
        } else {
            color.setLabel("Color Blind Mode : ON");
        }

        difficulty_level.setLabel("Level : " + PreferencesHandler.getMode());
        size.setLabel("Size : " + PreferencesHandler.getScreenSize());
    }

    private void setAction(){
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeFrameIn.setVisible(true);
                settingFrame.setVisible(false);
                homeFrameIn.requestFocus();
                scoreBoard.setLabel("Reset Scoreboard");
            }
        });

        keySetting.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reSetting.setLabel("Reset Setting");
                selected.setForeground(Color.black);
                selected = keySetting;
                selected.setForeground(Color.gray);
               // keySettingGUI.keySettingFrame.setVisible(true);
              //  settingFrame.setVisible(false);

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

}
/*


import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

public class SettingPane extends JLayeredPane implements IDesign {

    JRadioButton rbSmallSize, rbMediumSize, rbLargeSize, rbEasy, rbNormal, rbHard, rbNone, rbColorBlindess;
    JButton btnInitScoreboard, btnLeft, btnRight, btnRotateRight, btnHardDrop, btnSoftDrop, btnPause, btnExit, btnInit, btnConfirm;
    JLabel lScreenSize, lGameMode, lScoreboard, lBlindess;
    JPanel pScreenSize, pGameMode, pKeyboard, pScoreboard, pBlindess, pButton;
    GridBagLayout gb;
    GridBagConstraints gbc;

    public SettingPane() {
        setComp();
        setDesign();
        setAction();
    }

    @Override
    public void setComp() {
        gb = new GridBagLayout();
        setLayout(gb);
        gbc = new GridBagConstraints();

        /*
            게임 화면 크기

        lScreenSize = new JLabel("화면 크기 : ");
        pScreenSize = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rbSmallSize = new JRadioButton("작게",true);
        rbMediumSize = new JRadioButton("보통",true);
        rbLargeSize = new JRadioButton("크게",true);
        ButtonGroup groupScreenSize = new ButtonGroup();
        groupScreenSize.add(rbSmallSize);
        groupScreenSize.add(rbMediumSize);
        groupScreenSize.add(rbLargeSize);
        pScreenSize.add(rbSmallSize);
        pScreenSize.add(rbMediumSize);
        pScreenSize.add(rbLargeSize);

        /*
            일반 게임 모드 난이도 설정

        lGameMode = new JLabel("일반 게임모드 난이도 : ");
        pGameMode = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rbEasy = new JRadioButton("Easy",true);
        rbNormal = new JRadioButton("Normal",true);
        rbHard = new JRadioButton("Hard",true);
        ButtonGroup groupGameMOde = new ButtonGroup();
        groupGameMOde.add(rbEasy);
        groupGameMOde.add(rbNormal);
        groupGameMOde.add(rbHard);
        pGameMode.add(rbEasy);
        pGameMode.add(rbNormal);
        pGameMode.add(rbHard);

        /*
            게임 키 설정

        pKeyboard = new JPanel();
        pKeyboard.setBorder(new TitledBorder(null, "키 설정", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GridLayout gridLayout = new GridLayout(10, 2);
        pKeyboard.setLayout(gridLayout);
        pKeyboard.add(new JLabel("왼쪽 이동"));
        btnLeft = new JButton("");
        pKeyboard.add(btnLeft);
        pKeyboard.add(new JLabel("오른쪽 이동"));
        btnRight = new JButton("");
        pKeyboard.add(btnRight);
        pKeyboard.add(new JLabel("시계 방향 회전"));
        btnRotateRight = new JButton("");
        pKeyboard.add(btnRotateRight);
        pKeyboard.add(new JLabel("하드 드롭"));
        btnHardDrop = new JButton("");
        pKeyboard.add(btnHardDrop);
        pKeyboard.add(new JLabel("소프트 드롭"));
        btnSoftDrop = new JButton("");
        pKeyboard.add(btnSoftDrop);
        pKeyboard.add(new JLabel("일시 중지"));
        btnPause = new JButton("");
        pKeyboard.add(btnPause);
        pKeyboard.add(new JLabel("게임 종료"));
        btnExit = new JButton("");
        pKeyboard.add(btnExit);

        /*
            스코어보드

        lScoreboard = new JLabel("스코어보드 : ");
        pScoreboard = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnInitScoreboard = new JButton("초기화");
        pScoreboard.add(btnInitScoreboard);

        /*
            색각이상 모드

        lBlindess = new JLabel("색각이상 모드 : ");
        pBlindess = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rbNone = new JRadioButton("끄기",true);
        rbColorBlindess = new JRadioButton("켜기",true);
        ButtonGroup groupBlindess = new ButtonGroup();
        groupBlindess.add(rbNone);
        groupBlindess.add(rbColorBlindess);
        pBlindess.add(rbNone);
        pBlindess.add(rbColorBlindess);

        /*
            하단 버튼

        pButton = new JPanel();
        btnInit = new JButton("초기화");
        btnConfirm = new JButton("확인");
        pButton.add(btnInit);
  //      pButton.add(btnConfirm);
    }

    @Override
    public void setDesign() {
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        gbAdd(lScreenSize,0, 1, 1, 1);
        gbAdd(pScreenSize, 1, 1, 3, 1);

        gbAdd(lGameMode, 0, 2, 1, 1);
        gbAdd(pGameMode, 1, 2, 3, 1);

        gbAdd(pKeyboard, 0, 3, 4, 4);

        gbAdd(lScoreboard, 0,7,1,1);
        gbAdd(pScoreboard,1,7,3,1);

        gbAdd(lBlindess, 0,8,1,1);
        gbAdd(pBlindess,1,8,3,1);

        gbAdd(pButton, 0, 9, 4, 1);
    }

    @Override
    public void setAction() {
        // Screen Size
        ActionListener screenSizeActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                //AbstractButton aButton = (AbstractButton) actionEvent.getSource();
                if(rbSmallSize.isSelected()) {
                    PreferencesHandler.setScreenSize(ScreenSize.SMALL);
                    Pipeline.changeScreenSize(ScreenSize.SMALL);
                } else if (rbMediumSize.isSelected()) {
                    PreferencesHandler.setScreenSize(ScreenSize.MEDIUM);
                    Pipeline.changeScreenSize(ScreenSize.MEDIUM);
                } else if (rbLargeSize.isSelected()) {
                    PreferencesHandler.setScreenSize(ScreenSize.LARGE);
                    Pipeline.changeScreenSize(ScreenSize.LARGE);
                }
            }
        };

        rbSmallSize.addActionListener(screenSizeActionListener);
        rbMediumSize.addActionListener(screenSizeActionListener);
        rbLargeSize.addActionListener(screenSizeActionListener);

        // Game Mode
        ActionListener gameModeActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if(rbEasy.isSelected()) {
                    PreferencesHandler.setMode(Mode.EASY);
                } else if (rbNormal.isSelected()) {
                    PreferencesHandler.setMode(Mode.NORMAL);
                } else if (rbHard.isSelected()) {
                    PreferencesHandler.setMode(Mode.HARD);
                }
            }
        };

        rbEasy.addActionListener(gameModeActionListener);
        rbNormal.addActionListener(gameModeActionListener);
        rbHard.addActionListener(gameModeActionListener);

        // Keyboard
        ActionListener keyboardActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(ae.getSource() instanceof JButton) {
                    JDialog dialog = new JDialog(Pipeline.getMainFrame(), "설정");
                    dialog.setModal(true);
                    JLabel label = new JLabel("키를 입력하세요");
                    dialog.add(label);
                    dialog.setLocationRelativeTo(null);
                    dialog.setSize(100, 100);
                    dialog.setResizable(false);
                    dialog.setFocusTraversalKeysEnabled(false);
                    dialog.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            refreshPref();
                        }
                    });
                    dialog.addKeyListener(new KeyListener() {
                        @Override
                        public void keyTyped(KeyEvent e) {

                        }

                        @Override
                        public void keyPressed(KeyEvent e) {
                            System.out.println(e.getKeyCode());
                            if (ae.getSource() == btnLeft)
                                PreferencesHandler.setLeftBtnCode(e.getKeyCode());
                            else if (ae.getSource() == btnRight)
                                PreferencesHandler.setRightBtnCode(e.getKeyCode());
                            else if (ae.getSource() == btnRotateRight)
                                PreferencesHandler.setRotateRightBtnCode(e.getKeyCode());
                            else if (ae.getSource() == btnHardDrop)
                                PreferencesHandler.setHardDropBtnCode(e.getKeyCode());
                            else if (ae.getSource() == btnSoftDrop)
                                PreferencesHandler.setSoftDropBtnCode(e.getKeyCode());
                            else if (ae.getSource() == btnPause)
                                PreferencesHandler.setPauseBtnCode(e.getKeyCode());
                            else if (ae.getSource() == btnExit)
                                PreferencesHandler.setExitBtnCode(e.getKeyCode());

                            dialog.dispose();
                        }

                        @Override
                        public void keyReleased(KeyEvent e) {

                        }
                    });
                    dialog.setVisible(true);
                }
            }
        };

        btnLeft.addActionListener(keyboardActionListener);
        btnRight.addActionListener(keyboardActionListener);
        btnRotateRight.addActionListener(keyboardActionListener);
        btnHardDrop.addActionListener(keyboardActionListener);
        btnSoftDrop.addActionListener(keyboardActionListener);
        btnPause.addActionListener(keyboardActionListener);
        btnExit.addActionListener(keyboardActionListener);

        // Color Blindness
        ActionListener colorBlindnessActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if(rbNone.isSelected()) {
                    PreferencesHandler.setColorBlindnessType(ColorBlindnessType.NONE);
                } else if (rbColorBlindess.isSelected()) {
                    PreferencesHandler.setColorBlindnessType(ColorBlindnessType.BLINDNESS);
                }
            }
        };

        rbNone.addActionListener(colorBlindnessActionListener);
        rbColorBlindess.addActionListener(colorBlindnessActionListener);

        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pipeline.replacePane(new GameMenuPane());
            }
        });

        btnInitScoreboard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog((Component) null, "정말 스코어보드를 초기화 하시겠습니까?",
                        "확인", JOptionPane.OK_CANCEL_OPTION);
                if(result == 0) {
                    ScoreMemoryHandler scoreMemoryHandler = new ScoreMemoryHandler();
                    scoreMemoryHandler.clear();
                }
            }
        });

        btnInit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog((Component) null, "정말 스코어보드와 게임 설정을 초기화 하시겠습니까?",
                        "확인", JOptionPane.OK_CANCEL_OPTION);
                if(result == 0) {
                    ScoreMemoryHandler scoreMemoryHandler = new ScoreMemoryHandler();
                    scoreMemoryHandler.clear();
                    PreferencesHandler.clear();
                    refreshPref();
                }
            }
        });

        refreshPref();
    }

    private void gbAdd(JComponent c, int x, int y, int w, int h){
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        gbc.insets = new Insets(2, 2, 2, 2);
        add(c, gbc);
    }

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
}
 */