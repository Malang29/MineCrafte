package com.devil.itemgui;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class kittools implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("gm.kittools")) {
            Player p = (Player) sender;
            ItemStack food = new ItemStack(Material.GRILLED_PORK);
            ItemStack sowrd = new ItemStack(Material.STONE_SWORD);
            ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
            p.getInventory().addItem(food);
            p.getInventory().addItem(sowrd);
            p.getInventory().addItem(chestplate);
        }
        return false;
    }
}
