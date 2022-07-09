package com.azortis.combatlog.listener;

import com.azortis.combatlog.Setter;
import com.sk89q.worldedit.math.BlockVector2;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.List;

public class CombatAreaListener implements Listener {

    Setter setter;
    CombatListener combatListener;

    public CombatAreaListener(Setter setter) {
        this.setter = setter;
        this.combatListener = setter.getCombatListener();
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (combatListener.combatTimer.containsKey(event.getPlayer().getUniqueId())) {
            RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
            RegionManager regionManager = container.get((World) event.getPlayer().getWorld());
            if (regionManager != null) {
                ProtectedRegion region = regionManager.getRegion("noCombat");
                Location playerLocation = event.getPlayer().getLocation();
                ProtectedRegion playerRegion = new ProtectedCuboidRegion("player", BlockVector3.at(playerLocation.getX() + 5, playerLocation.getY() + 5, playerLocation.getZ() + 5), BlockVector3.at(playerLocation.getX() - 5, playerLocation.getY() - 5, playerLocation.getZ() - 5));
                List<ProtectedRegion> candidates = new ArrayList<>();
                candidates.add(playerRegion);
                if (region != null) {
                    List<ProtectedRegion> overlapRegion = region.getIntersectingRegions(candidates);
                    ProtectedRegion overlappingRegion = overlapRegion.get(0);
                    List<BlockVector2> overlappingPoints = overlappingRegion.getPoints();
                    BlockData fakeBlock = Material.RED_STAINED_GLASS.createBlockData();
                    for (BlockVector2 overlappingPoint : overlappingPoints) {
                        for (int y = -5; y < 6; y++) {
                            Location point = new Location(event.getPlayer().getWorld(), overlappingPoint.getX(), event.getPlayer().getLocation().getY() + y, overlappingPoint.getZ());
                            event.getPlayer().sendBlockChange(point, fakeBlock);
                        }
                    }
                }
            }
        }
    }
}
