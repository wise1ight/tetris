package org.teamseven.tetris.ui.game;

import org.teamseven.tetris.Pipeline;
import org.teamseven.tetris.handler.MatchModeBridge;
import org.teamseven.tetris.handler.PreferencesHandler;
import org.teamseven.tetris.ui.menu.SelectGameModePane;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import static org.teamseven.tetris.Const.*;
import static org.teamseven.tetris.Const.LEFT;

public class TwoPlayerKeyEventDispatcher implements KeyEventDispatcher {

    private final int KEY_CODE_LEFT_ONE = PreferencesHandler.getLeftOneBtnCode();
    private final int KEY_CODE_LEFT_TWO = PreferencesHandler.getLeftTwoBtnCode();
    private final int KEY_CODE_RIGHT_ONE = PreferencesHandler.getRightOneBtnCode();
    private final int KEY_CODE_RIGHT_TWO = PreferencesHandler.getRightTwoBtnCode();
    private final int KEY_CODE_ROTATE_RIGHT_ONE = PreferencesHandler.getRotateRightOneBtnCode();
    private final int KEY_CODE_ROTATE_RIGHT_TWO = PreferencesHandler.getRotateRightTwoBtnCode();
    private final int KEY_CODE_PAUSE = PreferencesHandler.getPauseBtnCode();
    private final int KEY_CODE_HARD_DROP_ONE = PreferencesHandler.getHardDropOneBtnCode();
    private final int KEY_CODE_HARD_DROP_TWO = PreferencesHandler.getHardDropTwoBtnCode();
    private final int KEY_CODE_SOFT_DROP_ONE = PreferencesHandler.getSoftDropOneBtnCode();
    private final int KEY_CODE_SOFT_DROP_TWO = PreferencesHandler.getSoftDropTwoBtnCode();
    private final int KEY_CODE_EXIT = PreferencesHandler.getExitBtnCode();

    private MatchModeBridge gameHandler;
    private IKeyInputFeedback feedbackListener;
    private final Set<Integer> pressedKeys;

    public TwoPlayerKeyEventDispatcher(MatchModeBridge gameHandler, IKeyInputFeedback feedbackListener) {
        this.gameHandler = gameHandler;
        this.feedbackListener = feedbackListener;
        this.pressedKeys = new HashSet<>();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if(e.getID() == KeyEvent.KEY_PRESSED) {
            if (isPressedKey(e)) {
                pressedKeys.add(e.getKeyCode());
            }

            for (Integer keyCode : pressedKeys) {
                if (keyCode == KEY_CODE_EXIT) {
                    feedbackListener.quit();
                    return true;
                }
                if (keyCode == KEY_CODE_PAUSE) {
                    if (gameHandler.isPaused()) {
                        gameHandler.start();
                    } else {
                        gameHandler.pause();
                    }
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
                if (keyCode == KEY_CODE_SOFT_DROP_ONE) {
                    gameHandler.getAGameHandler().move(DOWN);
                    feedbackListener.feedback();
                } else if (keyCode == KEY_CODE_SOFT_DROP_TWO) {
                    gameHandler.getBGameHandler().move(DOWN);
                    feedbackListener.feedback();
                } else if (keyCode == KEY_CODE_RIGHT_ONE) {
                    gameHandler.getAGameHandler().move(RIGHT);
                    feedbackListener.feedback();
                } else if (keyCode == KEY_CODE_RIGHT_TWO) {
                    gameHandler.getBGameHandler().move(RIGHT);
                    feedbackListener.feedback();
                } else if (keyCode == KEY_CODE_LEFT_ONE) {
                    gameHandler.getAGameHandler().move(LEFT);
                    feedbackListener.feedback();
                } else if (keyCode == KEY_CODE_LEFT_TWO) {
                    gameHandler.getBGameHandler().move(LEFT);
                    feedbackListener.feedback();
                }
            }
            if (e.getKeyCode() == KEY_CODE_HARD_DROP_ONE) {
                gameHandler.getAGameHandler().drop(gameHandler.getBGameHandler());
                feedbackListener.feedback();
            } else if (e.getKeyCode() == KEY_CODE_HARD_DROP_TWO) {
                gameHandler.getBGameHandler().drop(gameHandler.getAGameHandler());
                feedbackListener.feedback();
            } else if (e.getKeyCode() == KEY_CODE_ROTATE_RIGHT_ONE) {
                gameHandler.getAGameHandler().rotate();
                feedbackListener.feedback();
            } else if (e.getKeyCode() == KEY_CODE_ROTATE_RIGHT_TWO) {
                gameHandler.getBGameHandler().rotate();
                feedbackListener.feedback();
            }
        } else if (e.getID() == KeyEvent.KEY_RELEASED) {
            pressedKeys.remove(e.getKeyCode());
        }

        return false;
    }

    private boolean isPressedKey(KeyEvent e) {
        return e.getKeyCode() != KEY_CODE_HARD_DROP_ONE && e.getKeyCode() != KEY_CODE_HARD_DROP_TWO
                && e.getKeyCode() != KEY_CODE_ROTATE_RIGHT_ONE && e.getKeyCode() != KEY_CODE_ROTATE_RIGHT_TWO;
    }
}
