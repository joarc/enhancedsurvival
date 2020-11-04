package se.joarc.EnhancedSurvival.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import se.joarc.EnhancedSurvival.EnhancedSurvival;
import se.joarc.EnhancedSurvival.Items.MinecartBoosterListener;

public class DevCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player)sender;
            if (cmd.getName().equalsIgnoreCase("heal")) {
                if (p.getUniqueId().equals(EnhancedSurvival.JOARC_UUID)) {
                    p.setHealth(20);
                    p.setFoodLevel(20);
                    p.setSaturation(20f);
                } else {
                    p.sendMessage("Only Joarc may use this command!");
                }
            } else if (cmd.getName().equalsIgnoreCase("minecartspeed")) {
                if (MinecartBoosterListener.speedEnabled) {
                    MinecartBoosterListener.speedEnabled = false;
                    p.sendMessage(ChatColor.RED + "Minecart Speed Disabled");
                } else {
                    MinecartBoosterListener.speedEnabled = true;
                    p.sendMessage(ChatColor.GREEN + "Minecart Speed Enabled");
                }
            }
        }
        return true;
    }
}
