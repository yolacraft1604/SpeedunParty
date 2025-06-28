package de.yolacraft.speedrunparty.Utilitys;

import org.bukkit.Bukkit;

import javax.annotation.Nullable;

public class BrodcastTitle {
    public static void sendBrodcastTitle(String title, @Nullable String subtitle , int fadeIn, int stay, int fadeOut) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
        });
    }
}