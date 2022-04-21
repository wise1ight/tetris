package org.teamseven.tetris.ui;

import org.teamseven.tetris.Board.GameBoard;
import org.teamseven.tetris.Const;
import org.teamseven.tetris.block.Block;
import org.teamseven.tetris.block.CurrBlock;
import org.teamseven.tetris.block.UnitBlock;
import org.teamseven.tetris.factory.BlockFactory;
import org.teamseven.tetris.handler.GameHandler;
import org.teamseven.tetris.handler.ScoreHandler;

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

public class TetrisFrame extends JFrame implements KeyListener {

    public static final char BORDER_CHAR = 'X';

    private JTextPane pane;
    private GameBoard board;
    private SimpleAttributeSet styleSet;
    private Timer timer;
    private CurrBlock curr;
    private Block nextBlock;
    private GameHandler gameHandler = new GameHandler();

    private static final int initInterval = 1000;

    public TetrisFrame() {
        super("SeoulTech SE Tetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(400, 600);

        setVisible(true);

        //Board display setting.
        pane = new JTextPane();
        pane.setEditable(false);
        pane.setBackground(Color.BLACK);
        CompoundBorder border = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 10),
                BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
        pane.setBorder(border);
        this.getContentPane().add(pane, BorderLayout.CENTER);

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

        pane.setText(sb.toString());
        StyledDocument doc = pane.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
        pane.setStyledDocument(doc);
    }

    private StringBuffer drawWidthBorder(StringBuffer sb) {
        StringBuffer buffer = new StringBuffer(sb);
        for (int t = 0; t< Const.WIDTH+2; t++) {
            buffer.append(BORDER_CHAR);
        }
        return buffer;
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
                int cnt = curr.move(board, DOWN);
                gameHandler.addScoreByMove(cnt);
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
                curr.moveEnd(board);
                gameHandler.setErasedLines(board.eraseLines());
                gameHandler.addScoreByEraseLine();
                nextTurn();
                drawBoard();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
