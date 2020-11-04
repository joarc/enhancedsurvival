package se.joarc.EnhancedSurvival.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.persistence.PersistentDataType;

import static se.joarc.EnhancedSurvival.EnhancedSurvival.nsk_extra;

public class BlazePokeStickListener implements Listener {

    @EventHandler
    public void onCraftingSpeedBootsEvent(CraftItemEvent e) {
        CraftingInventory inv = e.getInventory();
        if (inv.getResult() == null) return;
        if (inv.getResult().getType().equals(Material.BLAZE_ROD) && inv.getResult().getItemMeta().hasEnchant(Enchantment.KNOCKBACK)) {
            boolean fireaspect = false;
            boolean pokestick = false;
            for(ItemStack item : inv.getMatrix()) {
                if (item != null) {
                    if (item.getType().equals(Material.ENCHANTED_BOOK)) {
                        EnchantmentStorageMeta spm = (EnchantmentStorageMeta)item.getItemMeta();
                        if (spm == null) continue;
                        if (spm.hasStoredEnchant(Enchantment.FIRE_ASPECT)) {
                            fireaspect = true;
                        }
                    } else if (item.getType().equals(Material.STICK)) {
                        if (item.getItemMeta().getPersistentDataContainer().has(nsk_extra, PersistentDataType.STRING) && item.getItemMeta().getPersistentDataContainer().get(nsk_extra, PersistentDataType.STRING).equals("pokestick")) {
                            pokestick = true;
                        }
                    }
                }
            }
            if (fireaspect && pokestick) {
                e.setCancelled(false);
            } else {
                e.getInventory().getViewers().get(0).sendMessage(ChatColor.RED + "Invalid Crafting Components!");
                inv.setResult(new ItemStack(Material.AIR));
                e.setCancelled(true);
            }
        }
    }
}
