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
    private Panel scoreBoardPanel, normalScoreBoardTable, itemScoreBoardTable, buttonPanel, titlePanel;

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

        normalScoreBoardTable = new Panel();
        itemScoreBoardTable = new Panel();

        buttonPanel = new Panel();

        home = new Button("Back to Menu");


        buttonPanel.add(home);

        rank1st = new Label("trying");
        rank2nd = new Label("trying hard");


       // normalScoreBoardTable.add(rank1st);
     //   normalScoreBoardTable.add(rank2nd);
   //     normalScoreBoardTable.add(new Label("test"));
        for(int i = 0; i<10; i++) {

            Label tmp = new Label(Integer.toString(i+1));
            Label j = new Label(Integer.toString(i+10));
            Label t = new Label(Integer.toString(i+100));

            normalScoreBoardTable.add(tmp);
            normalScoreBoardTable.add(j);
            normalScoreBoardTable.add(t);

            tmp.setForeground(Color.lightGray);
            j.setForeground(Color.lightGray);
            t.setForeground(Color.lightGray);
        }


        scoreBoardPanel.add(buttonPanel);
        scoreBoardPanel.add(titlePanel);
        scoreBoardPanel.add(normalScoreBoardTable);
        scoreBoardPanel.add(itemScoreBoardTable);

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

        normalScoreBoardTable.setBounds(sizeInt * 50, sizeInt * 60, Pipeline.getScreenResolutionX() - sizeInt * 50 , sizeInt * 160);
        normalScoreBoardTable.setLayout(new GridLayout(10,3));
        normalScoreBoardTable.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 10));

        rank1st.setForeground(Color.lightGray);
        rank2nd.setForeground(Color.lightGray);

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
}
