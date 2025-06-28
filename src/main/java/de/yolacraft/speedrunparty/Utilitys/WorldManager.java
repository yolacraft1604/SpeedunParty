package de.yolacraft.speedrunparty.Utilitys;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

public class WorldManager {
    public static void createWorld(String name, World.Environment type){
        if(Bukkit.getWorld(name) == null){
            WorldCreator worldCreator = new WorldCreator(name);
            worldCreator.environment(type);
            worldCreator.createWorld();
        }
    }
    public static void sendPlayerToWorld(Player player, String worldName){
        World world = Bukkit.getWorld(worldName);
        if (world != null) {
            Location spawn = world.getSpawnLocation();
            player.teleport(spawn);
        }
    }

    public static void sendAllPlayersToWorld(String worldName){
        World world = Bukkit.getWorld(worldName);
        if (world != null) {
            Location spawn = world.getSpawnLocation();
            Bukkit.getOnlinePlayers().forEach(player -> {
                player.teleport(spawn);
            });
        }
    }
}
