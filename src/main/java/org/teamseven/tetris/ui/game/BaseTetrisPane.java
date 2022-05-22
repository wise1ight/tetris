package org.teamseven.tetris.ui.game;

import org.teamseven.tetris.Pipeline;
import org.teamseven.tetris.ui.IDesign;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public abstract class BaseTetrisPane extends JLayeredPane implements IDesign {

    protected JPanel main;
    protected JTextPane tetrisBoard, nextBlockBoard, scoreBoard;
    protected JLabel pauseLabel;

    protected GridBagConstraints gridBagConstraints;
    protected GridBagLayout gridBagLayout;

    protected int[] preferredResolution;  // frame resolution - frame top border

    public BaseTetrisPane() {
        int[] frameBorderSize = new int[2];       // frame top border
        frameBorderSize[0] = this.getInsets().left + this.getInsets().right;
        frameBorderSize[1] = this.getInsets().top + this.getInsets().bottom;
        preferredResolution = new int[2];
        preferredResolution[0] = Pipeline.getScreenResolutionX() - frameBorderSize[0];
        preferredResolution[1] = Pipeline.getScreenResolutionY() - frameBorderSize[1];
    }

    @Override
    public void setComp() {
        main = new JPanel();
        tetrisBoard = new JTextPane();
        nextBlockBoard = new JTextPane();
        scoreBoard = new JTextPane();

        gridBagConstraints = new GridBagConstraints();
        gridBagLayout = new GridBagLayout();

        pauseLabel = new JLabel("PAUSE");
        pauseLabel.setOpaque(true);
        pauseLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pauseLabel.setBackground(new Color(50, 210, 250, 200));
        pauseLabel.setSize(200, 50);
        pauseLabel.setBorder(new LineBorder(Color.gray));
        pauseLabel.setVisible(false);
        this.add(pauseLabel, JLayeredPane.POPUP_LAYER);

        CompoundBorder border = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.gray, preferredResolution[1] / 60),
                BorderFactory.createLineBorder(Color.darkGray, preferredResolution[1] / 90));

        tetrisBoard.setBorder(border);
        nextBlockBoard.setBorder(border);
        scoreBoard.setBorder(border);

        main.setLayout(gridBagLayout);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
    }

    @Override
    public void setDesign() {
        tetrisBoard.setEditable(false);
        nextBlockBoard.setEditable(false);
        scoreBoard.setEditable(false);

        tetrisBoard.setBackground(Color.BLACK);
        nextBlockBoard.setBackground(Color.BLACK);
        scoreBoard.setBackground(Color.BLACK);
    }

    public abstract void finishGame();
}
