package org.teamseven.tetris.handler;

import lombok.Getter;

import javax.swing.*;

@Getter
public class MatchModeBridge {
    protected boolean pause = false;

    protected MatchModeHandler aGameHandler;
    protected MatchModeHandler bGameHandler;
    private Timer timeMatchTimer;

    public MatchModeBridge(MatchModeHandler aGameHandler, MatchModeHandler bGameHandler) {
        this.aGameHandler = aGameHandler;
        this.bGameHandler = bGameHandler;
    }

    public void setTimeMatchTimer(Timer timeMatchTimer) {
        this.timeMatchTimer = timeMatchTimer;
    }

    public boolean isPaused() {
        return pause;
    }

    public void pause() {
        this.pause = true;
        aGameHandler.getTimer().stop();
        bGameHandler.getTimer().stop();
        if(timeMatchTimer != null)
            timeMatchTimer.stop();
    }

    public void start() {
        this.pause = false;
        aGameHandler.getTimer().start();
        bGameHandler.getTimer().start();
        if(timeMatchTimer != null)
            timeMatchTimer.start();
    }

}
