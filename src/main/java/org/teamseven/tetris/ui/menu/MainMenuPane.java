package org.teamseven.tetris.ui.menu;

import org.teamseven.tetris.Pipeline;
import org.teamseven.tetris.ui.IDesign;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainMenuPane extends JLayeredPane implements IDesign {

    private Label title;
    private Panel homePanel, titlePanel, buttonPanel;
    private CustomButton start, scoreBoard, setting, exit;
    private java.util.List<CustomButton> buttonList = new ArrayList<>();

   // private int[] preferredResolution;  // frame resolution - frame top border

    private static int sizeInt;

    public MainMenuPane() {
//        preferredResolution = new int[2];
  //      preferredResolution[0] = Pipeline.getScreenResolutionX();
    //    preferredResolution[1] = Pipeline.getScreenResolutionY();

        sizeInt = Pipeline.getSizeInt();
        setComp();
        setDesign();
        setAction();
    }

    @Override
    public void setComp() {
        homePanel = new Panel();
        titlePanel = new Panel();

        title = new Label("SE4 Tetris");

        titlePanel.add(title);

        buttonPanel = new Panel();

        start = new CustomButton("Start");
        scoreBoard = new CustomButton("Scoreboard");
        setting = new CustomButton("Setting");
        exit = new CustomButton("Exit");

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
    }

    @Override
    public void setDesign() {
        homePanel.setSize(Pipeline.getScreenResolutionX(),Pipeline.getScreenResolutionY());
        homePanel.setBackground(Color.black);
        homePanel.setLayout(null);
        homePanel.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 8));

        titlePanel.setBounds(0, sizeInt * 50, Pipeline.getScreenResolutionX(), sizeInt * 40);
        titlePanel.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 25));

        buttonPanel.setBounds(sizeInt * 110, sizeInt * 110, Pipeline.getScreenResolutionX() - sizeInt * 110 * 2, sizeInt * 150);
        buttonPanel.setLayout(new GridLayout(5, 1));

        title.setForeground(Color.RED);

        homePanel.setBounds(0, 0, Pipeline.getScreenResolutionX(), Pipeline.getScreenResolutionY());
        this.add(homePanel, JLayeredPane.DEFAULT_LAYER);
    }

    @Override
    public void setAction() {
        // set focusTraversal
        setFocusTraversal();

        // set ButtonAction
        start.addActionListener(e -> {
            Pipeline.replacePane(new SelectGameModePane());
        });
        setting.addActionListener(e -> {
            Pipeline.replacePane(new SettingPane());
        });
        scoreBoard.addActionListener(e -> {
            Pipeline.replacePane(new SelectScoreBoardPane());
        });
        exit.addActionListener(e -> {
            System.exit(0);
        });

        /*
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

    class CustomButton extends Button {

        public CustomButton(String label) {
            super(label);

            // set Inputkey(focus)

            // set Listners
            //this.removeMouseListener(this.getMouseListeners()[0]);
            this.addFocusListener(focusListener);

            this.setFont(new Font("Courier", Font.ITALIC,sizeInt * 6));
        }

        //focus animation
        private FocusListener focusListener = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                CustomButton.this.setFont(new Font("Courier", Font.ITALIC,sizeInt * 10));
            }

            @Override
            public void focusLost(FocusEvent e) {
                CustomButton.this.setFont(new Font("Courier", Font.ITALIC,sizeInt * 6));
            }
        };

    }
}