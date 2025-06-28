package de.yolacraft.speedrunparty.PlayerManagement;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerStorage {
    private List<Player> players;

    public PlayerStorage(){
        players = new ArrayList<>();
        Bukkit.getOnlinePlayers().forEach(player -> {
            players.add(player);
        });
    }
    public void addPlayer(Player p){
        players.add(p);
    }
    public void delPlayer(Player p){
        players.remove(p);
    }
    public int getLen(){
        return players.size();
    }
    public List<Player> getPlayers(){
        return players;
    }
}
