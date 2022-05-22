package org.teamseven.tetris.ui.menu;

import org.teamseven.tetris.Pipeline;
import org.teamseven.tetris.ui.BasePane;
import org.teamseven.tetris.ui.CustomButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectScoreBoardPane extends BasePane {

    private Panel scoreBoardPanel, titlePanel, buttonPanel;

    private CustomButton normal, item, home;
    private Label title;

    @Override
    public void setComp() {
        scoreBoardPanel = new Panel();

        titlePanel = new Panel();

        title = new Label("Scoreboard");

        titlePanel.add(title);

        buttonPanel = new Panel();

        normal = new CustomButton("Normal Mode");
        item = new CustomButton("Item Mode");
        home = new CustomButton("Home");

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
        setFocusTraversal();

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
                Pipeline.replacePane(new MainMenuPane());
            }
        });
    }
}
