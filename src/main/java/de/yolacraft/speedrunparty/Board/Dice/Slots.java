package de.yolacraft.speedrunparty.Board.Dice;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Slots {
    private static int[][] Slots = {{-90, 69, 250},{-80, 69, 250},{-70, 69, 250},{-60, 69, 250},{-50, 69, 250},{-40, 69, 250},{-30, 69, 250},{-20, 69, 250},{-10, 69, 250},{0, 69, 250},{10, 69, 250}};

    public static void teleportPlayerToSlot(int slot, Player player){
        if(slot >= Slots.length){
            slot = 10;
        }
        Location loc = new Location(Bukkit.getWorld("Main"), Slots[slot][0], Slots[slot][1], Slots[slot][2]);
        player.teleport(loc);
    }
}
