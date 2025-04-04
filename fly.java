package com.devil.event;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class fly implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (!sender.hasPermission("gm.fly")) {
            sender.sendMessage(ChatColor.RED + "당신에겐 이 명령어를 사용할 수 있는 권한이 없습니다.");
            return true;
        }
        if (p.getAllowFlight() == false) {
            p.setAllowFlight(true);
            p.sendMessage(ChatColor.AQUA + "[FlY] " + ChatColor.GRAY + "플라이가 활성화 되었습니다.");
            return true;
        }
        p.setAllowFlight(false);
        p.sendMessage(ChatColor.AQUA + "[FlY] " + ChatColor.GRAY + "플라이가 비활성화 되었습니다.");
        return true;
    }
}
