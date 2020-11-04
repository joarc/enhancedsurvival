package se.joarc.EnhancedSurvival;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.world.TimeSkipEvent;

import static se.joarc.EnhancedSurvival.EnhancedSurvival.ticksSinceLastSlept;

public class SleepListener implements Listener {

    private static boolean timeSkip = false;

    @EventHandler
    public void onPlayerJoinSleep(PlayerJoinEvent e) {
        if(!ticksSinceLastSlept.containsKey(e.getPlayer().getUniqueId())) {
            ticksSinceLastSlept.put(e.getPlayer().getUniqueId(), 0);
        }
    }

    @EventHandler
    public void onPlayerEnterBed(PlayerBedEnterEvent e) {
        timeSkip = false;
        if (e.getBedEnterResult().equals(PlayerBedEnterEvent.BedEnterResult.OK)) {
            ticksSinceLastSlept.put(e.getPlayer().getUniqueId(), 0);
            Bukkit.getServer().broadcastMessage(e.getPlayer().getDisplayName() + ChatColor.GREEN + " has gone to sleep.");
            int onlineplayers = 0;
            int sleepingplayers = 0;
            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                onlineplayers++;
                if (p.isSleeping()) {
                    sleepingplayers++;
                }
            }
            if ((onlineplayers / 3) < sleepingplayers) {
                for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                    if (!p.isSleeping()) {
                        p.setSleepingIgnored(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerLeaveBed(PlayerBedLeaveEvent e) {
        if (!timeSkip) {
            Bukkit.getServer().broadcastMessage(e.getPlayer().getDisplayName() + ChatColor.GOLD + " has woken up.");
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onSleepTimeSkip(TimeSkipEvent e) {
        if (e.getSkipReason().equals(TimeSkipEvent.SkipReason.NIGHT_SKIP)) {
            timeSkip = true;
            Bukkit.getServer().broadcastMessage(ChatColor.BLUE + "Wakey Wakey! A new day dawns!");
            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                p.setSleepingIgnored(false);
            }
        }
    }
}
