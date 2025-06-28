package de.yolacraft.speedrunparty.PlayerManagement;

import de.yolacraft.speedrunparty.GameManagement.GameStart;
import de.yolacraft.speedrunparty.SpeedrunParty;
import de.yolacraft.speedrunparty.Utilitys.BrodcastMessage;
import de.yolacraft.speedrunparty.Utilitys.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuit implements Listener {
    @EventHandler
    void onJoin(PlayerJoinEvent event){
        event.setJoinMessage(null);
        if(SpeedrunParty.getMainManager().getServerState() == 0){
            WorldManager.sendPlayerToWorld(event.getPlayer(), "world");
            SpeedrunParty.getPlayerStorage().addPlayer(event.getPlayer());
            BrodcastMessage.sendBrodcastMessageWithPrefix(ChatColor.GRAY + event.getPlayer().getDisplayName() +
                    " has joined! (" + ChatColor.YELLOW + SpeedrunParty.getPlayerStorage().getLen() + ChatColor.GRAY + "/" +
                    ChatColor.YELLOW + Bukkit.getMaxPlayers() + ChatColor.GRAY + ")");

            event.getPlayer().setHealth(20.0);
            event.getPlayer().setFoodLevel(20);
            event.getPlayer().getInventory().clear();
            event.getPlayer().setGameMode(GameMode.ADVENTURE);
            event.getPlayer().teleport(new Location(Bukkit.getWorld("world"), 0 ,100, 0));

            if(SpeedrunParty.getPlayerStorage().getLen() == Bukkit.getMaxPlayers()){
                SpeedrunParty.getGameStart().StartGame();
            }
        }else{
            event.getPlayer().kickPlayer("Game already Started");
        }
    }
    @EventHandler
    void onQuit(PlayerQuitEvent event){
        event.setQuitMessage(null);

        if(SpeedrunParty.getMainManager().getServerState() == 0){
            SpeedrunParty.getPlayerStorage().delPlayer(event.getPlayer());
            BrodcastMessage.sendBrodcastMessageWithPrefix(ChatColor.GRAY + event.getPlayer().getDisplayName() +
                    " has left! (" + ChatColor.YELLOW + SpeedrunParty.getPlayerStorage().getLen() + ChatColor.GRAY + "/" +
                    ChatColor.YELLOW + Bukkit.getMaxPlayers() + ChatColor.GRAY + ")");
        }else{
            BrodcastMessage.sendBrodcastMessageWithPrefix(ChatColor.GRAY + event.getPlayer().getDisplayName() +
                    " has left! (" + ChatColor.YELLOW + "/rejoin" + ChatColor.GRAY + ")");
        }
    }
}
