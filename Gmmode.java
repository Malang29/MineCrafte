package com.devil.event;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gmmode implements CommandExecutor {
// implements CommandExecutor {
    // on치면 자동완성.
// }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String labal, String[] args) {
            if (sender.hasPermission("gm.gamemode")) {
                if (args.length > 0) {
                    Player p = (Player) sender;
                    if (args[0].equalsIgnoreCase("1")) {
                        p.setGameMode(GameMode.CREATIVE);
                        p.sendMessage(ChatColor.GREEN + "[GM] " + "§f게임모드가 §cOP §f모드로 전환되었습니다. ");
                    } else if (args[0].equalsIgnoreCase("2")) {
                        p.setGameMode(GameMode.SURVIVAL);
                        p.sendMessage(ChatColor.GREEN + "[GM] " + "§f게임모드가 §a생존 §f모드로 전환되었습니다. ");
                    } else if (args[0].equalsIgnoreCase("3")) {
                        p.setGameMode(GameMode.ADVENTURE);
                        p.sendMessage(ChatColor.GREEN + "[GM] " + "§f게임모드가 §6모험 §f모드로 전환되었습니다. ");
                    } else if (args[0].equalsIgnoreCase("4")) {
                        p.setGameMode(GameMode.SPECTATOR);
                        p.sendMessage(ChatColor.GREEN + "[GM] " + "§f게임모드가 §b관전 §f모드로 전환되었습니다. ");
                    }
                }
            }

        return false;
    }
}