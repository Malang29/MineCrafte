package com.devil.teleport;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class spawnpoint implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            p.teleport(p.getWorld().getSpawnLocation());
            p.sendMessage("스폰으로 이동합니다.");
        }
        return true;
    }
}
