package com.azortis.combatlog.gui;

import com.azortis.combatlog.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class CombatTimeGui {

    public final Inventory inv;
    Setter setter;
    public Integer combatTime = 30;

    public CombatTimeGui(Setter setter) {
        this.setter = setter;
        setter.setCombatTimeGui(this);
        inv = Bukkit.createInventory(null, 27, "Combat Timer Config");
        initializeItems();
    }

    public void initializeItems() {
        inv.setItem(9,createGuiItem(Material.GRAY_STAINED_GLASS_PANE, "", "", ""));
        inv.setItem(10,createGuiItem(Material.RED_STAINED_GLASS_PANE, "§c-10s", "remove 10 seconds", ""));
        inv.setItem(11,createGuiItem(Material.RED_STAINED_GLASS_PANE, "§c-5s", "remove 5 seconds", ""));
        inv.setItem(12,createGuiItem(Material.RED_STAINED_GLASS_PANE, "§c-1s", "remove 1 second", ""));
        inv.setItem(13,createGuiItem(Material.YELLOW_STAINED_GLASS_PANE, "§dCurrent Damage: " + combatTime, "§6Change the time that players are in combat", "§cValues can not be negative!"));
        inv.setItem(14,createGuiItem(Material.GREEN_STAINED_GLASS_PANE, "§a+1s", "Add 1 second", ""));
        inv.setItem(15,createGuiItem(Material.GREEN_STAINED_GLASS_PANE, "§a+5s", "Add 5 seconds", ""));
        inv.setItem(16,createGuiItem(Material.GREEN_STAINED_GLASS_PANE, "§a+10s", "Add 10 seconds", ""));
        inv.setItem(17,createGuiItem(Material.RED_STAINED_GLASS_PANE, "Go Back", "Go back to the config panel", ""));
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
