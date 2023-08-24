package fr.picsou.narutoduel.components.commands;

import fr.picsou.narutoduel.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandList implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.isOp()) {
                if (strings.length == 1) {
                    if (strings[0].equals("PlayerInDuel")) {
                        player.sendMessage(Main.getInstance().getPlayerInDuel().toString());
                    } else if (strings[0].equals("DuelRequest")) {
                        player.sendMessage(Main.getInstance().getDuelRequest().toString());
                    } else if (strings[0].equals("ArenaManager")) {
                    player.sendMessage(Main.getInstance().getArenaManager().toString());
                }else {
                        player.sendMessage("Liste inconnu");
                    }

                }
                }else {
                    player.sendMessage("Vous n'avez pas la permission d'exécuter cette commande !");
                    return false;
            }
        }
        return false;
    }
}
