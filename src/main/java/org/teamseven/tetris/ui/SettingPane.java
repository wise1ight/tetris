package org.teamseven.tetris.ui;

import org.teamseven.tetris.Const;
import org.teamseven.tetris.util.PreferencesUtil;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        btnLeft = new JButton("LEFT ARROW");
        pKeyboard.add(btnLeft);
        pKeyboard.add(new JLabel("오른쪽 이동"));
        btnRight = new JButton("RIGHT ARROW");
        pKeyboard.add(btnRight);
        pKeyboard.add(new JLabel("시계 방향 회전"));
        btnRotateRight = new JButton("RIGHT ARROW");
        pKeyboard.add(btnRotateRight);
        pKeyboard.add(new JLabel("일시 중지"));
        btnPause = new JButton("P");
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
                    PreferencesUtil.setScreenSize(Const.ScreenSize.SMALL);
                } else if (rbMediumSize.isSelected()) {
                    PreferencesUtil.setScreenSize(Const.ScreenSize.MEDIUM);
                } else if (rbLargeSize.isSelected()) {
                    PreferencesUtil.setScreenSize(Const.ScreenSize.LARGE);
                }
            }
        };

        rbSmallSize.addActionListener(screenSizeActionListener);
        rbMediumSize.addActionListener(screenSizeActionListener);
        rbLargeSize.addActionListener(screenSizeActionListener);

        // Color Blindness
        ActionListener colorBlindnessActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if(rbNone.isSelected()) {
                    PreferencesUtil.setColorBlindnessType(Const.ColorBlindnessType.NONE);
                } else if (rbRedBlindess.isSelected()) {
                    PreferencesUtil.setColorBlindnessType(Const.ColorBlindnessType.RED);
                } else if (rbGreenBlindess.isSelected()) {
                    PreferencesUtil.setColorBlindnessType(Const.ColorBlindnessType.GREEN);
                } else if (rbBlueBlindess.isSelected()) {
                    PreferencesUtil.setColorBlindnessType(Const.ColorBlindnessType.BLUE);
                }
            }
        };

        rbNone.addActionListener(colorBlindnessActionListener);
        rbRedBlindess.addActionListener(colorBlindnessActionListener);
        rbGreenBlindess.addActionListener(colorBlindnessActionListener);
        rbBlueBlindess.addActionListener(colorBlindnessActionListener);

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
        switch (PreferencesUtil.getScreenSize()) {
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

        // Color Blindness
        switch (PreferencesUtil.getColorBlindnessType()) {
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
