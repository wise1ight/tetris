package org.teamseven.tetris.ui;

import org.teamseven.tetris.Pipeline;
import org.teamseven.tetris.handler.PreferencesHandler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class KeySettingPane extends Canvas {
    private static int sizeInt = Pipeline.getSizeInt();


    public Frame keySettingFrame;
    private Frame settingFrameIn;
    private Panel keySettingPanel, buttonPanel, titlePanel, discriptionPanel;

    private Button left, right, down, drop, rotate, backToSetting;

    private Button selected;


    public KeySettingPane(Frame settingFrame) {
        settingFrameIn = settingFrame;
        prepareKeySettingGUI(settingFrame);
    }

    private void prepareKeySettingGUI(Frame settingFrame) {
        keySettingFrame = new Frame("Seoultech SE4 Tetris");
        keySettingFrame.setSize(Pipeline.getScreenResolutionX(), Pipeline.getScreenResolutionY());
        keySettingFrame.setResizable(false);
        keySettingFrame.setLayout(null);
        keySettingFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        keySettingPanel = new Panel();
        keySettingPanel.setSize(Pipeline.getScreenResolutionX(), Pipeline.getScreenResolutionY());
        keySettingPanel.setBackground(Color.black);
        keySettingPanel.setLayout(null);
        keySettingPanel.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 8));


        titlePanel = new Panel();
        titlePanel.setBounds(0, sizeInt * 50, Pipeline.getScreenResolutionX(), sizeInt * 40);
        titlePanel.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 25));

        Label title = new Label("KeySetting");
        title.setForeground(Color.BLUE);

        titlePanel.add(title);

        buttonPanel = new Panel();
        buttonPanel.setBounds(sizeInt * 125, sizeInt * 110, Pipeline.getScreenResolutionX() - 2 * sizeInt * 125, sizeInt * 125);
        buttonPanel.setLayout(new GridLayout(6, 2));

        left = new Button("Left : " + PreferencesHandler.getLeftBtnCode());
        right = new Button("Right : " + PreferencesHandler.getRightBtnCode());
        down = new Button("Down : " + PreferencesHandler.getSoftDropBtnCode());
        rotate = new Button("Rotate : " + PreferencesHandler.getRotateRightBtnCode());
        drop = new Button("Drop : " + PreferencesHandler.getHardDropBtnCode());
        backToSetting = new Button("Back to Setting");


        buttonPanel.add(left);
        buttonPanel.add(right);
        buttonPanel.add(down);
        buttonPanel.add(drop);
        buttonPanel.add(rotate);
        buttonPanel.add(backToSetting);


        keySettingFrame.add(keySettingPanel);

        keySettingPanel.add(buttonPanel);
        keySettingPanel.add(titlePanel);

        backToSetting.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                settingFrame.setVisible(true);
                keySettingFrame.setVisible(false);
            }
        });
    }
}
