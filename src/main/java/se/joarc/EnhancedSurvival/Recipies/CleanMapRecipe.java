package se.joarc.EnhancedSurvival.Recipies;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class CleanMapRecipe {

    public static ShapelessRecipe recipe(JavaPlugin plugin) {
        ItemStack map = new ItemStack(Material.MAP);

        ShapelessRecipe sr = new ShapelessRecipe(new NamespacedKey(plugin, "cleaned_map"), map);
        sr.addIngredient(Material.FILLED_MAP);
        sr.addIngredient(Material.WHITE_DYE);
        sr.addIngredient(Material.PAPER);

        return sr;
    }
}
