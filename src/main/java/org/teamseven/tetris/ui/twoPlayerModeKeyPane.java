package org.teamseven.tetris.ui;

import org.teamseven.tetris.Pipeline;
import org.teamseven.tetris.handler.PreferencesHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class twoPlayerModeKeyPane extends JLayeredPane implements IDesign {
    private static int sizeInt = Pipeline.getSizeInt();

    private Label title;
    private Frame settingFrameIn;
    private Panel keySettingPanel, buttonPanel, titlePanel;

    private Button left_one, right_one, down_one, drop_one, rotate_one, backToSetting;
    private Button left_two, right_two, down_two, drop_two, rotate_two;

    private Button selected;

    public twoPlayerModeKeyPane() {
        sizeInt = Pipeline.getSizeInt();
        setComp();
        setDesign();
        setAction();
        //refreshPref();
    }

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

    public String getStringKey(int keyCode) {


        if (keyCode >= KeyEvent.VK_0 && keyCode <= KeyEvent.VK_9 || keyCode >= KeyEvent.VK_A
                && keyCode <= KeyEvent.VK_Z) {
            return String.valueOf((char) keyCode);
        }
        switch (keyCode) {
            case KeyEvent.VK_COMMA:
                return "COMMA";
            case KeyEvent.VK_PERIOD:
                return "PERIOD";
            case KeyEvent.VK_SLASH:
                return "SLASH";
            case KeyEvent.VK_SEMICOLON:
                return "SEMICOLON";
            case KeyEvent.VK_EQUALS:
                return "EQUALS";
            case KeyEvent.VK_OPEN_BRACKET:
                return "OPEN_BRACKET";
            case KeyEvent.VK_BACK_SLASH:
                return "BACK_SLASH";
            case KeyEvent.VK_CLOSE_BRACKET:
                return "CLOSE_BRACKET";
            case KeyEvent.VK_ENTER:
                return "ENTER";
            case KeyEvent.VK_BACK_SPACE:
                return "BACK_SPACE";
            case KeyEvent.VK_TAB:
                return "TAB";
            case KeyEvent.VK_CANCEL:
                return "CANCEL";
            case KeyEvent.VK_CLEAR:
                return "CLEAR";
            case KeyEvent.VK_SHIFT:
                return "SHIFT";
            case KeyEvent.VK_CONTROL:
                return "CONTROL";
            case KeyEvent.VK_ALT:
                return "ALT";
            case KeyEvent.VK_PAUSE:
                return "PAUSE";
            case KeyEvent.VK_CAPS_LOCK:
                return "CAPS_LOCK";
            case KeyEvent.VK_ESCAPE:
                return "ESCAPE";
            case KeyEvent.VK_SPACE:
                return "SPACE";
            case KeyEvent.VK_PAGE_UP:
                return "PAGE_UP";
            case KeyEvent.VK_PAGE_DOWN:
                return "PAGE_DOWN";
            case KeyEvent.VK_END:
                return "END";
            case KeyEvent.VK_HOME:
                return "HOME";
            case KeyEvent.VK_LEFT:
                return "Left Arrow";
            case KeyEvent.VK_UP:
                return "Up Arrow";
            case KeyEvent.VK_RIGHT:
                return "Right Arrow";
            case KeyEvent.VK_DOWN:
                return "Down Arrow";
            case KeyEvent.VK_MULTIPLY:
                return "MULTIPLY";
            case KeyEvent.VK_ADD:
                return "ADD";
            case KeyEvent.VK_SEPARATOR:
                return "SEPARATOR";
            case KeyEvent.VK_SUBTRACT:
                return "SUBTRACT";
            case KeyEvent.VK_DECIMAL:
                return "DECIMAL";
            case KeyEvent.VK_DIVIDE:
                return "DIVIDE";
            case KeyEvent.VK_DELETE:
                return "DELETE";
            case KeyEvent.VK_NUM_LOCK:
                return "NUM_LOCK";
            case KeyEvent.VK_SCROLL_LOCK:
                return "SCROLL_LOCK";
            case KeyEvent.VK_F1:
                return "F1";
            case KeyEvent.VK_F2:
                return "F2";
            case KeyEvent.VK_F3:
                return "F3";
            case KeyEvent.VK_F4:
                return "F4";
            case KeyEvent.VK_F5:
                return "F5";
            case KeyEvent.VK_F6:
                return "F6";
            case KeyEvent.VK_F7:
                return "F7";
            case KeyEvent.VK_F8:
                return "F8";
            case KeyEvent.VK_F9:
                return "F9";
            case KeyEvent.VK_F10:
                return "F10";
            case KeyEvent.VK_F11:
                return "F11";
            case KeyEvent.VK_F12:
                return "F12";
            case KeyEvent.VK_F13:
                return "F13";
            case KeyEvent.VK_F14:
                return "F14";
            case KeyEvent.VK_F15:
                return "F15";
            case KeyEvent.VK_F16:
                return "F16";
            case KeyEvent.VK_F17:
                return "F17";
            case KeyEvent.VK_F18:
                return "F18";
            case KeyEvent.VK_F19:
                return "F19";
            case KeyEvent.VK_F20:
                return "F20";
            case KeyEvent.VK_F21:
                return "F21";
            case KeyEvent.VK_F22:
                return "F22";
            case KeyEvent.VK_F23:
                return "F23";
            case KeyEvent.VK_F24:
                return "F24";
            case KeyEvent.VK_PRINTSCREEN:
                return "PRINTSCREEN";
            case KeyEvent.VK_INSERT:
                return "INSERT";
            case KeyEvent.VK_HELP:
                return "HELP";
            case KeyEvent.VK_META:
                return "META";
            case KeyEvent.VK_BACK_QUOTE:
                return "BACK_QUOTE";
            case KeyEvent.VK_QUOTE:
                return "QUOTE";
            case KeyEvent.VK_KP_UP:
                return "KP_UP";
            case KeyEvent.VK_KP_DOWN:
                return "KP_DOWN";
            case KeyEvent.VK_KP_LEFT:
                return "KP_LEFT";
            case KeyEvent.VK_KP_RIGHT:
                return "KP_RIGHT";
            case KeyEvent.VK_DEAD_GRAVE:
                return "DEAD_GRAVE";
            case KeyEvent.VK_DEAD_ACUTE:
                return "DEAD_ACUTE";
            case KeyEvent.VK_DEAD_CIRCUMFLEX:
                return "DEAD_CIRCUMFLEX";
            case KeyEvent.VK_DEAD_TILDE:
                return "DEAD_TILDE";
            case KeyEvent.VK_DEAD_MACRON:
                return "DEAD_MACRON";
            case KeyEvent.VK_DEAD_BREVE:
                return "DEAD_BREVE";
            case KeyEvent.VK_DEAD_ABOVEDOT:
                return "DEAD_ABOVEDOT";
            case KeyEvent.VK_DEAD_DIAERESIS:
                return "DEAD_DIAERESIS";
            case KeyEvent.VK_DEAD_ABOVERING:
                return "DEAD_ABOVERING";
            case KeyEvent.VK_DEAD_DOUBLEACUTE:
                return "DEAD_DOUBLEACUTE";
            case KeyEvent.VK_DEAD_CARON:
                return "DEAD_CARON";
            case KeyEvent.VK_DEAD_CEDILLA:
                return "DEAD_CEDILLA";
            case KeyEvent.VK_DEAD_OGONEK:
                return "DEAD_OGONEK";
            case KeyEvent.VK_DEAD_IOTA:
                return "DEAD_IOTA";
            case KeyEvent.VK_DEAD_VOICED_SOUND:
                return "DEAD_VOICED_SOUND";
            case KeyEvent.VK_DEAD_SEMIVOICED_SOUND:
                return "DEAD_SEMIVOICED_SOUND";
            case KeyEvent.VK_AMPERSAND:
                return "AMPERSAND";
            case KeyEvent.VK_ASTERISK:
                return "ASTERISK";
            case KeyEvent.VK_QUOTEDBL:
                return "QUOTEDBL";
            case KeyEvent.VK_LESS:
                return "LESS";
            case KeyEvent.VK_GREATER:
                return "GREATER";
            case KeyEvent.VK_BRACELEFT:
                return "BRACELEFT";
            case KeyEvent.VK_BRACERIGHT:
                return "BRACERIGHT";
            case KeyEvent.VK_AT:
                return "AT";
            case KeyEvent.VK_COLON:
                return "COLON";
            case KeyEvent.VK_CIRCUMFLEX:
                return "CIRCUMFLEX";
            case KeyEvent.VK_DOLLAR:
                return "DOLLAR";
            case KeyEvent.VK_EURO_SIGN:
                return "EURO_SIGN";
            case KeyEvent.VK_EXCLAMATION_MARK:
                return "EXCLAMATION_MARK";
            case KeyEvent.VK_INVERTED_EXCLAMATION_MARK:
                return "INVERTED_EXCLAMATION_MARK";
            case KeyEvent.VK_LEFT_PARENTHESIS:
                return "LEFT_PARENTHESIS";
            case KeyEvent.VK_NUMBER_SIGN:
                return "NUMBER_SIGN";
            case KeyEvent.VK_MINUS:
                return "MINUS";
            case KeyEvent.VK_PLUS:
                return "PLUS";
            case KeyEvent.VK_RIGHT_PARENTHESIS:
                return "RIGHT_PARENTHESIS";
            case KeyEvent.VK_UNDERSCORE:
                return "UNDERSCORE";
            case KeyEvent.VK_FINAL:
                return "FINAL";
            case KeyEvent.VK_CONVERT:
                return "CONVERT";
            case KeyEvent.VK_NONCONVERT:
                return "NONCONVERT";
            case KeyEvent.VK_ACCEPT:
                return "ACCEPT";
            case KeyEvent.VK_MODECHANGE:
                return "MODECHANGE";
            case KeyEvent.VK_KANA:
                return "KANA";
            case KeyEvent.VK_KANJI:
                return "KANJI";
            case KeyEvent.VK_ALPHANUMERIC:
                return "ALPHANUMERIC";
            case KeyEvent.VK_KATAKANA:
                return "KATAKANA";
            case KeyEvent.VK_HIRAGANA:
                return "HIRAGANA";
            case KeyEvent.VK_FULL_WIDTH:
                return "FULL_WIDTH";
            case KeyEvent.VK_HALF_WIDTH:
                return "HALF_WIDTH";
            case KeyEvent.VK_ROMAN_CHARACTERS:
                return "ROMAN_CHARACTERS";
            case KeyEvent.VK_ALL_CANDIDATES:
                return "ALL_CANDIDATES";
            case KeyEvent.VK_PREVIOUS_CANDIDATE:
                return "PREVIOUS_CANDIDATE";
            case KeyEvent.VK_CODE_INPUT:
                return "CODE_INPUT";
            case KeyEvent.VK_JAPANESE_KATAKANA:
                return "JAPANESE_KATAKANA";
            case KeyEvent.VK_JAPANESE_HIRAGANA:
                return "JAPANESE_HIRAGANA";
            case KeyEvent.VK_JAPANESE_ROMAN:
                return "JAPANESE_ROMAN";
            case KeyEvent.VK_KANA_LOCK:
                return "KANA_LOCK";
            case KeyEvent.VK_INPUT_METHOD_ON_OFF:
                return "INPUT_METHOD_ON_OFF";

            case KeyEvent.VK_AGAIN:
                return "AGAIN";
            case KeyEvent.VK_UNDO:
                return "UNDO";
            case KeyEvent.VK_COPY:
                return "COPY";
            case KeyEvent.VK_PASTE:
                return "PASTE";
            case KeyEvent.VK_CUT:
                return "CUT";
            case KeyEvent.VK_FIND:
                return "FIND";
            case KeyEvent.VK_PROPS:
                return "PROPS";
            case KeyEvent.VK_STOP:
                return "STOP";

            case KeyEvent.VK_COMPOSE:
                return "COMPOSE";
            case KeyEvent.VK_ALT_GRAPH:
                return "ALT_GRAPH";
        }

        if (keyCode >= KeyEvent.VK_NUMPAD0 && keyCode <= KeyEvent.VK_NUMPAD9) {
            char c = (char) (keyCode - KeyEvent.VK_NUMPAD0 + '0');
            return "NUMPAD" + c;
        }

        return "unknown(0x" + Integer.toString(keyCode, 16) + ")";
    }



}

