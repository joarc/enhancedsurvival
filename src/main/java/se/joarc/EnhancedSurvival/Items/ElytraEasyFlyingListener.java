package se.joarc.EnhancedSurvival.Items;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class ElytraEasyFlyingListener implements Listener {

    @EventHandler
    public void onPlayerLanding(EntityDamageByBlockEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player)e.getEntity();
            if (p.isGliding()) {
                if (e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
