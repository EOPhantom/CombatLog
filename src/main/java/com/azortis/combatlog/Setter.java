package com.azortis.combatlog;

import com.azortis.combatlog.commands.BlockedCommandsCommand;
import com.azortis.combatlog.commands.CombatTimeCommand;
import com.azortis.combatlog.commands.ConfigCommand;
import com.azortis.combatlog.gui.CombatTimeGui;
import com.azortis.combatlog.gui.ConfigGui;
import com.azortis.combatlog.gui.PlayerCombatConfigGui;
import com.azortis.combatlog.listener.CombatListener;
import com.azortis.combatlog.listener.GuiListener;

public class Setter {

    private Combatlog combatlog;
    private CombatListener combatListener;
    private GuiListener guiListener;
    private BlockedCommandsCommand blockedCommandsCommand;
    private CombatTimeCommand combatTimeCommand;
    private ConfigCommand configCommand;
    private ConfigGui configGui;
    private CombatTimeGui combatTimeGui;
    private PlayerCombatConfigGui playerCombatConfigGui;

    public Combatlog getCombatlog() {return combatlog;}
    public CombatListener getCombatListener() {return combatListener;}
    public GuiListener getGuiListener() {return guiListener;}
    public BlockedCommandsCommand getBlockedCommandsCommand() {return blockedCommandsCommand;}
    public CombatTimeCommand getCombatTimeCommand() {return combatTimeCommand;}
    public ConfigCommand getConfigCommand() {return configCommand;}
    public ConfigGui getConfigGui() {return configGui;}
    public CombatTimeGui getCombatTimeGui() {return combatTimeGui;}
    public PlayerCombatConfigGui getPlayerCombatConfigGui() {return playerCombatConfigGui;}

    public void setCombatlog(Combatlog combatlog) {this.combatlog = combatlog;}
    public void setCombatListener(CombatListener combatListener) {this.combatListener = combatListener;}
    public void setGuiListener(GuiListener guiListener) {this.guiListener = guiListener;}
    public void setBlockedCommandsCommand(BlockedCommandsCommand blockedCommandsCommand) {this.blockedCommandsCommand = blockedCommandsCommand;}
    public void setCombatTimeCommand(CombatTimeCommand combatTimeCommand) {this.combatTimeCommand = combatTimeCommand;}
    public void setConfigCommand(ConfigCommand configCommand) {this.configCommand = configCommand;}
    public void setConfigGui(ConfigGui configGui) {this.configGui = configGui;}
    public void setCombatTimeGui(CombatTimeGui combatTimeGui) {this.combatTimeGui = combatTimeGui;}
    public void setPlayerCombatConfigGui(PlayerCombatConfigGui playerCombatConfigGui) {this.playerCombatConfigGui = playerCombatConfigGui;}

}
