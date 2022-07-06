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
        inv = Bukkit.createInventory(null, 9, "Combat Timer Config");
        initializeItems();
    }

    public void initializeItems() {
        inv.setItem(0,createGuiItem(Material.GRAY_STAINED_GLASS_PANE, "", "", ""));
        inv.setItem(1,createGuiItem(Material.RED_STAINED_GLASS_PANE, "§c-10s", "remove 10 seconds", ""));
        inv.setItem(2,createGuiItem(Material.RED_STAINED_GLASS_PANE, "§c-5s", "remove 5 seconds", ""));
        inv.setItem(3,createGuiItem(Material.RED_STAINED_GLASS_PANE, "§c-1s", "remove 1 second", ""));
        inv.setItem(4,createGuiItem(Material.YELLOW_STAINED_GLASS_PANE, "§dCurrent Damage: " + combatTime, "§6Change the time that players are in combat", "§cValues can not be negative!"));
        inv.setItem(5,createGuiItem(Material.GREEN_STAINED_GLASS_PANE, "§a+1s", "Add 1 second", ""));
        inv.setItem(6,createGuiItem(Material.GREEN_STAINED_GLASS_PANE, "§a+5s", "Add 5 seconds", ""));
        inv.setItem(7,createGuiItem(Material.GREEN_STAINED_GLASS_PANE, "§a+10s", "Add 10 seconds", ""));
        inv.setItem(8,createGuiItem(Material.RED_STAINED_GLASS_PANE, "Go Back", "Go back to the config panel", ""));
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
