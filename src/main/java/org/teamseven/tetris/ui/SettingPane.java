package org.teamseven.tetris.ui;

import org.teamseven.tetris.Const;
import org.teamseven.tetris.Pipeline;
import org.teamseven.tetris.enums.ColorBlindnessType;
import org.teamseven.tetris.enums.ScreenSize;
import org.teamseven.tetris.handler.PreferencesHandler;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

public class SettingPane extends JLayeredPane implements IDesign {

    private int[] preferredResolution;  // frame resolution - frame top border
    private CardSwitcher cardSwitcher;

    JRadioButton rbSmallSize, rbMediumSize, rbLargeSize, rbNone, rbGreenBlindess, rbRedBlindess, rbBlueBlindess;
    JButton btnInitScoreboard, btnLeft, btnRight, btnRotateRight, btnPause, btnInit, btnConfirm;
    JLabel lScreenSize, lScoreboard, lBlindess;
    JPanel pScreenSize, pKeyboard, pScoreboard, pBlindess, pButton;
    GridBagLayout gb;
    GridBagConstraints gbc;

    public SettingPane(CardSwitcher cardSwitcher, int[] preferredResolution) {
        this.preferredResolution = preferredResolution;
        this.cardSwitcher = cardSwitcher;

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
         */
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
            게임 키 설정
         */
        pKeyboard = new JPanel();
        pKeyboard.setBorder(new TitledBorder(null, "키 설정", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GridLayout gridLayout = new GridLayout(4, 2);
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
        pKeyboard.add(new JLabel("일시 중지"));
        btnPause = new JButton("");
        pKeyboard.add(btnPause);

        /*
            스코어보드
         */
        lScoreboard = new JLabel("스코어보드 : ");
        pScoreboard = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnInitScoreboard = new JButton("초기화");
        pScoreboard.add(btnInitScoreboard);

        /*
            색각이상 모드
         */
        lBlindess = new JLabel("색각이상 모드 : ");
        pBlindess = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rbNone = new JRadioButton("끄기",true);
        rbGreenBlindess = new JRadioButton("녹색맹",true);
        rbRedBlindess = new JRadioButton("적색맹",true);
        rbBlueBlindess = new JRadioButton("청색맹",true);
        ButtonGroup groupBlindess = new ButtonGroup();
        groupBlindess.add(rbNone);
        groupBlindess.add(rbGreenBlindess);
        groupBlindess.add(rbRedBlindess);
        groupBlindess.add(rbBlueBlindess);
        pBlindess.add(rbNone);
        pBlindess.add(rbGreenBlindess);
        pBlindess.add(rbRedBlindess);
        pBlindess.add(rbBlueBlindess);

        /*
            하단 버튼
         */
        pButton = new JPanel();
        btnInit = new JButton("초기화");
        btnConfirm = new JButton("확인");
        pButton.add(btnInit);
        pButton.add(btnConfirm);
    }

    @Override
    public void setDesign() {
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        gbAdd(lScreenSize,0, 1, 1, 1);
        gbAdd(pScreenSize, 1, 1, 3, 1);

        gbAdd(pKeyboard, 0, 2, 4, 4);

        gbAdd(lScoreboard, 0,6,1,1);
        gbAdd(pScoreboard,1,6,3,1);

        gbAdd(lBlindess, 0,7,1,1);
        gbAdd(pBlindess,1,7,3,1);

        gbAdd(pButton, 0, 8, 4, 1);
    }

    @Override
    public void setAction() {
        // Screen Size
        ActionListener screenSizeActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                //AbstractButton aButton = (AbstractButton) actionEvent.getSource();
                if(rbSmallSize.isSelected()) {
                    PreferencesHandler.setScreenSize(ScreenSize.SMALL);
                } else if (rbMediumSize.isSelected()) {
                    PreferencesHandler.setScreenSize(ScreenSize.MEDIUM);
                } else if (rbLargeSize.isSelected()) {
                    PreferencesHandler.setScreenSize(ScreenSize.LARGE);
                }
            }
        };

        rbSmallSize.addActionListener(screenSizeActionListener);
        rbMediumSize.addActionListener(screenSizeActionListener);
        rbLargeSize.addActionListener(screenSizeActionListener);

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
                            else if (ae.getSource() == btnPause)
                                PreferencesHandler.setPauseBtnCode(e.getKeyCode());

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
        btnPause.addActionListener(keyboardActionListener);

        // Color Blindness
        ActionListener colorBlindnessActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if(rbNone.isSelected()) {
                    PreferencesHandler.setColorBlindnessType(ColorBlindnessType.NONE);
                } else if (rbRedBlindess.isSelected()) {
                    PreferencesHandler.setColorBlindnessType(ColorBlindnessType.RED);
                } else if (rbGreenBlindess.isSelected()) {
                    PreferencesHandler.setColorBlindnessType(ColorBlindnessType.GREEN);
                } else if (rbBlueBlindess.isSelected()) {
                    PreferencesHandler.setColorBlindnessType(ColorBlindnessType.BLUE);
                }
            }
        };

        rbNone.addActionListener(colorBlindnessActionListener);
        rbRedBlindess.addActionListener(colorBlindnessActionListener);
        rbGreenBlindess.addActionListener(colorBlindnessActionListener);
        rbBlueBlindess.addActionListener(colorBlindnessActionListener);

        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardSwitcher.switchCard(Const.SCENE_START_MENU);
            }
        });

        btnInit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PreferencesHandler.clear();
                refreshPref();
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

        // Keyboard
        btnLeft.setText(KeyEvent.getKeyText(PreferencesHandler.getLeftBtnCode()));
        btnRight.setText(KeyEvent.getKeyText(PreferencesHandler.getRightBtnCode()));
        btnRotateRight.setText(KeyEvent.getKeyText(PreferencesHandler.getRotateRightBtnCode()));
        btnPause.setText(KeyEvent.getKeyText(PreferencesHandler.getPauseBtnCode()));

        // Color Blindness
        switch (PreferencesHandler.getColorBlindnessType()) {
            case NONE:
                rbNone.setSelected(true);
                break;
            case RED:
                rbRedBlindess.setSelected(true);
                break;
            case GREEN:
                rbGreenBlindess.setSelected(true);
                break;
            case BLUE:
                rbBlueBlindess.setSelected(true);
                break;
        }
    }
}
