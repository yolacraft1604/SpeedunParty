package de.yolacraft.speedrunparty.Utilitys;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

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

    public void copyAndLoadWorld(String templateName, String newWorldName) {
        File source = new File(Bukkit.getWorldContainer(), templateName);
        File target = new File(Bukkit.getWorldContainer(), newWorldName);

        if (!source.exists()) {
            return;
        }

        try {
            Files.walk(source.toPath()).forEach(path -> {
                try {
                    Path relative = source.toPath().relativize(path);
                    Path targetPath = target.toPath().resolve(relative);

                    if (path.getFileName().toString().equals("uid.dat") ||
                            path.getFileName().toString().equals("session.lock")) {
                        return;
                    }

                    if (Files.isDirectory(path)) {
                        Files.createDirectories(targetPath);
                    } else {
                        Files.copy(path, targetPath, StandardCopyOption.REPLACE_EXISTING);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            WorldCreator wc = new WorldCreator(newWorldName);
            World world = Bukkit.createWorld(wc);

            if (world != null) {
                return;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
