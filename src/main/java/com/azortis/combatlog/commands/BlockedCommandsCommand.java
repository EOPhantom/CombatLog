package com.azortis.combatlog.commands;

import com.azortis.combatlog.Setter;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BlockedCommandsCommand implements CommandExecutor {

    public List blockedCommands = new ArrayList();
    public List modifiedCommands = new ArrayList();

    Setter setter;

    public BlockedCommandsCommand(Setter setter) {
        this.setter = setter;
        setter.setBlockedCommandsCommand(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {return false;}
        if(args == null) {return false;}
        modifiedCommands.clear();
        for(int i = 1; i < args.length; i++){
            modifiedCommands.add(args[i]);
        }
        if(args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("include")) {
            for ( int i = 0; i < modifiedCommands.size(); i++) {
                if (!(blockedCommands.contains(modifiedCommands.get(i)))) {
                    blockedCommands.add(modifiedCommands.get(i));
                }
            }
            sender.sendMessage(ChatColor.GREEN + "Commands added: " + ChatColor.WHITE + modifiedCommands.toString());
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "Current blocked commands: " + ChatColor.WHITE + blockedCommands.toString());
        } else if (args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("exclude")) {
            args[0] = null;
            for ( int i = 0; i < modifiedCommands.size(); i++) {
                if (blockedCommands.contains(modifiedCommands.get(i))) {
                    blockedCommands.remove(modifiedCommands.get(i));
                }
            }
            sender.sendMessage(ChatColor.GREEN + "Commands Removed: " + ChatColor.WHITE + modifiedCommands.toString());
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "Current blocked commands: " + ChatColor.WHITE + blockedCommands.toString());
        } else if (args[0].equalsIgnoreCase("list")){
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "Current blocked commands: " + ChatColor.WHITE + blockedCommands.toString());
        } else {
            return false;
        }
        System.out.println(blockedCommands);
        return true;
    }
}