package org.teamseven.tetris.ui;

import org.teamseven.tetris.score.Score;
import org.teamseven.tetris.score.ScoreHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.text.AttributedString;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreBoardTabbedPane extends JTabbedPane implements  IDesign {
    private JLabel lTime;

    private int[] preferredResolution;  // frame resolution - frame top border
    private JLabel title, title2;

    private JPanel noItemScorePanelTab, itemScorePanelTab;
    private JPanel titlePanel, buttonPanel;
    private JPanel scorePanel1st, scorePanel2st, scorePanel3st, scorePanel4st, scorePanel5st,
                    scorePanel6st, scorePanel7st, scorePanel8st, scorePanel9st, scorePanel10st;
    private  JPanel[] scorePanels;
    private JPanel line1st, line2st, line3st, line4st, line5st, line6st, line7st, line8st, line9st, line10st;

    private CustomButton leftButton, rightButton;

    private int pageNum = 0;
    private int totalPageNum = 0;
    private GridBagConstraints gridBagConstraints;
    private GridBagLayout gridBagLayout, gridBagLayout2;
    ScoreHandler handler = new ScoreHandler();
    String fileName = "test.csv";


    public ScoreBoardTabbedPane(int[] preferredResolution) {

        this.preferredResolution = preferredResolution;


        setComp();
        setDesign();
        setAction();
    }

    @Override
    public void setComp() {

        gridBagConstraints = new GridBagConstraints();
        gridBagLayout = new GridBagLayout();
        gridBagLayout2 = new GridBagLayout();

        noItemScorePanelTab = new JPanel();
        itemScorePanelTab = new JPanel();

        titlePanel = new JPanel();
        scorePanel1st = new JPanel(new GridLayout(0,4,0,0));
        scorePanel2st = new JPanel(new GridLayout(0,4,0,0));
        scorePanel3st = new JPanel(new GridLayout(0,4,0,0));
        scorePanel4st = new JPanel(new GridLayout(0,4,0,0));
        scorePanel5st = new JPanel(new GridLayout(0,4,0,0));
        scorePanel6st = new JPanel(new GridLayout(0,4,0,0));
        scorePanel7st = new JPanel(new GridLayout(0,4,0,0));
        scorePanel8st = new JPanel(new GridLayout(0,4,0,0));
        scorePanel9st = new JPanel(new GridLayout(0,4,0,0));
        scorePanel10st = new JPanel(new GridLayout(0,4,0,0));

        scorePanels = new JPanel[]{scorePanel1st, scorePanel2st, scorePanel3st, scorePanel4st, scorePanel5st,
                scorePanel6st, scorePanel7st, scorePanel8st, scorePanel9st, scorePanel10st};

        line1st = new JPanel();
        line2st = new JPanel();
        line3st = new JPanel();
        line4st = new JPanel();
        line5st = new JPanel();
        line6st = new JPanel();
        line7st = new JPanel();
        line8st = new JPanel();
        line9st = new JPanel();
        line10st = new JPanel();

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 130, 0));

        title = new JLabel("SCORE");

        leftButton = new CustomButton();
        rightButton = new CustomButton();



    }

    @Override
    public void setDesign() {

        gridBagConstraints.fill = GridBagConstraints.BOTH;

        itemScorePanelTab.setLayout(gridBagLayout);

        titlePanel.add(title);
        titlePanel.setPreferredSize(new Dimension(preferredResolution[1] * 8 / 130,preferredResolution[1] * 8 / 130));
        title.setFont(new Font("ss",Font.CENTER_BASELINE,preferredResolution[1] * 10 / 130));
        title.setForeground(Color.orange);

        line1st.setBackground(Color.orange);
        line2st.setBackground(Color.orange);
        line3st.setBackground(Color.orange);
        line4st.setBackground(Color.orange);
        line5st.setBackground(Color.orange);
        line6st.setBackground(Color.orange);
        line7st.setBackground(Color.orange);
        line8st.setBackground(Color.orange);
        line9st.setBackground(Color.orange);
        line10st.setBackground(Color.orange);

        draw(pageNum);

        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 4.0;

        gridBagConstraints.insets = new Insets(preferredResolution[1] * 4 / 130, 0, 0, 0);
        make(itemScorePanelTab, titlePanel,1,0,1,1);
        gridBagConstraints.weighty = 2.0;
        gridBagConstraints.insets = new Insets(preferredResolution[1] * 4 / 130, preferredResolution[0] / 6, 0, preferredResolution[0] / 8);

        make(itemScorePanelTab, scorePanel1st,1,1,1,1);

        gridBagConstraints.insets = new Insets(0, preferredResolution[0] / 6, 0, preferredResolution[0] / 8);
        gridBagConstraints.weighty = 0.1;
        make(itemScorePanelTab, line1st,1,2,1,1);
        gridBagConstraints.weighty = 2.0;

        make(itemScorePanelTab, scorePanel2st,1,3,1,1);

        gridBagConstraints.weighty = 0.1;
        make(itemScorePanelTab, line2st,1,4,1,1);
        gridBagConstraints.weighty = 2.0;

        make(itemScorePanelTab, scorePanel3st,1,5,1,1);

        gridBagConstraints.weighty = 0.1;
        make(itemScorePanelTab, line3st,1,6,1,1);
        gridBagConstraints.weighty = 2.0;

        make(itemScorePanelTab, scorePanel4st,1,7,1,1);

        gridBagConstraints.weighty = 0.1;
        make(itemScorePanelTab, line4st,1,8,1,1);
        gridBagConstraints.weighty = 2.0;

        make(itemScorePanelTab, scorePanel5st,1,9,1,1);

        gridBagConstraints.weighty = 0.1;
        make(itemScorePanelTab, line5st,1,10,1,1);
        gridBagConstraints.weighty = 2.0;

        make(itemScorePanelTab, scorePanel6st,1,11,1,1);

        gridBagConstraints.weighty = 0.1;
        make(itemScorePanelTab, line6st,1,12,1,1);
        gridBagConstraints.weighty = 2.0;

        make(itemScorePanelTab, scorePanel7st,1,13,1,1);

        gridBagConstraints.weighty = 0.1;
        make(itemScorePanelTab, line7st,1,14,1,1);
        gridBagConstraints.weighty = 2.0;

        make(itemScorePanelTab, scorePanel8st,1,15,1,1);

        gridBagConstraints.weighty = 0.1;
        make(itemScorePanelTab, line8st,1,16,1,1);
        gridBagConstraints.weighty = 2.0;

        make(itemScorePanelTab, scorePanel9st,1,17,1,1);

        gridBagConstraints.weighty = 0.1;
        make(itemScorePanelTab, line9st,1,18,1,1);
        gridBagConstraints.weighty = 2.0;

        make(itemScorePanelTab, scorePanel10st,1,19,1,1);

        gridBagConstraints.weighty = 0.1;
        make(itemScorePanelTab, line10st,1,20,1,1);
        gridBagConstraints.weighty = 2.0;

        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        make(itemScorePanelTab, buttonPanel,1,21,1,1);

        itemScorePanelTab.setBackground(Color.white);

        this.addTab("noitem",noItemScorePanelTab);
        this.addTab("item",itemScorePanelTab);


        leftButton.setFont(new Font("ss",Font.BOLD,preferredResolution[1] * 8 / 130));
        leftButton.setForeground(Color.gray);
        rightButton.setFont(new Font("ss",Font.BOLD,preferredResolution[1] * 8 / 130));
        rightButton.setForeground(Color.gray);
        leftButton.removeFocusListener(leftButton.getFocusListeners()[1]);
        leftButton.removeFocusListener(leftButton.getFocusListeners()[0]);

        rightButton.removeFocusListener(rightButton.getFocusListeners()[1]);
        rightButton.removeFocusListener(rightButton.getFocusListeners()[0]);

        rightButton.requestFocus();
        leftButton.setText("<");
        rightButton.setText(">");
        buttonPanel.add(leftButton);
        buttonPanel.add(rightButton);


    }

    @Override
    public void setAction() {
        leftButton.addActionListener(e -> {
            if(pageNum > 0) {
                pageNum--;
                clear();
                draw(pageNum);
                this.revalidate();
                this.repaint();
           }
        });

        rightButton.addActionListener(e -> {
            if(pageNum < totalPageNum) {
                pageNum++;
                clear();
                draw(pageNum);
                this.revalidate();
                this.repaint();
            }
        });
    }

    public void make(JPanel p, JComponent c, int x, int y, int w, int h) {
        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;
        gridBagConstraints.gridwidth = w;
        gridBagConstraints.gridheight = h;
        p.add(c, gridBagConstraints);

        // for test
        c.setBackground(Color.white);
        // c.setBackground(Color.getHSBColor((y+1)  /9f + (x+1) /9f,0.75f,0.95f));
        //  gridBagConstraints.insets = new Insets(15,10,15,10);

    }

    public  void draw(int pageNum){
        List<Score> scores = handler.getScores(fileName);
        totalPageNum = scores.size() % 10;
        for(int i = 0; i<10; i++){
            if(scores.size() == pageNum*10 + i){break;};
            JLabel tempLabel = new JLabel();
            tempLabel.setFont(new Font("ss",Font.BOLD,preferredResolution[1] * 4 / 130));
            tempLabel.setForeground(Color.gray);
            tempLabel.setText(String.valueOf(pageNum*10+i+1));
            scorePanels[i].add(tempLabel);

            JLabel tempLabel2 = new JLabel();
            tempLabel2.setFont(new Font("ss",Font.BOLD,preferredResolution[1] * 4 / 130));
            tempLabel2.setForeground(Color.gray);
            tempLabel2.setText(scores.get(pageNum*10+i).getName());
            scorePanels[i].add(tempLabel2);

            JLabel tempLabel3 = new JLabel();
            tempLabel3.setFont(new Font("ss",Font.BOLD,preferredResolution[1] * 4 / 130));
            tempLabel3.setForeground(Color.gray);
            tempLabel3.setText(scores.get(pageNum*10+i).getScore());
            scorePanels[i].add(tempLabel3);

            JLabel tempLabel4 = new JLabel();
            tempLabel4.setFont(new Font("ss",Font.BOLD,preferredResolution[1] * 4 / 130));
            tempLabel4.setForeground(Color.gray);
            tempLabel4.setText(scores.get(pageNum*10+i).getDate());
            scorePanels[i].add(tempLabel4);

            scorePanels[i].setPreferredSize(new Dimension(preferredResolution[1] * 8 / 130,preferredResolution[1] * 3 / 130));
        }
    }

    public  void clear(){
        List<Score> scores = handler.getScores(fileName);
        for(int i = 0; i<10; i++) {
            scorePanels[i].removeAll();

        }

    }
}
