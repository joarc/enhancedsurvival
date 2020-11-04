package se.joarc.EnhancedSurvival;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import se.joarc.EnhancedSurvival.Blocks.FireplaceRegenListener;
import se.joarc.EnhancedSurvival.Blocks.FixAnvilNamesListener;
import se.joarc.EnhancedSurvival.Blocks.WalkwayBlocksListener;
import se.joarc.EnhancedSurvival.Blocks.WaypointListener;
import se.joarc.EnhancedSurvival.Commands.DevCommands;
import se.joarc.EnhancedSurvival.Commands.SleepCheckCommand;
import se.joarc.EnhancedSurvival.Commands.WoodToolsCleanupCommand;
import se.joarc.EnhancedSurvival.Items.*;
import se.joarc.EnhancedSurvival.Recipies.*;
import se.joarc.EnhancedSurvival.Tasks.FireplaceRegenTask;
import se.joarc.EnhancedSurvival.Tasks.SleepTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class EnhancedSurvival extends JavaPlugin implements Listener {

    public static ArrayList<UUID> wtce = new ArrayList<UUID>();
    public static NamespacedKey nsk_type;
    public static NamespacedKey nsk_extra;
    public static UUID JOARC_UUID = UUID.fromString("f8c2437b-b0ae-414a-837d-85258d839311");

    public static HashMap<UUID, Integer> ticksSinceLastSlept = new HashMap<UUID, Integer>();

    @Override
    public void onEnable() {

        EnhancedSurvival.nsk_type = new NamespacedKey(this, "type");
        EnhancedSurvival.nsk_extra = new NamespacedKey(this, "extra");

        getServer().getPluginManager().registerEvents(new FireballListener(), this);
        getServer().getPluginManager().registerEvents(new WoodToolsCleanupListener(), this);
        getServer().getPluginManager().registerEvents(new WalkwayBlocksListener(), this);
        getServer().getPluginManager().registerEvents(new SpeedBootsListener(), this);
        getServer().getPluginManager().registerEvents(new HoeReplanterListener(this), this);
        getServer().getPluginManager().registerEvents(new SleepListener(), this);
        getServer().getPluginManager().registerEvents(new FixAnvilNamesListener(), this);
        getServer().getPluginManager().registerEvents(new ElytraEasyFlyingListener(), this);
        getServer().getPluginManager().registerEvents(new MinecartBoosterListener(), this);
        getServer().getPluginManager().registerEvents(new WaypointListener(), this);
        getServer().getPluginManager().registerEvents(new PokeStickListener(), this);
        getServer().getPluginManager().registerEvents(new BlazePokeStickListener(), this);
        getServer().getPluginManager().registerEvents(new FireplaceRegenListener(this), this);
        getServer().getPluginManager().registerEvents(this, this);

        this.getCommand("woodtoolcleanup").setExecutor(new WoodToolsCleanupCommand());
        this.getCommand("sleepcheck").setExecutor(new SleepCheckCommand());
        this.getCommand("heal").setExecutor(new DevCommands());
        this.getCommand("minecartspeed").setExecutor(new DevCommands());

        Bukkit.getServer().addRecipe(SpeedBootsRecipe.recipe(this));
        Bukkit.getServer().addRecipe(HoeReplanterRecipe.recipe(this, Material.STONE_HOE));
        Bukkit.getServer().addRecipe(HoeReplanterRecipe.recipe(this, Material.IRON_HOE));
        Bukkit.getServer().addRecipe(HoeReplanterRecipe.recipe(this, Material.GOLDEN_HOE));
        Bukkit.getServer().addRecipe(HoeReplanterRecipe.recipe(this, Material.DIAMOND_HOE));
        Bukkit.getServer().addRecipe(PokeStickRecipe.recipe(this));
        Bukkit.getServer().addRecipe(BlazePokeStickRecipe.recipe(this));
        Bukkit.getServer().addRecipe(CleanMapRecipe.recipe(this));

        BukkitTask sleepTask = new SleepTask(this).runTaskTimer(this, 1, 1);
        BukkitTask fireplaceRegenTask = new FireplaceRegenTask(this).runTaskTimer(this, 20*10, 20*10);

        this.saveDefaultConfig();

        new DiscordBot(this);

        getLogger().info("EnhancedSurvival is enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("EnhancedSurvival is disabled!");
    }
}
