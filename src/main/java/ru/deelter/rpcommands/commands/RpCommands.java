package ru.deelter.rpcommands.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import ru.deelter.rpcommands.Config;

public class RpCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1 && args[0] == null)
            return true;

        if (args[0].equalsIgnoreCase("reload")) {
            sender.sendMessage("Reload configuration");
            Config.reloadConfig();
        }

        return true;
    }
}
