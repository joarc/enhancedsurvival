package se.joarc.EnhancedSurvival.Recipies;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

import static se.joarc.EnhancedSurvival.EnhancedSurvival.nsk_extra;

public class HoeReplanterRecipe {

    public static ArrayList<Material> hoe_types = new ArrayList<Material>();

    public static ShapelessRecipe recipe(JavaPlugin plugin, Material hoe_material) {
        hoe_types.add(hoe_material);
        ItemStack hoe = item(hoe_material);

        ShapelessRecipe sr = new ShapelessRecipe(new NamespacedKey(plugin, "hoe_replanter_"+hoe_material.toString()), hoe);
        sr.addIngredient(hoe_material);
        sr.addIngredient(Material.ENDER_EYE);

        return sr;
    }

    public static ItemStack item(Material hoe_material) {
        ItemStack hoe = new ItemStack(hoe_material);
        ItemMeta im = hoe.getItemMeta();

        ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "Auto-Replant I");
        im.setLore(lore);

        im.getPersistentDataContainer().set(nsk_extra, PersistentDataType.STRING, "autoreplant");
        hoe.setItemMeta(im);

        return hoe;
    }
}
