package org.teamseven.tetris.ui;

import org.teamseven.tetris.Board.GameBoard;
import org.teamseven.tetris.Const;
import org.teamseven.tetris.Pipeline;
import org.teamseven.tetris.block.Block;
import org.teamseven.tetris.block.CurrBlock;
import org.teamseven.tetris.block.UnitBlock;
import org.teamseven.tetris.block.item.WeightBlock;
import org.teamseven.tetris.factory.BlockFactory;
import org.teamseven.tetris.handler.BlockMovementHandler;
import org.teamseven.tetris.handler.GameHandler;
import org.teamseven.tetris.handler.ItemModeHandler;
import org.teamseven.tetris.handler.WeightMovementHandler;
import org.teamseven.tetris.handler.PreferencesHandler;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import static org.teamseven.tetris.Const.*;

public class TetrisPane extends JLayeredPane implements IDesign, KeyEventDispatcher {

    private JPanel main;
    private JTextPane tetrisBoard, nextBlockBoard, scoreBoard;

    private GridBagConstraints gridBagConstraints;
    private GridBagLayout gridBagLayout;

    private GameBoard board;
    private Timer timer;
    private CurrBlock curr;
    private Block nextBlock;
    private GameHandler gameHandler;
    private ItemModeHandler itemModeHandler = new ItemModeHandler();

    private int[] preferredResolution;  // frame resolution - frame top border

    private static final int KEY_CODE_LEFT = PreferencesHandler.getLeftBtnCode();
    private static final int KEY_CODE_RIGHT = PreferencesHandler.getRightBtnCode();
    private static final int KEY_CODE_ROTATE_RIGHT = PreferencesHandler.getRotateRightBtnCode();
    private static final int KEY_CODE_PAUSE = PreferencesHandler.getPauseBtnCode();
    private static final int KEY_CODE_HARD_DROP = PreferencesHandler.getHardDropBtnCode();
    private static final int KEY_CODE_SOFT_DROP = PreferencesHandler.getSoftDropBtnCode();
    private static final int KEY_CODE_EXIT = PreferencesHandler.getExitBtnCode();

    public TetrisPane(boolean itemMode) {
        int[] frameBorderSize = new int[2];       // frame top border
        frameBorderSize[0] = this.getInsets().left + this.getInsets().right;
        frameBorderSize[1] = this.getInsets().top + this.getInsets().bottom;
        preferredResolution = new int[2];
        preferredResolution[0] = Pipeline.getScreenResolutionX() - frameBorderSize[0];
        preferredResolution[1] = Pipeline.getScreenResolutionY() - frameBorderSize[1];

        gameHandler = new GameHandler(itemMode);

        setComp();
        setDesign();
        setAction();
    }

    @Override
    public void setComp() {
        main = new JPanel();
        tetrisBoard = new JTextPane();
        nextBlockBoard = new JTextPane();
        scoreBoard = new JTextPane();

        gridBagConstraints = new GridBagConstraints();
        gridBagLayout = new GridBagLayout();

        //Create first block and next block
        curr = new CurrBlock();
        nextBlock = BlockFactory.blockGenerator("random").generate();
        gameHandler.addBlockCnt();

        //Set timer for block drops.
        timer = new Timer(INIT_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (curr.isStopped(board, nextBlock)) {
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

        //Draw board.
        board.placeBlock(curr);
        drawBoard();
        timer.start();
    }

    private boolean isFinished() {
        return !curr.canMove(board);
    }

    private void nextTurn() {
        if (gameHandler.isItemMode() && itemModeHandler.hasItem(curr)) {
            itemModeHandler.executeItem(board, curr, gameHandler);
        }
        gameHandler.setErasedLines(board.eraseLines());
        gameHandler.addScoreByEraseLine();

        gameHandler.speedUp(timer);
        if (nextBlock instanceof WeightBlock) {
            curr.setHandler(new WeightMovementHandler());
        } else {
            curr.setHandler(new BlockMovementHandler());
        }
        curr.newBlock(nextBlock);
        gameHandler.addBlockCnt();

        if (isFinished()) {
            timer.stop();
            main.removeAll();
            main.setLayout(new BorderLayout());
            main.add(new ScoreBoardPanelTab(preferredResolution, gameHandler.isItemMode(), gameHandler.getScore()));
            main.revalidate();
            main.repaint();
            return;
        }

        if (gameHandler.isItemMode() && itemModeHandler.isNewItem(gameHandler.getTotalErasedLines())) {
            nextBlock = BlockFactory.blockGenerator("item").generate();
        } else {
            nextBlock = BlockFactory.blockGenerator("random").generate();
        }
        board.placeBlock(curr);
    }

    public void drawBoard() {
        drawGameBoard();
        drawNextBlock();
        drawScore();
    }
    
    private void drawGameBoard() {
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
        doc.setParagraphAttributes(0, doc.getLength(), TetrisStyle.getStyle(Color.WHITE), false);
        tetrisBoard.setStyledDocument(doc);

        for (int row = 0; row < Const.HEIGHT; row++) {
            for (int col = 0; col < Const.WIDTH; col++) {
                int offset = col + (row + 1) * (Const.WIDTH + 3) + 1;
                if(unitBlocks[row][col] != null) {
                    if(unitBlocks[row][col].getColor().equals(Color.LIGHT_GRAY)) { // Boom
                        try {
                            doc.remove(offset, 1);
                            doc.insertString(offset, "B", TetrisStyle.getStyle(Color.WHITE));
                        } catch (BadLocationException e) {
                            e.printStackTrace();
                        }
                    } else if (unitBlocks[row][col].getColor().equals(Color.DARK_GRAY)) { // Clear
                        try {
                            doc.remove(offset, 1);
                            doc.insertString(offset, "C", TetrisStyle.getStyle(Color.WHITE));
                        } catch (BadLocationException e) {
                            e.printStackTrace();
                        }
                    } else if (unitBlocks[row][col].getColor().equals(Color.GRAY)) { // ColorScore
                        try {
                            doc.remove(offset, 1);
                            doc.insertString(offset, "S", TetrisStyle.getStyle(Color.WHITE));
                        } catch (BadLocationException e) {
                            e.printStackTrace();
                        }
                    } else if (unitBlocks[row][col].getColor().equals(Color.BLACK)) { // Line Remove
                        try {
                            doc.remove(offset, 1);
                            doc.insertString(offset, "L", TetrisStyle.getStyle(Color.WHITE));
                        } catch (BadLocationException e) {
                            e.printStackTrace();
                        }
                    } else if (unitBlocks[row][col].getColor().equals(Color.PINK)) { // Weight Block
                        try {
                            doc.remove(offset, 1);
                            doc.insertString(offset, "W", TetrisStyle.getStyle(Color.WHITE));
                        } catch (BadLocationException e) {
                            e.printStackTrace();
                        }
                    } else {
                        doc.setCharacterAttributes(offset, 1, TetrisStyle.getStyle(unitBlocks[row][col].getColor()), false);
                    }
                }
            }
        }
    }
    
    private void drawNextBlock() {
        StringBuffer sb = new StringBuffer();

        UnitBlock[][] unitBlocks = nextBlock.getShape();
        for (UnitBlock[] unitBlock : unitBlocks) {
            for (UnitBlock block : unitBlock) {
                if (block != null) {
                    sb.append("O");
                } else {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }

        nextBlockBoard.setText(sb.toString());
        StyledDocument doc = nextBlockBoard.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), TetrisStyle.getStyle(Color.WHITE), false);
        nextBlockBoard.setStyledDocument(doc);

        for (int row = 0; row < unitBlocks.length; row++) {
            for (int col = 0; col < unitBlocks[row].length; col++) {
                int offset = (unitBlocks[row].length + 1) * row + col;
                if(unitBlocks[row][col] != null) {
                    if(unitBlocks[row][col].getColor().equals(Color.LIGHT_GRAY)) { // Boom
                        try {
                            doc.remove(offset, 1);
                            doc.insertString(offset, "B", TetrisStyle.getStyle(Color.WHITE));
                        } catch (BadLocationException e) {
                            e.printStackTrace();
                        }
                    } else if (unitBlocks[row][col].getColor().equals(Color.DARK_GRAY)) { // Clear
                        try {
                            doc.remove(offset, 1);
                            doc.insertString(offset, "C", TetrisStyle.getStyle(Color.WHITE));
                        } catch (BadLocationException e) {
                            e.printStackTrace();
                        }
                    } else if (unitBlocks[row][col].getColor().equals(Color.GRAY)) { // ColorScore
                        try {
                            doc.remove(offset, 1);
                            doc.insertString(offset, "S", TetrisStyle.getStyle(Color.WHITE));
                        } catch (BadLocationException e) {
                            e.printStackTrace();
                        }
                    } else if (unitBlocks[row][col].getColor().equals(Color.BLACK)) { // Line Remove
                        try {
                            doc.remove(offset, 1);
                            doc.insertString(offset, "L", TetrisStyle.getStyle(Color.WHITE));
                        } catch (BadLocationException e) {
                            e.printStackTrace();
                        }
                    } else if (unitBlocks[row][col].getColor().equals(Color.PINK)) { // Weight Block
                        try {
                            doc.remove(offset, 1);
                            doc.insertString(offset, "W", TetrisStyle.getStyle(Color.WHITE));
                        } catch (BadLocationException e) {
                            e.printStackTrace();
                        }
                    } else {
                        doc.setCharacterAttributes(offset, 1, TetrisStyle.getStyle(unitBlocks[row][col].getColor()), false);
                    }
                }
            }
        }
    }

    private void drawScore() {
        scoreBoard.setText("Score\n" + gameHandler.getScore());
        StyledDocument doc = scoreBoard.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), TetrisStyle.getStyle(Color.WHITE), false);
        scoreBoard.setStyledDocument(doc);
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
        nextBlockBoard.setEditable(false);
        scoreBoard.setEditable(false);

        tetrisBoard.setBackground(Color.BLACK);
        nextBlockBoard.setBackground(Color.BLACK);
        scoreBoard.setBackground(Color.BLACK);

        CompoundBorder border = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.gray, preferredResolution[1] / 60),
                BorderFactory.createLineBorder(Color.darkGray, preferredResolution[1] / 90));

        tetrisBoard.setBorder(border);
        nextBlockBoard.setBorder(border);
        scoreBoard.setBorder(border);

        main.setLayout(gridBagLayout);
        gridBagConstraints.fill = GridBagConstraints.BOTH;

        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 3.0;
        gridBagConstraints.insets = new Insets(preferredResolution[1] / 18, preferredResolution[0] * 3 / 8, preferredResolution[1] / 18, 0);
        make(tetrisBoard, 0, 0, 1, 2);

        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(preferredResolution[1] / 18, preferredResolution[0] / 16, preferredResolution[1] / 180, preferredResolution[0] * 3 / 16);
        make(nextBlockBoard, 1, 0, 1, 1);

        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(preferredResolution[1] / 180, preferredResolution[0] / 16, preferredResolution[1] * 3 / 5, preferredResolution[0] * 3 / 16);
        make(scoreBoard, 1, 1, 1, 1);

        main.add(tetrisBoard);
        main.add(nextBlockBoard);
        main.add(scoreBoard);

        main.setBounds(0, 0, preferredResolution[0], preferredResolution[1]);
        this.add(main, JLayeredPane.DEFAULT_LAYER);

    }

    @Override
    public void setAction() {
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(this);
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
    public boolean dispatchKeyEvent(KeyEvent e) {
        if(e.getID() == KeyEvent.KEY_PRESSED && e.getModifiers() == 0) {
            if (e.getKeyCode() == KEY_CODE_EXIT) {
                System.exit(0);
                return true;
            }
            if (e.getKeyCode() == KEY_CODE_PAUSE) {
                if (gameHandler.isPaused()) {
                    gameHandler.start();
                    timer.start();
                } else {
                    gameHandler.pause();
                    timer.stop();
                }
                return true;
            }
            if (gameHandler.isPaused()) {
                return true;
            }
            int cnt = 0;
            if (e.getKeyCode() == KEY_CODE_SOFT_DROP) {
                cnt = curr.move(board, DOWN);
                gameHandler.addScoreByMove(cnt);
                drawBoard();
                return true;
            } else if (e.getKeyCode() == KEY_CODE_RIGHT) {
                curr.move(board, RIGHT);
                drawBoard();
                return true;
            } else if (e.getKeyCode() == KEY_CODE_LEFT) {
                curr.move(board, LEFT);
                drawBoard();
                return true;
            } else if (e.getKeyCode() == KEY_CODE_ROTATE_RIGHT) {
                board.eraseCurr(curr);
                curr.rotate(board);
                board.placeBlock(curr);
                drawBoard();
                return true;
            } else if (e.getKeyCode() == KEY_CODE_HARD_DROP) {
                cnt = curr.moveEnd(board);
                gameHandler.setErasedLines(board.eraseLines());
                gameHandler.addScoreByMove(cnt);
                gameHandler.addScoreByEraseLine();
                nextTurn();
                drawBoard();
                return true;
            }
        }

        return false;
    }
}
