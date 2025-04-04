package com.devil.event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuit implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if (p.hasPlayedBefore()) {
            e.setJoinMessage(ChatColor.GOLD + p.getName() + ChatColor.YELLOW + "Firest Join");
        } else {
            Bukkit.broadcastMessage(ChatColor.GREEN + "[접속] " + ChatColor.WHITE + p.getName() + "님께서 서버에 접속하셨습니다.");
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        Bukkit.broadcastMessage(ChatColor.RED + "[퇴장] " + ChatColor.WHITE + p.getName() + "님께서 서버를 퇴장하셧습니다.");
    }
}
