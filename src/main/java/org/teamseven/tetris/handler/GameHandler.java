package org.teamseven.tetris.handler;

public class GameHandler {
    private boolean pause = false;
    private int score;

    public boolean isPaused() {
        return pause;
    }

    public void pause() {
        this.pause = true;
    }

    public void start() {
        this.pause = false;
    }
}
