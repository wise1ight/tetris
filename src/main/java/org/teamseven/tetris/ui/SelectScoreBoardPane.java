package org.teamseven.tetris.ui;

import org.teamseven.tetris.Pipeline;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectScoreBoardPane extends JLayeredPane implements IDesign {

    private static int sizeInt = Pipeline.getSizeInt();

    private Panel scoreBoardPanel, titlePanel, buttonPanel;

    private Button normal, item, home;
    private Label title;

    public SelectScoreBoardPane() {
        sizeInt = Pipeline.getSizeInt();
        setComp();
        setDesign();
        setAction();
    }

    @Override
    public void setComp() {
        scoreBoardPanel = new Panel();

        titlePanel = new Panel();

        title = new Label("Scoreboard");

        titlePanel.add(title);

        buttonPanel = new Panel();

        normal = new Button("Normal Mode");
        item = new Button("Item Mode");
        home = new Button("Home");

        buttonPanel.add(normal);
        buttonPanel.add(item);
        buttonPanel.add(home);

        scoreBoardPanel.add(titlePanel);
        scoreBoardPanel.add(buttonPanel);

        this.add(scoreBoardPanel);
    }

    @Override
    public void setDesign() {
        scoreBoardPanel.setSize(Pipeline.getScreenResolutionX(), Pipeline.getScreenResolutionY());
        scoreBoardPanel.setBackground(Color.black);
        scoreBoardPanel.setLayout(null);
        scoreBoardPanel.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 8));

        titlePanel.setBounds(0, sizeInt * 50, Pipeline.getScreenResolutionX(), sizeInt * 40);
        titlePanel.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 25));

        title.setForeground(Color.RED);

        buttonPanel.setBounds(sizeInt * 75, sizeInt * 110, Pipeline.getScreenResolutionX() - 2 * sizeInt * 75, sizeInt * 150);
        buttonPanel.setLayout(new GridLayout(3, 1));
    }

    @Override
    public void setAction() {
        normal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pipeline.replacePane(new ScoreBoardPane(false, -1));
            }
        });

        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pipeline.replacePane(new ScoreBoardPane(true, -1));
            }
        });

        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pipeline.replacePane(new GameMenuPane());
            }
        });
    }
}
