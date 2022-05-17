package org.teamseven.tetris.handler;

import lombok.Getter;

@Getter
public class TwoPersonGameHandler {
    protected boolean pause = false;

    protected GameHandler aGameHandler;
    protected GameHandler bGameHandler;

    public TwoPersonGameHandler(GameHandler aGameHandler, GameHandler bGameHandler) {
        this.aGameHandler = aGameHandler;
        this.bGameHandler = bGameHandler;
    }

    public boolean isPaused() {
        return pause;
    }

    public void pause() {
        this.pause = true;
        aGameHandler.getTimer().stop();
        bGameHandler.getTimer().stop();
    }

    public void start() {
        this.pause = false;
        aGameHandler.getTimer().start();
        bGameHandler.getTimer().start();
    }

}
