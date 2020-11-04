package se.joarc.EnhancedSurvival.Tasks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import static se.joarc.EnhancedSurvival.EnhancedSurvival.ticksSinceLastSlept;

public class SleepTask extends BukkitRunnable {

    private final JavaPlugin plugin;

    public SleepTask(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            if (!ticksSinceLastSlept.containsKey(p.getUniqueId())) {
                ticksSinceLastSlept.put(p.getUniqueId(), 0);
                continue;
            }
            final int lastSlept = ticksSinceLastSlept.get(p.getUniqueId());

            if (lastSlept == 120000) {
                p.sendMessage(ChatColor.RED + "You haven't slept for five days...");
            } else if (lastSlept == 168000) {
                p.sendMessage(ChatColor.RED + "You are getting sleepy...");
            } else if (lastSlept == 192000) {
                p.sendMessage(ChatColor.RED + "You are getting reeeeal sleepy now...");
                PotionEffect nausea = new PotionEffect(PotionEffectType.CONFUSION, 24000, 1);
                nausea.apply(p);
            } else if (lastSlept == 216000) {
                p.sendMessage(ChatColor.DARK_RED + "You might die of sleep deprivation if you don't sleep tonight...");
            } else if (lastSlept > 240000) {
                p.sendMessage(ChatColor.DARK_RED + "You are now dying....");
                PotionEffect sleepdeprivation = new PotionEffect(PotionEffectType.WITHER, 20*10, 10);
                sleepdeprivation.apply(p);
            }
            ticksSinceLastSlept.put(p.getUniqueId(), lastSlept+1);
        }
    }
}
