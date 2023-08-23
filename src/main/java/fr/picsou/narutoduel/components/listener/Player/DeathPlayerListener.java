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

    private World world;

    @EventHandler
    public void OnPlayerDeath(PlayerDeathEvent event) {
        event.setDeathMessage(null);
        Player player = event.getEntity();
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                event.getEntity().spigot().respawn();
            }
        }, 2L);
        World worldtodelete = player.getWorld();
        player.teleport(new Location(Bukkit.getWorld("world"), -6.50, 88, -1.50));
        if(player.getKiller().isOnline()) {
            Player killer = player.getKiller();
            Bukkit.broadcastMessage("Fin du Duel");
            Main.getInstance().getPlayerInDuel().remove(player);
            player.teleport(new Location(Bukkit.getWorld("world"), -6.50, 88, -1.50));
            killer.teleport(new Location(Bukkit.getWorld("world"), -6.50, 88, -1.50));
            killer.setHealth(20);
            killer.setSaturation(20);
            killer.setFoodLevel(20);
                for (PotionEffect effect : killer.getActivePotionEffects())
                    killer.removePotionEffect(effect.getType());
            if(worldtodelete.toString().length() >= 4 && worldtodelete.toString().substring(0, 4).equals("Duel")) {
                    unloadWorld(worldtodelete);
                    File deleteFolder = worldtodelete.getWorldFolder();
                    deleteWorld(deleteFolder);
            }
        }
    }
    public void unloadWorld(World world) {
        this.world = Bukkit.getWorld("");
        if(!world.equals(null)) {
            Bukkit.getServer().unloadWorld(world, true);

        }
    }

    public boolean deleteWorld(File path) {
        if(path.exists()) {
            File files[] = path.listFiles();
            for(int i=0; i<files.length; i++) {
                if(files[i].isDirectory()) {
                    deleteWorld(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }
        return(path.delete());
    }
}
