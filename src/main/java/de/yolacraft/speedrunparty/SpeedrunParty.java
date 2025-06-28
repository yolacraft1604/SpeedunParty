package de.yolacraft.speedrunparty;

import de.yolacraft.speedrunparty.Board.Dice.Dice;
import de.yolacraft.speedrunparty.Board.Dice.DiceListener;
import de.yolacraft.speedrunparty.GameManagement.Controller;
import de.yolacraft.speedrunparty.GameManagement.GameStart;
import de.yolacraft.speedrunparty.GameManagement.MainManager;
import de.yolacraft.speedrunparty.Games.GameClusterRoot;
import de.yolacraft.speedrunparty.PlayerManagement.JoinQuit;
import de.yolacraft.speedrunparty.PlayerManagement.PlayerStorage;
import de.yolacraft.speedrunparty.Scoreboard.MainScoreboard;
import de.yolacraft.speedrunparty.Utilitys.Runnables;
import de.yolacraft.speedrunparty.Utilitys.WorldManager;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

public final class SpeedrunParty extends JavaPlugin {

    private static PlayerStorage playerStorage;
    private static MainManager mainManager;
    private static SpeedrunParty instance;
    private static Controller mainController;
    private static Dice dice;
    private static GameStart gameStart;
    private static GameClusterRoot gameClusterRoot;

    @Override
    public void onEnable() {
        WorldManager.createWorld("Main", World.Environment.NORMAL);
        WorldManager.sendAllPlayersToWorld("world");
        instance = this;
        gameClusterRoot = new GameClusterRoot();
        getServer().getPluginManager().registerEvents(new JoinQuit(), this);
        getServer().getPluginManager().registerEvents(new DiceListener(), this);
        playerStorage = new PlayerStorage();
        mainManager = new MainManager(playerStorage);
        dice = new Dice(SpeedrunParty.getPlayerStorage().getPlayers());
        mainController = new Controller(dice);
        mainManager.setServerState(0);


        gameStart = new GameStart();

        MainScoreboard mainScoreboard = new MainScoreboard();

        Runnables.runTimer(0, 20, () -> {
            mainScoreboard.run();
        });
    }

    @Override
    public void onDisable() {

    }

    public static PlayerStorage getPlayerStorage() {
        return playerStorage;
    }
    public static MainManager getMainManager() {
        return mainManager;
    }

    public static SpeedrunParty getInstance() {
        return instance;
    }
    public static Controller getMainController() {
        return mainController;
    }
    public static GameStart getGameStart() {
        return gameStart;
    }

    public static GameClusterRoot getGameClusterRoot() {
        return gameClusterRoot;
    }
}
