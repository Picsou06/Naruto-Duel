package fr.picsou.narutoduel.components.commands;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;

import javax.annotation.Nonnull;
import java.util.Random;

public class CommandCreateDuel implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.isOp()) {
                WorldCreator wc = new WorldCreator("DUEL");
                wc.generator(new EmptyChunkGenerator());
                wc.createWorld();

                player.teleport(new Location(Bukkit.getWorld("DUEL"), 0, 100, 0));
                player.setGameMode(GameMode.SPECTATOR);
            } else {
                player.sendMessage("Vous n'avez pas la permission d'exécuter cette commande !");
                return false;
            }
        }
        return false;
    }

    public class EmptyChunkGenerator extends ChunkGenerator {

        @Override
        @Nonnull
        public ChunkData generateChunkData(@Nonnull World world, @Nonnull Random random, int x, int z, @Nonnull BiomeGrid biome) {
            return createChunkData(world);
        }
    }

}
