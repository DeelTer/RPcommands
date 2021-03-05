package ru.deelter.rpcommands;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import ru.deelter.rpcommands.utils.MyCommand;
import ru.deelter.rpcommands.utils.Other;

public class Config {

    public static String CD_MESSAGE;
    public static String RELOAD_MESSAGE;

    public static String RELOAD_PERM;

    public static void reloadConfig() {
        RpCommands.getInstance().reloadConfig();

        FileConfiguration config = RpCommands.getInstance().getConfig();
        ConfigurationSection messages = config.getConfigurationSection("messages");
        CD_MESSAGE = Other.color(messages.getString("cooldown"));
        RELOAD_MESSAGE = Other.color(messages.getString("permission"));

        ConfigurationSection permissions = config.getConfigurationSection("permissions");
        RELOAD_PERM = permissions.getString("reload");

        ConfigurationSection commands = config.getConfigurationSection("commands");
        for (String id : commands.getKeys(false)) {
            Bukkit.getCommandMap().getKnownCommands().remove(id);
            ConfigurationSection command = commands.getConfigurationSection(id);

            String display = command.getString("display");
            String description = command.getString("description");
            MyCommand myCommand = new MyCommand(id, display, description);

            double radius = command.getDouble("radius");
            myCommand.setRadius(radius);

            double cooldown = command.getDouble("cooldown");
            myCommand.setCooldown(cooldown);
            myCommand.register();
        }
    }
}
