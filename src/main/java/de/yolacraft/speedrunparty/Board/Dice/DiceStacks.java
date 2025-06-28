package de.yolacraft.speedrunparty.Board.Dice;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DiceStacks {
    public static ItemStack GoldDice(){
        ItemStack dice = new ItemStack(Material.SNOWBALL);
        ItemMeta itemMeta = dice.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GOLD + "Gold Dice (Throw to roll)");
        itemMeta.addEnchant(Enchantment.LUCK, 3, true);
        dice.setItemMeta(itemMeta);
        return dice;
    }
    public static ItemStack SilverDice(){
        ItemStack dice = new ItemStack(Material.SNOWBALL);
        ItemMeta itemMeta = dice.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GOLD + "Silver Dice (Throw to roll)");
        itemMeta.addEnchant(Enchantment.LUCK, 3, true);
        dice.setItemMeta(itemMeta);
        return dice;
    }
    public static ItemStack BronzeDice(){
        ItemStack dice = new ItemStack(Material.SNOWBALL);
        ItemMeta itemMeta = dice.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GOLD + "Bronze Dice (Throw to roll)");
        itemMeta.addEnchant(Enchantment.LUCK, 3, true);
        dice.setItemMeta(itemMeta);
        return dice;
    }
    public static ItemStack Dice(){
        ItemStack dice = new ItemStack(Material.SNOWBALL);
        ItemMeta itemMeta = dice.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GOLD + "Dice (Throw to roll)");
        itemMeta.addEnchant(Enchantment.LUCK, 3, true);
        dice.setItemMeta(itemMeta);
        return dice;
    }
}
