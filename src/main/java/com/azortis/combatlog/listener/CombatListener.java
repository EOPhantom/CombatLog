package com.azortis.combatlog.listener;

import com.azortis.combatlog.Combatlog;
import com.azortis.combatlog.Setter;
import com.azortis.combatlog.commands.BlockedCommandsCommand;
import com.azortis.combatlog.gui.CombatTimeGui;
import com.azortis.combatlog.gui.ConfigGui;
import com.azortis.combatlog.gui.PlayerCombatConfigGui;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;
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
        //System.out.println("player has been hit");
        combatTimer.put(player.getUniqueId(),combatTimeGui.combatTime);
        //System.out.println(combatTimer.get(player.getUniqueId()));
        new CountDown(player, setter);
    }

    @EventHandler
    public void logOut (PlayerQuitEvent event) {
        System.out.println("player logged out and died");
        if (combatTimer.get(event.getPlayer().getUniqueId()) != null) {
            if (playerCombatConfigGui.villagerToggle) {
                Villager playerVillager = (Villager) event.getPlayer().getWorld().spawnEntity(event.getPlayer().getLocation(), EntityType.VILLAGER);
                playerVillager.setCustomName(event.getPlayer().getDisplayName());
                //playerVillager.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 600, 1000));
                playerVillager.setHealth(event.getPlayer().getHealth());
                playerVillager.setAI(false);
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
            if (combatTimer.get(event.getEntity().getUniqueId()) != null) {
                event.setKeepInventory(true);
                event.setKeepLevel(true);
                event.getDrops().clear();
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

    private class CountDown extends BukkitRunnable {

        private Player player;
        private Combatlog combatlog;
        private PlayerCombatConfigGui playerCombatConfigGui;
        Setter setter;

        public CountDown(Player player, Setter setter){
            this.player = player;
            this.setter = setter;
            combatlog = setter.getCombatlog();
            playerCombatConfigGui = setter.getPlayerCombatConfigGui();
            //System.out.println(player.getUniqueId());
            this.runTaskTimer(combatlog, 0, 20);
        }

        @Override
        public void run() {
            if (combatTimer.get(player.getUniqueId()) > 1) {
                combatTimer.put(player.getUniqueId(), combatTimer.get(player.getUniqueId()) - 1);
                if (playerCombatConfigGui.toolBarToggle) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.LIGHT_PURPLE + "Combat timer: " + ChatColor.WHITE + combatTimer.get(player.getUniqueId())));
                }
            } else {
                combatTimer.remove(player.getUniqueId());
                if (playerCombatConfigGui.toolBarToggle) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.LIGHT_PURPLE + "Combat timer: " + ChatColor.WHITE + "0"));
                }
                for (LivingEntity entity: player.getWorld().getLivingEntities()) {
                    if (entity instanceof Villager && entity.getCustomName().equals(player.getDisplayName())) {

                    }
                }
                this.cancel();
            }
            //System.out.println(combatTimer.get(player.getUniqueId()));
        }
    }
}
