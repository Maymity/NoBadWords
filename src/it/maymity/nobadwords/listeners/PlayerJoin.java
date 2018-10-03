package it.maymity.nobadwords.listeners;

import it.maymity.nobadwords.Utils;
import it.maymity.nobadwords.managers.MessagesManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (Utils.getInstance().getConfig().getBoolean("settings.check_update_on_join")) {
            if (player.hasPermission("nbw.checkupdate")) {
                if (Utils.getInstance().getNewUpdateCheck()) {
                    MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getMessages().getString("messages.update_message"));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cDownload:&e " + Utils.getInstance().getUpdateLink()));
                }
            }
        }
    }
}