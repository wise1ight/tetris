package org.teamseven.tetris.ui;


import org.teamseven.tetris.Const;
import org.teamseven.tetris.Pipeline;
import org.teamseven.tetris.ui.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class GameMenuPane extends Canvas {
    /*
       private JLabel title;

       private CustomButton startButton, settingButton, scoreButton, exitButton, noItemModeButton, ItemModeButton, backButton;
       private JButton settingBoard_ExitButton, scoreBoard_ExitButton;                         // exit button on popup panel
       private JPanel main;                                                                    // main panel on startMenuPane (DEFAULT_LAYER)
       //  private JPanel titlePanel, buttonPannel1, buttonPannel2, buttonPannel3, buttonPannel4;  // BoardLayout, panels on main panel
       private JPanel buttonPannel1, buttonPannel2, buttonPannel3, buttonPannel4;  // BoardLayout, panels on main panel
       private JPanel settingBoard, modeBoard;                                                // scoreBoard panel on startMenuPane (POPUP_LAYER)
       private GridBagConstraints gridBagConstraints;
       private GridBagLayout gridBagLayout;
   */
    private int[] preferredResolution;  // frame resolution - frame top border


    public Frame homeFrame;
    public SettingPane settingPane;

    private Panel homePanel, titlePanel, buttonPanel;
    static private int sizeInt = Pipeline.getSizeInt();

    private Button start, item, scoreBoard, setting, exit;
    private Button selected;
    private List<Button> buttonList = new ArrayList<>();


    public GameMenuPane() {
        // for test
        //  this.setOpaque(true);
        //this.setBackground(Color.orange);

        //  int[] frameBorderSize = new int[2];       // frame top border
        //  frameBorderSize[0] = this.getInsets().left + this.getInsets().right;
        //   frameBorderSize[1] = this.getInsets().top + this.getInsets().bottom;
        preferredResolution = new int[2];
        preferredResolution[0] = Pipeline.getScreenResolutionX();
        preferredResolution[1] = Pipeline.getScreenResolutionY();

        setDesign();
        setAction();
        settingPane = new SettingPane(homeFrame);

    }

    public void setComp() {

        setDesign();
    }

    public void setDesign() {
        homeFrame = new Frame("Seoultech SE4 Tetris");
        homeFrame.setSize(preferredResolution[0], preferredResolution[1]);
        homeFrame.setResizable(true);
        homeFrame.setLayout(null);
        homeFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        homePanel = new Panel();
        homePanel.setSize(preferredResolution[0], preferredResolution[1]);
        homePanel.setBackground(Color.black);
        homePanel.setLayout(null);
        homePanel.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 8));

        titlePanel = new Panel();
        titlePanel.setBounds(0, sizeInt * 50, preferredResolution[0], sizeInt * 40);
        titlePanel.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 25));

        Label title = new Label("SE4 Tetris");
        title.setForeground(Color.RED);

        titlePanel.add(title);

        buttonPanel = new Panel();
        buttonPanel.setBounds(sizeInt * 110, sizeInt * 110, preferredResolution[0] - sizeInt * 110 * 2, sizeInt * 150);
        buttonPanel.setLayout(new GridLayout(5, 1));

        start = new Button("start");
        scoreBoard = new Button("Scoreboard");
        setting = new Button("Setting");
        exit = new Button("Exit");

        selected = start;
        selected.setForeground(Color.gray);
        buttonList.add(start);
        buttonList.add(scoreBoard);
        buttonList.add(setting);
        buttonList.add(exit);

        buttonPanel.add(start);
        buttonPanel.add(scoreBoard);
        buttonPanel.add(setting);
        buttonPanel.add(exit);

        homePanel.add(titlePanel);
        homePanel.add(buttonPanel);

        homeFrame.add(homePanel);

        homeFrame.setVisible(true);

        //   homeFrame.addKeyListener();
        homeFrame.requestFocus();
    }

    public void setAction(){
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected.setForeground(Color.black);
                selected = start;
                selected.setForeground(Color.gray);
                // gameGUI.gameFrame.setVisible(true);
                //   homeFrame.setVisible(false);
            }
        });
        scoreBoard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected.setForeground(Color.black);
                selected = scoreBoard;
                selected.setForeground(Color.gray);
                //     scoreBoardGUI.scoreBoardFrame.setVisible(true);
                //      homeFrame.setVisible(false);
                //     scoreBoardGUI.scoreBoardFrame.requestFocus();
            }
        });

        setting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected.setForeground(Color.black);
                selected = setting;
                selected.setForeground(Color.gray);
                settingPane.settingFrame.setVisible(true);
                homeFrame.setVisible(false);
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


    }
}




/*


import org.teamseven.tetris.Const;
import org.teamseven.tetris.Pipeline;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class GameMenuPane extends JLayeredPane implements IDesign {
    private JLabel title;
    private CustomButton startButton, settingButton, scoreButton, exitButton, noItemModeButton, ItemModeButton, backButton;
    private JButton settingBoard_ExitButton, scoreBoard_ExitButton;                         // exit button on popup panel
    private JPanel main;                                                                    // main panel on startMenuPane (DEFAULT_LAYER)
    private JPanel titlePanel, buttonPannel1, buttonPannel2, buttonPannel3, buttonPannel4;  // BoardLayout, panels on main panel
    private JPanel settingBoard, modeBoard;                                                // scoreBoard panel on startMenuPane (POPUP_LAYER)
    private GridBagConstraints gridBagConstraints;
    private GridBagLayout gridBagLayout;

    private int[] preferredResolution;  // frame resolution - frame top border

    public GameMenuPane() {
        // for test
        this.setOpaque(true);
        this.setBackground(Color.orange);

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
        main = new JPanel();

        titlePanel = new JPanel(new BorderLayout());
        buttonPannel1 = new JPanel(new BorderLayout());
        buttonPannel2 = new JPanel(new BorderLayout());
        buttonPannel3 = new JPanel(new BorderLayout());
        buttonPannel4 = new JPanel(new BorderLayout());

       buttonPannel1.setPreferredSize(new Dimension(0, preferredResolution[1] / 20));
        buttonPannel2.setPreferredSize(new Dimension(0, preferredResolution[1] / 20));
        buttonPannel3.setPreferredSize(new Dimension(0, preferredResolution[1] / 20));
        buttonPannel4.setPreferredSize(new Dimension(0, preferredResolution[1] / 20));

        title = new JLabel("TETRIS");
        startButton = new CustomButton();
        settingButton = new CustomButton();
        scoreButton = new CustomButton();
        exitButton = new CustomButton();
        noItemModeButton = new CustomButton();
        ItemModeButton = new CustomButton();
        backButton = new CustomButton();

        settingBoard_ExitButton = new JButton();
        settingBoard = new JPanel();
        modeBoard = new JPanel(new GridLayout(1, 2, preferredResolution[0] / 32, 0));

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

        gridBagConstraints.insets = new Insets(preferredResolution[1] / 40, 0, preferredResolution[1] / 40, 0);
        make(titlePanel, 1, 0, 1, 1);

        gridBagConstraints.weighty = 0.1;

        gridBagConstraints.insets = new Insets(preferredResolution[1] / 40, 0, preferredResolution[1] / 40, 0);
        make(buttonPannel1, 1, 2, 1, 1);

        gridBagConstraints.insets = new Insets(preferredResolution[1] / 40, 0, preferredResolution[1] / 40, 0);
        make(buttonPannel2, 1, 4, 1, 1);

        gridBagConstraints.insets = new Insets(preferredResolution[1] / 40, 0, preferredResolution[1] / 40, 0);
        make(buttonPannel3, 1, 6, 1, 1);

        gridBagConstraints.insets = new Insets(preferredResolution[1] / 40, 0, preferredResolution[1] / 20, 0);
        make(buttonPannel4, 1, 8, 1, 1);


        // set Label, Button
        title.setFont(new Font(title.getFont().getName(), title.getFont().getStyle(), preferredResolution[0] * 1 / 10));
        title.setHorizontalAlignment(JLabel.CENTER);


        startButton.setText("시작");

        settingButton.setText("설정");
        scoreButton.setText("스코어보드");
        exitButton.setText("게임 종료 메뉴");
        exitButton.setText("끄기");
        noItemModeButton.setText("일반 모드");
        noItemModeButton.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, preferredResolution[1] / 90));
        noItemModeButton.setBorderPainted(true);

        ItemModeButton.setText("아이템 모드");
        ItemModeButton.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, preferredResolution[1] / 90));
        ItemModeButton.setBorderPainted(true);

        backButton.setText("뒤로");
        backButton.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, preferredResolution[1] / 90));
        backButton.setBorderPainted(true);

        settingBoard_ExitButton.setPreferredSize(new Dimension(50, 50));


        // add components
        titlePanel.add(title);
        buttonPannel1.add(startButton);
        buttonPannel2.add(settingButton);
        buttonPannel3.add(scoreButton);
        buttonPannel4.add(exitButton);

        settingBoard.add(settingBoard_ExitButton);
        modeBoard.add(noItemModeButton);
        modeBoard.add(ItemModeButton);
        modeBoard.add(backButton);

        main.add(titlePanel);
        main.add(buttonPannel1);
        main.add(buttonPannel2);
        main.add(buttonPannel3);
        main.add(buttonPannel4);

        // set popUpPannel
        settingBoard.setBackground(Color.CYAN);
        settingBoard.setBounds(preferredResolution[0] * 1 / 10, preferredResolution[1] * 1 / 10,
                preferredResolution[0] * 8 / 10, preferredResolution[1] * 8 / 10);
        modeBoard.setBackground(Color.white);
        modeBoard.setBounds(preferredResolution[0] * 1 / 10, preferredResolution[1] * 1 / 10,
                preferredResolution[0] * 8 / 10, preferredResolution[1] * 8 / 10);
        modeBoard.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.lightGray, preferredResolution[1] / 180),
                BorderFactory.createEmptyBorder(preferredResolution[1] / 15, preferredResolution[1] / 15,
                        preferredResolution[1] / 15, preferredResolution[1] / 15)));

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
            //System.out.println(e.getActionCommand());
            //cardSwitcher.switchCard("jl1");
            this.add(modeBoard, JLayeredPane.POPUP_LAYER);
            noItemModeButton.requestFocus();
            startButton.setFocusable(false);
            settingButton.setFocusable(false);
            scoreButton.setFocusable(false);
            exitButton.setFocusable(false);

        });
        settingButton.addActionListener(e -> {
            Pipeline.replacePane(new SettingPane());
        });
        scoreButton.addActionListener(e -> {
            Pipeline.replacePane(new ScoreBoardTabbedPane());
        });
        exitButton.addActionListener(e -> {
            System.exit(0);
        });
        settingBoard_ExitButton.addActionListener(e -> {
            this.remove(settingBoard);
            this.revalidate();
            this.repaint();
            settingButton.requestFocus();
        });
        noItemModeButton.addActionListener(e -> {
            Pipeline.replacePane(new TetrisPane(false));
        });
        ItemModeButton.addActionListener(e -> {
            Pipeline.replacePane(new TetrisPane(true));
        });
        backButton.addActionListener(e -> {
            Pipeline.replacePane(new GameMenuPane());
        });
    }

    public void make(JComponent c, int x, int y, int w, int h) {
        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;
        gridBagConstraints.gridwidth = w;
        gridBagConstraints.gridheight = h;
        gridBagLayout.setConstraints(c, gridBagConstraints);

        // for test


        //c.setBackground(Color.getHSBColor((y-1)/9f,0.75f,0.95f));
        //  gridBagConstraints.insets = new Insets(15,10,15,10);

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
 */