package org.teamseven.tetris.ui.menu;

import org.teamseven.tetris.Pipeline;
import org.teamseven.tetris.handler.PreferencesHandler;
import org.teamseven.tetris.ui.BasePane;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static org.teamseven.tetris.ui.KeyTool.getStringKey;

public class KeySettingPane extends BasePane {

    private Label title;
    private Panel keySettingPanel, buttonPanel, titlePanel;

    private Button left, right, down, drop, rotate, backToSetting;

    @Override
    public void setComp() {

        keySettingPanel = new Panel();

        titlePanel = new Panel();

        title = new Label("KeySetting");

        titlePanel.add(title);

        buttonPanel = new Panel();

        left = new Button("Left : " + getStringKey(PreferencesHandler.getLeftBtnCode()));
        left.setFocusTraversalKeysEnabled(false);
        right = new Button("Right : " + getStringKey(PreferencesHandler.getRightBtnCode()));
        right.setFocusTraversalKeysEnabled(false);
        down = new Button("Down : " + getStringKey(PreferencesHandler.getSoftDropBtnCode()));
        down.setFocusTraversalKeysEnabled(false);
        rotate = new Button("Rotate : " + getStringKey(PreferencesHandler.getRotateRightBtnCode()));
        rotate.setFocusTraversalKeysEnabled(false);
        drop = new Button("Drop : " + getStringKey(PreferencesHandler.getHardDropBtnCode()));
        drop.setFocusTraversalKeysEnabled(false);
        backToSetting = new Button("Back to Setting");

        buttonPanel.add(left);
        buttonPanel.add(right);
        buttonPanel.add(down);
        buttonPanel.add(drop);
        buttonPanel.add(rotate);
        buttonPanel.add(backToSetting);

        keySettingPanel.add(buttonPanel);
        keySettingPanel.add(titlePanel);

        this.add(keySettingPanel);
    }

    @Override
    public void setDesign() {
        keySettingPanel.setSize(Pipeline.getScreenResolutionX(), Pipeline.getScreenResolutionY());
        keySettingPanel.setBackground(Color.black);
        keySettingPanel.setLayout(null);
        keySettingPanel.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 8));

        titlePanel.setBounds(0, sizeInt * 50, Pipeline.getScreenResolutionX(), sizeInt * 40);
        titlePanel.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 25));

        title.setForeground(Color.BLUE);

        buttonPanel.setBounds(sizeInt * 125, sizeInt * 110, Pipeline.getScreenResolutionX() - 2 * sizeInt * 125, sizeInt * 125);
        buttonPanel.setLayout(new GridLayout(6, 2));
    }

    private void setLabel(){
        left.setLabel("Left : " + getStringKey(PreferencesHandler.getLeftBtnCode()));
        right.setLabel("Right : " + getStringKey(PreferencesHandler.getRightBtnCode()));
        down.setLabel("Down : " + getStringKey(PreferencesHandler.getSoftDropBtnCode()));
        rotate.setLabel("Rotate : " + getStringKey(PreferencesHandler.getRotateRightBtnCode()));
        drop.setLabel("Drop : " + getStringKey(PreferencesHandler.getHardDropBtnCode()));
    }

    @Override
    public void setAction() {
        backToSetting.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pipeline.replacePane(new SettingPane());
            }
        });

        left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLabel();
                left.setLabel("Press Key for LEFT");

                left.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {}

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if(isOverlapped(e)){
                            PreferencesHandler.setLeftBtnCode(e.getKeyCode());
                            setLabel();
                        }
                        setLabel();
                    }
                    @Override
                    public void keyReleased(KeyEvent e) {}
                });

            }

        });
        right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLabel();

                right.setLabel("Press Key for RIGHT");
                right.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {}

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if(isOverlapped(e)){
                            PreferencesHandler.setRightBtnCode(e.getKeyCode());
                            setLabel();
                        }
                        setLabel();
                    }
                    @Override
                    public void keyReleased(KeyEvent e) {}
                });
            }
        });
        rotate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLabel();
                rotate.setLabel("Press Key for ROTATE");

                rotate.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {}

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if(isOverlapped(e)){
                            PreferencesHandler.setRotateRightBtnCode(e.getKeyCode());
                        }
                        setLabel();
                    }
                    @Override
                    public void keyReleased(KeyEvent e) {}
                });
            }
        });
        down.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLabel();

                down.setLabel("Press Key for DOWN");
                down.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {}

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if(isOverlapped(e)){
                            PreferencesHandler.setSoftDropBtnCode(e.getKeyCode());
                            setLabel();
                        }
                        setLabel();

                    }
                    @Override
                    public void keyReleased(KeyEvent e) {}
                });
            }
        });
        drop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLabel();

                drop.setLabel("Press Key for DROP");
                drop.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {}

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if(isOverlapped(e)){
                            PreferencesHandler.setHardDropBtnCode(e.getKeyCode());
                        }
                        setLabel();
                    }
                    @Override
                    public void keyReleased(KeyEvent e) {}
                });
            }
        });
    }

    public boolean isOverlapped(KeyEvent e){
        if(e.getKeyCode() == PreferencesHandler.getRightBtnCode()) return false;
        if(e.getKeyCode() == PreferencesHandler.getLeftBtnCode()) return false;
        if(e.getKeyCode() == PreferencesHandler.getRotateRightBtnCode()) return false;
        if(e.getKeyCode() == PreferencesHandler.getSoftDropBtnCode()) return false;
        if(e.getKeyCode() == PreferencesHandler.getHardDropBtnCode()) return false;

        if(e.getKeyCode() == PreferencesHandler.getExitBtnCode()) return false;
        if(e.getKeyCode() == PreferencesHandler.getPauseBtnCode()) return false;

        return true;
    }

}
