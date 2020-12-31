package ru.deelter.rpcommands.utils;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class MyText {

    private final TextComponent component;

    public MyText(String text) {
        String colorText = Other.color(text);
        this.component = new TextComponent(text);
    }


    public void setDescription(String text) {
        String colorText = Other.color(text);
        HoverEvent hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(colorText));
        component.setHoverEvent(hoverEvent);
    }

    public void setSuggestCommand(String command) {
        ClickEvent clickEvent = new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/" + command + " ");
        component.setClickEvent(clickEvent);
    }

    public void sendAllPlayers() {
        Bukkit.broadcast(component);
    }

    public void sendNearbyPlayers(Location location, double radius) {
        location.getNearbyPlayers(radius).forEach(player -> player.sendMessage(component));
    }
}
