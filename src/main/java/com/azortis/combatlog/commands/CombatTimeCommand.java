package com.azortis.combatlog.commands;

import com.azortis.combatlog.Setter;
import com.azortis.combatlog.gui.CombatTimeGui;
import com.azortis.combatlog.listener.CombatListener;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CombatTimeCommand implements CommandExecutor {

    CombatListener combatListener;
    Setter setter;

    public CombatTimeCommand(Setter setter) {
        this.setter = setter;
        setter.setCombatTimeCommand(this);
        combatListener = setter.getCombatListener();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) { return false;}
        if (combatListener.combatTimer.get(((Player) sender).getUniqueId()) != null){
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "Combat Time: " + ChatColor.WHITE + combatListener.combatTimer.get(((Player) sender).getUniqueId()) + " seconds");
        } else {
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "Not in combat");
        }
        return true;
    }
}
