package se.joarc.EnhancedSurvival.Blocks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class FireplaceRegenListener implements Listener {

    private final JavaPlugin plugin;

    public FireplaceRegenListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onFireplacePlaceEvent(BlockPlaceEvent e) {
        if (e.getBlockPlaced().getType().equals(Material.CAMPFIRE)) {
            Location loc = e.getBlockPlaced().getLocation();

            List<Location> campfires = null;
            campfires = (List<Location>) plugin.getConfig().getList("campfires");
            if (campfires == null) {
                campfires = new ArrayList<>();
            }

            campfires.add(loc);

            plugin.getConfig().set("campfires", campfires);
            plugin.saveConfig();
        }

        // TODO: Add support for SOUL_CAMPFIRE
    }

    @EventHandler
    public void onFireplaceBreakEvent(BlockBreakEvent e) {
        if (e.getBlock().getType().equals(Material.CAMPFIRE)) {
            Location loc = e.getBlock().getLocation();


            List<Location> campfires = null;
            campfires = (List<Location>) plugin.getConfig().getList("campfires");
            if (campfires == null) {
                campfires = new ArrayList<>();
            }

            campfires.remove(loc);

            plugin.getConfig().set("campfires", campfires);
            plugin.saveConfig();
        }

        // TODO: Add support for SOUL_CAMPFIRE
    }
}
