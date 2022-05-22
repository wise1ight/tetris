
package org.teamseven.tetris.ui;

import com.sun.org.apache.xpath.internal.operations.String;
import org.teamseven.tetris.Pipeline;
import org.teamseven.tetris.handler.PreferencesHandler;
import org.teamseven.tetris.handler.ScoreMemoryHandler;
import org.teamseven.tetris.score.Score;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static javax.swing.JOptionPane.showInputDialog;
import static org.teamseven.tetris.Const.SCORE_ITEM_FILE;
import static org.teamseven.tetris.Const.SCORE_NORMAL_FILE;

public class ScoreBoardPane extends JLayeredPane implements IDesign {
    private Label title;

    private Label rank1,  rank2, rank3, rank4, rank5, rank6, rank7, rank8, rank9, rank10;
    private Panel scoreBoardPanel, scoreBoardTable, buttonPanel, titlePanel;

    private Label rank1st, rank2nd;
    private Button home;
    private static int sizeInt = Pipeline.getSizeInt();

    public ScoreBoardPane(){
        sizeInt = Pipeline.getSizeInt();
        setComp();
        setDesign();
        setAction();
    }

    @Override
    public void setComp() {
        scoreBoardPanel = new Panel();

        titlePanel = new Panel();

        title = new Label("ScoreBoard");
        titlePanel.add(title);

        scoreBoardTable = new Panel();

        buttonPanel = new Panel();

        home = new Button("Back to Menu");

        buttonPanel.add(home);

        rank1 = new Label();
        rank2 = new Label();
        rank3 = new Label();
        rank4 = new Label();
        rank5 = new Label();
        rank6 = new Label();
        rank7 = new Label();
        rank8 = new Label();
        rank9 = new Label();
        rank10 = new Label();

        scoreBoardTable.add(rank1);
        scoreBoardTable.add(rank2);
        scoreBoardTable.add(rank3);
        scoreBoardTable.add(rank4);
        scoreBoardTable.add(rank5);
        scoreBoardTable.add(rank6);
        scoreBoardTable.add(rank7);
        scoreBoardTable.add(rank8);
        scoreBoardTable.add(rank9);
        scoreBoardTable.add(rank10);


        scoreBoardPanel.add(buttonPanel);
        scoreBoardPanel.add(titlePanel);
        scoreBoardPanel.add(scoreBoardTable);

        this.add(scoreBoardPanel);
    }

    @Override
    public void setDesign() {
        scoreBoardPanel.setSize(Pipeline.getScreenResolutionX(), Pipeline.getScreenResolutionY());
        scoreBoardPanel.setBackground(Color.black);
        scoreBoardPanel.setLayout(null);
        scoreBoardPanel.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 8));

        titlePanel.setBounds(0, sizeInt * 20, Pipeline.getScreenResolutionX(), sizeInt * 30);
        titlePanel.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 25));

        title.setForeground(Color.RED);

        scoreBoardTable.setBounds(sizeInt * 50, sizeInt * 60, Pipeline.getScreenResolutionX() - sizeInt * 50 , sizeInt * 160);
        scoreBoardTable.setLayout(new GridLayout(10,1));
        scoreBoardTable.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 10));

        setScoreBoardFontColor(Color.lightGray);

        buttonPanel.setBounds(sizeInt * 50, sizeInt * 240, Pipeline.getScreenResolutionX() - sizeInt * 50 *2 , sizeInt * 80);
        buttonPanel.setLayout(new GridLayout(3, 5));
    }

    public void setAction(){
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pipeline.replacePane(new GameMenuPane());
            }
        });

    }

    private void setScoreBoardFontColor(Color color){
        rank1.setForeground(color);
        rank2.setForeground(color);
        rank3.setForeground(color);
        rank4.setForeground(color);
        rank5.setForeground(color);
        rank6.setForeground(color);
        rank7.setForeground(color);
        rank8.setForeground(color);
        rank9.setForeground(color);
        rank10.setForeground(color);
    }
}
