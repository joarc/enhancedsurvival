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

public class SpeedBootsRecipe {

    public static ShapelessRecipe recipe(JavaPlugin plugin) {
        ItemStack boots = item();

        ShapelessRecipe sr = new ShapelessRecipe(new NamespacedKey(plugin, "speed_boots"), boots);
        sr.addIngredient(Material.GOLDEN_BOOTS);
        sr.addIngredient(Material.POTION);

        return sr;
    }

    public static ItemStack item() {
        ItemStack gold_boots = new ItemStack(Material.GOLDEN_BOOTS);
        ItemMeta im = gold_boots.getItemMeta();

        ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "Permanent Speed I");
        im.setLore(lore);

        im.getPersistentDataContainer().set(nsk_extra, PersistentDataType.STRING, "speedboost");
        gold_boots.setItemMeta(im);

        return gold_boots;
    }
}
