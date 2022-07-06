package com.azortis.combatlog.listener;

import com.azortis.combatlog.Setter;
import com.azortis.combatlog.gui.CombatTimeGui;
import com.azortis.combatlog.gui.ConfigGui;
import com.azortis.combatlog.gui.PlayerCombatConfigGui;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GuiListener implements Listener {

    Setter setter;
    ConfigGui configGui;
    CombatTimeGui combatTimeGui;
    PlayerCombatConfigGui playerCombatConfigGui;

    public GuiListener(Setter setter) {
        this.setter = setter;
        setter.setGuiListener(this);
        configGui = setter.getConfigGui();
        combatTimeGui = setter.getCombatTimeGui();
        playerCombatConfigGui = setter.getPlayerCombatConfigGui();
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().equals(configGui.inv)) {
            event.setCancelled(true);
            ItemStack clickedItem = event.getCurrentItem();
            Player player = (Player) event.getWhoClicked();
            if (clickedItem == null || clickedItem.getType().isAir()) return;
            if (event.getRawSlot() == 2) {
                if (configGui.toggle) {
                    configGui.toggle = false;
                    configGui.inv.setItem(2,configGui.createGuiItem(Material.RED_STAINED_GLASS_PANE, "Toggle DamageTools", "Green = Plugin is active", "Red = Plugin is disabled"));
                } else {
                    configGui.toggle = true;
                    configGui.inv.setItem(2,configGui.createGuiItem(Material.GREEN_STAINED_GLASS_PANE, "Toggle DamageTools", "Green = Plugin is active", "Red = Plugin is disabled"));
                }
            } else if (event.getRawSlot() == 4) {
                player.closeInventory();
                combatTimeGui.openInventory(player);
            } else if (event.getRawSlot() == 6) {
                player.closeInventory();
                playerCombatConfigGui.openInventory(player);
            }
        } else if (event.getInventory().equals(combatTimeGui.inv)) {
            event.setCancelled(true);
            ItemStack clickedItem = event.getCurrentItem();
            Player player = (Player) event.getWhoClicked();
            if (clickedItem == null || clickedItem.getType().isAir()) return;
            if (event.getRawSlot() == 1) {
                combatTimeGui.combatTime -= 10;
            } else if (event.getRawSlot() == 2) {
                combatTimeGui.combatTime -= 5;
            } else if (event.getRawSlot() == 3) {
                combatTimeGui.combatTime -= 1;
            } else if (event.getRawSlot() == 5) {
                combatTimeGui.combatTime += 1;
            } else if (event.getRawSlot() == 6) {
                combatTimeGui.combatTime += 5;
            } else if (event.getRawSlot() == 7) {
                combatTimeGui.combatTime += 10;
            } else if (event.getRawSlot() == 8) {
                player.closeInventory();
            }
            if (combatTimeGui.combatTime < 0) {
                combatTimeGui.combatTime = 0;
            }
            combatTimeGui.inv.setItem(4,combatTimeGui.createGuiItem(Material.YELLOW_STAINED_GLASS_PANE, "Current Damage: " + combatTimeGui.combatTime, "§6Change the value items take each time", "§cValues can not be negative!"));
        } else if (event.getInventory().equals(playerCombatConfigGui.inv)) {
            event.setCancelled(true);
            ItemStack clickedItem = event.getCurrentItem();
            Player player = (Player) event.getWhoClicked();
            if (clickedItem == null || clickedItem.getType().isAir()) return;
            if (event.getRawSlot() == 2) {
                if (playerCombatConfigGui.toolBarToggle) {
                    playerCombatConfigGui.toolBarToggle = false;
                    playerCombatConfigGui.inv.setItem(2,playerCombatConfigGui.createGuiItem(Material.RED_STAINED_GLASS_PANE, "Toggle Toolbar", "§aGreen = Toolbar is active", "§cRed = Toolbar is disabled"));
                } else {
                    playerCombatConfigGui.toolBarToggle = true;
                    playerCombatConfigGui.inv.setItem(2,playerCombatConfigGui.createGuiItem(Material.GREEN_STAINED_GLASS_PANE, "Toggle Toolbar", "§aGreen = Toolbar is active", "§cRed = Toolbar is disabled"));
                }
            } else if (event.getRawSlot() == 4) {
                if (playerCombatConfigGui.keepInventoryToggle) {
                    playerCombatConfigGui.keepInventoryToggle = false;
                    playerCombatConfigGui.inv.setItem(4,playerCombatConfigGui.createGuiItem(Material.RED_STAINED_GLASS_PANE, "Toggle Keep Inventory", "§aGreen = Keep Inventory is active", "§cRed = Keep Inventory is disabled"));
                } else {
                    playerCombatConfigGui.keepInventoryToggle = true;
                    playerCombatConfigGui.inv.setItem(4,playerCombatConfigGui.createGuiItem(Material.GREEN_STAINED_GLASS_PANE, "Toggle Keep Inventory", "§aGreen = Keep Inventory is active", "§cRed = Keep Inventory is disabled"));
                }
            } else if (event.getRawSlot() == 6) {
                if (playerCombatConfigGui.villagerToggle) {
                    playerCombatConfigGui.villagerToggle = false;
                    playerCombatConfigGui.inv.setItem(6,playerCombatConfigGui.createGuiItem(Material.RED_STAINED_GLASS_PANE, "Toggle villager CombatLog", "§aGreen = Villager CombatLog is active", "§cRed = Villager CombatLog is disabled"));
                } else {
                    playerCombatConfigGui.villagerToggle = true;
                    playerCombatConfigGui.inv.setItem(6,playerCombatConfigGui.createGuiItem(Material.GREEN_STAINED_GLASS_PANE, "Toggle villager CombatLog", "§aGreen = Villager CombatLog is active", "§cRed = Villager CombatLog is disabled"));
                }
            } else if (event.getRawSlot() == 8) {
                player.closeInventory();
                playerCombatConfigGui.openInventory(player);
            }
        }
    }

}
