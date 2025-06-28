package de.yolacraft.speedrunparty.Games.TestGameX;

import de.yolacraft.speedrunparty.Games.GameMain;
import de.yolacraft.speedrunparty.Games.ResultType;
import de.yolacraft.speedrunparty.SpeedrunParty;
import de.yolacraft.speedrunparty.Utilitys.*;
import org.bukkit.*;

import java.util.List;
import java.util.Random;

public class TestGameXMain implements GameMain {

    private String world = "TESTGAME_X";
    private List<ResultType> results;
    private int RemainingSeconds = 30;
    private Random rand = new Random();
    private int Gameindex = 10000 + rand.nextInt(99999 - 10000);

    public TestGameXMain() {}

    @Override
    public void start() {
        WorldManager.sendAllPlayersToWorld(world);
        results = CreateEmptyResults.getEmptyResults();
        BrodcastMessage.sendBrodcastMessageWithPrefix(ChatColor.YELLOW + "Test Game X");
        BrodcastMessage.sendBrodcastMessageWithPrefix(ChatColor.GRAY + "Reach the highest X-Coordinate in 30 Seconds!");
        Bukkit.getOnlinePlayers().forEach(player -> player.getInventory().clear());
        Bukkit.getOnlinePlayers().forEach(player -> player.setGameMode(GameMode.ADVENTURE));

        for (int i = 1; i <= 3; i++) {
            int delay = 80 - i * 20;
            int count = 4 - i;
            Runnables.runLater(delay, () -> {
                BrodcastMessage.sendBrodcastMessage(ChatColor.GRAY + "Game starts in " + ChatColor.YELLOW + (4 - count));
                BrodcastSound.sendBrodcastSound(Sound.BLOCK_NOTE_BLOCK_BANJO, 1f, 1f);
            });
        }

        Runnables.runLater(80, () -> {
            BrodcastMessage.sendBrodcastMessage(ChatColor.GRAY + "Game started!");
            BrodcastSound.sendBrodcastSound(Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
            Bukkit.getOnlinePlayers().forEach(player -> player.setGameMode(GameMode.SURVIVAL));
            Runnables.runAmount(0, 20, 30, () -> {
                RemainingSeconds -= 1;
                updateScores();
            });
        });

        Runnables.runLater(80 + 30 * 20, () -> {
            BrodcastSound.sendBrodcastSound(Sound.UI_TOAST_CHALLENGE_COMPLETE, 1f, 1f);
            Bukkit.getOnlinePlayers().forEach(player -> player.setGameMode(GameMode.ADVENTURE));

            BrodcastTitle.sendBrodcastTitle(ChatColor.YELLOW + "Game Over", null, 5, 40, 5);

            updateScores();

            BrodcastMessage.sendBrodcastMessageWithPrefix(ChatColor.WHITE + "Game Results:");
            BrodcastMessage.sendBrodcastMessageWithPrefix("");
            BrodcastMessage.sendBrodcastMessageWithPrefix(ChatColor.YELLOW + "" + results.get(0).getPlacement() + ". " + ChatColor.WHITE + results.get(0).getPlayer().getDisplayName() + " " + ChatColor.YELLOW + results.get(0).getScore());
            BrodcastMessage.sendBrodcastMessageWithPrefix(ChatColor.YELLOW + "" + results.get(1).getPlacement() + ". " + ChatColor.WHITE + results.get(1).getPlayer().getDisplayName() + " " + ChatColor.YELLOW + results.get(1).getScore());
            if (Bukkit.getMaxPlayers() > 2) {
                BrodcastMessage.sendBrodcastMessageWithPrefix(ChatColor.YELLOW + "" + results.get(2).getPlacement() + ". " + ChatColor.WHITE + results.get(2).getPlayer().getDisplayName() + " " + ChatColor.YELLOW + results.get(2).getScore());
            }

            Runnables.runLater(140, () -> {
                SpeedrunParty.getMainController().GameDone();
            });
        });
    }

    @Override
    public void boot() {
        WorldManager.createWorld(world + "-" + Gameindex, World.Environment.NORMAL);
    }

    @Override
    public int getRemainingTime() {
        return RemainingSeconds;
    }

    @Override
    public String getName() {
        return "TestGameX";
    }

    @Override
    public List<ResultType> getResults() {
        return results;
    }

    @Override
    public String getWorld() {
        return world + "-" + Gameindex;
    }

    private void updateScores() {
        results.forEach(resultType -> {
            resultType.setScore((int) Math.round(resultType.getPlayer().getLocation().getX()));
        });

        results.sort((a, b) -> Integer.compare(b.getScore(), a.getScore()));

        if (!results.isEmpty()) {
            int currentPlace = 1;
            int lastScore = results.get(0).getScore();
            results.get(0).setPlacement(currentPlace);

            for (int i = 1; i < results.size(); i++) {
                ResultType currentResult = results.get(i);
                if (currentResult.getScore() < lastScore) {
                    currentPlace++;
                }
                currentResult.setPlacement(currentPlace);
                lastScore = currentResult.getScore();
            }
        }
    }
}
