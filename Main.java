package com.devil;

import com.devil.event.*;
import com.devil.itemgui.kit;
import com.devil.itemgui.kittools;
import com.devil.teleport.spawnpoint;
import com.devil.teleport.tpall;
import com.devil.teleport.tpcommand;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Main extends JavaPlugin implements Listener {
    public HashMap<String, Long> cooldowns = new HashMap<String, Long>();
    public HashMap<UUID, UUID> request = new HashMap<>();
    public static Map<String, ItemStack[]> menus = new HashMap<String, ItemStack[]>();
    public static Main instance;

    private static final ConsoleCommandSender console = Bukkit.getConsoleSender();

    public Economy eco = null;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        if (!setupEconomy()) {
        }
        getCommand("feed").setExecutor(new Feed());
        getCommand("heal").setExecutor(new Heal());
        getCommand("kit").setExecutor(new kit());
        getCommand("gm").setExecutor(new Gmmode());
        getCommand("fly").setExecutor(new fly());
        getCommand("tp").setExecutor(new tpcommand());
        getCommand("speed").setExecutor(new Speed());
        getCommand("tpall").setExecutor(new tpall());
        getCommand("setspawn").setExecutor(new spawnpoint());
        getCommand("test").setExecutor(new kittools());
        Bukkit.getPluginManager().registerEvents(new JoinQuit(), this);
        console.sendMessage("§f[ETC] §7플러그인 활성화");
    }

    @Override
    public void onDisable() {

        console.sendMessage("§f[ETC] §7플러그인 비활성화");
    }

    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            eco = economyProvider.getProvider();
        }
        return (eco != null);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("etc")) {
            if (!sender.hasPermission("etc.reload")) {
                sender.sendMessage(ChatColor.RED + "You cannot run this command");
                return true;
            }
            if (args.length == 0) { // command
                sender.sendMessage(ChatColor.RED + "Usage: /etc reload");
                return true;
            }
            if (args.length > 0) { // reload
                if (args[0].equalsIgnoreCase("reload")) {
                    for (String msg : getConfig().getStringList("reload.message")) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                msg));
                    }
                    reloadConfig();
                }
            }
        }
        return false;
    }
}



