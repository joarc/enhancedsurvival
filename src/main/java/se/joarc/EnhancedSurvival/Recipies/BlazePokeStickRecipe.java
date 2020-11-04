package se.joarc.EnhancedSurvival.Recipies;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class BlazePokeStickRecipe {

    public static ShapelessRecipe recipe(JavaPlugin plugin) {
        ItemStack boots = item();

        ShapelessRecipe sr = new ShapelessRecipe(new NamespacedKey(plugin, "blaze_poke_stick"), boots);
        sr.addIngredient(Material.BLAZE_ROD);
        sr.addIngredient(Material.ENCHANTED_BOOK);
        sr.addIngredient(Material.ENCHANTING_TABLE);
        sr.addIngredient(Material.STICK);

        return sr;
    }

    public static ItemStack item() {
        ItemStack poke_stick = new ItemStack(Material.BLAZE_ROD);
        ItemMeta im = poke_stick.getItemMeta();

        im.setDisplayName(ChatColor.RESET + "" + ChatColor.GOLD + "Blazing Poke-Stick");
        im.addEnchant(Enchantment.KNOCKBACK, 10, true);
        im.addEnchant(Enchantment.FIRE_ASPECT, 1, true);

        poke_stick.setItemMeta(im);
        return poke_stick;
    }
}
