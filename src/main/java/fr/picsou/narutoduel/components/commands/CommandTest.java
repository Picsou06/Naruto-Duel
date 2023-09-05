package fr.picsou.narutoduel.components.commands;

import fr.picsou.narutoduel.Main;
import fr.picsou.narutoduel.components.Gui.GuiBuilder;
import fr.picsou.narutoduel.components.Gui.GuiManager;
import fr.picsou.narutoduel.components.Items.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Skull;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class CommandTest implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.isOp()) {

            } else {
                player.sendMessage("Vous n'avez pas la permission d'exécuter cette commande !");
            }
        }
        return false;
    }

    }