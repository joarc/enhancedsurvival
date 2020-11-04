package se.joarc.EnhancedSurvival.Blocks;

import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class WaypointListener implements Listener {

    @EventHandler
    public void onWaypointPassthru(PlayerMoveEvent e) {
        if (e.getFrom().getBlock().getLocation() == e.getTo().getBlock().getLocation()) return;
        Location loc = e.getTo().getBlock().getLocation();
        loc.add(0, -2, 0);
        if (loc.getBlock().getState() instanceof Sign) {
            Sign sign = (Sign)loc.getBlock().getState();

            String activator = sign.getLine(0);
            if (activator.equalsIgnoreCase("[Waypoint]")) {
                String title = sign.getLine(1);
                String subtitle = sign.getLine(2);
                int stay = Integer.parseInt(sign.getLine(3));
                e.getPlayer().sendTitle(title, subtitle, 20, stay * 20, 20);
            }
        }
    }
}
