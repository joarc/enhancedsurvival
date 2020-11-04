package se.joarc.EnhancedSurvival.Items;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Minecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleCreateEvent;

import static se.joarc.EnhancedSurvival.EnhancedSurvival.JOARC_UUID;

public class MinecartBoosterListener implements Listener {

    public static boolean speedEnabled = true;

    @EventHandler
    public void onMinecartSpawnEvent(VehicleCreateEvent e) {
        if (e.getVehicle().getType().equals(EntityType.MINECART)) {
            if (speedEnabled) {
                Minecart mc = (Minecart)e.getVehicle();
                mc.setMaxSpeed(100);
            }
        }
    }
}
