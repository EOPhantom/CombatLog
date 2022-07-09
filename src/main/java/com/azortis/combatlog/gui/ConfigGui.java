package com.azortis.combatlog.gui;

import com.azortis.combatlog.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ConfigGui {

    public final Inventory inv;
    Setter setter;
    public boolean toggle;

    public ConfigGui(Setter setter) {
        this.setter = setter;
        setter.setConfigGui(this);
        inv = Bukkit.createInventory(null, 27, "CombatLog Config");
        initializeItems();
    }

    public void initializeItems() {
        inv.setItem(9,createGuiItem(Material.WHITE_STAINED_GLASS_PANE, "", "", ""));
        inv.setItem(10,createGuiItem(Material.WHITE_STAINED_GLASS_PANE, "", "", ""));
        if (toggle) {
            inv.setItem(11,createGuiItem(Material.GREEN_STAINED_GLASS_PANE, "Toggle CombatLog", "§aGreen = Plugin is active", "§cRed = Plugin is disabled"));
        } else {
            inv.setItem(11,createGuiItem(Material.RED_STAINED_GLASS_PANE, "Toggle CombatLog", "§aGreen = Plugin is active", "§cRed = Plugin is disabled"));
        }
        inv.setItem(12,createGuiItem(Material.WHITE_STAINED_GLASS_PANE, "", "", ""));
        inv.setItem(13,createGuiItem(Material.ORANGE_STAINED_GLASS_PANE, "Set Combat Timer", "§6Change the time that a player is in combat for", "§cValue can not be negative!"));
        inv.setItem(14,createGuiItem(Material.WHITE_STAINED_GLASS_PANE, "", "", ""));
        inv.setItem(15,createGuiItem(Material.PURPLE_STAINED_GLASS_PANE, "Modify Combat Log", "§dChange what happens to the player", "§d"));
        inv.setItem(16,createGuiItem(Material.WHITE_STAINED_GLASS_PANE, "", "", ""));
        inv.setItem(17,createGuiItem(Material.WHITE_STAINED_GLASS_PANE, "", "", ""));
        for (int i = 0; i < 9; i++) {
            inv.setItem(i ,createGuiItem(Material.GRAY_STAINED_GLASS_PANE, "", "", ""));
        }
        for (int i = 18; i < 27; i++) {
            inv.setItem(i ,createGuiItem(Material.GRAY_STAINED_GLASS_PANE, "", "", ""));
        }
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
