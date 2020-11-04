package se.joarc.EnhancedSurvival.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static se.joarc.EnhancedSurvival.EnhancedSurvival.ticksSinceLastSlept;

public class SleepCheckCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("sleepcheck")) {
                p.sendMessage(p.getDisplayName() + ChatColor.BLUE + " last slept " + ticksSinceLastSlept.get(p.getUniqueId()) + " ticks ago.");
            }
        }
        return true;
    }
}