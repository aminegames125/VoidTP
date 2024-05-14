package dev.imfound.voidtp;

import dev.imfound.voidtp.Commands.VoidTPCommand;
import dev.imfound.voidtp.Listeners.VoidTPFirstMethod;
import dev.imfound.voidtp.Listeners.VoidTPSecondMethod;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class VoidTP extends JavaPlugin {

    static VoidTP plugin;

    public void getMethod() {
        if(getConfig().getString("method").equals("1")){
            this.getServer().getPluginManager().registerEvents(new VoidTPFirstMethod(), this);
        } else if(getConfig().getString("method").equals("2")) {
            this.getServer().getPluginManager().registerEvents(new VoidTPSecondMethod(), this);
        } else {
            Bukkit.getLogger().info("This method dosen't exist, I'm gonna use First Method!");
            this.getServer().getPluginManager().registerEvents(new VoidTPFirstMethod(), this);
        }
    }

    public void loadConfig() {
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        this.reloadConfig();
    }

    @Override
    public void onEnable() {
        plugin = this;
        loadConfig();
        getMethod();
        // My addition: Display my name along with original messages
        Bukkit.getLogger().info("#########");
        Bukkit.getLogger().info("");
        Bukkit.getLogger().info("VoidTP");
        Bukkit.getLogger().info("by ImFound");
        Bukkit.getLogger().info("Edited by Amineos"); // My edit
        Bukkit.getLogger().info("Enabled!");
        Bukkit.getLogger().info("");
        Bukkit.getLogger().info("#########");
        this.getCommand("voidtp").setExecutor(new VoidTPCommand());
    }

    @Override
    public void onDisable() {
        // My addition: Display my name along with original messages
        Bukkit.getLogger().info("#########");
        Bukkit.getLogger().info("");
        Bukkit.getLogger().info("VoidTP");
        Bukkit.getLogger().info("by ImFound");
        Bukkit.getLogger().info("Edited by Amineos"); // My edit
        Bukkit.getLogger().info("Disabled!");
        Bukkit.getLogger().info("");
        Bukkit.getLogger().info("#########");
    }

    public static VoidTP getInstance() {
        return plugin;
    }
}
