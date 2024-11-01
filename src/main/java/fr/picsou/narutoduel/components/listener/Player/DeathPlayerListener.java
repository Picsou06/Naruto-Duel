package fr.picsou.narutoduel.components.listener.Player;

import fr.picsou.narutoduel.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_19_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;

import java.io.File;

public class DeathPlayerListener implements Listener {

    @EventHandler
    public void OnPlayerDeath(PlayerDeathEvent event) {
        event.setDeathMessage(null);
        Player player = event.getEntity();
        event.getDrops().clear();

        if(player.getKiller().isOnline()) {
            Player killer = player.getKiller();
            killer.getInventory().clear();
            Main.getInstance().getPlayerInDuel().remove(player);
            killer.teleport(new Location(Bukkit.getWorld("world"), -6.50, 88, -1.50));
            killer.setMaxHealth(20);
            killer.setHealth(20);
            killer.setSaturation(20);
            killer.setFoodLevel(20);
            for (PotionEffect effect : killer.getActivePotionEffects())
                killer.removePotionEffect(effect.getType());
        }
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
                @Override
                public void run() {
                    event.getEntity().spigot().respawn();
                }
            }, 2L);
        }
    }