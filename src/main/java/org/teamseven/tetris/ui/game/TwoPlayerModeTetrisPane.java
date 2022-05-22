package org.teamseven.tetris.ui.game;

import org.teamseven.tetris.block.UnitBlock;
import org.teamseven.tetris.handler.GameHandler;
import org.teamseven.tetris.handler.MatchModeHandler;
import org.teamseven.tetris.handler.MatchModeBridge;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.teamseven.tetris.Const.*;

public class TwoPlayerModeTetrisPane extends BaseTetrisPane {

    private JTextPane attackBoard, bTetrisBoard, bNextBlockBoard, bScoreBoard, bAttackBoard;

    private final MatchModeBridge gameHandler;

    private TwoPlayerKeyEventDispatcher keyEventDispatcher;

    public TwoPlayerModeTetrisPane(MatchModeBridge gameHandler) {
        this.gameHandler = gameHandler;

        setComp();
        setDesign();
        setAction();
    }

    @Override
    public void setComp() {
        super.setComp();

        attackBoard = new JTextPane();
        bTetrisBoard = new JTextPane();
        bNextBlockBoard = new JTextPane();
        bScoreBoard = new JTextPane();
        bAttackBoard = new JTextPane();

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

    @Override
    public void finishGame() {
        gameHandler.pause();
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.removeKeyEventDispatcher(keyEventDispatcher);
        //TODO 2인 게임 했을때 스코어 처리
        //Pipeline.replacePane(new ScoreBoardPanelTab(preferredResolution, itemMode, gameHandler.getScore()));
    }

    @Override
    public void drawBoard() {
        drawGameBoard(gameHandler.getAGameHandler(), tetrisBoard);
        drawGameBoard(gameHandler.getBGameHandler(), bTetrisBoard);
        drawNextBlock(gameHandler.getAGameHandler(), nextBlockBoard);
        drawNextBlock(gameHandler.getBGameHandler(), bNextBlockBoard);
        drawScore(gameHandler.getAGameHandler(), scoreBoard);
        drawScore(gameHandler.getBGameHandler(), bScoreBoard);
        drawAttackBoard(gameHandler.getBGameHandler(), attackBoard);
        drawAttackBoard(gameHandler.getAGameHandler(), bAttackBoard);
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

    @Override
    public void setDesign() {
        super.setDesign();

        bTetrisBoard.setEditable(false);
        bNextBlockBoard.setEditable(false);
        bScoreBoard.setEditable(false);
        attackBoard.setEditable(false);
        bAttackBoard.setEditable(false);

        bTetrisBoard.setBackground(Color.BLACK);
        bNextBlockBoard.setBackground(Color.BLACK);
        bScoreBoard.setBackground(Color.BLACK);
        attackBoard.setBackground(Color.BLACK);
        bAttackBoard.setBackground(Color.BLACK);

        CompoundBorder border = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.gray, preferredResolution[1] / 60),
                BorderFactory.createLineBorder(Color.darkGray, preferredResolution[1] / 90));

        bTetrisBoard.setBorder(border);
        bNextBlockBoard.setBorder(border);
        bScoreBoard.setBorder(border);
        attackBoard.setBorder(border);
        bAttackBoard.setBorder(border);

        main.setLayout(gridBagLayout);
        gridBagConstraints.fill = GridBagConstraints.BOTH;

        //gridBagConstraints.weightx = 2.0;
        //gridBagConstraints.weighty = 3.0;
        gridBagConstraints.insets = new Insets(preferredResolution[1] / 18, 0, preferredResolution[1] / 18, 0);
        make(tetrisBoard, 0, 0, 1, 3);
        make(bTetrisBoard, 2, 0, 1, 3);

        //gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(preferredResolution[1] / 18, preferredResolution[0] / 32, preferredResolution[1] / 180, preferredResolution[0] / 32);
        make(nextBlockBoard, 1, 0, 1, 1);
        make(bNextBlockBoard, 3, 0, 1, 1);

        //gridBagConstraints.weighty = 1.0;
        make(scoreBoard, 1, 1, 1, 1);
        make(bScoreBoard, 3, 1, 1, 1);

        //gridBagConstraints.weighty = 1.0;
        //gridBagConstraints.insets = new Insets(preferredResolution[1] / 180, preferredResolution[0] / 32, preferredResolution[1] * 3 / 5, preferredResolution[0] / 32);
        //gridBagConstraints.insets = new Insets(preferredResolution[1] / 180, preferredResolution[0] / 32, preferredResolution[1] * 3 / 5, preferredResolution[0] / 32);
        make(attackBoard, 1, 2, 1, 1);
        make(bAttackBoard, 3, 2, 1, 1);

        main.add(tetrisBoard);
        main.add(nextBlockBoard);
        main.add(scoreBoard);
        main.add(attackBoard);
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
        keyEventDispatcher = new TwoPlayerKeyEventDispatcher(gameHandler, new IKeyInputFeedback() {
            @Override
            public void feedback() {
                drawBoard();
            }
        });
        manager.addKeyEventDispatcher(keyEventDispatcher);
    }
}