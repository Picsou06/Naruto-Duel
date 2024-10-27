package fr.picsou.narutoduel.components.role.Naruto;

import fr.picsou.narutoduel.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NarutoListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        Main pluginInstance = Main.getInstance();
            Player player = event.getPlayer();
            if(player.getItemInHand().getItemMeta().getDisplayName().contains("Mode Bijû")){
                player.getInventory().remove(player.getInventory().getItemInMainHand());
                player.sendMessage(ChatColor.RED + "Mode Bijû activé!");
            }
    }
}
