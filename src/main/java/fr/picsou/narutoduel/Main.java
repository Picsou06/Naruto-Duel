package fr.picsou.narutoduel;

import fr.picsou.narutoduel.components.commands.*;
import fr.picsou.narutoduel.components.listener.ListenerManager;
import fr.picsou.narutoduel.components.listener.Player.ListAskDuel;
import fr.picsou.narutoduel.utils.Commands.SimpleCommand;
import org.bukkit.craftbukkit.v1_19_R3.CraftServer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main extends JavaPlugin {
    private static Main instance;

    private List<fr.picsou.narutoduel.components.listener.Player.PlayerInDuel> PlayerInDuel = new ArrayList<>();
    private List<ListAskDuel> ListAskDuel = new ArrayList<>();

    private HashMap<Player, Player> requestTestAhix = new HashMap<Player, Player>();

    @Override
    public void onEnable(){
        instance = this;
        System.out.println("[Naruto Duel] ON");
        createCommand(new SimpleCommand("createduelmap", "", new CommandCreateDuel()));
        createCommand(new SimpleCommand("list", "", new CommandList()));
        createCommand(new SimpleCommand("duel", "", new CommandDuel()));
        //getCommand("duel").setTabCompleter(new DuelTabCompletion());
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

    public List<fr.picsou.narutoduel.components.listener.Player.PlayerInDuel> getPlayerInDuel() {
        return PlayerInDuel;
    }

    public HashMap<Player, Player> getRequestTestAhix() {
        return requestTestAhix;
    }

    public static Main getInstance() {
        return instance;
    }
}
