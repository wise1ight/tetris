package org.teamseven.tetris.ui.game;

import org.teamseven.tetris.Const;
import org.teamseven.tetris.Pipeline;
import org.teamseven.tetris.block.UnitBlock;
import org.teamseven.tetris.handler.GameHandler;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import java.awt.*;

import static org.teamseven.tetris.Const.BORDER_CHAR;

public abstract class BaseTetrisPane extends JLayeredPane {

    protected JPanel main;
    protected JTextPane tetrisBoard, nextBlockBoard, scoreBoard;

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

    private StringBuffer drawWidthBorder(StringBuffer sb) {
        StringBuffer buffer = new StringBuffer(sb);
        for (int t = 0; t< Const.WIDTH+2; t++) {
            buffer.append(BORDER_CHAR);
        }
        return buffer;
    }

    protected void drawGameBoard(GameHandler gh, JTextPane tb) {
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

    protected void drawNextBlock(GameHandler gh, JTextPane nbb) {
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

    protected void drawScore(GameHandler gh, JTextPane sb) {
        sb.setText("Score\n" + gh.getScore());
        StyledDocument doc = sb.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), TetrisStyle.getStyle(Color.WHITE), false);
        sb.setStyledDocument(doc);
    }

    protected void make(JComponent c, int x, int y, int w, int h) {
        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;
        gridBagConstraints.gridwidth = w;
        gridBagConstraints.gridheight = h;
        gridBagLayout.setConstraints(c, gridBagConstraints);
    }

    public abstract void drawBoard();
    public abstract void finishGame();
}
