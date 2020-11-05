package se.joarc.EnhancedSurvival.Blocks;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class FixAnvilNamesListener implements Listener {

    @EventHandler
    public void onAnvilNaming(PrepareAnvilEvent e) {
        ItemStack is = e.getResult();
        if (is == null || !is.hasItemMeta()) return;

        ItemMeta im = is.getItemMeta();
        if (im == null || im.getDisplayName().equalsIgnoreCase("")) return;

        String name = ChatColor.stripColor(im.getDisplayName());
        name = ChatColor.AQUA + ChatColor.translateAlternateColorCodes('&', name);
        im.setDisplayName(name);

        is.setItemMeta(im);
        e.setResult(is);
    }
}
