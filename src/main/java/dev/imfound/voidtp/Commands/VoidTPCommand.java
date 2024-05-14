package dev.imfound.voidtp.Commands;

import dev.imfound.voidtp.VoidTP;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VoidTPCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be executed by players.");
            return false;
        }
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("voidtp")) {
            if (!p.hasPermission("voidtp.execute")) {
                p.sendMessage("§b• §7Running §b§nVoidTP 1.0§f by §bImFound");
                return true;
            }
            if (args.length == 2 && args[0].equalsIgnoreCase("setspawn")) {
                if (!p.hasPermission("voidtp.setspawn")) {
                    p.sendMessage("§b• §fYou don't have the permission!");
                    return true;
                }
                String worldName = args[1];
                String x = String.valueOf(p.getLocation().getBlockX());
                String y = String.valueOf(p.getLocation().getBlockY());
                String z = String.valueOf(p.getLocation().getBlockZ());
                String pl = String.valueOf(p.getLocation().getPitch());
                String ya = String.valueOf(p.getLocation().getYaw());

                // Set the spawn point for the specified world
                VoidTP.getInstance().getConfig().set("Worlds." + worldName + ".Spawn.x", x);
                VoidTP.getInstance().getConfig().set("Worlds." + worldName + ".Spawn.y", y);
                VoidTP.getInstance().getConfig().set("Worlds." + worldName + ".Spawn.z", z);
                VoidTP.getInstance().getConfig().set("Worlds." + worldName + ".Spawn.p", pl);
                VoidTP.getInstance().getConfig().set("Worlds." + worldName + ".Spawn.ya", ya);
                VoidTP.getInstance().saveConfig();
                VoidTP.getInstance().reloadConfig();
                p.sendMessage("§b• §7I set the coordinates for world " + worldName + ", §bX: " + x + "§f, §bY: " + y + "§f, §bZ: " + z);
                return true;
            } else {
                p.sendMessage("§b• §fUsage: /voidtp setspawn <world>");
                return true;
            }
        }
        return false;
    }
}
