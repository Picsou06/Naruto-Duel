package fr.picsou.narutoduel.components.role.Sakura;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import fr.picsou.narutoduel.components.Items.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

public class Sakura {
    public void setrole(Player player){
     player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, -1, 0));
     player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, -1, 0));
     ItemBuilder Sceau = new ItemBuilder(Material.NETHER_STAR);
        Sceau.setName(ChatColor.RED+"Sceau de Création et de Renouveau");
     player.getInventory().setItem(1, Sceau.toItemStack());
     ItemBuilder ModeBiju = new ItemBuilder(Material.NETHER_STAR);
     ModeBiju.setName(ChatColor.RED + "Déchainement");
        player.getInventory().setItem(2, ModeBiju.toItemStack());

    }

    public ItemStack gethead(){
        ItemStack head = getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGFmNGQxMzg3MzcwYTgxNjMzOTdmMTM5Yzc4ZDE2YjZkZWY3ZTcxZTIxZDQxZWMyNzYyMDRlNzFlZWM3MzIifX19");
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_RED + "Sakura Haruno");
        head.setItemMeta(meta);
        return head;
    }

    public ItemStack getCustomSkull(String headURL) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        if (headURL.isEmpty()) return head;
        SkullMeta skullMeta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", headURL));
        try {
            Method mtd = skullMeta.getClass().getDeclaredMethod("setProfile", GameProfile.class);
            mtd.setAccessible(true);
            mtd.invoke(skullMeta, profile);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            ex.printStackTrace();
        }
        head.setItemMeta(skullMeta);
        return head;
    }

}
