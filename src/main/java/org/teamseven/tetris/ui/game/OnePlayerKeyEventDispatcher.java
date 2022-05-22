package org.teamseven.tetris.ui.game;

import org.teamseven.tetris.handler.GameHandler;
import org.teamseven.tetris.handler.PreferencesHandler;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.teamseven.tetris.Const.*;

public class OnePlayerKeyEventDispatcher implements KeyEventDispatcher {

    private final int KEY_CODE_LEFT = PreferencesHandler.getLeftBtnCode();
    private final int KEY_CODE_RIGHT = PreferencesHandler.getRightBtnCode();
    private final int KEY_CODE_ROTATE_RIGHT = PreferencesHandler.getRotateRightBtnCode();
    private final int KEY_CODE_PAUSE = PreferencesHandler.getPauseBtnCode();
    private final int KEY_CODE_HARD_DROP = PreferencesHandler.getHardDropBtnCode();
    private final int KEY_CODE_SOFT_DROP = PreferencesHandler.getSoftDropBtnCode();
    private final int KEY_CODE_EXIT = PreferencesHandler.getExitBtnCode();

    private GameHandler gameHandler;
    private IKeyInputFeedback feedbackListener;

    private OnePlayerKeyEventDispatcher() {

    }

    public OnePlayerKeyEventDispatcher(GameHandler gameHandler, IKeyInputFeedback feedbackListener) {
        this.gameHandler = gameHandler;
        this.feedbackListener = feedbackListener;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if(e.getID() == KeyEvent.KEY_PRESSED) {
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
            if (gameHandler.isAnimating()) {
                return true;
            }
            if (e.getKeyCode() == KEY_CODE_SOFT_DROP) {
                gameHandler.move(DOWN);
                feedbackListener.feedback();
                return true;
            } else if (e.getKeyCode() == KEY_CODE_RIGHT) {
                gameHandler.move(RIGHT);
                feedbackListener.feedback();
                return true;
            } else if (e.getKeyCode() == KEY_CODE_LEFT) {
                gameHandler.move(LEFT);
                feedbackListener.feedback();
                return true;
            } else if (e.getKeyCode() == KEY_CODE_ROTATE_RIGHT) {
                gameHandler.rotate();
                feedbackListener.feedback();
                return true;
            } else if (e.getKeyCode() == KEY_CODE_HARD_DROP) {
                gameHandler.drop();
                feedbackListener.feedback();
                return true;
            }
        }

        return false;
    }
}
