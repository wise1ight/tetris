package org.teamseven.tetris.handler;

import lombok.Getter;

@Getter
public class MatchModeBridge {
    protected boolean pause = false;

    protected MatchModeHandler aGameHandler;
    protected MatchModeHandler bGameHandler;

    public MatchModeBridge(MatchModeHandler aGameHandler, MatchModeHandler bGameHandler) {
        this.aGameHandler = aGameHandler;
        this.bGameHandler = bGameHandler;
    }

    public boolean isPaused() {
        return pause;
    }

//    public void attack(MatchModeHandler attackPlayer) {
//        MatchModeHandler attackedPlayer = bGameHandler;
//        if (attackPlayer != aGameHandler) {
//            attackPlayer = bGameHandler;
//            attackedPlayer = attackPlayer;
//        }
//        attackedPlayer.attacked(attackPlayer.attack());
//        attackPlayer.clearAttackLines();
//    }

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
