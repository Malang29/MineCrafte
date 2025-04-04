package com.devil.teleport;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class request implements CommandExecutor {

    private HashMap<UUID, UUID> request = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("tpa")) {
                if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null)
                        p.sendMessage(ChatColor.GOLD + "플레이어가 온라인 상태가 아닙니다.");
                    
                }
            }
        }
        return false;
    }
}
