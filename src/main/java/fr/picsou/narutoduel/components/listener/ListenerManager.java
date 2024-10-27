package fr.picsou.narutoduel.components.listener;

import fr.picsou.narutoduel.components.Gui.GuiManager;
import fr.picsou.narutoduel.components.listener.Player.DeathPlayerListener;
import fr.picsou.narutoduel.components.listener.Player.JoinPlayerListener;
import fr.picsou.narutoduel.components.listener.Player.LeavePlayerListener;
import fr.picsou.narutoduel.components.listener.interact.ClickListener;
import fr.picsou.narutoduel.components.role.Naruto.NarutoListener;
import fr.picsou.narutoduel.components.role.Sakura.SakuraListener;
import org.bukkit.plugin.java.JavaPlugin;

public class ListenerManager {

    public ListenerManager(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new JoinPlayerListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new LeavePlayerListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new ClickListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new DeathPlayerListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new GuiManager(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new NarutoListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new SakuraListener(), plugin);
    }
}
