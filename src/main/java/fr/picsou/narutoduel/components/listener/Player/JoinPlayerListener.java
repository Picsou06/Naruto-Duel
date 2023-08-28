package fr.picsou.narutoduel.components.listener.Player;

import fr.picsou.narutoduel.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class JoinPlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Main pluginInstance = Main.getInstance();
        if (pluginInstance != null) {
            Player player = event.getPlayer();            player.teleport(new Location(Bukkit.getWorld("world"), -6.50, 88, -1.50));
        } else {
        }
    }
}
