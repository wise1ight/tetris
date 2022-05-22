package org.teamseven.tetris.ui.game;

import org.teamseven.tetris.Pipeline;
import org.teamseven.tetris.block.UnitBlock;
import org.teamseven.tetris.handler.GameHandler;
import org.teamseven.tetris.handler.ItemModeHandler;
import org.teamseven.tetris.handler.PreferencesHandler;
import org.teamseven.tetris.ui.menu.ScoreBoardPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import static org.teamseven.tetris.Const.*;
import static org.teamseven.tetris.ui.KeyTool.getStringKey;

public class TetrisPane extends BaseTetrisPane {

    private final GameHandler gameHandler;

    private OnePlayerKeyEventDispatcher keyEventDispatcher;

    public TetrisPane(GameHandler gameHandler) {
        this.gameHandler = gameHandler;

        setComp();
        setDesign();
        setAction();
    }

    @Override
    public void setComp() {
        super.setComp();

        //Set timer for block drops.
        Timer timer = new Timer(INIT_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameHandler.playing()) {
                    repaint();
                } else {
                    finishGame();
                }
            }
        });

        gameHandler.setTimer(timer);

        repaint();
        timer.start();
    }

    @Override
    public void finishGame() {
        gameHandler.pause();
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.removeKeyEventDispatcher(keyEventDispatcher);
        Pipeline.replacePane(new ScoreBoardPane(gameHandler instanceof ItemModeHandler, gameHandler.getScore()));
    }

    @Override
    public void setDesign() {
        super.setDesign();

        main.setBounds(0, 0, preferredResolution[0], preferredResolution[1]);
        this.add(main, JLayeredPane.DEFAULT_LAYER);
    }

    @Override
    public void setAction() {
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        keyEventDispatcher = new OnePlayerKeyEventDispatcher(gameHandler, new IKeyInputFeedback() {
            @Override
            public void feedback() {
                repaint();
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

        drawBoards(g, 10, 30);
        drawCell(g, 10, 30);
        drawNextBlock(g, 120, 30);
        drawHelpBox(g, 120, 60);

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

    public void drawNextBlock(Graphics g, int X, int Y){
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

    public void drawCell(Graphics g, int boardConerX, int boardConerY){
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
            g.drawString("B", x + sizeInt, 8 * sizeInt + y);
        }
        if (color == Color.DARK_GRAY) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 8));
            g.drawString("C", x + sizeInt, 8 * sizeInt + y);
        }
        if (color == Color.GRAY) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 8));
            g.drawString("S", x + sizeInt, 8 * sizeInt + y);
        }
        if (color == Color.BLACK) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 8));
            g.drawString("L", x + sizeInt, 8 * sizeInt + y);
        }
        if (color == Color.PINK) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 8));
            g.drawString("W", x + sizeInt, 8 * sizeInt + y);
        }
    }
}