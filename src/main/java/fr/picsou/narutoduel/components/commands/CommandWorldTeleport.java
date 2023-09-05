package fr.picsou.narutoduel.components.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandWorldTeleport implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.isOp()) {
                if(Bukkit.getWorld(strings[0])!=null) {
                    player.teleport(new Location(Bukkit.getWorld(strings[0]), Bukkit.getWorld(strings[0]).getSpawnLocation().getX(), Bukkit.getWorld(strings[0]).getSpawnLocation().getY(), Bukkit.getWorld(strings[0]).getSpawnLocation().getZ()));
                } else {
                    player.sendMessage("Ce monde n'existe pas !");
                }
            } else {
                player.sendMessage("Vous n'avez pas la permission d'exécuter cette commande !");
            }
        }
        return false;
    }
    }