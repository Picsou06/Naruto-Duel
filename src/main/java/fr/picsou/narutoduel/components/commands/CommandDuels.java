package fr.picsou.narutoduel.components.commands;

import fr.picsou.narutoduel.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import javax.swing.text.AttributeSet;

public class CommandDuels implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            FileConfiguration config = Main.getInstance().getConfig();
            if (player.isOp()) {
                if (strings.length == 2) {
                    if (strings[0].equals("create")) {
                        if (config.contains("ZONE."+strings[1])){
                            player.sendMessage(ChatColor.RED + "L'arène existe déjà!");
                            return false;
                        }
                        config.set("ZONE."+strings[1]+".open", false);
                            player.sendMessage(ChatColor.GREEN + "L'arène à bien été crée !");
                            Main.getInstance().saveConfig();
                            return false;
                    }
                    if (strings[0].equals("delete")) {
                        if (config.contains("ZONE." + strings[1])) {
                            config.set("ZONE." + strings[1], null);
                            Main.getInstance().saveConfig();
                            player.sendMessage("Arène "+strings[1]+" supprimé!");
                            return false;
                        } else {
                            player.sendMessage(ChatColor.RED + "Cette arène n'existe pas !");
                            return false;
                        }
                    }
                    if (strings[0].equals("setpos1")) {
                        if (config.contains("ZONE." + strings[1])){
                            config.set("ZONE."+strings[1]+".x1", player.getLocation().getX());
                            config.set("ZONE."+strings[1]+".y1", player.getLocation().getY());
                            config.set("ZONE."+strings[1]+".z1", player.getLocation().getZ());
                            config.set("ZONE."+strings[1]+".yaw1", player.getLocation().getYaw());
                            config.set("ZONE."+strings[1]+".pitch1", player.getLocation().getPitch());
                            Main.getInstance().saveConfig();
                            if (config.contains("ZONE."+strings[1]+".x2")){
                                config.set("ZONE."+strings[1]+".open", true);
                            }
                            player.sendMessage(ChatColor.GREEN + "Position 1 définit pour l'arène "+strings[1]);
                            return false;
                        } else{
                            player.sendMessage(ChatColor.RED + "Cette arène n'existe pas !");
                            return false;
                        }
                    }
                    if (strings[0].equals("setpos2")) {
                        if (config.contains("ZONE." + strings[1])){
                            config.set("ZONE."+strings[1]+".x2", player.getLocation().getX());
                            config.set("ZONE."+strings[1]+".y2", player.getLocation().getY());
                            config.set("ZONE."+strings[1]+".z2", player.getLocation().getZ());
                            config.set("ZONE."+strings[1]+".yaw2", player.getLocation().getYaw());
                            config.set("ZONE."+strings[1]+".pitch2", player.getLocation().getPitch());
                            Main.getInstance().saveConfig();
                            player.sendMessage(ChatColor.GREEN + "Position 2 définit pour l'arène "+strings[1]);
                            if (config.contains("ZONE."+strings[1]+".x1")){
                                config.set("ZONE."+strings[1]+".open", true);
                            }
                            return false;
                        } else{
                            player.sendMessage(ChatColor.RED + "Cette arène n'existe pas !");
                            return false;
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "Commande inconnue!");
                        return false;
                    }
                } else{
                    player.sendMessage("Merci de préciser le nom de l'arene.");
                    return false;
                }
                }else {
                    player.sendMessage(ChatColor.RED + "Vous n'avez pas la permission d'exécuter cette commande !");
                    return false;
            }
        }
        return false;
    }
}