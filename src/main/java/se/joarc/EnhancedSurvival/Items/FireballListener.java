package se.joarc.EnhancedSurvival.Items;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import static org.bukkit.Bukkit.getLogger;

public class FireballListener implements Listener {

    @EventHandler
    public void onRightClickFireball(PlayerInteractEvent e) {
        if (e.getMaterial().equals(Material.FIRE_CHARGE)) {
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
                Location loc = e.getPlayer().getEyeLocation();
                Vector dir = e.getPlayer().getEyeLocation().getDirection();
                Entity fireball = loc.getWorld().spawnEntity(loc, EntityType.FIREBALL);
                fireball.setVelocity(dir);
                e.getItem().setAmount(e.getItem().getAmount()-1);
            }
        }
    }
}
