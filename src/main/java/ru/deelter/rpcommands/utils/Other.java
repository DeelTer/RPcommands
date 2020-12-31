package ru.deelter.rpcommands.utils;

import net.md_5.bungee.api.ChatColor;

public class Other {

    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static String strip(String s) {
        return ChatColor.stripColor(s);
    }
}
