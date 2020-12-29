package ru.deelter.rpcommands.utils;

import org.bukkit.Bukkit;
import ru.deelter.rpcommands.commands.Command;

/** @author DeelTer */
public class MyCommand {

    private final String id, display, description;

    private boolean hasRadius, hasCooldown;
    private long cooldown;
    private double radius;

    public MyCommand(String id, String display, String description) {
        this.id = id;
        this.display = display;
        this.description = description;
    }

    /* Setters */
    public void setRadius(double radius) {
        this.hasRadius = radius > 0;
        this.radius = radius;
    }

    public void setCooldown(long cooldown) {
        this.hasCooldown = cooldown > 0;
        this.cooldown = cooldown * 20;
    }

    /* Getters */
    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getDisplay() {
        return display;
    }

    public double getRadius() {
        return radius;
    }

    public long getCooldown() {
        return cooldown;
    }

    /* Hassers? xd */
    public boolean hasRadius() {
        return hasRadius;
    }

    public boolean hasCooldown() {
        return hasCooldown;
    }

    public void register() {
        Bukkit.getCommandMap().register("roleplay", new Command(this));
    }
}
