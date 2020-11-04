package se.joarc.EnhancedSurvival;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import javax.security.auth.login.LoginException;

public class DiscordBot extends ListenerAdapter implements Listener {

    public EnhancedSurvival plugin;
    public JDA jda;
    private static int playerCount = 0;

    public DiscordBot(EnhancedSurvival plugin) {
        this.plugin = plugin;
        startBot();
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    private void startBot() {
        plugin.getLogger().info("Starting Discord Bot...");
        JDABuilder jdab = JDABuilder.createDefault(plugin.getConfig().getString("discordbottoken"), GatewayIntent.GUILD_MESSAGES);
        jdab.setActivity(Activity.playing(""+playerCount+" / 50 | mc.joarc.se"));
        jdab.addEventListeners(this);
        try {
            jda = jdab.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }


    /*
    * MC -> Discord
    *
    * */
    @EventHandler
    public void chatEvent(AsyncPlayerChatEvent e) {
        TextChannel tx = jda.getTextChannelById("692156066347352106");
        tx.sendMessage("**"+e.getPlayer().getDisplayName()+":** "+e.getMessage()).queue();
    }

    @EventHandler
    public void joinEvent(PlayerJoinEvent e) {
        playerCount++;

        TextChannel tx = jda.getTextChannelById("692156066347352106");
        tx.sendMessage("**"+e.getPlayer().getDisplayName()+" joined mc.joarc.se! ("+playerCount+" / 50)**").queue();
    }

    @EventHandler
    public void leaveEvent(PlayerQuitEvent e) {
        playerCount--;

        TextChannel tx = jda.getTextChannelById("692156066347352106");
        tx.sendMessage("**"+e.getPlayer().getDisplayName()+" left mc.joarc.se! ("+playerCount+" / 50)**").queue();
    }

    /*
    * Discord -> MC
    *
    * */
    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getAuthor().isBot() || e.isWebhookMessage()) return;

        String message = e.getMessage().getContentRaw();
        User user = e.getAuthor();

        Bukkit.getServer().broadcastMessage("[" + ChatColor.DARK_PURPLE + "D" + ChatColor.RESET + "] <" + user.getName() + "> " + message);
    }

}
