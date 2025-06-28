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
    private static int current = 1;
    private static BukkitRunnable runnable;

    public Dice(List<Player> p){
        players = p;
    }

    public void giveDiceToPlayers(){
        results = new int[SpeedrunParty.getPlayerStorage().getLen()];
        Bukkit.getOnlinePlayers().forEach(player -> {
            ItemStack dice = new ItemStack(Material.SNOWBALL);
            ItemMeta itemMeta = dice.getItemMeta();
            itemMeta.setDisplayName(ChatColor.GOLD + "Throw to roll Dice");
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
                        p.sendTitle(String.valueOf(current), null, 0,6,0);
                    }else{
                        p.sendTitle(String.valueOf(results[idx]), null, 0,6,0);
                    }
                    idx++;
                }
                current = current = current % 6 + 1;
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
