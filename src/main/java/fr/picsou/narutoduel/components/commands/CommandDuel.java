package fr.picsou.narutoduel.components.commands;

import fr.picsou.narutoduel.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandDuel implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(strings[0]))) {
                if (Main.getInstance().getRequestTestAhix().containsValue(p)) {
                    p.sendMessage(ChatColor.RED + "Le joueur a déjà une demande en cours!");
                    return false;
                } else {
                    Player opponent = Bukkit.getPlayer(strings[0]);
                    p.sendMessage(ChatColor.AQUA + "Vous avez envoyé une demande de duel à " + ChatColor.GRAY + opponent.getName());
                    Main.getInstance().getRequestTestAhix().put(opponent, p);
                    opponent.sendMessage(ChatColor.GRAY + p.getName() + ChatColor.AQUA + " vous a envoyé une demande de duel");
                    return false;
                }
            }
                if (strings[0].equals("accept")) {
                    if (Main.getInstance().getRequestTestAhix().containsKey(p)) {
                        Player opponent = Main.getInstance().getRequestTestAhix().get(p);
                        if (Bukkit.getOnlinePlayers().contains(opponent)) {
                            //Commande de duel (Tp, Creation du Monde, Kits, Panel...)
                            Bukkit.broadcastMessage(ChatColor.AQUA + "Téléportation en cours!");
                            Main.getInstance().getRequestTestAhix().remove(p);
                        } else {
                            p.sendMessage(ChatColor.RED + "Ce joueur n'est plus en ligne");
                        }
                    } else {
                        p.sendMessage(ChatColor.RED + "Vous n'avez pas de demande de duel");
                    }

                } else {
                    if (strings[0].equals("deny")) {
                        if (Main.getInstance().getRequestTestAhix().containsKey(p)) {
                            Player opponent = Main.getInstance().getRequestTestAhix().get(p);
                            if (Bukkit.getOnlinePlayers().contains(opponent)) {
                                opponent.sendMessage(ChatColor.GRAY + p.getName() + ChatColor.RED + " a refusé votre demande");
                                Main.getInstance().getRequestTestAhix().remove(p);
                            } else {
                                p.sendMessage(ChatColor.RED + "Ce joueur n'est plus en ligne");
                            }
                        } else {
                            p.sendMessage(ChatColor.RED + "Vous n'avez pas de demande de duel");
                        }
                    } else {
                        p.sendMessage(ChatColor.RED + strings[0] + " n'est pas connecté");
                    }
                }
            }
        return false;
    }
    }
