package com.azortis.combatlog.listener;

import com.azortis.combatlog.CountDown;
import com.azortis.combatlog.Setter;
import com.azortis.combatlog.commands.BlockedCommandsCommand;
import com.azortis.combatlog.gui.CombatTimeGui;
import com.azortis.combatlog.gui.ConfigGui;
import com.azortis.combatlog.gui.PlayerCombatConfigGui;
import org.bukkit.ChatColor;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import com.destroystokyo.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CombatListener implements Listener {

    private BlockedCommandsCommand blockedCommandsCommand;
    private ConfigGui configGui;
    private CombatTimeGui combatTimeGui;
    Setter setter;
    PlayerCombatConfigGui playerCombatConfigGui;

    public CombatListener(Setter setter) {
        this.setter = setter;
        setter.setCombatListener(this);
        blockedCommandsCommand = setter.getBlockedCommandsCommand();
        configGui = setter.getConfigGui();
        combatTimeGui = setter.getCombatTimeGui();
        playerCombatConfigGui = setter.getPlayerCombatConfigGui();
    }

    public Map<UUID, Integer> combatTimer = new HashMap<>();

    @EventHandler
    public void setCombatTimer (EntityDamageByEntityEvent event) {
        if (!(configGui.toggle)) {return;}
        if(!(event.getEntity() instanceof Player) || (!(event.getDamager() instanceof Player) && !(event.getDamager() instanceof Arrow))) {return;}
        if(event.getDamager() instanceof Arrow){
            Arrow arrow = (Arrow) event.getDamager();
            if (!(arrow.getShooter() instanceof Player)) {return;}
        }
        Player player = (Player) event.getEntity();
        combatTimer.put(player.getUniqueId(),combatTimeGui.combatTime);
        new CountDown(player, setter);
    }

    @EventHandler
    public void logOut (PlayerQuitEvent event) {
        System.out.println("player logged out and died");
        if (combatTimer.get(event.getPlayer().getUniqueId()) != null) {
            if (playerCombatConfigGui.villagerToggle) {
                Entity playerVillager =  event.getPlayer().getWorld().spawnEntity(event.getPlayer().getLocation(), EntityType.VILLAGER);
            } else {
                event.getPlayer().setHealth(0);
                combatTimer.remove(event.getPlayer().getUniqueId());
            }

        }
    }

    @EventHandler
    public void onDeath (PlayerDeathEvent event) {
        combatTimer.remove(event.getEntity().getUniqueId());
        if (playerCombatConfigGui.keepInventoryToggle){
                if (!(event.getEntity().getServer().getOnlinePlayers().contains(event.getEntity()))) {
                    if (combatTimer.get(event.getEntity().getUniqueId()) != null) {
                        event.setKeepInventory(true);
                        event.setKeepLevel(true);
                        event.getDrops().clear();
                }
            }
        }
    }

    @EventHandler
    public void preCommand (PlayerCommandPreprocessEvent event) {
        if(combatTimer.get(event.getPlayer().getUniqueId()) != null) {
            System.out.println(event.getMessage());
            String command = event.getMessage().replace("/", "");
            System.out.println(command);
            if (blockedCommandsCommand.blockedCommands.contains(command)) {
                event.getPlayer().sendMessage(ChatColor.RED + "Unable to perform this command while in combat");
                event.setCancelled(true);
            }
        }
    }
}
