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

public class TwoPlayerModeKeyPane extends BasePane {

    private Label title;
    private Panel keySettingPanel, buttonPanel, titlePanel;

    private Button left_one, right_one, down_one, drop_one, rotate_one, backToSetting;
    private Button left_two, right_two, down_two, drop_two, rotate_two;

    @Override
    public void setComp() {
        keySettingPanel = new Panel();

        titlePanel = new Panel();

        title = new Label("KeySetting");

        titlePanel.add(title);

        buttonPanel = new Panel();

        left_one = new Button("Left_one : " + PreferencesHandler.getLeftOneBtnCode());
        left_one.setFocusTraversalKeysEnabled(false);
        right_one = new Button("Right : ");
        right_one.setFocusTraversalKeysEnabled(false);
        down_one = new Button("Down : " );
        down_one.setFocusTraversalKeysEnabled(false);
        rotate_one = new Button("Rotate : ");
        rotate_one.setFocusTraversalKeysEnabled(false);
        drop_one = new Button("Drop : ");
        drop_one.setFocusTraversalKeysEnabled(false);
        backToSetting = new Button("Back to Setting");

        left_two = new Button("Left_two : ");
        left_two.setFocusTraversalKeysEnabled(false);
        right_two = new Button("Right : ");
        right_two.setFocusTraversalKeysEnabled(false);
        down_two = new Button("Down : ");
        down_two.setFocusTraversalKeysEnabled(false);
        rotate_two = new Button("Rotate : ");
        rotate_two.setFocusTraversalKeysEnabled(false);
        drop_two = new Button("Drop : " );
        drop_two.setFocusTraversalKeysEnabled(false);

        setLabel();


        buttonPanel.add(left_one);
        buttonPanel.add(left_two);
        buttonPanel.add(right_one);
        buttonPanel.add(right_two);
        buttonPanel.add(down_one);
        buttonPanel.add(down_two);
        buttonPanel.add(drop_one);
        buttonPanel.add(drop_two);
        buttonPanel.add(rotate_one);
        buttonPanel.add(rotate_two);
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


    @Override
    public void setAction() {
        backToSetting.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pipeline.replacePane(new SettingPane());
            }
        });
        left_one.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLabel();
                left_one.setLabel("Press Key for LEFT ONE");

                left_one.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {}

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if(isOverlapped(e)){
                            PreferencesHandler.setLeftOneBtnCode(e.getKeyCode());
                            setLabel();
                        }
                        setLabel();
                    }
                    @Override
                    public void keyReleased(KeyEvent e) {}
                });
            }
        });

        right_one.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLabel();
                right_one.setLabel("Press Key for RIGHT ONE");

                right_one.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {}

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if(isOverlapped(e)){
                            PreferencesHandler.setRightOneBtnCode(e.getKeyCode());
                            setLabel();
                        }
                        setLabel();
                    }
                    @Override
                    public void keyReleased(KeyEvent e) {}
                });
            }
        });

        rotate_one.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLabel();
                rotate_one.setLabel("Press Key for ROTATE ONE");

                rotate_one.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {}

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if(isOverlapped(e)){
                            PreferencesHandler.setRotateRightOneBtnCode(e.getKeyCode());
                            setLabel();
                        }
                        setLabel();
                    }
                    @Override
                    public void keyReleased(KeyEvent e) {}
                });
            }
        });

        down_one.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLabel();
                down_one.setLabel("Press Key for DOWN ONE");

                down_one.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {}

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if(isOverlapped(e)){
                            PreferencesHandler.setSoftDropOneBtnCode(e.getKeyCode());
                            setLabel();
                        }
                        setLabel();
                    }
                    @Override
                    public void keyReleased(KeyEvent e) {}
                });
            }
        });

        drop_one.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLabel();
                drop_one.setLabel("Press Key for DROP ONE");

                drop_one.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {}

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if(isOverlapped(e)){
                            PreferencesHandler.setHardDropOneBtnCode(e.getKeyCode());
                            setLabel();
                        }
                        setLabel();
                    }
                    @Override
                    public void keyReleased(KeyEvent e) {}
                });
            }
        });


        left_two.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLabel();
                left_two.setLabel("Press Key for LEFT TWO");

                left_two.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {}

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if(isOverlapped(e)){
                            PreferencesHandler.setLeftTwoBtnCode(e.getKeyCode());
                            setLabel();
                        }
                        setLabel();
                    }
                    @Override
                    public void keyReleased(KeyEvent e) {}
                });
            }
        });

        right_two.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLabel();
                right_two.setLabel("Press Key for RIGHT TWO");

                right_two.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {}

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if(isOverlapped(e)){
                            PreferencesHandler.setRightTwoBtnCode(e.getKeyCode());
                            setLabel();
                        }
                        setLabel();
                    }
                    @Override
                    public void keyReleased(KeyEvent e) {}
                });
            }
        });

        rotate_two.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLabel();
                rotate_two.setLabel("Press Key for ROTATE TWO");

                rotate_two.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {}

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if(isOverlapped(e)){
                            PreferencesHandler.setRotateRightTwoBtnCode(e.getKeyCode());
                            setLabel();
                        }
                        setLabel();
                    }
                    @Override
                    public void keyReleased(KeyEvent e) {}
                });
            }
        });

        down_two.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLabel();
                down_two.setLabel("Press Key for DOWN TWO");

                down_two.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {}

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if(isOverlapped(e)){
                            PreferencesHandler.setSoftDropTwoBtnCode(e.getKeyCode());
                            setLabel();
                        }
                        setLabel();
                    }
                    @Override
                    public void keyReleased(KeyEvent e) {}
                });
            }
        });

        drop_two.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLabel();
                drop_two.setLabel("Press Key for DROP TWO");

                drop_two.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {}

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if(isOverlapped(e)){
                            PreferencesHandler.setHardDropTwoBtnCode(e.getKeyCode());
                            setLabel();
                        }
                        setLabel();
                    }
                    @Override
                    public void keyReleased(KeyEvent e) {}
                });
            }
        });

    }

    private void setLabel(){
        left_one.setLabel("Left One: " + getStringKey(PreferencesHandler.getLeftOneBtnCode()));
        right_one.setLabel("Right One: " + getStringKey(PreferencesHandler.getRightOneBtnCode()));
        down_one.setLabel("Down One: " + getStringKey(PreferencesHandler.getSoftDropOneBtnCode()));
        rotate_one.setLabel("Rotate One: " + getStringKey(PreferencesHandler.getRotateRightOneBtnCode()));
        drop_one.setLabel("Drop One: " + getStringKey(PreferencesHandler.getHardDropOneBtnCode()));

        left_two.setLabel("Left Two: " + getStringKey(PreferencesHandler.getLeftTwoBtnCode()));
        right_two.setLabel("Right Two: " + getStringKey(PreferencesHandler.getRightTwoBtnCode()));
        down_two.setLabel("Down Two: " + getStringKey(PreferencesHandler.getSoftDropTwoBtnCode()));
        rotate_two.setLabel("Rotate Two: " + getStringKey(PreferencesHandler.getRotateRightTwoBtnCode()));
        drop_two.setLabel("Drop Two: " + getStringKey(PreferencesHandler.getHardDropTwoBtnCode()));

    }

    public boolean isOverlapped(KeyEvent e){
        if(e.getKeyCode() == PreferencesHandler.getRightOneBtnCode()) return false;
        if(e.getKeyCode() == PreferencesHandler.getLeftOneBtnCode()) return false;
        if(e.getKeyCode() == PreferencesHandler.getRotateRightOneBtnCode()) return false;
        if(e.getKeyCode() == PreferencesHandler.getSoftDropOneBtnCode()) return false;
        if(e.getKeyCode() == PreferencesHandler.getHardDropOneBtnCode()) return false;

        if(e.getKeyCode() == PreferencesHandler.getRightTwoBtnCode()) return false;
        if(e.getKeyCode() == PreferencesHandler.getLeftTwoBtnCode()) return false;
        if(e.getKeyCode() == PreferencesHandler.getRotateRightTwoBtnCode()) return false;
        if(e.getKeyCode() == PreferencesHandler.getSoftDropTwoBtnCode()) return false;
        if(e.getKeyCode() == PreferencesHandler.getHardDropTwoBtnCode()) return false;

        return true;
    }

}