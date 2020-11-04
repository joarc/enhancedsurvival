package se.joarc.EnhancedSurvival.Tasks;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;
import java.util.List;

public class FireplaceRegenTask extends BukkitRunnable {

    private final JavaPlugin plugin;

    public FireplaceRegenTask(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        if (plugin.getConfig().getList("campfires") == null || plugin.getConfig().getList("campfires").isEmpty()) return;
        List<Location> campfires = (List<Location>) plugin.getConfig().getList("campfires");
        for (Location campfire : campfires) {
            Collection<Entity> nearby = campfire.getWorld().getNearbyEntities(campfire, 5, 5, 5);
            for (Entity ent : nearby) {
                if (ent instanceof LivingEntity) {
                    PotionEffect regen = new PotionEffect(PotionEffectType.REGENERATION, 20 * 15, 0, false, false);
                    regen.apply((LivingEntity) ent);
                }
            }
        }
    }
}
