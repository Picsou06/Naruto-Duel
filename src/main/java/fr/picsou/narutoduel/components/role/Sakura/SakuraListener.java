package fr.picsou.narutoduel.components.role.Sakura;

import fr.picsou.narutoduel.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class SakuraListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(player.getItemInHand()!=null) {
            if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.RED + "Sceau de Création et de Renouveau")) {
                player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, -1, 2));
                player.getInventory().remove(Material.NETHER_STAR);
                player.setMaxHealth(30);
                (new BukkitRunnable() {
                    public void run() {
                        if(Main.getInstance().getPlayerInDuel().contains(player)) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, -1, 0));
                            player.removePotionEffect(PotionEffectType.REGENERATION);
                            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, -1, 0));
                            if (player.getHealth() >= 20) {
                                player.setMaxHealth(20);
                            } else {
                                player.setMaxHealth(20);
                            }
                        }
                    }
                }).runTaskLater(Main.getInstance(), 500L);
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.RED + "Déchainement")) {
                player.removePotionEffect(PotionEffectType.REGENERATION);
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, -1, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, -1, 0));
                player.getInventory().remove(Material.NETHER_STAR);
                (new BukkitRunnable() {
                    public void run() {
                        if (Main.getInstance().getPlayerInDuel().contains(player)) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, -1, 0));
                            player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                            player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, -1, 0));
                        }
                    }
                }).runTaskLater(Main.getInstance(), 500L);
            }
        }
    }
}
