package fr.picsou.narutoduel.components.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHeal implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.isOp()) {
                player.setHealth(20);

            } else {
                player.sendMessage("Vous n'avez pas la permission d'exécuter cette commande !");
                return false;
            }
        }
        return false;
    }
}