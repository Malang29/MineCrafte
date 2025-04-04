package com.devil.event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Feed implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("Food")) {
            if (!(sender.hasPermission("feed.use"))) {
                sender.sendMessage("§c당신에게는 사용할수있는 권한이 없습니다.");
                return true;
            }
            if (args.length == 0) {
                Player p = (Player) sender;
                if (sender instanceof  Player) {
                    if (p.getFoodLevel() == 20.0) {
                        p.sendMessage(ChatColor.GOLD + "허기를 채웠습니다.");
                        return true;
                    }
                    sender.sendMessage(ChatColor.GOLD + "/Feed <player>");
                    return true;
                }
                if (args.length >=1) {
                    if (Bukkit.getPlayerExact(args[0]) != null) {
                        Player target = Bukkit.getPlayer(args[0]);
                        target.setFoodLevel(20);
                        target.sendMessage("허기를 채웠습니다.");
                        sender.sendMessage(ChatColor.GOLD + target.getName() + "님에 허기를 채웠습니다.");
                        return true;
                    }
                    sender.sendMessage(ChatColor.GRAY + "플레이어가 서버에 없습니다.");
                    return true;
                }
            }
        }
        return false;
    }
}
