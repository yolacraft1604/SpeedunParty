package de.yolacraft.speedrunparty.Utilitys;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class BrodcastMessage {
    public static void sendBrodcastMessage(String msg) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.sendMessage(msg);
        });
    }
    public static void sendBrodcastMessageWithPrefix(String msg) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.sendMessage(ChatColor.BLUE + "[SPEEDRUN PARTY] " + msg);
        });
    }
}