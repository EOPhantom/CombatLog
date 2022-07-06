package com.azortis.combatlog;

import com.azortis.combatlog.commands.BlockedCommandsCommand;
import com.azortis.combatlog.commands.ConfigCommand;
import com.azortis.combatlog.gui.CombatTimeGui;
import com.azortis.combatlog.gui.ConfigGui;
import com.azortis.combatlog.gui.PlayerCombatConfigGui;
import com.azortis.combatlog.listener.CombatListener;
import com.azortis.combatlog.commands.CombatTimeCommand;
import com.azortis.combatlog.listener.GuiListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Combatlog extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("CombatLog Online");
        Setter setter = new Setter();
        setter.setCombatlog(this);
        new ConfigGui(setter);
        new CombatTimeGui(setter);
        new PlayerCombatConfigGui(setter);
        getServer().getPluginManager().registerEvents(new CombatListener(setter), this);
        getServer().getPluginManager().registerEvents(new GuiListener(setter), this);
        getCommand("CombatTime").setExecutor(new CombatTimeCommand(setter));
        getCommand("BlockedCommands").setExecutor(new BlockedCommandsCommand(setter));
        getCommand("CombatLogConfig").setExecutor(new ConfigCommand(setter));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
