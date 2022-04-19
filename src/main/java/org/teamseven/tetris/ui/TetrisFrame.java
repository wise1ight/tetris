package org.teamseven.tetris.ui;

import org.teamseven.tetris.Board.GameBoard;
import org.teamseven.tetris.Const;
import org.teamseven.tetris.block.CurrBlock;
import org.teamseven.tetris.block.UnitBlock;
import org.teamseven.tetris.block.handler.BlockMovementHandler;

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

public class TetrisFrame extends JFrame implements KeyListener {

    public static final char BORDER_CHAR = 'X';

    private JTextPane pane;
    private GameBoard board;
    private SimpleAttributeSet styleSet;
    private Timer timer;
    private CurrBlock curr;
    int x = 3; //Default Position.
    int y = 0;

    private static final int initInterval = 1000;

    public TetrisFrame() {
        super("SeoulTech SE Tetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(400, 400);

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

        //Set timer for block drops.
        timer = new Timer(initInterval, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BlockMovementHandler.moveDown(board, curr);
                drawBoard();
            }
        });

        //Initialize board for the game.
        board = new GameBoard();
        addKeyListener(this);
        setFocusable(true);
        requestFocus();

        //Create the first block and draw.
        curr = new CurrBlock();
        board.placeBlock(curr);
        drawBoard();
        timer.start();
    }

    public void drawBoard() {
        StringBuffer sb = new StringBuffer();
        for(int t = 0; t< Const.WIDTH+2; t++)sb.append(BORDER_CHAR);
        sb.append("\n");
        UnitBlock[][] unitBlocks = board.getBoard();
        for(int i=0; i < unitBlocks.length; i++) {
            sb.append(BORDER_CHAR);
            for(int j=0; j < unitBlocks[i].length; j++) {
                if(unitBlocks[i][j] != null) {
                    sb.append("O");
                } else {
                    sb.append(" ");
                }
            }
            sb.append(BORDER_CHAR);
            sb.append("\n");
        }
        for(int t=0; t<Const.WIDTH+2; t++) sb.append(BORDER_CHAR);
        pane.setText(sb.toString());
        StyledDocument doc = pane.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
        pane.setStyledDocument(doc);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                BlockMovementHandler.moveDown(board, curr);
                drawBoard();
                break;
            case KeyEvent.VK_RIGHT:
                BlockMovementHandler.moveRight(board, curr);
                drawBoard();
                break;
            case KeyEvent.VK_LEFT:
                BlockMovementHandler.moveLeft(board, curr);
                drawBoard();
                break;
            case KeyEvent.VK_UP:
                board.eraseCurr(curr);
                curr.getBlock().right_rotate();
                drawBoard();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
