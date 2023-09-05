package fr.picsou.narutoduel.components.list;

import org.bukkit.World;
import org.bukkit.entity.Player;

public class PlayerInDuel {
    private Player playerName;
    private Player opponentName;
    private World world;

    public PlayerInDuel(Player playerName, Player opponentName, World world) {
        this.playerName = playerName;
        this.opponentName = opponentName;
        this.world = world;
    }

    public Player getPlayerName() {
        return playerName;
    }

    public Player getOpponentName() {
        return opponentName;
    }

    public World getWorldName() {
        return world;
    }
    public void setWorldDuel(World world) {
        this.world = world;
    }

    public void setOpponent(Player opponentName) {
        this.opponentName = opponentName;
    }
}
