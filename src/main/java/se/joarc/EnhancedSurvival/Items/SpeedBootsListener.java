package se.joarc.EnhancedSurvival.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import se.joarc.EnhancedSurvival.Recipies.SpeedBootsRecipe;

import static org.bukkit.Bukkit.getLogger;
import static se.joarc.EnhancedSurvival.EnhancedSurvival.nsk_extra;

public class SpeedBootsListener implements Listener {

    @EventHandler
    public void onWalkSpeedBoots(PlayerMoveEvent e) {
        if (e.getFrom().getBlock().getLocation() == e.getTo().getBlock().getLocation()) return;
        if (e.getPlayer().getInventory().getBoots() != null && e.getPlayer().getInventory().getBoots().getType().equals(Material.GOLDEN_BOOTS)) {
            ItemStack boots = e.getPlayer().getInventory().getBoots();
            PersistentDataContainer pdc = boots.getItemMeta().getPersistentDataContainer();
            if (pdc.has(nsk_extra, PersistentDataType.STRING)) {
                String itemextra = pdc.get(nsk_extra, PersistentDataType.STRING);
                if (itemextra.equals("speedboost")) {
                    PotionEffect speed = new PotionEffect(PotionEffectType.SPEED, 20*2, 0, false, false);
                    speed.apply(e.getPlayer());
                }
            }
        }
    }

    @EventHandler
    public void onCraftingSpeedBootsEvent(CraftItemEvent e) {
        CraftingInventory inv = e.getInventory();
        if (inv.getResult() == null) return;
        ItemStack speed_boots = SpeedBootsRecipe.item();
        if (inv.getResult().equals(speed_boots)) {
            ItemStack input_boots = null;
            ItemStack input_potion = null;
            boolean is_potion = false;
            boolean is_boots = false;
            int crafting_items = 0;
            for(ItemStack item : inv.getMatrix()) {
                if (item == null) continue;
                crafting_items++;
                if (item.getType().equals(Material.POTION)) {
                    is_potion = true;
                    if (!item.hasItemMeta()) continue;
                    PotionMeta spm = (PotionMeta)item.getItemMeta();
                    if (spm == null) continue;
                    getLogger().info("type:"+spm.getBasePotionData().getType().toString());
                    if (spm.getBasePotionData().getType().equals(PotionType.SPEED)) {
                        getLogger().info("Is speed");
                        input_potion = item;
                    }
                } else if (item.getType().equals(Material.GOLDEN_BOOTS)) {
                    is_boots = true;
                    if (!item.hasItemMeta() || item.getItemMeta() == null || !(item.getItemMeta().getPersistentDataContainer().has(nsk_extra, PersistentDataType.STRING)) || !(item.getItemMeta().getPersistentDataContainer().get(nsk_extra, PersistentDataType.STRING).equals("speedboost"))) {
                        getLogger().info("Is gold boots");
                        input_boots = item;
                    }
                }
            }
            if (crafting_items != 2) { getLogger().info("too many items"); return; }
            if (is_boots && is_potion) {
                if (input_boots != null && input_potion != null) {
                    speed_boots.addEnchantments(input_boots.getEnchantments());
                    if (input_boots.hasItemMeta()) {
                        speed_boots.getItemMeta().setDisplayName(input_boots.getItemMeta().getDisplayName());
                    }
                    inv.setResult(speed_boots);
                } else {
                    inv.setResult(new ItemStack(Material.AIR));
                    e.setCancelled(true);
                }
            }
        }
    }
}
