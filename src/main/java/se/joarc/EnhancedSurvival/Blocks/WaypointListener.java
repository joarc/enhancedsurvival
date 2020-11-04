package se.joarc.EnhancedSurvival.Blocks;

import org.bukkit.ChatColor;
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
                if (title.equalsIgnoreCase("")) {
                    return;
                }
                String subtitle = "";
                int stay = 3;
                sign.getLine(2);
                if (sign.getLine(2).equalsIgnoreCase("")) {
                    subtitle = sign.getLine(2);
                }
                sign.getLine(3);
                if (sign.getLine(3).equalsIgnoreCase("")) {
                    stay = Integer.parseInt(sign.getLine(3));

                }
                e.getPlayer().sendTitle(ChatColor.translateAlternateColorCodes('&', title), ChatColor.translateAlternateColorCodes('&', subtitle), 20, stay * 20, 20);
            }
        }
    }
}
