package it.maymity.nobadwords.commands;

import it.maymity.nobadwords.Main;
import it.maymity.nobadwords.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import it.maymity.nobadwords.managers.MessagesManager;

public class Nbw implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            MessagesManager.getInstance().sendMessage(sender, Utils.getInstance().getMessages().getString("messages.must_player"));
            return false;
        }

        Player player = (Player) sender;

        if (!sender.hasPermission("nbw.commands")) {
            MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getMessages().getString("messages.no_permission"));
            return false;
        }

        if (!(args.length > 0)) {
            Utils.getInstance().gethelp(player);
            return false;
        }

        if (args.length == 1) {
            switch (args[0]) {
                case "reload":
                    MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getMessages().getString("messages.reload"));
                    Bukkit.getPluginManager().getPlugin("NoBadWords").reloadConfig();
                    Main.getInstance().getMessages().reload();
                    Main.getInstance().getWord().clear();
                    Main.getInstance().loadWords();
                    break;
            }
        }
        return true;
    }
}