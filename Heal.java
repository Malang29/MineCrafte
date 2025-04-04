package com.devil.event;

import com.devil.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class Heal implements CommandExecutor {

    public static Main instance;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        int cooldownTime = 60;
        if (label.equalsIgnoreCase("heal")) {
            if (sender.hasPermission("heal.use")) {
                if (args.length == 0) {
                    Player p = (Player) sender;
                    if (sender instanceof Player) {
                        if (p.getHealth() == 20.0) {
                            p.sendMessage("§6You have been healed!");
                            return true;
                        }
                        p.setHealth(Main.instance.getConfig().getDouble("heal-a"));
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.instance.getConfig().getString("message.heal").replace("%player%", p.getName())));
                        return true;
                    }
                    sender.sendMessage("§6> /heal <player-name>");
                    return true;
                }
                if (args.length >= 1) {
                    if (Bukkit.getPlayerExact(args[0]) != null) {
                        Player target = Bukkit.getPlayer(args[0]);
                        target.setHealth(20.0);
                        target.setFoodLevel(20);
                        target.sendMessage("§6회복되었습니다.");
                        sender.sendMessage(ChatColor.GOLD + target.getName() + "님을 회복하였습니다.");
                        return true;
                    }
                    sender.sendMessage("§8플레이어가 서버에 없습니다.");
                    return true;
                }
            }
            sender.sendMessage("§4당신에게는 명령어를 사용할 수 있는 권한이 없습니다.");
            return true;
        }
        return false;
    }
}


