package de.yolacraft.speedrunparty.GameManagement;

import de.yolacraft.speedrunparty.Board.Dice.Dice;
import de.yolacraft.speedrunparty.Board.Dice.Slots;
import de.yolacraft.speedrunparty.Games.ResultType;
import de.yolacraft.speedrunparty.Games.TestGame.TestGameMain;
import de.yolacraft.speedrunparty.SpeedrunParty;
import de.yolacraft.speedrunparty.Utilitys.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    private List<PlayerScore> data;
    private static Dice dice;
    private List<Player> first;
    private List<Player> second;
    private List<Player> third;

    public Controller(Dice d){
        dice = d;
    }
    public void startGame(){
        data = new ArrayList<>();
        first = new ArrayList<>();
        second = new ArrayList<>();
        third = new ArrayList<>();
        SpeedrunParty.getPlayerStorage().getPlayers().forEach(player -> {
            data.add(new PlayerScore(player, 0));
        });

        SpeedrunParty.getMainManager().setServerState(1);
        WorldManager.sendAllPlayersToWorld("Main");
        BrodcastMessage.sendBrodcastMessageWithPrefix("Game Started");
        Next();
    }
    public void Next(){
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.setHealth(20.0);
            player.setFoodLevel(20);
            player.getInventory().clear();
        });

        SpeedrunParty.getGameClusterRoot().recreate();

        for (PlayerScore score : data){
            Slots.teleportPlayerToSlot(score.getScore(), score.getPlayer());
            score.getPlayer().setGameMode(GameMode.ADVENTURE);
        }

        Runnables.runLater(60, () -> {
            if(data.get(0).getScore() != 0){
                if(SpeedrunParty.getMainManager().getGameCycle() == 1)
                    SpeedrunParty.getMainManager().setGameCycle(0);
                else
                    SpeedrunParty.getMainManager().setGameCycle(1);
            }

            int c = SpeedrunParty.getMainManager().getGameCycle();
            if(c == 0){
                DiceNow();
            }else if(c == 1){
                GameNow();
            }
        });

    }
    private void DiceNow(){
        dice.giveDiceToPlayers();
    }

    private void GameNow(){
        SpeedrunParty.getGameClusterRoot().pick();
        SpeedrunParty.getGameClusterRoot().showAnimation();

        Runnables.runLater(160, () -> {
            SpeedrunParty.getGameClusterRoot().launch();
        });
    }
    public void DiceDone(){
        for (PlayerScore score : data){
            score.setScore(score.getScore() + dice.getPlayerScore(score.getPlayer()));
            score.getPlayer().sendTitle(String.valueOf(dice.getPlayerScore(score.getPlayer())), null, 0, 40, 5);
        }
        dice.resetDice();
        System.out.println(data);
        first = new ArrayList<>();
        second = new ArrayList<>();
        third = new ArrayList<>();
        Next();
    }

    public void GameDone(){
        for (ResultType result : SpeedrunParty.getGameClusterRoot().getScores()){
            if(result.getPlacement() == 1)
                first.add(result.getPlayer());
            if(result.getPlacement() == 2)
                second.add(result.getPlayer());
            if(result.getPlacement() == 3)
                third.add(result.getPlayer());
        }


        Next();
    }

    public Dice getDice() {
        return dice;
    }

    public List<PlayerScore> getData() {
        return data;
    }
    private void sortData() {
        data.sort((a, b) -> Integer.compare(b.getScore(), a.getScore()));
    }

    public List<Player> getFirst() {
        return first;
    }

    public List<Player> getSecond() {
        return second;
    }

    public List<Player> getThird() {
        return third;
    }
}
