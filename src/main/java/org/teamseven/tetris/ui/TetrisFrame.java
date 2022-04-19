package org.teamseven.tetris.ui;

import org.teamseven.tetris.Board.GameBoard;
import org.teamseven.tetris.Const;
import org.teamseven.tetris.block.Block;
import org.teamseven.tetris.block.CurrBlock;
import org.teamseven.tetris.block.UnitBlock;
import org.teamseven.tetris.block.handler.BlockMovementHandler;
import org.teamseven.tetris.factory.BlockFactory;

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
    private boolean nextFlag;

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

        //Set timer for block drops.
        timer = new Timer(initInterval, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nextFlag) {
                    nextTurn();
                } else {
                    BlockMovementHandler.move(board, curr, DOWN);
                    if (BlockMovementHandler.isStopped(board, curr, nextBlock)) {
                        board.eraseLines();
                        nextFlag = true;
                    }
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

    private void nextTurn() {
        curr.newBlock(nextBlock);
        nextBlock = BlockFactory.blockGenerator("random").generate();
        board.placeBlock(curr);
        nextFlag = false;
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
        System.out.println("e.toString() = " + e.toString());
        switch(e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                BlockMovementHandler.move(board, curr, DOWN);
                drawBoard();
                break;
            case KeyEvent.VK_RIGHT:
                BlockMovementHandler.move(board, curr, RIGHT);
                drawBoard();
                break;
            case KeyEvent.VK_LEFT:
                BlockMovementHandler.move(board, curr, LEFT);
                drawBoard();
                break;
            case KeyEvent.VK_UP:
                board.eraseCurr(curr);
                curr.getBlock().right_rotate();
                board.placeBlock(curr);
                drawBoard();
                break;
            case KeyEvent.VK_SPACE:
                BlockMovementHandler.moveEnd(board, curr);
                board.eraseLines();
                nextTurn();
                drawBoard();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
