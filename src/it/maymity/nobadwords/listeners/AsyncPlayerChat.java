package it.maymity.nobadwords.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import it.maymity.nobadwords.Utils;
import java.util.regex.Matcher;
import it.maymity.nobadwords.Main;

public class AsyncPlayerChat implements Listener {

    @EventHandler
    public void OnAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        if (!event.getPlayer().hasPermission("nbw.bypass")) {
            for (String parola : Utils.getInstance().getConfig().getStringList("badwords")) {
                if (event.getMessage().contains(parola)) {
                    String frase = event.getMessage().replaceAll(parola, ChatColor.translateAlternateColorCodes('&', Utils.getInstance().getMessages().getString("messages.replacewith")));
                    event.setMessage(frase);
                    if(Utils.getInstance().getConfig().getBoolean("settings.punish")){
                        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(),Utils.getInstance().getConfig().getString("commands.punish_command").replaceAll("%player%", event.getPlayer().getName()));
                    }
                }
            }
            String m = event.getMessage().toLowerCase();
            for (String s : Main.getInstance().getWord().keySet()) {
                if (m.contains(s)) {
                    event.setMessage(event.getMessage().replaceAll(Matcher.quoteReplacement("(?i)" + s), Matcher.quoteReplacement(Main.getInstance().getWord().get(s))));
                }
            }
        }
    }
}