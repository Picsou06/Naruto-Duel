package fr.picsou.narutoduel.components.commands;

import fr.picsou.narutoduel.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CommandDuel implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(strings[0]))) {
                if (Main.getInstance().getDuelRequest().containsKey(Bukkit.getPlayer(strings[0]))) {
                    p.sendMessage(ChatColor.RED + "Le joueur a déjà une demande en cours!");
                    return false;
                } else {
                    Player opponent = Bukkit.getPlayer(strings[0]);
                    p.sendMessage(ChatColor.AQUA + "Vous avez envoyé une demande de duel à " + ChatColor.GRAY + opponent.getName());
                    Main.getInstance().getDuelRequest().put(opponent, p);
                    opponent.sendMessage(ChatColor.GRAY + p.getName() + ChatColor.AQUA + " vous a envoyé une demande de duel");
                    (new BukkitRunnable() {
                        public void run() {
                            Main.getInstance().getDuelRequest().remove(opponent);
                        }
                    }).runTaskLaterAsynchronously(Main.getInstance(), 1200L);
                    return false;
                }
            }
            if (strings[0].equals("accept")) {
                if (Main.getInstance().getDuelRequest().containsKey(p)) {
                    Player opponent = Main.getInstance().getDuelRequest().get(p);
                    if (Bukkit.getOnlinePlayers().contains(opponent)) {
                        //Commande de duel (Tp, Creation du Monde, Kits, Panel...)
                        Bukkit.broadcastMessage(ChatColor.AQUA + "Téléportation en cours!");
                        Main.getInstance().getDuelRequest().remove(p);
                        return false;
                    } else {
                        p.sendMessage(ChatColor.RED + "Ce joueur n'est plus en ligne");
                        return false;
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "Vous n'avez pas de demande de duel");
                    return false;
                }

            }
                if (strings[0].equals("deny")) {
                    if (Main.getInstance().getDuelRequest().containsKey(p)) {
                        Player opponent = Main.getInstance().getDuelRequest().get(p);
                        if (Bukkit.getOnlinePlayers().contains(opponent)) {
                            opponent.sendMessage(ChatColor.GRAY + p.getName() + ChatColor.RED + " a refusé votre demande");
                            Main.getInstance().getDuelRequest().remove(p);
                            return false;
                        } else {
                            p.sendMessage(ChatColor.RED + "Ce joueur n'est plus en ligne");
                            return false;
                        }
                    } else {
                        p.sendMessage(ChatColor.RED + "Vous n'avez pas de demande de duel");
                        return false;
                    }
            }
        }return false;
    }}