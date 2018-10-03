package it.maymity.nobadwords;

import it.maymity.nobadwords.commands.Nbw;
import it.maymity.nobadwords.listeners.PlayerJoin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import it.maymity.nobadwords.listeners.AsyncPlayerChat;
import it.maymity.nobadwords.managers.Configuration;

import java.util.HashMap;

public class Main extends JavaPlugin implements Listener {

    private static Main instance;
    private HashMap<String, String> word = new HashMap();
    private Configuration messages;
    private static double config_version = 1.0;

    public static Main getInstance(){
        return instance;
    }
    public HashMap<String, String> getWord() {
        return word;
    }
    public Configuration getMessages(){ return messages;}

    public void onEnable() {
        instance = this;
        System.out.println("NoBadWords > Start plugin...");

        System.out.println("NoBadWords > Registring the event...");
        registerListeners();
        System.out.println("NoBadWords > Event registred!");

        System.out.println("NoBadWords > Registring the command...");
        registerExecutors();
        System.out.println("NoBadWords > Command registred!");
        loadWords();

        SpigotUpdater updater = new SpigotUpdater(this, 54919);
        try {
            if (updater.checkForUpdates()) {
                Utils.getInstance().setBoolUpdate(true);
                Utils.getInstance().setUpdateLink(updater.getResourceURL());
                System.out.println("========================================================");
                System.out.println("NoBadWords Update Checker");
                System.out.println("There is a new update available");
                System.out.println("Latest Version: " + updater.getLatestVersion());
                System.out.println("Your Version: " + updater.getPlugin().getDescription().getVersion());
                System.out.println("Get it here: " + updater.getResourceURL());
                System.out.println("========================================================");
            }
            else{
                System.out.println("========================================================");
                System.out.println("NoBadWords Update Checker");
                System.out.println("You are using the latest version!");
                System.out.println("========================================================");
            }
        } catch (Exception e) {
            System.out.println("========================================================");
            System.out.println("NoBadWords Update Checker");
            System.out.println("Could not connect to Spigot's API!");
            System.out.println("Error: ");
            e.printStackTrace();
            System.out.println("========================================================");
        }

        System.out.println("NoBadWords > Plugin enabled!");
        System.out.println("NoBadWords > Plugin created by Maymity!");


        if ((!Utils.getInstance().getConfig().contains("settings.config_version")) || (Utils.getInstance().getConfig().getDouble("settings.config_version") < config_version)) {
            System.out.println("&cYou config is out of date!");
            System.out.println("&cPlease, regenerate you config file!");
            System.out.println("&eYour version: &a" + Utils.getInstance().getConfig().getDouble("settings.config_version"));
            System.out.println("&cNewest version: &a" + config_version);
        }

        messages = new Configuration("messages.yml", this, true);
        saveDefaultConfig();
    }

    public void onDisable() {
        System.out.println("NoBadWords > Plugin Disabled");
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new AsyncPlayerChat(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
    }

    private void registerExecutors() {
        Bukkit.getPluginCommand("nbw").setExecutor(new Nbw());
    }

    public void loadWords(){
        int count = 0;
        for (String s : getConfig().getStringList("filters")) {
            String[] split = s.split(":");
            if (split.length == 2) {
                getWord().put(split[0], split[1]);
                count++;
            }
        }
        System.out.println("NoBadWords > " + count + " blacklist words loaded!");
    }

}