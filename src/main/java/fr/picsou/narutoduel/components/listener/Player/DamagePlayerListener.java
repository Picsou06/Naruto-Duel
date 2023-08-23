package fr.picsou.narutoduel.components.listener.Player;

import fr.picsou.narutoduel.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.io.File;

public class DamagePlayerListener implements Listener {

    private World world;

    @EventHandler
    public void OnDamage(EntityDamageEvent event) {
        Entity player = event.getEntity();
        String worldName = player.getWorld().getName();
        if(worldName.length() >= 4 && worldName.substring(0, 4).equalsIgnoreCase("Duel")){

        } else{
            event.setCancelled(true);
        }

    }
}
