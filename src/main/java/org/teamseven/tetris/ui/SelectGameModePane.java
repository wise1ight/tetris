package org.teamseven.tetris.ui;

import org.teamseven.tetris.Pipeline;
import org.teamseven.tetris.handler.GameHandler;
import org.teamseven.tetris.handler.ItemModeHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SelectGameModePane extends JLayeredPane implements IDesign {
    private Label title;
    private Panel gameModePanel, buttonPanel, titlePanel;
    private Button normalMode, itemMode, twoPlayerMode_Normal, twoPlayerMode_Item, twoPlayerMode_Time, home;
    private Button selected;
    private static int sizeInt = Pipeline.getSizeInt();

    public SelectGameModePane(){
        sizeInt = Pipeline.getSizeInt();
        setComp();
        setDesign();
        setAction();
    }

    @Override
    public void setComp() {
        gameModePanel = new Panel();

        titlePanel = new Panel();

        title = new Label("Select Game Mode");
        titlePanel.add(title);

        buttonPanel = new Panel();

        normalMode = new Button("Normal Mode");
        itemMode = new Button("Item Mode");
        twoPlayerMode_Normal = new Button("Two Player Mode : Normal");
        twoPlayerMode_Item = new Button("Two Player Mode : Item");
        twoPlayerMode_Time = new Button("Two Player Mode : Time");
        home = new Button("Back to Menu");

        selected = normalMode;

        buttonPanel.add(normalMode);
        buttonPanel.add(itemMode);
        buttonPanel.add(twoPlayerMode_Normal);
        buttonPanel.add(twoPlayerMode_Item);
        buttonPanel.add(twoPlayerMode_Time);
        buttonPanel.add(home);

        gameModePanel.add(buttonPanel);
        gameModePanel.add(titlePanel);

        this.add(gameModePanel);
    }

    @Override
    public void setDesign() {
        gameModePanel.setSize(Pipeline.getScreenResolutionX(), Pipeline.getScreenResolutionY());
        gameModePanel.setBackground(Color.black);
        gameModePanel.setLayout(null);
        gameModePanel.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 8));

        titlePanel.setBounds(0, sizeInt * 50, Pipeline.getScreenResolutionX(), sizeInt * 40);
        titlePanel.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 25));

        title.setForeground(Color.RED);

        buttonPanel.setBounds(sizeInt * 50, sizeInt * 110, Pipeline.getScreenResolutionX() - sizeInt * 50 *2 , sizeInt * 150);
        buttonPanel.setLayout(new GridLayout(3, 5));
    }

    public void setAction(){
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pipeline.replacePane(new GameMenuPane());
            }
        });

        normalMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected.setForeground(Color.black);
                selected = normalMode;
                selected.setForeground(Color.gray);

                Pipeline.replacePane(new TetrisPane(new GameHandler()));
            }
        });

        itemMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected.setForeground(Color.black);
                selected = normalMode;
                selected.setForeground(Color.gray);

                Pipeline.replacePane(new TetrisPane(new ItemModeHandler()));
            }
        });

        twoPlayerMode_Normal.addActionListener(new ActionListener() {
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
