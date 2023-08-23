/*package fr.picsou.narutoduel.components.commands;

import fr.picsou.narutoduel.Main;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;

import javax.annotation.Nonnull;
import java.io.*;
import java.util.Random;

public class OLDCommandDuel implements CommandExecutor {

    private World world;
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            String targetPlayerName = strings[0];
            Player targetPlayer = Bukkit.getServer().getPlayerExact(targetPlayerName);
            if (targetPlayer == player) {
                player.sendMessage("§4Vous ne pouvez pas vous demander en duel");
                return false;
            }

            if (targetPlayer.isOnline()) {
                if (Main.getInstance().getPlayerInDuel().stream().anyMatch(info -> info.getPlayerName().equals(targetPlayer.getName()))) {
                    player.sendMessage("Le joueur " + targetPlayerName + " est en duel et ne peut pas être défié.");
                    return false;
                }
                if (Main.getInstance().getPlayerInDuel().stream().anyMatch(info -> info.getPlayerName().equals(player.getName()))) {
                    player.sendMessage("Vous êtes déjà en duel.");
                    return false;
                }
                World source = Bukkit.getWorld("DUEL");
                World DuelWorld = Bukkit.getWorld("Duel_" + player.getName());

                if (source==null){
                    WorldCreator DUEL = new WorldCreator("DUEL");
                    Bukkit.createWorld(DUEL);
                }

                    if (DuelWorld == null) {
                        WorldCreator wc = new WorldCreator("Duel_" + player.getName());
                        wc.generator(new EmptyChunkGenerator());
                        Bukkit.broadcastMessage("AVANT LE CW");
                        World target = Bukkit.createWorld(wc);
                        Bukkit.broadcastMessage("APRES LE CW");
                        Bukkit.broadcastMessage("AVANT LE UNLOAD");
                        unloadWorld(target);
                        unloadWorld(source);
                        Bukkit.broadcastMessage("APRES LE UNLOAD");

                        File sourceFolder = source.getWorldFolder();
                        File targetFolder = target.getWorldFolder();

                        File uidFile = new File(targetFolder, "uid.dat");
                        if (uidFile.exists()) {
                            uidFile.delete();
                            Bukkit.broadcastMessage("DELETE UID");
                        }

                        Bukkit.broadcastMessage("AVANT LE COPY");
                        copyWorldRecursive(sourceFolder, targetFolder);
                        Bukkit.broadcastMessage("APRES LE COPY");

                        Bukkit.createWorld(wc);
                        player.teleport(new Location(target, -28, 101, -5, -90, 0));
                        targetPlayer.teleport(new Location(target, 8, 101, -5, 90, 0));
                        if (player.getWorld().getName().equals("Duel_" + player.getName()) && targetPlayer.getWorld().getName().equals("Duel_" + player.getName())) {
                            Main.getInstance().setDuel(player.getName(), targetPlayerName);
                        }
                    } else {
                        player.sendMessage("§4Le monde 'PLAYER' existe déjà!");
                        unloadWorld(DuelWorld);
                        deleteWorld(DuelWorld.getWorldFolder());

                    }
            } else {
                player.sendMessage("§4Le joueur " + targetPlayerName + " n'est pas connecté!");
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

    public void unloadWorld(World world) {
        if (world != null) {
            Bukkit.broadcastMessage("UNLOAD DU MONDE EN COURS");
            Bukkit.unloadWorld(world, false);
            Bukkit.broadcastMessage("UNLOAD DU MONDE PASSE");
        }
    }

    public void copyWorldRecursive(File source, File target) {
        try {
            if (source.isDirectory()) {
                if (!target.exists())
                    target.mkdirs();
                String files[] = source.list();
                for (String file : files) {
                    File srcFile = new File(source, file);
                    File destFile = new File(target, file);
                    copyWorldRecursive(srcFile, destFile);
                }
            } else {
                InputStream in = new FileInputStream(source);
                OutputStream out = new FileOutputStream(target);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = in.read(buffer)) > 0)
                    out.write(buffer, 0, length);
                in.close();
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
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

}*/
