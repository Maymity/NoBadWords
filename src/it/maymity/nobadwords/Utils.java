package it.maymity.nobadwords;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.FileConfiguration;

public class Utils {

    private static Utils instance;
    private FileConfiguration config;
    private boolean newupdate = false;
    private String updatelink;

    public static synchronized Utils getInstance() {
        if (instance == null) {
            instance = new Utils();
        }
        return instance;
    }

    public FileConfiguration getConfig() {
        config = Bukkit.getServer().getPluginManager().getPlugin("NoBadWords").getConfig();
        return config;
    }

    public FileConfiguration getMessages() { return Main.getInstance().getMessages().getConfig(); }

    public void setBoolUpdate(boolean b){
        newupdate = b;
    }

    public void setUpdateLink(String b){ updatelink = b; }

    public Boolean getNewUpdateCheck() { return newupdate;}

    public String getUpdateLink() { return updatelink; }

    public void gethelp(Player player) {
        for (String c : getMessages().getStringList("messages.help-commands"))
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', c));
    }
}
