package se.joarc.EnhancedSurvival.Recipies;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import static se.joarc.EnhancedSurvival.EnhancedSurvival.nsk_extra;

public class PokeStickRecipe {

    public static ShapelessRecipe recipe(JavaPlugin plugin) {
        ItemStack boots = item();

        ShapelessRecipe sr = new ShapelessRecipe(new NamespacedKey(plugin, "poke_stick"), boots);
        sr.addIngredient(Material.STICK);
        sr.addIngredient(Material.ENCHANTED_BOOK);
        sr.addIngredient(Material.ENCHANTING_TABLE);

        return sr;
    }

    public static ItemStack item() {
        ItemStack poke_stick = new ItemStack(Material.STICK);
        ItemMeta im = poke_stick.getItemMeta();

        im.setDisplayName(ChatColor.RESET + "" + ChatColor.GREEN + "Wooden Poke-Stick");
        im.addEnchant(Enchantment.KNOCKBACK, 5, true);

        im.getPersistentDataContainer().set(nsk_extra, PersistentDataType.STRING, "pokestick");

        poke_stick.setItemMeta(im);
        return poke_stick;
    }
}
