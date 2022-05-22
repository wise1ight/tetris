package org.teamseven.tetris.ui.game;

import org.teamseven.tetris.handler.MatchModeBridge;
import org.teamseven.tetris.handler.PreferencesHandler;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.teamseven.tetris.Const.*;
import static org.teamseven.tetris.Const.LEFT;

public class TwoPlayerKeyEventDispatcher implements KeyEventDispatcher {

    private static final int KEY_CODE_LEFT_ONE = PreferencesHandler.getLeftOneBtnCode();
    private static final int KEY_CODE_LEFT_TWO = PreferencesHandler.getLeftTwoBtnCode();
    private static final int KEY_CODE_RIGHT_ONE = PreferencesHandler.getRightOneBtnCode();
    private static final int KEY_CODE_RIGHT_TWO = PreferencesHandler.getRightTwoBtnCode();
    private static final int KEY_CODE_ROTATE_RIGHT_ONE = PreferencesHandler.getRotateRightOneBtnCode();
    private static final int KEY_CODE_ROTATE_RIGHT_TWO = PreferencesHandler.getRotateRightTwoBtnCode();
    private static final int KEY_CODE_PAUSE = PreferencesHandler.getPauseBtnCode();
    private static final int KEY_CODE_HARD_DROP_ONE = PreferencesHandler.getHardDropOneBtnCode();
    private static final int KEY_CODE_HARD_DROP_TWO = PreferencesHandler.getHardDropTwoBtnCode();
    private static final int KEY_CODE_SOFT_DROP_ONE = PreferencesHandler.getSoftDropOneBtnCode();
    private static final int KEY_CODE_SOFT_DROP_TWO = PreferencesHandler.getSoftDropTwoBtnCode();
    private static final int KEY_CODE_EXIT = PreferencesHandler.getExitBtnCode();

    private MatchModeBridge gameHandler;
    private IKeyInputFeedback feedbackListener;

    private TwoPlayerKeyEventDispatcher() {

    }

    public TwoPlayerKeyEventDispatcher(MatchModeBridge gameHandler, IKeyInputFeedback feedbackListener) {
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
            if (gameHandler.getAGameHandler().isAnimating()) {
                return true;
            }
            if (gameHandler.getBGameHandler().isAnimating()) {
                return true;
            }
            if (e.getKeyCode() == KEY_CODE_SOFT_DROP_ONE) {
                gameHandler.getAGameHandler().move(DOWN);
                feedbackListener.feedback();
                return true;
            } else if (e.getKeyCode() == KEY_CODE_SOFT_DROP_TWO) {
                gameHandler.getBGameHandler().move(DOWN);
                feedbackListener.feedback();
                return true;
            } else if (e.getKeyCode() == KEY_CODE_RIGHT_ONE) {
                gameHandler.getAGameHandler().move(RIGHT);
                feedbackListener.feedback();
                return true;
            } else if (e.getKeyCode() == KEY_CODE_RIGHT_TWO) {
                gameHandler.getBGameHandler().move(RIGHT);
                feedbackListener.feedback();
                return true;
            } else if (e.getKeyCode() == KEY_CODE_LEFT_ONE) {
                gameHandler.getAGameHandler().move(LEFT);
                feedbackListener.feedback();
                return true;
            } else if (e.getKeyCode() == KEY_CODE_LEFT_TWO) {
                gameHandler.getBGameHandler().move(LEFT);
                feedbackListener.feedback();
                return true;
            } else if (e.getKeyCode() == KEY_CODE_ROTATE_RIGHT_ONE) {
                gameHandler.getAGameHandler().rotate();
                feedbackListener.feedback();
                return true;
            } else if (e.getKeyCode() == KEY_CODE_ROTATE_RIGHT_TWO) {
                gameHandler.getBGameHandler().rotate();
                feedbackListener.feedback();
                return true;
            } else if (e.getKeyCode() == KEY_CODE_HARD_DROP_ONE) {
                gameHandler.getAGameHandler().drop(gameHandler.getBGameHandler());
                feedbackListener.feedback();
                return true;
            } else if (e.getKeyCode() == KEY_CODE_HARD_DROP_TWO) {
                gameHandler.getBGameHandler().drop(gameHandler.getAGameHandler());
                feedbackListener.feedback();
                return true;
            }
        }

        return false;
    }
}
