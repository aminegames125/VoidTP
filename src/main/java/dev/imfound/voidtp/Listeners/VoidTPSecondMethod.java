package dev.imfound.voidtp.Listeners;

import dev.imfound.voidtp.VoidTP;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class VoidTPSecondMethod implements Listener {

    @EventHandler
    public void onVoid(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        Player p = (Player) e.getEntity();
        if (e.getCause() == EntityDamageEvent.DamageCause.VOID) {
            VoidTP.getInstance().reloadConfig();

            // Get the world name of the player
            String worldName = p.getWorld().getName();

            // Get the spawn point for the player's world from the config
            ConfigurationSection worldSpawnConfig = VoidTP.getInstance().getConfig().getConfigurationSection("Worlds." + worldName + ".Spawn");
            if (worldSpawnConfig == null) {
                // Spawn point not defined for this world, do nothing or teleport to default spawn
                // For example, teleporting to world's spawn point if available
                World world = Bukkit.getWorld(worldName);
                if (world != null) {
                    Location spawnLocation = world.getSpawnLocation();
                    p.teleport(spawnLocation);
                }
                return;
            }

            // Get spawn point coordinates
            String x = worldSpawnConfig.getString("x");
            String y = worldSpawnConfig.getString("y");
            String z = worldSpawnConfig.getString("z");
            int xint = Integer.parseInt(x);
            int yint = Integer.parseInt(y);
            int zint = Integer.parseInt(z);
            float pint = Float.parseFloat(worldSpawnConfig.getString("p"));
            float yaint = Float.parseFloat(worldSpawnConfig.getString("ya"));
            Location loc = new Location(p.getWorld(), xint, yint, zint);
            loc.setPitch(pint);
            loc.setYaw(yaint);
            p.teleport(loc);
        }
    }
}
