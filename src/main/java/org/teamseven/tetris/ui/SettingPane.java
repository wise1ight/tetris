package org.teamseven.tetris.ui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class SettingPane extends JLayeredPane implements IDesign {

    private int[] preferredResolution;  // frame resolution - frame top border
    private CardSwitcher cardSwitcher;

    JRadioButton rbSmallSize, rbMediumSize, rbLargeSize, rbNormal, rbGreenBlindess, rbRedBlindess, rbBrueBlindess;
    JButton btnInitScoreboard, btnLeft, btnRight, btnRotateRight, btnPause, btnInit, btnApply, btnConfirm;
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
        rbNormal = new JRadioButton("일반",true);
        rbGreenBlindess = new JRadioButton("녹색맹",true);
        rbRedBlindess = new JRadioButton("적색맹",true);
        rbBrueBlindess = new JRadioButton("청색맹",true);
        ButtonGroup groupBlindess = new ButtonGroup();
        groupBlindess.add(rbNormal);
        groupBlindess.add(rbGreenBlindess);
        groupBlindess.add(rbRedBlindess);
        groupBlindess.add(rbBrueBlindess);
        pBlindess.add(rbNormal);
        pBlindess.add(rbGreenBlindess);
        pBlindess.add(rbRedBlindess);
        pBlindess.add(rbBrueBlindess);

        /*
            하단 버튼
         */
        pButton = new JPanel();
        btnInit = new JButton("초기화");
        btnApply = new JButton("적용");
        btnConfirm = new JButton("확인");
        pButton.add(btnInit);
        pButton.add(btnApply);
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

    }

    private void gbAdd(JComponent c, int x, int y, int w, int h){
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        gbc.insets = new Insets(2, 2, 2, 2);
        add(c, gbc);
    }
}
