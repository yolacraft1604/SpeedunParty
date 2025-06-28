package de.yolacraft.speedrunparty.GameManagement;

import de.yolacraft.speedrunparty.PlayerManagement.PlayerStorage;

public class MainManager {
    private PlayerStorage playerStorage;
    private int ServerState = 0; // 0=> Lobby Phase // 1=> Playing // 2=> Game End
    private int GameCycle = 0; // 0=> Würfeln // 1=> Game

    public MainManager(PlayerStorage ps){
        this.playerStorage = ps;
    }

    public int getServerState() {
        return ServerState;
    }

    public void setServerState(int serverState) {
        ServerState = serverState;
    }

    public int getGameCycle() {
        return GameCycle;
    }

    public void setGameCycle(int gameCycle) {
        GameCycle = gameCycle;
    }
}
