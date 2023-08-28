package fr.picsou.narutoduel.components.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGm implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.isOp()) {
                if (strings[0].equals("1")) {
                    player.setGameMode(GameMode.CREATIVE);
                }
                if (strings[0].equals("0")) {
                    player.setGameMode(GameMode.SURVIVAL);
                }
                if (strings[0].equals("3")) {
                    player.setGameMode(GameMode.SPECTATOR);
                }
            } else {
                player.sendMessage("Vous n'avez pas la permission d'exécuter cette commande !");
                return false;
            }
        }
        return false;
    }
}
