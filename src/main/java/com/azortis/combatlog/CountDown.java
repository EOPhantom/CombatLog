package com.azortis.combatlog;

import com.azortis.combatlog.gui.PlayerCombatConfigGui;
import com.azortis.combatlog.listener.CombatListener;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.scheduler.BukkitRunnable;

public final class CountDown extends BukkitRunnable {

    private Player player;
    private Combatlog combatlog;
    private PlayerCombatConfigGui playerCombatConfigGui;
    private CombatListener combatListener;
    Setter setter;

    public CountDown(Player player, Setter setter){
        this.player = player;
        this.setter = setter;
        this.combatlog = setter.getCombatlog();
        this.playerCombatConfigGui = setter.getPlayerCombatConfigGui();
        this.combatListener = setter.getCombatListener();
        this.runTaskTimer(combatlog, 0, 20);
    }

    @Override
    public void run() {
        if (combatListener.combatTimer.get(player.getUniqueId()) > 1) {
            combatListener.combatTimer.put(player.getUniqueId(), combatListener.combatTimer.get(player.getUniqueId()) - 1);
            if (playerCombatConfigGui.toolBarToggle) {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.LIGHT_PURPLE + "Combat timer: " + ChatColor.WHITE + combatListener.combatTimer.get(player.getUniqueId())));
            }
        } else {
            combatListener.combatTimer.remove(player.getUniqueId());
            if (playerCombatConfigGui.toolBarToggle) {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.LIGHT_PURPLE + "Combat timer: " + ChatColor.WHITE + "0"));
            }
            for (LivingEntity entity: player.getWorld().getLivingEntities()) {
                if (entity instanceof Villager && entity.getCustomName().equals(player.getDisplayName())) {
                    //File datFile = new File(player.getWorld().getWorldFolder().getAbsolutePath() + "/playerdata/" + player.getUniqueId().toString().toLowerCase() + ".dat");
                    //NBTTagCompound compound = null;
                    //try {
                    //    compound = NBTCompressedStreamTools.a(new FileInputStream(datFile));
                    //} catch (IOException e) {
                    //    throw new RuntimeException(e);
                    //}
                    //String item = compound.l("Inventory");
                    //System.out.println(item);
                }
            }
            this.cancel();
        }
    }
}
