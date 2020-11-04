package se.joarc.EnhancedSurvival.Blocks;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class WalkwayBlocksListener implements Listener {

    @EventHandler
    public void onPlayerWalkOnWalkwayBlockEvent(PlayerMoveEvent e) {
        if (e.getFrom().getBlock().getLocation() == e.getTo().getBlock().getLocation()) return;
        Location loc = e.getTo().getBlock().getLocation();
        loc.add(0, -2, 0);
        if (loc.getBlock().getType().equals(Material.LIGHT_BLUE_STAINED_GLASS)) {
            PotionEffect speed = new PotionEffect(PotionEffectType.SPEED, 10, 0, false, false);
            speed.apply(e.getPlayer());
        }
    }
}
