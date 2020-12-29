package ru.deelter.rpcommands;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import ru.deelter.rpcommands.utils.MyCommand;
import ru.deelter.rpcommands.utils.Other;

public class Config {

    public static String CD_MESSAGE;

    public static void reloadConfig(Main instance) {
        instance.reloadConfig();

        FileConfiguration config = instance.getConfig();
        ConfigurationSection messages = config.getConfigurationSection("messages");
        CD_MESSAGE = Other.color(messages.getString("cooldown"));

        ConfigurationSection commands = config.getConfigurationSection("commands");
        for (String id : commands.getKeys(false)) {
            ConfigurationSection command = commands.getConfigurationSection(id);

            String display = command.getString("display");
            String description = command.getString("description");
            MyCommand myCommand = new MyCommand(id, display, description);

            double radius = command.getDouble("radius");
            myCommand.setRadius(radius);

            long cooldown = command.getLong("cooldown");
            myCommand.setCooldown(cooldown);
            myCommand.register();
        }
    }
}
