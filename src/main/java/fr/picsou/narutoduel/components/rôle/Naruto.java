package fr.picsou.narutoduel.components.rôle;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import fr.picsou.narutoduel.components.Items.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

public class Naruto {
    public void setrole(Player player){
     player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 99999, 1));
     player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 1));
     player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 99999, 1));
     ItemBuilder Rasenshuriken = new ItemBuilder(Material.BOW);
     Rasenshuriken.setName(ChatColor.RED+"Rasenshuriken");
     Rasenshuriken.setEnchant(Enchantment.ARROW_DAMAGE, 5);
     Rasenshuriken.setEnchant(Enchantment.ARROW_INFINITE, 1);
     Rasenshuriken.setDurability((short) 380);
     player.getInventory().setItem(1, Rasenshuriken.toItemStack());
    }

    public ItemStack gethead(){
        ItemStack head = getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjkwYjQ4MDgxNDIyOGY0Yjc5NDJiMTY0MTQ5ZTJhMzMxZTY0YTI1NTEyNTFjMjE4ZjBkMjFiYTgyYTYzZmMxNCJ9fX0=");
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_RED + "Naruto Uzumaki");
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
