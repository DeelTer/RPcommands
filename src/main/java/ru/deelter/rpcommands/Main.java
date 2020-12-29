package ru.deelter.rpcommands;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import ru.deelter.rpcommands.commands.RpCommands;

import java.io.File;

public final class Main extends JavaPlugin {

    private static JavaPlugin instance;

    public static Plugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        File config = new File(instance.getDataFolder().getPath() + "/config.yml");
        if (!config.exists())
            saveDefaultConfig();

        Config.reloadConfig();
        getCommand("rpcommands").setExecutor(new RpCommands());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
