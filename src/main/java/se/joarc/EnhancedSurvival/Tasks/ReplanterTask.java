package se.joarc.EnhancedSurvival.Tasks;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ReplanterTask extends BukkitRunnable {

    private final JavaPlugin plugin;
    private final Material old_type;
    private final Location loc;
    private final BlockData blockdata;

    public ReplanterTask(JavaPlugin plugin, Location loc, Material old_type) {
        this.plugin = plugin;
        this.old_type = old_type;
        this.loc = loc;
        this.blockdata = null;
    }

    public ReplanterTask(JavaPlugin plugin, Location loc, Material old_type, BlockData bd) {
        this.plugin = plugin;
        this.old_type = old_type;
        this.loc = loc;
        this.blockdata = bd;
    }

    @Override
    public void run() {
        if (this.blockdata == null) {
            this.loc.getBlock().setType(this.old_type);
        } else {
            Block b = this.loc.getBlock();
            b.setType(this.old_type);
            b.setBlockData(this.blockdata);
        }
    }
}
