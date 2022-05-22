package org.teamseven.tetris.ui.game;

import org.teamseven.tetris.Pipeline;
import org.teamseven.tetris.block.UnitBlock;
import org.teamseven.tetris.handler.GameHandler;
import org.teamseven.tetris.handler.MatchModeHandler;
import org.teamseven.tetris.handler.MatchModeBridge;
import org.teamseven.tetris.handler.PreferencesHandler;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.teamseven.tetris.Const.*;
import static org.teamseven.tetris.ui.KeyTool.getStringKey;

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

        this.repaint();
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

            @Override
            public void pause() {
                pauseLabel.setLocation(new Point(main.getWidth() / 2 - pauseLabel.getWidth() / 2,
                        main.getHeight() / 2 - pauseLabel.getHeight() / 2));
                pauseLabel.setVisible(true);
            }

            @Override
            public void start() {
                pauseLabel.setVisible(false);
            }
        });
        manager.addKeyEventDispatcher(keyEventDispatcher);
    }


    private int BLOCK_WIDTH = 10;
    private int sizeInt;

    public void paint(Graphics g){
        sizeInt = Pipeline.getSizeInt();

        super.paint(g);

        drawHelpBox(g, 120, 60);

        drawGameBoard(g, 10, 30, gameHandler.getAGameHandler());
        drawNextBlock(g, 120, 30, gameHandler.getAGameHandler());

        drawGameBoard(g, 230, 30, gameHandler.getBGameHandler());
        drawNextBlock(g, 340, 30, gameHandler.getBGameHandler());

        drawAttackBoards(g, 340, 130, gameHandler.getAGameHandler());
        drawAttackBoards(g, 120, 130, gameHandler.getBGameHandler());
    }

    public void drawGameBoard(Graphics g, int boardConerX, int boardConerY, GameHandler gameHandler){
        drawBoards(g, boardConerX, boardConerY);
        drawCell(g, boardConerX, boardConerY, gameHandler);

    }

    public void drawHelpBox(Graphics g, int X, int Y){
        int x = X * sizeInt;
        int y = Y * sizeInt;
        g.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 8));
        g.setColor(Color.RED);

        g.drawString("H E L P", x, y);
        g.drawString(getStringKey(PreferencesHandler.getRotateRightBtnCode())+" : Rotate", x, y + 10*sizeInt);
        g.drawString(getStringKey(PreferencesHandler.getLeftBtnCode()) + " : Move Left", x, y + 20*sizeInt);
        g.drawString(getStringKey(PreferencesHandler.getRightBtnCode()) + " : Move Right", x,y + 30*sizeInt);
        g.drawString(getStringKey(PreferencesHandler.getSoftDropBtnCode())+ " : Move Down", x, y + 40*sizeInt);
        g.drawString(getStringKey(PreferencesHandler.getHardDropBtnCode()) + " : Drop", x, y + 50*sizeInt);
//        g.drawString("F2: Home", sizeInt * 5, sizeInt * 150);
        //    g.drawString("F1: New Game", sizeInt * 5, sizeInt * 80);
        //      g.drawString("ESC: Pause Game/Continue", sizeInt * 5, sizeInt * 90);

    }
    public void drawNextBlock(Graphics g, int X, int Y, GameHandler gameHandler){
        int x = X *sizeInt;
        int y = Y *sizeInt;

        UnitBlock[][] unitBlocks = gameHandler.getNextBlock().getShape();
        for (UnitBlock[] unitBlock : unitBlocks) {
            for (UnitBlock block : unitBlock) {
                if (block != null) {
                    drawBlock(g, x, y, block.getColor());
                    x += sizeInt*BLOCK_WIDTH;
                } else {
                    x += sizeInt*BLOCK_WIDTH;
                }
            }
            y += sizeInt*BLOCK_WIDTH;
            x = X *sizeInt;
        }

    }
    public void drawBoards(Graphics g, int boardConerX, int boardConerY){
        g.setColor(Color.BLACK);
        g.fillRect(boardConerX*sizeInt -1,boardConerY*sizeInt -1,BLOCK_WIDTH*sizeInt*10 +2 ,BLOCK_WIDTH*sizeInt*20 +2);
        g.setColor(Color.GRAY);
        g.drawRect(boardConerX*sizeInt -1,boardConerY*sizeInt -1,BLOCK_WIDTH*sizeInt*10 +2,BLOCK_WIDTH*sizeInt*20 +2); //주의

    }
    public void drawCell(Graphics g, int boardConerX, int boardConerY, GameHandler gameHandler){
        int x = boardConerX *sizeInt;
        int y = boardConerY *sizeInt;

        UnitBlock[][] unitBlocks = gameHandler.getBoard();
        for (UnitBlock[] unitBlock : unitBlocks) {
            for (UnitBlock block : unitBlock) {
                if (block != null) {
                    drawBlock(g, x, y, block.getColor());
                    x += sizeInt*BLOCK_WIDTH;
                } else {
                    x += sizeInt*BLOCK_WIDTH;
                }
            }
            y += sizeInt*BLOCK_WIDTH;
            x = boardConerX *sizeInt;
        }

    }
    public void drawBlock(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        g.fillRect(x, y, BLOCK_WIDTH * sizeInt, BLOCK_WIDTH * sizeInt);

        g.setColor(Color.GRAY);
        g.drawRect(x, y, BLOCK_WIDTH* sizeInt, BLOCK_WIDTH * sizeInt);


        if (color == Color.LIGHT_GRAY) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 8));
            g.drawString("B", x + BLOCK_WIDTH / 5, BLOCK_WIDTH * 8 / 10 + y);
        }
        if (color == Color.DARK_GRAY) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 8));
            g.drawString("C", x + BLOCK_WIDTH / 5, BLOCK_WIDTH * 8 / 10 + y);
        }
        if (color == Color.GRAY) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 8));
            g.drawString("S", x + BLOCK_WIDTH / 5, BLOCK_WIDTH * 8 / 10 + y);
        }
        if (color == Color.BLACK) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 8));
            g.drawString("L", x + BLOCK_WIDTH / 5, BLOCK_WIDTH * 8 / 10 + y);
        }
        if (color == Color.PINK) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 8));
            g.drawString("W", x + BLOCK_WIDTH / 5, BLOCK_WIDTH * 8 / 10 + y);
        }
    }
    public void drawAttackBoards(Graphics g, int X, int Y, GameHandler gameHandler){
        g.setColor(Color.BLACK);
        g.fillRect(X * sizeInt -1,Y*sizeInt -1,BLOCK_WIDTH*sizeInt*10 +2 ,BLOCK_WIDTH*sizeInt*10 +2);
        g.setColor(Color.GRAY);
        g.drawRect(X*sizeInt -1,Y*sizeInt -1,BLOCK_WIDTH*sizeInt*10 +2,BLOCK_WIDTH*sizeInt*10 +2);

        int x = X *sizeInt;
        int y = Y *sizeInt;

        UnitBlock[][] unitBlocks = ((MatchModeHandler) gameHandler).getAttackLines();
        for (UnitBlock[] unitBlock : unitBlocks) {
            for (UnitBlock block : unitBlock) {
                if (block != null) {
                    drawBlock(g, x, y, block.getColor());
                    x += sizeInt*BLOCK_WIDTH;
                } else {
                    x += sizeInt*BLOCK_WIDTH;
                }
            }
            y += sizeInt*BLOCK_WIDTH;
            x = X *sizeInt;
        }



    }


}