package de.yolacraft.speedrunparty.Games;

import de.yolacraft.speedrunparty.Games.TestGame.TestGameMain;
import de.yolacraft.speedrunparty.Utilitys.BrodcastTeleport;
import org.bukkit.Bukkit;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameClusterRoot {

    private List<GameMain> games;
    private int activeGame;
    private Random rand = new Random();
    private GameMain game;

    public GameClusterRoot() {
        games = Arrays.asList(
                new TestGameMain()
        );

    }

    public void recreate (){
        games = Arrays.asList(
                new TestGameMain()
        );

    }

    public void pick() {
        activeGame = rand.nextInt(games.size());
        game = games.get(activeGame);
        game.boot();
    }

    public void showAnimation() {
        // Animation-Logik
    }

    public void launch(){
        BrodcastTeleport.sendTeleport(Bukkit.getWorld(game.getWorld()).getSpawnLocation());
        game.start();
    }

    public String getTitle() {
        return game.getName();
    }

    public List<ResultType> getScores() {
        return game.getResults();
    }

    public int getRemainingTime(){
        return game.getRemainingTime();
    }
}
