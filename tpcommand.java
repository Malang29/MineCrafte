package com.devil.teleport;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class tpcommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("tp")) {
            if (sender.hasPermission("gm.tp")) {
                if (!(sender instanceof Player)) {
                    System.out.println("You can only run this command as a player");
                }
                Player p = (Player) sender;
                if (!p.hasPermission("tp.use")) {
                    p.sendMessage(ChatColor.RED + "You can not use this command!");
                    return true;
                }
                if (args.length == 0) {
                    p.sendMessage(ChatColor.RED + "Correct usage: /tp <player> <otherplayer> or /tp <x> <y> <z> <player>");
                    return true;
                }
                if (args.length >= 3) {
                    if (args.length == 3) {
                        int x, y, z;
                        try {
                            x = Integer.parseInt(args[0]);
                            y = Integer.parseInt(args[1]);
                            z = Integer.parseInt(args[2]);
                        } catch (NumberFormatException e) {
                            p.sendMessage(ChatColor.RED + "잘못된 좌표입니다!");
                            return true;
                        }
                        p.teleport(new Location(p.getWorld(), x, y, z));
                        p.sendMessage(ChatColor.GOLD + "해당 좌표로 이동합니다");
                        return true;
                    }
                    if (args.length == 4) {
                        int x, y, z;
                        try {
                            x = Integer.parseInt(args[0]);
                            y = Integer.parseInt(args[1]);
                            z = Integer.parseInt(args[2]);
                        } catch (NumberFormatException e) {
                            p.sendMessage(ChatColor.RED + "잘못된 좌표입니다!");
                            return true;
                        }
                        Player target = Bukkit.getPlayer(args[3]);
                        if (target == null || !target.isOnline()) {
                            p.sendMessage(ChatColor.RED + "That player is not online!");
                            return true;
                        }
                        target.teleport(new Location(p.getWorld(), x, y, z));
                        p.sendMessage(ChatColor.GREEN + "Teleport" + target.getName() + " to " + x + "," + y + "," + z + ",");
                        target.sendMessage(ChatColor.GREEN + "You were teleport to " + x + "," + +y + "," + z);
                        return true;
                    }
                } else if (args.length >= 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null || !target.isOnline()) {
                        p.sendMessage(ChatColor.RED + "That player is not online");
                        return true;
                    }
                    if (args.length == 2) {
                        Player target2 = Bukkit.getPlayer(args[1]);
                        if (target2 == null || !target2.isOnline()) {
                            p.sendMessage(ChatColor.RED + "That player is not online.");
                            return true;
                        }
                        p.teleport(target);
                        p.sendMessage(ChatColor.GREEN + "Teleported to" + target.getName() + "to" + target2.getName());
                        return true;
                    }
                    p.teleport(target);
                    p.sendMessage(ChatColor.GOLD + target.getName() + "님에게 이동합니다.");
                    return true;
                } else {
                    p.sendMessage(ChatColor.RED + "Correct usage: /tp <player> <otherplayer> or /tp <x> <y> <z> <player>");
                }
            }
        }
        return true;
    }
}


