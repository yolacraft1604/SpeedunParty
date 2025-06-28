package de.yolacraft.speedrunparty.Board.Dice;

import de.yolacraft.speedrunparty.SpeedrunParty;
import de.yolacraft.speedrunparty.Utilitys.PlayerScore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.List;

public class Dice {
    private static List<Player> players;
    private int[] results;
    private int[] buffer;
    private static int current = 1;
    private static int currentSilver = 1;
    private static int currentBronze = 1;
    private static BukkitRunnable runnable;

    public Dice(List<Player> p){
        players = p;
    }

    public void giveDiceToPlayers(){
        results = new int[SpeedrunParty.getPlayerStorage().getLen()];
        buffer = new int[SpeedrunParty.getPlayerStorage().getLen()];
        Bukkit.getOnlinePlayers().forEach(player -> {
            ItemStack dice = new ItemStack(Material.SNOWBALL);
            ItemMeta itemMeta = dice.getItemMeta();
            itemMeta.setDisplayName(ChatColor.GOLD + "Dice (Throw to roll)");
            itemMeta.addEnchant(Enchantment.LUCK, 3, true);
            dice.setItemMeta(itemMeta);
            player.getInventory().setItem(4, dice);
        });



        runnable = new BukkitRunnable() {
            @Override
            public void run() {

                int idx = 0;
                for (Player p : players){
                    if(results[idx] == 0){
                        if(p.getInventory().contains(DiceStacks.GoldDice())){
                            p.sendTitle(ChatColor.WHITE + String.valueOf(buffer[players.indexOf(p)]) + " " + ChatColor.YELLOW + String.valueOf(current), null, 0,6,0);
                        }else if(p.getInventory().contains(DiceStacks.SilverDice())){
                            p.sendTitle(ChatColor.WHITE + String.valueOf(buffer[players.indexOf(p)]) + " " + ChatColor.GRAY + String.valueOf(currentSilver), null, 0,6,0);
                        }else if(p.getInventory().contains(DiceStacks.BronzeDice())){
                            p.sendTitle(ChatColor.WHITE + String.valueOf(buffer[players.indexOf(p)]) + " " + ChatColor.GOLD + String.valueOf(currentBronze), null, 0,6,0);
                        }else {
                            p.sendTitle(ChatColor.WHITE + String.valueOf(current), null, 0,6,0);
                        }
                    }else{
                        p.sendTitle(String.valueOf(results[idx]), null, 0,6,0);
                    }
                    idx++;
                }
                current = current % 6 + 1;
                currentSilver = current % 5 + 1;
                currentBronze = current % 3 + 1;
            }
        }; runnable.runTaskTimer(SpeedrunParty.getInstance(), 5, 2);
    }
    public void setPlayersScore(Player p){
        int index = players.indexOf(p);
        results[index] = current;
        if(lastHasDone()){
            SpeedrunParty.getMainController().DiceDone();
            runnable.cancel();
        }
    }
    public void setPlayersScoreBuffer(Player p){
        int index = players.indexOf(p);
        buffer[index] = current;
    }

    public void setPlayersScoreAndMerge(Player p, int extra){
        int index = players.indexOf(p);
        switch (extra){
            case 1:
                results[index] = current + buffer[index];
                break;
            case 2:
                results[index] = currentSilver + buffer[index];
                break;
            case 3:
                results[index] = currentBronze + buffer[index];
                break;
        }
        if(lastHasDone()){
            SpeedrunParty.getMainController().DiceDone();
            runnable.cancel();
        }
    }

    public int getPlayerScore(Player p){
        int index = players.indexOf(p);
        return(results[index]);
    }

    private boolean lastHasDone(){
        for (int result : results) {
            if(result == 0){
                return false;
            }
        }
        return true;
    }

    public void resetDice(){
        results = new int[SpeedrunParty.getPlayerStorage().getLen()];
    }

    public static List<Player> getPlayers() {
        return players;
    }

    public static void setPlayers(List<Player> players) {
        Dice.players = players;
    }

    public int[] getResults() {
        return results;
    }

    public void setResults(int[] results) {
        this.results = results;
    }
}
