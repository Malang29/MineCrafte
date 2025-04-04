package com.devil.event;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Speed implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("speed.use")) {
            if (!(sender instanceof Player)) {
                System.out.println("You can only run this command as a player");
                return false;
            }
            Player p = (Player) sender;
            if (!p.hasPermission("flyspeed.spd")) {
                p.sendMessage(ChatColor.RED + "You Don't have Permission");
                return false;
            }
            if (args.length == 0) {
                p.sendMessage(ChatColor.RED + "Please provide a speed from 1 - 10");
                return false;
            }

            int speed;
            try {
                speed = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                p.sendMessage(ChatColor.RED + "Please provide a speed from 1 - 10");
                return false;
            }
            if (p.isFlying()) {
                p.setFlySpeed((float) speed / 10);
            } else {
                p.setWalkSpeed((float) speed / 10);
            }
            p.sendMessage(ChatColor.GREEN + "Speed set to "+ ChatColor.BLUE + speed);
            return true;
        }

        return false;
    }
}



