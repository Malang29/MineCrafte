package com.devil.itemgui;

import com.devil.Main;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class kit implements CommandExecutor, Listener {

    HashMap<String, Boolean> part = new HashMap<String, Boolean>();
    HashMap<String, Integer> id = new HashMap<String, Integer>();
    int taskID;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("kit")) {
            if (sender.hasPermission("gm use"))
                if (!(sender instanceof Player)) {
                    sender.sendMessage(ChatColor.DARK_RED + "이 명령을 실행할 수 없습니다");
                    return true;
                }
            Player p = (Player) sender;
            openGUI(p);
            return true;
        }
        return false;
    }

    public void openGUI(Player p) {

        Inventory inv = Bukkit.createInventory(null, 9, ChatColor.AQUA + "Kit GUI");

        ItemStack item = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.RED + "[Edelie] " + ChatColor.WHITE + "곡괭이");
        meta.addEnchant(Enchantment.DIG_SPEED,3, false);
        meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 2, false);

        List<String> lore = new ArrayList<String>();
        lore.add("");
        lore.add("§b설명 §8:: 기본적으로 지급되는 아이템입니다.");
        lore.add("");
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(0, item);

        ItemStack i = new ItemStack(Material.IRON_AXE);
        ItemMeta m = i.getItemMeta();

        p.openInventory(inv);
    }

    public ItemStack giveItem(String name) {
        ItemStack item = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.RED + "[Edelie] " + ChatColor.WHITE + "곡괭이");

        List<String> lore = new ArrayList<String>();
        lore.add("");
        lore.add("§b설명 §8:: 기본적으로 지급되는 아이템입니다.");
        lore.add("");
        meta.setLore(lore);

        item.setItemMeta(meta);

        return item;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!ChatColor.stripColor(e.getView().getTitle().toString()).equalsIgnoreCase("Kit GUI")) return;
        if (e.getCurrentItem() == null) return;
        if (e.getCurrentItem().getItemMeta() == null ) return;
        if (e.getCurrentItem().getItemMeta().getDisplayName() == null) return;

        if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("[Edelie] 곡괭이")) {
            Player p = (Player) e.getWhoClicked() ;
           // if (Main.getPlugin(Main.class).eco.getBalance(p) < 10000) {
             //              p.sendMessage("&c당신은 금액이 없습니다.");
               //           return;
                //     }
            //Main.getPlugin(Main.class).eco.withdrawPlayer(p,10000);
            p.getInventory().addItem(giveItem(p.getName()));
            e.setCancelled(true);
            p.closeInventory();
            return;
        }
        return;
    }
    @EventHandler
    public void onUse(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if ((p.getInventory().getItemInMainHand().getType() == Material.IRON_PICKAXE) &&
                (p.getInventory().getItemInMainHand().getItemMeta().hasLore())) {
            if (part.get(p.getName()) == null) part.put(p.getName(), false);
            if (part.get(p.getName()) == true) return;
            if (part.get(p.getName()) == false){
                //Does not have particle effect
                part.put(p.getName(), true);
                taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(Main.class), new Runnable() {
                    double a = 0;

                    public void run() {
                        if (id.get(p.getName()) == null){
                            id.put(p.getName(), taskID);
                        }
                        a += Math.PI / 16;
                        Location loc = p.getLocation();
                        Location first = loc.clone().add( Math.cos(a), Math.sin(a) + 1, Math.sin(a));
                        Location second = loc.clone().add( Math.cos(a + Math.PI), Math.sin(a) + 1, Math.sin(a + Math.PI));

                        p.getWorld().spawnParticle(Particle.FLAME, first,0,0,0,0,0);
                        p.getWorld().spawnParticle(Particle.FLAME, second,0,0,0,0,0);
                    }
                },0,1);
            }
        }
        if (part.get(p.getName()) == null) part.put(p.getName(), false);
        if (part.get(p.getName()) == true){
            Bukkit.getScheduler().cancelTask(id.get(p.getName())); // <-- {Cmaaxx=1217}
            id.remove(p.getName());
            part.put(p.getName(), false);
        }
    }
}
