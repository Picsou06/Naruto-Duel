package fr.picsou.narutoduel.components.listener.interact;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ClickListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        String worldName = player.getWorld().getName();
        if (worldName.length() >= 4 && worldName.substring(0, 4).equalsIgnoreCase("Duel")) {
            if (!player.hasPermission("NarutoDuel.build") && !player.isOp()) {
                event.setCancelled(true);
            }
        }
    }
}
