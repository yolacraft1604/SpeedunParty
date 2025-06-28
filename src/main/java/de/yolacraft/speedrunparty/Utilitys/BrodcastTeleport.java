package de.yolacraft.speedrunparty.Utilitys;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class BrodcastTeleport {
    public static void sendTeleport(Location location) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.teleport(location);
        });
    }
}
