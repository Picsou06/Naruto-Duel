package fr.picsou.narutoduel;

import fr.picsou.narutoduel.components.commands.*;
import fr.picsou.narutoduel.components.list.ArenaManager;
import fr.picsou.narutoduel.components.listener.ListenerManager;
import fr.picsou.narutoduel.utils.Commands.SimpleCommand;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_19_R3.CraftServer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main extends JavaPlugin {
    private static Main instance;

    private List<fr.picsou.narutoduel.components.list.PlayerInDuel> PlayerInDuel = new ArrayList<>();

    private List<ArenaManager> ArenaManager = new ArrayList<>();

    private HashMap<Player, Player> DuelRequest = new HashMap<Player, Player>();

    @Override
    public void onEnable(){
        saveDefaultConfig();
        instance = this;
        System.out.println("[Naruto Duel] ON");
        createCommand(new SimpleCommand("list", "", new CommandList()));
        createCommand(new SimpleCommand("duel", "", new CommandDuel()));
        createCommand(new SimpleCommand("reloadConfig", "", new CommandReloadConfig()));
        createCommand(new SimpleCommand("duels", "", new CommandDuels()));
        new ListenerManager(this);
    }

    @Override
    public void onDisable() {
        System.out.println("[Naruto Duel] OFF");
    }
    private void createCommand(SimpleCommand command) {
        CraftServer server = (CraftServer) getServer();
        server.getCommandMap().register(getName(), command);
    }
    public List<fr.picsou.narutoduel.components.list.PlayerInDuel> getPlayerInDuel() {
        return PlayerInDuel;
    }

    public List<fr.picsou.narutoduel.components.list.ArenaManager> getArenaManager() {
        return ArenaManager;
    }

    public HashMap<Player, Player> getDuelRequest() {
        return DuelRequest;
    }

    private void loadArenasFromConfig() {
        FileConfiguration config = getConfig();
        if (config.contains("arena")) {
            ConfigurationSection arenasSection = config.getConfigurationSection("arena");
            for (String arenaName : arenasSection.getKeys(false)) {
                if (config.contains("arena." + arenaName + ".x1") &&
                        config.contains("arena." + arenaName + ".y1") &&
                        config.contains("arena." + arenaName + ".z1") &&
                        config.contains("arena." + arenaName + ".yaw1") &&
                        config.contains("arena." + arenaName + ".pitch1") &&
                        config.contains("arena." + arenaName + ".x2") &&
                        config.contains("arena." + arenaName + ".y2") &&
                        config.contains("arena." + arenaName + ".z2") &&
                        config.contains("arena." + arenaName + ".yaw2") &&
                        config.contains("arena." + arenaName + ".pitch2") &&
                        config.contains("arena." + arenaName + ".disponible")) {

                    double x1 = config.getDouble("arena." + arenaName + ".x1");
                    double y1 = config.getDouble("arena." + arenaName + ".y1");
                    double z1 = config.getDouble("arena." + arenaName + ".z1");
                    float yaw1 = (float) config.getDouble("arena." + arenaName + ".yaw1");
                    float pitch1 = (float) config.getDouble("arena." + arenaName + ".pitch1");
                    double x2 = config.getDouble("arena." + arenaName + ".x2");
                    double y2 = config.getDouble("arena." + arenaName + ".y2");
                    double z2 = config.getDouble("arena." + arenaName + ".z2");
                    float yaw2 = (float) config.getDouble("arena." + arenaName + ".yaw2");
                    float pitch2 = (float) config.getDouble("arena." + arenaName + ".pitch2");
                    boolean disponible = config.getBoolean("arena." + arenaName + ".disponible");

                    boolean open = true;

                    ArenaManager arenaManager = new ArenaManager(arenaName, x1, y1, z1, yaw1, pitch1, x2, y2, z2, yaw2, pitch2, disponible, open);
                    ArenaManager.add(arenaManager);
                }
            }
        }
    }



    public static Main getInstance() {
        return instance;
    }
}
