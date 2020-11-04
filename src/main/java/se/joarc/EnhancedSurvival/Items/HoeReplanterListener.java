package se.joarc.EnhancedSurvival.Items;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Cocoa;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import se.joarc.EnhancedSurvival.EnhancedSurvival;
import se.joarc.EnhancedSurvival.Tasks.ReplanterTask;

import java.util.HashMap;

import static se.joarc.EnhancedSurvival.EnhancedSurvival.nsk_extra;
import static se.joarc.EnhancedSurvival.Recipies.HoeReplanterRecipe.hoe_types;

public class HoeReplanterListener implements Listener {

    public EnhancedSurvival plugin;

    public HoeReplanterListener(EnhancedSurvival plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onIronHoeBlockBreakEvent(final BlockBreakEvent e) {
        // e.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.AIR) != null &&
        if (hoe_types.contains(e.getPlayer().getInventory().getItemInMainHand().getType())) {

            ItemStack hoe = e.getPlayer().getInventory().getItemInMainHand();

            if (hoe.getItemMeta().getPersistentDataContainer().has(nsk_extra, PersistentDataType.STRING) && hoe.getItemMeta().getPersistentDataContainer().get(nsk_extra, PersistentDataType.STRING).equals("autoreplant")) {
                final Material old_type = e.getBlock().getType();
                boolean runReplanter = false;
                boolean replanterBlockData = false;
                BlockData bd = null;
                if (e.getBlock().getType().equals(Material.WHEAT)) {
                    if (e.getPlayer().getInventory().contains(Material.WHEAT_SEEDS, 1) || e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
                        HashMap<Integer, ItemStack> ri = e.getPlayer().getInventory().removeItem(new ItemStack(Material.WHEAT_SEEDS, 1));
                        if (ri.isEmpty() || e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
                            runReplanter = true;
                        }
                    }
                } else if (e.getBlock().getType().equals(Material.BEETROOTS)) {
                    if (e.getPlayer().getInventory().contains(Material.BEETROOT_SEEDS, 1) || e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
                        HashMap<Integer, ItemStack> ri = e.getPlayer().getInventory().removeItem(new ItemStack(Material.BEETROOT_SEEDS, 1));
                        if (ri.isEmpty() || e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
                            runReplanter = true;
                        }
                    }
                } else if (e.getBlock().getType().equals(Material.CARROTS)) {
                    if (e.getPlayer().getInventory().contains(Material.CARROT, 1) || e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
                        HashMap<Integer, ItemStack> ri = e.getPlayer().getInventory().removeItem(new ItemStack(Material.CARROT, 1));
                        if (ri.isEmpty() || e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
                            runReplanter = true;
                        }
                    }
                } else if (e.getBlock().getType().equals(Material.POTATOES)) {
                    if (e.getPlayer().getInventory().contains(Material.POTATO, 1) || e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
                        HashMap<Integer, ItemStack> ri = e.getPlayer().getInventory().removeItem(new ItemStack(Material.POTATO, 1));
                        if (ri.isEmpty() || e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
                            runReplanter = true;
                        }
                    }
                } else if (e.getBlock().getType().equals(Material.COCOA)) {
                    if (e.getPlayer().getInventory().contains(Material.COCOA_BEANS, 1) || e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
                        HashMap<Integer, ItemStack> ri = e.getPlayer().getInventory().removeItem(new ItemStack(Material.COCOA_BEANS, 1));
                        if (ri.isEmpty() || e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
                            runReplanter = true;
                            replanterBlockData = true;
                            bd = e.getBlock().getBlockData();
                            Cocoa c = (Cocoa)bd;
                            c.setAge(0);
                            bd = c;
                        }
                    }
                } else if (e.getBlock().getType().equals(Material.NETHER_WART)) {
                    if (e.getPlayer().getInventory().contains(Material.NETHER_WART, 1) || e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
                        HashMap<Integer, ItemStack> ri = e.getPlayer().getInventory().removeItem(new ItemStack(Material.NETHER_WART, 1));
                        if (ri.isEmpty() || e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
                            runReplanter = true;
                        }
                    }
                }

                if (runReplanter) {
                    Damageable im = (Damageable) e.getPlayer().getInventory().getItemInMainHand().getItemMeta();
                    im.setDamage(im.getDamage()-1);
                    e.getPlayer().getInventory().getItemInMainHand().setItemMeta((ItemMeta) im);

                    if (replanterBlockData) {
                        new ReplanterTask(this.plugin, e.getBlock().getLocation(), e.getBlock().getType(), bd).runTaskLater(this.plugin, 20);
                    } else {
                        new ReplanterTask(this.plugin, e.getBlock().getLocation(), e.getBlock().getType()).runTaskLater(this.plugin, 20);
                    }
                }
            }
        }
    }
}
