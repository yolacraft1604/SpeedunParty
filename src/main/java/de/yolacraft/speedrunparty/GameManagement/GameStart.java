package de.yolacraft.speedrunparty.GameManagement;

import de.yolacraft.speedrunparty.SpeedrunParty;
import de.yolacraft.speedrunparty.Utilitys.BrodcastMessage;
import de.yolacraft.speedrunparty.Utilitys.BrodcastSound;
import de.yolacraft.speedrunparty.Utilitys.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;

public class GameStart {
    private static int RemainingSeconds = -1;

    public GameStart() {

    }

    public void StartGame(){
        RemainingSeconds = 10;
        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                int[] print = {1, 2, 3, 5, 10};
                if(SpeedrunParty.getPlayerStorage().getLen() < Bukkit.getMaxPlayers()){
                    BrodcastMessage.sendBrodcastMessageWithPrefix(ChatColor.RED + "Game Start Canceled");
                    RemainingSeconds = -1;
                    this.cancel();
                }
                if(Arrays.stream(print).anyMatch(x -> x == RemainingSeconds)){
                    BrodcastMessage.sendBrodcastMessageWithPrefix(ChatColor.GREEN + "Game starts in " +
                            ChatColor.YELLOW + RemainingSeconds + ChatColor.GREEN + " Seconds");
                    BrodcastSound.sendBrodcastSound(Sound.BLOCK_NOTE_BLOCK_BANJO, 1f, 1f);
                }
                if(RemainingSeconds == 0){
                    SpeedrunParty.getMainController().startGame();
                    this.cancel();
                }
                RemainingSeconds--;
            }
        };
        runnable.runTaskTimer(SpeedrunParty.getInstance(), 0, 20);
    }

    public int getRemainingSeconds() {
        return RemainingSeconds;
    }
}
