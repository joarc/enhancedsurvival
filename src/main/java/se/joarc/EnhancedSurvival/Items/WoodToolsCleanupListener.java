package se.joarc.EnhancedSurvival.Items;

import static se.joarc.EnhancedSurvival.EnhancedSurvival.wtce;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class WoodToolsCleanupListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if (!(wtce.contains(e.getPlayer().getUniqueId()))) {
            wtce.add(e.getPlayer().getUniqueId());
            e.getPlayer().sendMessage(ChatColor.GREEN +"Wood-Tool-Cleanup Enabled! (Toggle with /woodtoolcleanup)");
        }
    }

    @EventHandler
    public void onPlayerBreakWithWoodToolEvent(PlayerItemDamageEvent e) {
        ItemStack i = e.getItem();
        if (i.getType().equals(Material.WOODEN_AXE) ||
                i.getType().equals(Material.WOODEN_HOE) ||
                i.getType().equals(Material.WOODEN_PICKAXE) ||
                i.getType().equals(Material.WOODEN_SHOVEL)) {
            if (wtce.contains(e.getPlayer().getUniqueId())) {
                e.setDamage(20);
            }
        }
    }
}
