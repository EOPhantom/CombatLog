package com.azortis.combatlog.gui;

import com.azortis.combatlog.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class PlayerCombatConfigGui {

    public final Inventory inv;
    Setter setter;
    public boolean toolBarToggle = true;
    public boolean keepInventoryToggle = false;
    public boolean villagerToggle = false;

    public PlayerCombatConfigGui(Setter setter) {
        this.setter = setter;
        setter.setPlayerCombatConfigGui(this);
        inv = Bukkit.createInventory(null,9,"Damage Amount");
        initializeItems();
    }

    public void initializeItems() {
        inv.setItem(0,createGuiItem(Material.GRAY_STAINED_GLASS_PANE, "", "", ""));
        inv.setItem(1,createGuiItem(Material.GRAY_STAINED_GLASS_PANE, "", "", ""));
        if (toolBarToggle) {
            inv.setItem(2,createGuiItem(Material.GREEN_STAINED_GLASS_PANE, "Toggle Toolbar", "§aGreen = Toolbar is active", "§cRed = Toolbar is disabled"));
        } else {
            inv.setItem(2,createGuiItem(Material.RED_STAINED_GLASS_PANE, "Toggle Toolbar", "§aGreen = Toolbar is active", "§cRed = Toolbar is disabled"));
        }
        inv.setItem(3,createGuiItem(Material.GRAY_STAINED_GLASS_PANE, "", "", ""));
        if (keepInventoryToggle) {
            inv.setItem(4,createGuiItem(Material.GREEN_STAINED_GLASS_PANE, "Toggle Keep Inventory", "§aGreen = Keep Inventory is active", "§cRed = Keep Inventory is disabled"));
        } else {
            inv.setItem(4,createGuiItem(Material.RED_STAINED_GLASS_PANE, "Toggle Keep Inventory", "§aGreen = Keep Inventory is active", "§cRed = Keep Inventory is disabled"));
        }
        inv.setItem(5,createGuiItem(Material.GRAY_STAINED_GLASS_PANE, "", "", ""));
        if (villagerToggle) {
            inv.setItem(6,createGuiItem(Material.GREEN_STAINED_GLASS_PANE, "Toggle villager CombatLog", "§aGreen = Villager CombatLog is active", "§cRed = Villager CombatLog is disabled"));
        } else {
            inv.setItem(6,createGuiItem(Material.RED_STAINED_GLASS_PANE, "Toggle villager CombatLog", "§aGreen = Villager CombatLog is active", "§cRed = Villager CombatLog is disabled"));
        }
        inv.setItem(7,createGuiItem(Material.GRAY_STAINED_GLASS_PANE, "", "", ""));
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
