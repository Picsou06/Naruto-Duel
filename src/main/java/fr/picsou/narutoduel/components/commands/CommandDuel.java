package fr.picsou.narutoduel.components.commands;

import fr.picsou.narutoduel.Main;
import fr.picsou.narutoduel.components.Gui.GuiBuilder;
import fr.picsou.narutoduel.components.Gui.GuiManager;
import fr.picsou.narutoduel.components.Items.ItemBuilder;
import fr.picsou.narutoduel.components.role.Naruto.Naruto;
import fr.picsou.narutoduel.components.role.Sakura.Sakura;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.apache.commons.lang.BitField;
import org.apache.commons.lang.StringUtils;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class CommandDuel implements CommandExecutor {
    FileConfiguration config = Main.getInstance().getConfig();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(strings[0]))) {
                if (Main.getInstance().getDuelRequest().containsKey(Bukkit.getPlayer(strings[0]))) {
                    player.sendMessage(ChatColor.RED + "Le joueur a déjà une demande en cours!");
                    return false;
                } else {
                    if(Bukkit.getPlayer(strings[0]).equals(player)){
                        player.sendMessage(ChatColor.RED+"Vous ne pouvez pas vous demandez en duel!");
                        return false;
                    }
                    Player opponent = Bukkit.getPlayer(strings[0]);
                    GuiBuilder DuelGUI = new CommandDuel.DuelGUI(opponent);

                    GuiManager guiManager = new GuiManager();

                    guiManager.addMenu(DuelGUI);

                    guiManager.open(player, CommandDuel.DuelGUI.class);
                    return false;
                }
            }
            if (strings[0].equals("accept")) {
                if (Main.getInstance().getDuelRequest().containsKey(player)) {
                    Player opponent = Main.getInstance().getDuelRequest().get(player);
                    player.setMaxHealth(20);
                    player.setHealth(20);
                    player.setFoodLevel(20);
                    player.setSaturation(10);
                    player.setVelocity(new Vector(0,0,0));
                    player.setFallDistance(0F);
                    for (PotionEffect effect : player.getActivePotionEffects())
                        player.removePotionEffect(effect.getType());
                    player.setMaxHealth(20);
                    opponent.setHealth(20);
                    opponent.setFoodLevel(20);
                    opponent.setSaturation(10);
                    player.setVelocity(new Vector(0,0,0));
                    player.setFallDistance(0F);
                    for (PotionEffect effect : opponent.getActivePotionEffects())
                        opponent.removePotionEffect(effect.getType());
                    if (Bukkit.getOnlinePlayers().contains(opponent)) {
                        Main.getInstance().getDuelRequest().remove(player);
                        player.teleport(new Location(Bukkit.getWorld("Arene1"),config.getDouble("ZONE.arene.x1"),config.getDouble("ZONE.arene.y1"),config.getDouble("ZONE.arene.z1"), (float) config.getDouble("ZONE.arene.yaw1"), (float) config.getDouble("ZONE.arene.pitch1")));
                        opponent.teleport(new Location(Bukkit.getWorld("Arene1"),config.getDouble("ZONE.arene.x2"),config.getDouble("ZONE.arene.y2"),config.getDouble("ZONE.arene.z2"), (float) config.getDouble("ZONE.arene.yaw2"), (float) config.getDouble("ZONE.arene.pitch2")));
                        player.getInventory().clear();
                        opponent.getInventory().clear();
                        Bukkit.broadcastMessage(ChatColor.AQUA + "Téléportation en cours!");
                        ItemBuilder classicsword = new ItemBuilder(Material.IRON_SWORD);
                        classicsword.setDurability((short) -100);
                        classicsword.setEnchant(Enchantment.DAMAGE_ALL,3);
                        classicsword.setName("Katana");
                        ItemBuilder RDS = new ItemBuilder(Material.GOLDEN_APPLE);
                        RDS.setName(ChatColor.GOLD+"Ration de survie");
                        ItemBuilder ArmorHelmet = new ItemBuilder(Material.IRON_HELMET);
                        ArmorHelmet.setEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,2);
                        ItemBuilder ArmorChestplate = new ItemBuilder(Material.DIAMOND_CHESTPLATE);
                        ArmorChestplate.setEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,2);
                        ItemBuilder ArmorLeggings = new ItemBuilder(Material.IRON_LEGGINGS);
                        ArmorLeggings.setEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,2);
                        ItemBuilder ArmorBoots = new ItemBuilder(Material.DIAMOND_BOOTS);
                        ArmorBoots.setEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,2);
                        player.getInventory().setItem(17, new ItemStack(Material.ARROW, 10));
                        player.getInventory().setItem(0, new ItemStack(classicsword.toItemStack()));
                        player.getInventory().setItem(8, new ItemStack(RDS.toItemStack()));
                        for (int i = 1; i < 3; i++){
                            player.getInventory().addItem(RDS.toItemStack());
                        }
                        player.getInventory().setHelmet(ArmorHelmet.toItemStack());
                        player.getInventory().setChestplate(ArmorChestplate.toItemStack());
                        player.getInventory().setLeggings(ArmorLeggings.toItemStack());
                        player.getInventory().setBoots(ArmorBoots.toItemStack());

                        opponent.getInventory().setItem(0, new ItemStack(classicsword.toItemStack()));
                        opponent.getInventory().setItem(8, new ItemStack(RDS.toItemStack()));
                        opponent.getInventory().setItem(17, new ItemStack(Material.ARROW, 10));
                        for (int i = 1; i < 3; i++){
                            opponent.getInventory().addItem(RDS.toItemStack());
                        }
                        opponent.getInventory().setHelmet(ArmorHelmet.toItemStack());
                        opponent.getInventory().setChestplate(ArmorChestplate.toItemStack());
                        opponent.getInventory().setLeggings(ArmorLeggings.toItemStack());
                        opponent.getInventory().setBoots(ArmorBoots.toItemStack());
                        GuiBuilder RoleGUI = new RoleGUI();
                        GuiManager guiManager = new GuiManager();
                        guiManager.addMenu(RoleGUI);
                        guiManager.open(player, CommandDuel.RoleGUI.class);
                        guiManager.open(opponent, CommandDuel.RoleGUI.class);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200,254));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200,127));
                        opponent.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200,254));
                        opponent.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200,127));


                        return false;
                    } else {
                        player.sendMessage(ChatColor.RED + "Ce joueur n'est plus en ligne");
                        return false;
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Vous n'avez pas de demande de duel");
                    return false;
                }

            }
                if (strings[0].equals("deny")) {
                    if (Main.getInstance().getDuelRequest().containsKey(player)) {
                        Player opponent = Main.getInstance().getDuelRequest().get(player);
                        if (Bukkit.getOnlinePlayers().contains(opponent)) {
                            opponent.sendMessage(ChatColor.GRAY + player.getName() + ChatColor.RED + " a refusé votre demande");
                            player.sendMessage(ChatColor.RED + "Vous avez refusé la demande de " + ChatColor.GRAY.BOLD + opponent.getName());
                            Main.getInstance().getDuelRequest().remove(player);
                            return false;
                        } else {
                            player.sendMessage(ChatColor.RED + "Ce joueur n'est plus en ligne");
                            return false;
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "Vous n'avez pas de demande de duel");
                        return false;
                    }
            }
        }return false;
    }


    private static String buildDuelRequestText(Player p) {
        ChatColor gold = ChatColor.GOLD;
        ChatColor boldGray = ChatColor.BOLD.GRAY;
        ChatColor resetAqua = ChatColor.RESET.AQUA;

        String separator = StringUtils.repeat("-", 42); // 42 hyphens
        String playerName = p.getName();

        return String.format("%s%s\n   %s%s%s vous a envoyé une demande de duel.\n" +
                        "%sVous avez 60 secondes pour taper /duel accept.\n%s",
                gold, separator, boldGray, playerName, resetAqua, gold, separator);
    }

    private static String centerText(String text) {
        int maxWidth = 80;
        int spaces = (int) Math.round((maxWidth - text.length()) / 2);
        return StringUtils.repeat(" ", spaces) + text;
    }

    private static class RoleGUI implements GuiBuilder{

        @Override
        public String name(Player player) {
            return "Rôle:";
        }

        @Override
        public int getSize() {
            return 27;
        }

        @Override
        public void contents(Player player, Inventory inv) throws Exception {
            Naruto naruto = new Naruto();
            inv.setItem(0, naruto.gethead());
            Sakura sakura = new Sakura();
            inv.setItem(1, sakura.gethead());
        }

        @Override
        public void onClick(Player player, Inventory inv, ItemStack current, int slot, ClickType action) {
            Naruto naruto = new Naruto();
            Sakura sakura = new Sakura();
            if(slot == 0) {
                naruto.setrole(player);
                player.closeInventory();
            }if(slot == 1) {
                sakura.setrole(player);
                player.closeInventory();
            }
        }
    }

    private static class DuelGUI implements GuiBuilder {
        private Player opponent;

        public DuelGUI(Player opponent) {
            this.opponent = opponent;
        }
        @Override
        public String name(Player player) {
            return "Demande de duel pour "+opponent.getName();
        }

        @Override
        public int getSize() {
            return 27;
        }

        @Override
        public void contents(Player player, Inventory inv) throws Exception {
            ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
            ItemBuilder sendduel = new ItemBuilder(Material.LIME_STAINED_GLASS_PANE, 1);
            sendduel.setName(ChatColor.GREEN + "ACCEPT");
            ItemBuilder denyduel = new ItemBuilder(Material.RED_STAINED_GLASS_PANE, 1);
            denyduel.setName(ChatColor.RED + "CANCEL");
            ItemBuilder setdifficulty = new ItemBuilder(Material.CLOCK, 1);
            setdifficulty.setName(ChatColor.GOLD + "Rôle: ALL");
            ItemBuilder setclasse = new ItemBuilder(Material.PAPER, 1);
            setclasse.setName(ChatColor.GOLD + "CLASSE: ON");
            SkullMeta meta = (SkullMeta) skull.getItemMeta();
            meta.setOwner(opponent.getName());
            meta.setDisplayName(ChatColor.LIGHT_PURPLE + opponent.getName());
            skull.setItemMeta(meta);
            inv.setItem(4, skull);
            inv.setItem(0, sendduel.toItemStack());
            inv.setItem(1, sendduel.toItemStack());
            inv.setItem(2, sendduel.toItemStack());
            inv.setItem(9, sendduel.toItemStack());
            inv.setItem(10, sendduel.toItemStack());
            inv.setItem(11, sendduel.toItemStack());
            inv.setItem(18, sendduel.toItemStack());
            inv.setItem(19, sendduel.toItemStack());
            inv.setItem(20, sendduel.toItemStack());
            inv.setItem(8, denyduel.toItemStack());
            inv.setItem(7, denyduel.toItemStack());
            inv.setItem(6, denyduel.toItemStack());
            inv.setItem(17, denyduel.toItemStack());
            inv.setItem(16, denyduel.toItemStack());
            inv.setItem(15, denyduel.toItemStack());
            inv.setItem(26, denyduel.toItemStack());
            inv.setItem(25, denyduel.toItemStack());
            inv.setItem(24, denyduel.toItemStack());
            inv.setItem(13, setdifficulty.toItemStack());
        }

        @Override
        public void onClick(Player player, Inventory inv, ItemStack current, int slot, ClickType action) {
            if (slot == 0 || slot == 1 || slot == 2 || slot == 9 || slot == 10 || slot == 11 || slot == 18 || slot == 19 || slot == 20) {
                String rôle = "BUG";
                if(inv.getItem(13).getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Rôle: ALL")){
                    rôle = "ALL";
                } else if (inv.getItem(13).getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Rôle: EASY")) {
                    rôle = "EASY";
                } else if (inv.getItem(13).getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Rôle: NORMAL")) {
                    rôle = "NORMAL";
                } else if (inv.getItem(13).getItemMeta().getDisplayName().equals(ChatColor.RED + "Rôle: HARD")) {
                    rôle = "HARD";
                }
                player.sendMessage(ChatColor.AQUA + "Vous avez envoyé une demande de duel à " + ChatColor.GRAY + opponent.getName());
                Main.getInstance().getDuelRequest().put(opponent, player);
                net.md_5.bungee.api.chat.TextComponent message = new TextComponent(centerText(buildDuelRequestText(player)));
                message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§6§l/duel accept").create()));
                message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/duel accept"));
                opponent.spigot().sendMessage(message);
                (new BukkitRunnable() {
                    public void run() {
                        Main.getInstance().getDuelRequest().remove(opponent);
                    }
                }).runTaskLaterAsynchronously(Main.getInstance(), 1200L);
                player.closeInventory();
            }
            if (slot == 8 || slot == 7 || slot == 6 || slot == 17 || slot == 16 || slot == 15 || slot == 26 || slot == 25 || slot == 24) {
                player.closeInventory();
            }
            if (slot == 13) {
                if(inv.getItem(13).getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Rôle: ALL")){
                    ItemBuilder setdifficulty = new ItemBuilder(Material.CLOCK, 1);
                    setdifficulty.setName(ChatColor.AQUA + "Rôle: EASY");
                    inv.setItem(13, setdifficulty.toItemStack());
                } else if (inv.getItem(13).getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Rôle: EASY")) {
                    ItemBuilder setdifficulty = new ItemBuilder(Material.CLOCK, 1);
                    setdifficulty.setName(ChatColor.GREEN + "Rôle: NORMAL");
                    inv.setItem(13, setdifficulty.toItemStack());
                } else if (inv.getItem(13).getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Rôle: NORMAL")) {
                    ItemBuilder setdifficulty = new ItemBuilder(Material.CLOCK, 1);
                    setdifficulty.setName(ChatColor.RED + "Rôle: HARD");
                    inv.setItem(13, setdifficulty.toItemStack());
                } else if (inv.getItem(13).getItemMeta().getDisplayName().equals(ChatColor.RED + "Rôle: HARD")) {
                    ItemBuilder setdifficulty = new ItemBuilder(Material.CLOCK, 1);
                    setdifficulty.setName(ChatColor.GOLD + "Rôle: ALL");
                    inv.setItem(13, setdifficulty.toItemStack());
                }
            }
        }


    }

}