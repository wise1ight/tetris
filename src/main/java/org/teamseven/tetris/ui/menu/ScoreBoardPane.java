package org.teamseven.tetris.ui.menu;

import org.teamseven.tetris.Pipeline;
import org.teamseven.tetris.handler.PreferencesHandler;
import org.teamseven.tetris.handler.ScoreMemoryHandler;
import org.teamseven.tetris.score.Score;
import org.teamseven.tetris.ui.BasePane;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static javax.swing.JOptionPane.showInputDialog;
import static org.teamseven.tetris.Const.SCORE_ITEM_FILE;
import static org.teamseven.tetris.Const.SCORE_NORMAL_FILE;

public class ScoreBoardPane extends BasePane {

    private Label title;
    private Panel scoreBoardPanel, scoreBoardTable, homeButtonPanel, titlePanel;
    //private Panel levelButtonPanel;
    private Button home;
    //private Button level;

    private String highlightUUID = null;
    private List<Score> scores;
    private final String fileName;

    private final ScoreMemoryHandler handler;
    private static final int LIST_MAX_ITEM_SIZE = 10;

    public ScoreBoardPane(boolean itemMode, int newScore){
        super();

        handler = new ScoreMemoryHandler();

        fileName = itemMode ? SCORE_ITEM_FILE : SCORE_NORMAL_FILE;
        scores = handler.getScores(fileName);

        if(newScore >= 0 && (scores.size() < 10 || newScore > scores.stream().map(Score::getScore).map(Integer::valueOf).mapToInt(k -> k).min().getAsInt())) {
            this.revalidate();
            this.repaint();
            String name = showInputDialog("이름을 입력하세요.");
            Score score = new Score(newScore, name, itemMode ? "ITEM" : PreferencesHandler.getMode().name());
            highlightUUID = score.getUuid();
            addScore(score);
        }

        drawScore();
    }

    @Override
    public void setComp() {
        scoreBoardPanel = new Panel();

        titlePanel = new Panel();

        title = new Label("ScoreBoard");
        titlePanel.add(title);

        scoreBoardTable = new Panel();

        homeButtonPanel = new Panel();

//        levelButtonPanel = new Panel();

        home = new Button("Back to Menu");
//        level = new Button("Level : " + PreferencesHandler.getMode());

        homeButtonPanel.add(home);
//        levelButtonPanel.add(level);

        scoreBoardPanel.add(homeButtonPanel);
//        scoreBoardPanel.add(levelButtonPanel);
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

//        levelButtonPanel.setBounds(50 * sizeInt, 30* sizeInt, 60*sizeInt, sizeInt* 20);
//        levelButtonPanel.setLayout(new GridLayout(1,1));

        title.setForeground(Color.RED);

        scoreBoardTable.setBounds(sizeInt * 50, sizeInt * 60, Pipeline.getScreenResolutionX() - sizeInt * 50 , sizeInt * 160);
        scoreBoardTable.setLayout(new GridLayout(10,1));
        scoreBoardTable.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 10));

        homeButtonPanel.setBounds(sizeInt * 50, sizeInt * 240, Pipeline.getScreenResolutionX() - sizeInt * 50 *2 , sizeInt * 80);
        homeButtonPanel.setLayout(new GridLayout(3, 5));
    }

    public void setAction(){
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pipeline.replacePane(new MainMenuPane());
            }
        });

/*
        level.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
*/
    }

    public void addScore(Score score){
        handler.saveScoreFile(score, this.fileName);
    }

    public void drawScore(){
        scores = handler.getScores(fileName);

        int scoreSize = Math.min(scores.size(), LIST_MAX_ITEM_SIZE);
        for(int i = 0; i < scoreSize; i++){
            Score score = scores.get(i);

            Panel scoreItemPanel = new Panel();
            scoreItemPanel.setLayout(new GridLayout(1, 5));

            Label scoreIndexLabel = new Label();
            scoreIndexLabel.setForeground(Color.lightGray);
            scoreIndexLabel.setText(String.valueOf(i + 1));
            scoreItemPanel.add(scoreIndexLabel);

            Label scoreNameLabel = new Label();
            if(score.getUuid().equals(highlightUUID))
                scoreNameLabel.setForeground(Color.YELLOW);
            else
                scoreNameLabel.setForeground(Color.lightGray);
            scoreNameLabel.setText(score.getName());
            scoreItemPanel.add(scoreNameLabel);

            Label scoreValueLabel = new Label();
            scoreValueLabel.setForeground(Color.lightGray);
            scoreValueLabel.setText(score.getScore());
            scoreItemPanel.add(scoreValueLabel);

            Label scoreDateLabel = new Label();
            scoreDateLabel.setForeground(Color.lightGray);
            scoreDateLabel.setText(score.getDate().substring(5));
            scoreItemPanel.add(scoreDateLabel);

            Label scoreModeLabel = new Label();
            scoreModeLabel.setForeground(Color.gray);
            scoreModeLabel.setText(score.getMode());
            scoreItemPanel.add(scoreModeLabel);

            scoreItemPanel.setPreferredSize(new Dimension(Pipeline.getScreenResolutionX() * 8 / 130,Pipeline.getScreenResolutionY() * 3 / 130));
            scoreBoardTable.add(scoreItemPanel);
        }

        this.revalidate();
        this.repaint();
    }
}
