package org.teamseven.tetris.ui;

import org.teamseven.tetris.Const;
import org.teamseven.tetris.Pipeline;
import org.teamseven.tetris.block.UnitBlock;
import org.teamseven.tetris.handler.GameHandler;
import org.teamseven.tetris.handler.MatchModeHandler;
import org.teamseven.tetris.handler.PreferencesHandler;
import org.teamseven.tetris.handler.MatchModeBridge;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import static org.teamseven.tetris.Const.*;

public class TwoPlayerModeTetrisPane extends JLayeredPane implements IDesign, KeyEventDispatcher {

    private JPanel main;
    private JTextPane aTetrisBoard, aNextBlockBoard, aScoreBoard, aAttackBoard;
    private JTextPane bTetrisBoard, bNextBlockBoard, bScoreBoard, bAttackBoard;

    private GridBagConstraints gridBagConstraints;
    private GridBagLayout gridBagLayout;

    private final MatchModeBridge gameHandler;
    private boolean itemMode;
    private int[] preferredResolution;  // frame resolution - frame top border

    private static final int KEY_CODE_LEFT_ONE = PreferencesHandler.getLeftOneBtnCode();
    private static final int KEY_CODE_LEFT_TWO = PreferencesHandler.getLeftTwoBtnCode();
    private static final int KEY_CODE_RIGHT_ONE = PreferencesHandler.getRightOneBtnCode();
    private static final int KEY_CODE_RIGHT_TWO = PreferencesHandler.getRightTwoBtnCode();
    private static final int KEY_CODE_ROTATE_RIGHT_ONE = PreferencesHandler.getRotateRightTwoBtnCode();
    private static final int KEY_CODE_ROTATE_RIGHT_TWO = PreferencesHandler.getRotateRightTwoBtnCode();
    private static final int KEY_CODE_PAUSE = PreferencesHandler.getPauseBtnCode();
    private static final int KEY_CODE_HARD_DROP_ONE = PreferencesHandler.getHardDropOneBtnCode();
    private static final int KEY_CODE_HARD_DROP_TWO = PreferencesHandler.getHardDropTwoBtnCode();
    private static final int KEY_CODE_SOFT_DROP_ONE = PreferencesHandler.getSoftDropOneBtnCode();
    private static final int KEY_CODE_SOFT_DROP_TWO = PreferencesHandler.getSoftDropTwoBtnCode();
    private static final int KEY_CODE_EXIT = PreferencesHandler.getExitBtnCode();

    public TwoPlayerModeTetrisPane(MatchModeBridge gameHandler) {
        int[] frameBorderSize = new int[2];       // frame top border
        frameBorderSize[0] = this.getInsets().left + this.getInsets().right;
        frameBorderSize[1] = this.getInsets().top + this.getInsets().bottom;
        preferredResolution = new int[2];
        preferredResolution[0] = Pipeline.getScreenResolutionX() - frameBorderSize[0];
        preferredResolution[1] = Pipeline.getScreenResolutionY() - frameBorderSize[1];

        this.gameHandler = gameHandler;

        setComp();
        setDesign();
        setAction();
    }

    @Override
    public void setComp() {
        main = new JPanel();
        aTetrisBoard = new JTextPane();
        aNextBlockBoard = new JTextPane();
        aScoreBoard = new JTextPane();
        aAttackBoard = new JTextPane();
        bTetrisBoard = new JTextPane();
        bNextBlockBoard = new JTextPane();
        bScoreBoard = new JTextPane();
        bAttackBoard = new JTextPane();

        gridBagConstraints = new GridBagConstraints();
        gridBagLayout = new GridBagLayout();

        //Set timer for block drops.
        Timer aTimer = new Timer(INIT_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameHandler.getAGameHandler().playing(gameHandler.getBGameHandler())) {
                    drawBoard();
                } else {
                    finishGame();
                }
            }
        });

        Timer bTimer = new Timer(INIT_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameHandler.getBGameHandler().playing(gameHandler.getAGameHandler())) {
                    drawBoard();
                } else {
                    finishGame();
                }
            }
        });

        gameHandler.getAGameHandler().setTimer(aTimer);
        gameHandler.getBGameHandler().setTimer(bTimer);

        drawBoard();
        aTimer.start();
        bTimer.start();
    }

    private void finishGame() {
        gameHandler.pause();
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.removeKeyEventDispatcher(this);
        //TODO 2인 게임 했을때 스코어 처리
        //Pipeline.replacePane(new ScoreBoardPanelTab(preferredResolution, itemMode, gameHandler.getScore()));
    }

    public void drawBoard() {
        drawGameBoard(gameHandler.getAGameHandler(), aTetrisBoard);
        drawGameBoard(gameHandler.getBGameHandler(), bTetrisBoard);
        drawNextBlock(gameHandler.getAGameHandler(), aNextBlockBoard);
        drawNextBlock(gameHandler.getBGameHandler(), bNextBlockBoard);
        drawScore(gameHandler.getAGameHandler(), aScoreBoard);
        drawScore(gameHandler.getBGameHandler(), bScoreBoard);
        drawAttackBoard(gameHandler.getBGameHandler(), aAttackBoard);
        drawAttackBoard(gameHandler.getAGameHandler(), bAttackBoard);
    }

    private void drawGameBoard(GameHandler gh, JTextPane tb) {
        StringBuffer sb = new StringBuffer();

        sb = drawWidthBorder(sb);
        sb.append("\n");
        UnitBlock[][] unitBlocks = gh.getBoard();
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

        tb.setText(sb.toString());
        StyledDocument doc = tb.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), TetrisStyle.getStyle(Color.WHITE), false);
        tb.setStyledDocument(doc);

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

    private void drawNextBlock(GameHandler gh, JTextPane nbb) {
        StringBuffer sb = new StringBuffer();

        UnitBlock[][] unitBlocks = gh.getNextBlock().getShape();
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

        nbb.setText(sb.toString());
        StyledDocument doc = nbb.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), TetrisStyle.getStyle(Color.WHITE), false);
        nbb.setStyledDocument(doc);

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

    private void drawScore(GameHandler gh, JTextPane sb) {
        sb.setText("Score\n" + gh.getScore());
        StyledDocument doc = sb.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), TetrisStyle.getStyle(Color.WHITE), false);
        sb.setStyledDocument(doc);
    }

    private void drawAttackBoard(GameHandler gh, JTextPane nbb) {
        if(!(gh instanceof MatchModeHandler))
            return;

        StringBuffer sb = new StringBuffer();

        UnitBlock[][] unitBlocks = ((MatchModeHandler) gh).getAttackLines();
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

        nbb.setText(sb.toString());
        StyledDocument doc = nbb.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), TetrisStyle.getStyle(Color.WHITE), false);
        nbb.setStyledDocument(doc);

        for (int row = 0; row < unitBlocks.length; row++) {
            for (int col = 0; col < unitBlocks[row].length; col++) {
                int offset = (unitBlocks[row].length + 1) * row + col;
                if(unitBlocks[row][col] != null) {
                    doc.setCharacterAttributes(offset, 1, TetrisStyle.getStyle(unitBlocks[row][col].getColor()), false);
                }
            }
        }
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
        aTetrisBoard.setEditable(false);
        bTetrisBoard.setEditable(false);
        aNextBlockBoard.setEditable(false);
        bNextBlockBoard.setEditable(false);
        aScoreBoard.setEditable(false);
        bScoreBoard.setEditable(false);
        aAttackBoard.setEditable(false);
        bAttackBoard.setEditable(false);

        aTetrisBoard.setBackground(Color.BLACK);
        bTetrisBoard.setBackground(Color.BLACK);
        aNextBlockBoard.setBackground(Color.BLACK);
        bNextBlockBoard.setBackground(Color.BLACK);
        aScoreBoard.setBackground(Color.BLACK);
        bScoreBoard.setBackground(Color.BLACK);
        aAttackBoard.setBackground(Color.BLACK);
        bAttackBoard.setBackground(Color.BLACK);

        CompoundBorder border = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.gray, preferredResolution[1] / 60),
                BorderFactory.createLineBorder(Color.darkGray, preferredResolution[1] / 90));

        aTetrisBoard.setBorder(border);
        bTetrisBoard.setBorder(border);
        aNextBlockBoard.setBorder(border);
        bNextBlockBoard.setBorder(border);
        aScoreBoard.setBorder(border);
        bScoreBoard.setBorder(border);
        aAttackBoard.setBorder(border);
        bAttackBoard.setBorder(border);

        main.setLayout(gridBagLayout);
        gridBagConstraints.fill = GridBagConstraints.BOTH;

        //gridBagConstraints.weightx = 2.0;
        //gridBagConstraints.weighty = 3.0;
        gridBagConstraints.insets = new Insets(preferredResolution[1] / 18, 0, preferredResolution[1] / 18, 0);
        make(aTetrisBoard, 0, 0, 1, 3);
        make(bTetrisBoard, 2, 0, 1, 3);

        //gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(preferredResolution[1] / 18, preferredResolution[0] / 32, preferredResolution[1] / 180, preferredResolution[0] / 32);
        make(aNextBlockBoard, 1, 0, 1, 1);
        make(bNextBlockBoard, 3, 0, 1, 1);

        //gridBagConstraints.weighty = 1.0;
        make(aScoreBoard, 1, 1, 1, 1);
        make(bScoreBoard, 3, 1, 1, 1);

        //gridBagConstraints.weighty = 1.0;
        //gridBagConstraints.insets = new Insets(preferredResolution[1] / 180, preferredResolution[0] / 32, preferredResolution[1] * 3 / 5, preferredResolution[0] / 32);
        //gridBagConstraints.insets = new Insets(preferredResolution[1] / 180, preferredResolution[0] / 32, preferredResolution[1] * 3 / 5, preferredResolution[0] / 32);
        make(aAttackBoard, 1, 2, 1, 1);
        make(bAttackBoard, 3, 2, 1, 1);

        main.add(aTetrisBoard);
        main.add(aNextBlockBoard);
        main.add(aScoreBoard);
        main.add(aAttackBoard);
        main.add(bTetrisBoard);
        main.add(bNextBlockBoard);
        main.add(bScoreBoard);
        main.add(bAttackBoard);

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
                } else {
                    gameHandler.pause();
                }
                return true;
            }
            if (gameHandler.isPaused()) {
                return true;
            }
            if (gameHandler.getAGameHandler().isAnimating()) {
                return true;
            }
            if (gameHandler.getBGameHandler().isAnimating()) {
                return true;
            }
            if (e.getKeyCode() == KEY_CODE_SOFT_DROP_ONE) {
                gameHandler.getAGameHandler().move(DOWN);
                drawBoard();
                return true;
            } else if (e.getKeyCode() == KEY_CODE_SOFT_DROP_TWO) {
                gameHandler.getBGameHandler().move(DOWN);
                drawBoard();
                return true;
            } else if (e.getKeyCode() == KEY_CODE_RIGHT_ONE) {
                gameHandler.getAGameHandler().move(RIGHT);
                drawBoard();
                return true;
            } else if (e.getKeyCode() == KEY_CODE_RIGHT_TWO) {
                gameHandler.getBGameHandler().move(RIGHT);
                drawBoard();
                return true;
            } else if (e.getKeyCode() == KEY_CODE_LEFT_ONE) {
                gameHandler.getAGameHandler().move(LEFT);
                drawBoard();
                return true;
            } else if (e.getKeyCode() == KEY_CODE_LEFT_TWO) {
                gameHandler.getBGameHandler().move(LEFT);
                drawBoard();
                return true;
            } else if (e.getKeyCode() == KEY_CODE_ROTATE_RIGHT_ONE) {
                gameHandler.getAGameHandler().rotate();
                drawBoard();
                return true;
            } else if (e.getKeyCode() == KEY_CODE_ROTATE_RIGHT_TWO) {
                gameHandler.getBGameHandler().rotate();
                drawBoard();
                return true;
            } else if (e.getKeyCode() == KEY_CODE_HARD_DROP_ONE) {
                gameHandler.getAGameHandler().drop();
                drawBoard();
                return true;
            } else if (e.getKeyCode() == KEY_CODE_HARD_DROP_TWO) {
                gameHandler.getBGameHandler().drop();
                drawBoard();
                return true;
            }
        }

        return false;
    }
}