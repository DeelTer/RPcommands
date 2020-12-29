package ru.deelter.rpcommands;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import ru.deelter.rpcommands.utils.MyCommand;
import ru.deelter.rpcommands.utils.Other;

public class Config {

    public static String CD_MESSAGE;

    public static void reloadConfig() {
        Main.getInstance().reloadConfig();

        FileConfiguration config = Main.getInstance().getConfig();
        ConfigurationSection messages = config.getConfigurationSection("messages");
        CD_MESSAGE = Other.color(messages.getString("cooldown"));

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
