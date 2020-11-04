package se.joarc.EnhancedSurvival.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static se.joarc.EnhancedSurvival.EnhancedSurvival.wtce;

public class WoodToolsCleanupCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player)sender;
            if (cmd.getName().equalsIgnoreCase("woodtoolcleanup")) {
                if (wtce.contains(p.getUniqueId())) {
                    wtce.remove(p.getUniqueId());
                    p.sendMessage(ChatColor.RED + "Wood-Tool-Cleanup Disabled!");
                } else {
                    wtce.add(p.getUniqueId());
                    p.sendMessage(ChatColor.GREEN + "Wood-Tool-Cleanup Enabled!");
                }
            }
        }
        return true;
    }
}
