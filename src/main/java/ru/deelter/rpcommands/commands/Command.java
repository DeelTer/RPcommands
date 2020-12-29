package ru.deelter.rpcommands.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import ru.deelter.rpcommands.Config;
import ru.deelter.rpcommands.Main;
import ru.deelter.rpcommands.utils.MyCommand;
import ru.deelter.rpcommands.utils.MyText;
import ru.deelter.rpcommands.utils.Other;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Command extends BukkitCommand {

    private final List<UUID> cooldown = new ArrayList<>();
    private final MyCommand myCommand;

    public Command(MyCommand myCommand) {
        super(myCommand.getId());
        this.myCommand = myCommand;
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Доступно только игроку");
            return true;
        }

        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();

        /* Check cooldown */
        if (cooldown.contains(uuid)) {
            player.sendMessage(Config.CD_MESSAGE);
            return true;
        }

        String message = String.join(" ", args).trim();

        /* Removes the color from player message */
        String stripMessage = Other.strip(message);
        String finalMessage = applyPlaceholders(player, stripMessage, myCommand.getDisplay());

        MyText text = new MyText(finalMessage);
        text.setDescription(myCommand.getDescription());
        text.setSuggestCommand(label);

        if (myCommand.hasRadius()) {
            double radius = myCommand.getRadius();
            text.sendNearbyPlayers(player.getLocation(), radius);
        } else {
            text.sendAllPlayers();
        }

        /* Set cooldown */
        if (myCommand.hasCooldown()) {
            cooldown.add(uuid);

            long seconds = myCommand.getCooldown();
            Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getInstance(), () -> cooldown.remove(uuid), seconds);
        }

        return true;
    }

    private String applyPlaceholders(Player player, String message, String display) {
            String withPlaceholders = display;
            String name = player.getName();
            withPlaceholders = withPlaceholders.replaceAll("%player%", name).replaceAll("%message%", message);

            String random = Math.random() < 0.5D ? "&aУспешно" : "&cНеуспешно";
            withPlaceholders = withPlaceholders.replaceAll("%try%", random);

        return withPlaceholders;
    }
}
