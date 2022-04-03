package org.teamseven.tetris.UI.StartMenu;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class StartMenuPane extends JLayeredPane implements IDesign {
    private JLabel title;
    private CustomButton startButton, settingButton, scoreButton, exitButton;
    private JButton settingBoard_ExitButton, scoreBoard_ExitButton;                         // exit button on popup panel
    private JPanel main;                                                                    // main panel on startMenuPane (DEFAULT_LAYER)     
    private JPanel titlePanel, buttonPannel1, buttonPannel2, buttonPannel3, buttonPannel4;  // BoardLayout, panels on main panel
    private JPanel settingBoard, scoreBoard;                                                // scoreBoard panel on startMenuPane (POPUP_LAYER)
    private GridBagConstraints gridBagConstraints;
    private GridBagLayout gridBagLayout;

    private int[] preferredResolution;  // frame resolution - frame top border
    private CardSwitcher cardSwitcher;

    public StartMenuPane(CardSwitcher cardSwitcher, int[] preferredResolution) {
        // for test
        this.setOpaque(true);
        this.setBackground(Color.orange);

        this.preferredResolution = preferredResolution;
        this.cardSwitcher = cardSwitcher;

        setComp();
        setDesign();
        setAction();
    }

    @Override
    public void setComp() {
        main = new JPanel();

        titlePanel = new JPanel(new BorderLayout());
        buttonPannel1 = new JPanel(new BorderLayout());
        buttonPannel2 = new JPanel(new BorderLayout());
        buttonPannel3 = new JPanel(new BorderLayout());
        buttonPannel4 = new JPanel(new BorderLayout());

        title = new JLabel("TETRIS");
        startButton = new CustomButton();
        settingButton = new CustomButton();
        scoreButton = new CustomButton();
        exitButton = new CustomButton();

        settingBoard_ExitButton = new JButton();
        scoreBoard_ExitButton = new JButton();
        scoreBoard = new JPanel();
        settingBoard = new JPanel();

        gridBagConstraints = new GridBagConstraints();
        gridBagLayout = new GridBagLayout();
    }

    @Override
    public void setDesign() {
        // set pannels in gridBagLayout on main panel
        main.setLayout(gridBagLayout);
        gridBagConstraints.fill = GridBagConstraints.BOTH;

        gridBagConstraints.weightx = 4.0;
        gridBagConstraints.weighty = 4.0;

        gridBagConstraints.insets = new Insets(15, 250, 15, 250);
        make(titlePanel, 1, 0, 1, 1);

        gridBagConstraints.weighty = 0.1;

        gridBagConstraints.insets = new Insets(15, 10, 15, 10);
        make(buttonPannel1, 1, 2, 1, 1);

        gridBagConstraints.insets = new Insets(15, 10, 15, 10);
        make(buttonPannel2, 1, 4, 1, 1);

        gridBagConstraints.insets = new Insets(15, 10, 15, 10);
        make(buttonPannel3, 1, 6, 1, 1);

        gridBagConstraints.insets = new Insets(15, 330, 30, 330);
        make(buttonPannel4, 1, 8, 1, 1);


        // set Label, Button
        title.setFont(new Font(title.getFont().getName(), title.getFont().getStyle(), preferredResolution[1] * 1 / 5));
        title.setHorizontalAlignment(JLabel.CENTER);

        startButton.setText("시작");
        settingButton.setText("설정");
        scoreButton.setText("스코어보드");
        exitButton.setText("게임 종료 메뉴");
        exitButton.setText("끄기");
        settingBoard_ExitButton.setPreferredSize(new Dimension(50, 50));
        scoreBoard_ExitButton.setPreferredSize(new Dimension(50, 50));


        // add components
        titlePanel.add(title);
        buttonPannel1.add(startButton);
        buttonPannel2.add(settingButton);
        buttonPannel3.add(scoreButton);
        buttonPannel4.add(exitButton);

        scoreBoard.add(scoreBoard_ExitButton);
        settingBoard.add(settingBoard_ExitButton);

        main.add(titlePanel);
        main.add(buttonPannel1);
        main.add(buttonPannel2);
        main.add(buttonPannel3);
        main.add(buttonPannel4);

        // set popUpPannel
        settingBoard.setBackground(Color.CYAN);
        settingBoard.setBounds(preferredResolution[0] * 1 / 10, preferredResolution[1] * 1 / 10,
                preferredResolution[0] * 8 / 10, preferredResolution[1] * 8 / 10);
        scoreBoard.setBackground(Color.GREEN);
        scoreBoard.setBounds(preferredResolution[0] * 1 / 10, preferredResolution[1] * 1 / 10,
                preferredResolution[0] * 8 / 10, preferredResolution[1] * 8 / 10);


        // set main pannel on StartMenuPane
        main.setBounds(0, 0, preferredResolution[0], preferredResolution[1]);
        this.add(main, JLayeredPane.DEFAULT_LAYER);
    }

    @Override
    public void setAction() {
        // set focusTraversal
        setFocusTraversal();

        // set ButtonAction
        startButton.addActionListener(e -> {
            System.out.println(e.getActionCommand());
            cardSwitcher.switchCard("jl1");
        });
        settingButton.addActionListener(e -> {
            this.add(settingBoard, JLayeredPane.POPUP_LAYER);
        });
        scoreButton.addActionListener(e -> {
            this.add(scoreBoard, JLayeredPane.POPUP_LAYER);
        });
        exitButton.addActionListener(e -> {
            ((JFrame) this.getTopLevelAncestor()).dispose();
        });
        settingBoard_ExitButton.addActionListener(e -> {
            this.remove(settingBoard);
            this.revalidate();
            this.repaint();
            settingButton.requestFocus();
        });
        scoreBoard_ExitButton.addActionListener(e -> {
            this.remove(scoreBoard);
            this.revalidate();
            this.repaint();
            scoreButton.requestFocus();
        });

    }

    public void make(JComponent c, int x, int y, int w, int h) {
        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;
        gridBagConstraints.gridwidth = w;
        gridBagConstraints.gridheight = h;
        gridBagLayout.setConstraints(c, gridBagConstraints);

        // for test

        /*
        c.setBackground(Color.getHSBColor((y-1)/9f,0.75f,0.95f));
        gridBagConstraints.insets = new Insets(15,10,15,10);
         */
    }

    // TAB -> up,down key
    public void setFocusTraversal() {
        KeyStroke DownKeyStroke = KeyStroke.getKeyStroke("DOWN");
        KeyStroke UpKeyStroke = KeyStroke.getKeyStroke("UP");

        Set<AWTKeyStroke> ForwardKey = new HashSet<AWTKeyStroke>(KeyboardFocusManager.getCurrentKeyboardFocusManager().
                getDefaultFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        ForwardKey.clear();
        ForwardKey.add(DownKeyStroke);
        KeyboardFocusManager.getCurrentKeyboardFocusManager().setDefaultFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, ForwardKey);

        Set<AWTKeyStroke> BackwardKey = new HashSet<AWTKeyStroke>(KeyboardFocusManager.getCurrentKeyboardFocusManager().
                getDefaultFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS));
        BackwardKey.clear();
        BackwardKey.add(UpKeyStroke);
        KeyboardFocusManager.getCurrentKeyboardFocusManager().setDefaultFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, BackwardKey);
    }

}
