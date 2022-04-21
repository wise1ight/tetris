package org.teamseven.tetris.ui;

import org.teamseven.tetris.Board.GameBoard;
import org.teamseven.tetris.Const;
import org.teamseven.tetris.block.Block;
import org.teamseven.tetris.block.CurrBlock;
import org.teamseven.tetris.block.UnitBlock;
import org.teamseven.tetris.factory.BlockFactory;
import org.teamseven.tetris.handler.GameHandler;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static org.teamseven.tetris.Const.*;

public class TetrisPane extends JLayeredPane implements IDesign, KeyListener {

    public static final char BORDER_CHAR = 'X';

    private JPanel main;
    private JTextPane tetrisBoard, currBlockBoard, scoreBoard;

    private GridBagConstraints gridBagConstraints;
    private GridBagLayout gridBagLayout;

    private GameBoard board;
    private SimpleAttributeSet styleSet;
    private Timer timer;
    private CurrBlock curr;
    private Block nextBlock;
    private GameHandler gameHandler = new GameHandler();

    private static final int initInterval = 1000;

    private int[] preferredResolution;  // frame resolution - frame top border

    public TetrisPane() {
        int[] frameBorderSize = new int[2];       // frame top border
        frameBorderSize[0] = this.getInsets().left + this.getInsets().right;
        frameBorderSize[1] = this.getInsets().top + this.getInsets().bottom;
        preferredResolution = new int[2];
        preferredResolution[0] = Const.SCREEN_RESOLUTION_X - frameBorderSize[0];
        preferredResolution[1] = Const.SCREEN_RESOLUTION_Y - frameBorderSize[1];

        setComp();
        setDesign();
        setAction();
    }

    @Override
    public void setComp() {
        main = new JPanel();
        tetrisBoard = new JTextPane();
        currBlockBoard = new JTextPane();
        scoreBoard = new JTextPane();

        gridBagConstraints = new GridBagConstraints();
        gridBagLayout = new GridBagLayout();

        tetrisBoard = new JTextPane();
        tetrisBoard.setEditable(false);
        tetrisBoard.setBackground(Color.BLACK);
        CompoundBorder border = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 10),
                BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
        tetrisBoard.setBorder(border);

        //Document default style.
        styleSet = new SimpleAttributeSet();
        StyleConstants.setFontSize(styleSet, 18);
        StyleConstants.setFontFamily(styleSet, "Courier");
        StyleConstants.setBold(styleSet, true);
        StyleConstants.setForeground(styleSet, Color.WHITE);
        StyleConstants.setAlignment(styleSet, StyleConstants.ALIGN_CENTER);

        //Create first block and next block
        curr = new CurrBlock();
        nextBlock = BlockFactory.blockGenerator("random").generate();
        gameHandler.addBlockCnt();

        //Set timer for block drops.
        timer = new Timer(initInterval, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("gameHandler.getScore() = " + gameHandler.getScore());
                if (curr.isStopped(board, nextBlock)) {
                    gameHandler.setErasedLines(board.eraseLines());
                    gameHandler.addScoreByEraseLine();
                    nextTurn();
                } else {
                    int cnt = curr.move(board, DOWN);
                    gameHandler.addScoreByMove(cnt);
                }
                drawBoard();
            }
        });

        //Initialize board for the game.
        board = new GameBoard();
        addKeyListener(this);
        setFocusable(true);
        requestFocus();

        //Draw board.
        board.placeBlock(curr);
        drawBoard();
        timer.start();
    }

    private boolean isFinished() {
        return !curr.canMove(board);
    }

    private void nextTurn() {
        gameHandler.speedUp(timer);
        curr.newBlock(nextBlock);
        if (isFinished()) {
            System.out.println("Finished!");
            System.exit(0);
        }
        gameHandler.addBlockCnt();
        nextBlock = BlockFactory.blockGenerator("random").generate();
        board.placeBlock(curr);
    }

    public void drawBoard() {
        StringBuffer sb = new StringBuffer();

        sb = drawWidthBorder(sb);
        sb.append("\n");
        UnitBlock[][] unitBlocks = board.getBoard();
        for (UnitBlock[] unitBlock : unitBlocks) {
            sb.append(BORDER_CHAR);
            for (UnitBlock block : unitBlock) {
                if (block != null) {
                    sb.append("O");
                } else {
                    sb.append(" ");
                }
            }
            sb.append(BORDER_CHAR);
            sb.append("\n");
        }
        sb = drawWidthBorder(sb);

        tetrisBoard.setText(sb.toString());
        StyledDocument doc = tetrisBoard.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
        tetrisBoard.setStyledDocument(doc);
    }

    private StringBuffer drawWidthBorder(StringBuffer sb) {
        StringBuffer buffer = new StringBuffer(sb);
        for (int t = 0; t< Const.WIDTH+2; t++) {
            buffer.append(BORDER_CHAR);
        }
        return buffer;
    }

    @Override
    public void setDesign() {
        tetrisBoard.setEditable(false);
        currBlockBoard.setEditable(false);
        scoreBoard.setEditable(false);

        CompoundBorder border = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.gray, preferredResolution[1] / 60),
                BorderFactory.createLineBorder(Color.darkGray, preferredResolution[1] / 90));

        tetrisBoard.setBorder(border);
        currBlockBoard.setBorder(border);
        scoreBoard.setBorder(border);

        main.setLayout(gridBagLayout);
        gridBagConstraints.fill = GridBagConstraints.BOTH;

        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 3.0;
        gridBagConstraints.insets = new Insets(preferredResolution[1] / 18, preferredResolution[0] * 3 / 8, preferredResolution[1] / 18, 0);
        make(tetrisBoard, 0, 0, 1, 2);

        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(preferredResolution[1] / 18, preferredResolution[0] / 16, preferredResolution[1] / 180, preferredResolution[0] * 3 / 16);
        make(currBlockBoard, 1, 0, 1, 1);

        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(preferredResolution[1] / 180, preferredResolution[0] / 16, preferredResolution[1] * 3 / 5, preferredResolution[0] * 3 / 16);
        make(scoreBoard, 1, 1, 1, 1);

        main.add(tetrisBoard);
        main.add(currBlockBoard);
        main.add(scoreBoard);

        main.setBounds(0, 0, preferredResolution[0], preferredResolution[1]);
        this.add(main, JLayeredPane.DEFAULT_LAYER);

    }

    @Override
    public void setAction() {
        this.requestFocus();
        addKeyListener(this);
    }

    public void make(JComponent c, int x, int y, int w, int h) {
        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;
        gridBagConstraints.gridwidth = w;
        gridBagConstraints.gridheight = h;
        gridBagLayout.setConstraints(c, gridBagConstraints);

        // for test


        // c.setBackground(Color.getHSBColor((y+1)  /9f + (x+1) /9f,0.75f,0.95f));
        //  gridBagConstraints.insets = new Insets(15,10,15,10);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
        if (e.getKeyCode() == KeyEvent.VK_P) {
            if (gameHandler.isPaused()) {
                gameHandler.start();
                timer.start();
            } else {
                gameHandler.pause();
                timer.stop();
            }
            return;
        }
        if (gameHandler.isPaused()) {
            return;
        }
        switch(e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                curr.move(board, DOWN);
                drawBoard();
                break;
            case KeyEvent.VK_RIGHT:
                curr.move(board, RIGHT);
                drawBoard();
                break;
            case KeyEvent.VK_LEFT:
                curr.move(board, LEFT);
                drawBoard();
                break;
            case KeyEvent.VK_UP:
                board.eraseCurr(curr);
                curr.rotate(board);
                board.placeBlock(curr);
                drawBoard();
                break;
            case KeyEvent.VK_SPACE:
                int cnt = curr.moveEnd(board);
                gameHandler.setErasedLines(board.eraseLines());
                gameHandler.addScoreByMove(cnt);
                gameHandler.addScoreByEraseLine();
                nextTurn();
                drawBoard();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
