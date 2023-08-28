package fr.picsou.narutoduel.components.commands;

import fr.picsou.narutoduel.Main;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.awt.*;

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
                    //opponent.sendMessage(centerText(buildDuelRequestText(p)));
                    net.md_5.bungee.api.chat.TextComponent message = new TextComponent(centerText(buildDuelRequestText(p)));
                    message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§6§l/duel accept").create()));
                    message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/duel accept"));
                    opponent.spigot().sendMessage(message);
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
                            p.sendMessage(ChatColor.RED + "Vous avez refusé la demande de " + ChatColor.GRAY.BOLD + opponent.getName());
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
    }


    private static String buildDuelRequestText(Player p) {
        ChatColor gold = ChatColor.GOLD;
        ChatColor boldGray = ChatColor.BOLD.GRAY;
        ChatColor resetAqua = ChatColor.RESET.AQUA;

        String separator = StringUtils.repeat("-", 42); // 42 hyphens
        String playerName = p.getName();

        return String.format("%s%s\n   %s%s%s vous a envoyé une demande de duel.\n" +
                        "%sVous avez 60 secondes pour taper /duel accept.\n%s",
                gold, separator, boldGray, playerName, resetAqua, gold, separator);
    }

    private static String centerText(String text) {
        int maxWidth = 80;
        int spaces = (int) Math.round((maxWidth - text.length()) / 2);
        return StringUtils.repeat(" ", spaces) + text;
    }

}