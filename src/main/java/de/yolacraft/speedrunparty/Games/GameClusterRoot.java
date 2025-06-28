package de.yolacraft.speedrunparty.Games;

import de.yolacraft.speedrunparty.Games.TestGame.TestGameMain;
import de.yolacraft.speedrunparty.Games.TestGameX.TestGameXMain;
import de.yolacraft.speedrunparty.Utilitys.BrodcastSound;
import de.yolacraft.speedrunparty.Utilitys.BrodcastTeleport;
import de.yolacraft.speedrunparty.Utilitys.BrodcastTitle;
import de.yolacraft.speedrunparty.Utilitys.Runnables;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;

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
                new TestGameMain(),
                new TestGameXMain()
        );

    }

    public void recreate (){
        games = Arrays.asList(
                new TestGameMain(),
                new TestGameXMain()
        );

    }

    public void pick() {
        activeGame = rand.nextInt(games.size());
        game = games.get(activeGame);
        game.boot();
    }

    public void showAnimation() {
        Runnables.runAmount(20,4,5*3, () -> {
            int selection = rand.nextInt(games.size());
            BrodcastTitle.sendBrodcastTitle(ChatColor.YELLOW + games.get(selection).getName(), "selecting Game...", 0, 10, 0);
            BrodcastSound.sendBrodcastSound(Sound.BLOCK_NOTE_BLOCK_HAT, 1f, 1f);
        });
        Runnables.runLater(80, () -> {
            BrodcastTitle.sendBrodcastTitle(game.getName(), "selected Game", 0, 40, 5);
            BrodcastSound.sendBrodcastSound(Sound.BLOCK_NOTE_BLOCK_CHIME, 1f, 1f);
        });
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
