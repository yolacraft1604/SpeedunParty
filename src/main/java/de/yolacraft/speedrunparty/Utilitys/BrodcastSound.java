package de.yolacraft.speedrunparty.Utilitys;

import org.bukkit.Bukkit;
import org.bukkit.Sound;

public class BrodcastSound {
    public static void sendBrodcastSound(Sound sound, Float volume, Float pitch) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.playSound(player.getLocation(), sound, volume, pitch);
        });
    }
}
