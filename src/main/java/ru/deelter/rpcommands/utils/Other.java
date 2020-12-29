package ru.deelter.rpcommands.utils;

import org.bukkit.ChatColor;

public class Other {

    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static String strip(String s) {
        return ChatColor.stripColor(s);
    }
}
