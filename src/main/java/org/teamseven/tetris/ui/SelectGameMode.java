package org.teamseven.tetris.ui;

import org.teamseven.tetris.Pipeline;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SelectGameMode extends Canvas {
    private static Frame homeFrameIn;
    public Frame selectGameModeFrame;

    private Panel gameModePanel, buttonPanel, titlePanel;
    private Button normalMode, itemMode, twoPlayerMode;
    private Button selected;
    private static int sizeInt = Pipeline.getSizeInt();

    public SelectGameMode(Frame homeFrame){
        homeFrameIn = homeFrame;
        setDesign(homeFrame);
    }
    
    public void setDesign(Frame hoemFrame){
        selectGameModeFrame = new Frame("Seoultech SE4 Tetris");
        selectGameModeFrame.setSize(Pipeline.getScreenResolutionX(), Pipeline.getScreenResolutionY());
        selectGameModeFrame.setResizable(false);
        selectGameModeFrame.setLayout(null);
        selectGameModeFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        
        gameModePanel = new Panel();
        gameModePanel.setSize(Pipeline.getScreenResolutionX(), Pipeline.getScreenResolutionY());
        gameModePanel.setBackground(Color.black);
        gameModePanel.setLayout(null);
        gameModePanel.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 8));

        titlePanel = new Panel();
        titlePanel.setBounds(0, sizeInt * 50, Pipeline.getScreenResolutionX(), sizeInt * 40);
        titlePanel.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 25));
        Label title = new Label("Select Game Mode");
        title.setForeground(Color.RED);

        titlePanel.add(title);

        buttonPanel = new Panel();
        buttonPanel.setBounds(sizeInt * 50, sizeInt * 110, Pipeline.getScreenResolutionX() - sizeInt * 50 *2 , sizeInt * 150);
        buttonPanel.setLayout(new GridLayout(1, 5));

        normalMode = new Button("Normal Mode");
        itemMode = new Button("Item Mode");
        twoPlayerMode = new Button("Two Player Mode");

        selected = normalMode;

        buttonPanel.add(normalMode);
        buttonPanel.add(itemMode);
        buttonPanel.add(twoPlayerMode);

        gameModePanel.add(buttonPanel);
        gameModePanel.add(titlePanel);

        selectGameModeFrame.add(gameModePanel);

    }

    public void setAction(){
        normalMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected.setForeground(Color.black);
                selected = normalMode;
                selected.setForeground(Color.gray);
                //TetrisPane tetrisPane = new TetrisPane(false);

                //tetrisPane.setVisible(true);
                selectGameModeFrame.setVisible(false);
            }
        });

        itemMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected.setForeground(Color.black);
                selected = normalMode;
                selected.setForeground(Color.gray);
                //Pipeline.replacePane(new TetrisPane(true));
                //SelectGameMode.selectGameModeFrame.setVisible(true);
                //homeFrame.setVisible(false);
            }
        });

        twoPlayerMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected.setForeground(Color.black);
                selected = normalMode;
                selected.setForeground(Color.gray);

                //SelectGameMode.selectGameModeFrame.setVisible(true);
                //homeFrame.setVisible(false);
            }
        });
    }
}
