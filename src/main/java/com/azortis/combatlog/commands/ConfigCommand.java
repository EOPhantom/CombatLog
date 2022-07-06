package com.azortis.combatlog.commands;

import com.azortis.combatlog.Setter;
import com.azortis.combatlog.gui.ConfigGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

public class ConfigCommand implements CommandExecutor {

    Setter setter;
    ConfigGui configGui;

    public ConfigCommand(Setter setter){
        this.setter = setter;
        setter.setConfigCommand(this);
        configGui = setter.getConfigGui();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {return false;}
        sender.sendMessage("Opening Config");
        configGui.openInventory((HumanEntity) sender);
        return true;
    }
}
