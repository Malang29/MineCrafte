package com.devil.teleport;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class tpall implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("tpall.gm")){
            if (sender instanceof Player) {
                Player player = (Player) sender;
                    for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                        p.teleport(player.getLocation());
                    }
            }
        }
        return false;
    }
}
