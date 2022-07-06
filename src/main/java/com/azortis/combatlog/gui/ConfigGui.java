package com.azortis.combatlog.gui;

import com.azortis.combatlog.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitTask;

import java.util.Arrays;

public class ConfigGui {

    public final Inventory inv;
    Setter setter;
    public boolean toggle;

    public ConfigGui(Setter setter) {
        this.setter = setter;
        setter.setConfigGui(this);
        inv = Bukkit.createInventory(null, 9, "CombatLog Config");
        initializeItems();
    }

    public void initializeItems() {
        inv.setItem(0,createGuiItem(Material.GRAY_STAINED_GLASS_PANE, "", "", ""));
        inv.setItem(1,createGuiItem(Material.GRAY_STAINED_GLASS_PANE, "", "", ""));
        if (toggle) {
            inv.setItem(2,createGuiItem(Material.GREEN_STAINED_GLASS_PANE, "Toggle CombatLog", "§aGreen = Plugin is active", "§cRed = Plugin is disabled"));
        } else {
            inv.setItem(2,createGuiItem(Material.RED_STAINED_GLASS_PANE, "Toggle CombatLog", "§aGreen = Plugin is active", "§cRed = Plugin is disabled"));
        }
        inv.setItem(3,createGuiItem(Material.GRAY_STAINED_GLASS_PANE, "", "", ""));
        inv.setItem(4,createGuiItem(Material.ORANGE_STAINED_GLASS_PANE, "Set Combat Timer", "§6Change the time that a player is in combat for", "§cValue can not be negative!"));
        inv.setItem(5,createGuiItem(Material.GRAY_STAINED_GLASS_PANE, "", "", ""));
        inv.setItem(6,createGuiItem(Material.PURPLE_STAINED_GLASS_PANE, "Modify Combat Log", "§dChange what happens to the player", "§d"));
        inv.setItem(7,createGuiItem(Material.GRAY_STAINED_GLASS_PANE, "", "", ""));
        inv.setItem(8,createGuiItem(Material.GRAY_STAINED_GLASS_PANE, "", "", ""));
    }

    public ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(name);

        // Set the lore of the item
        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }

    public void openInventory(final HumanEntity ent) {
        ent.openInventory(inv);
    }

}
