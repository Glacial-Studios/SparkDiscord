package dev.panda.discord.events;

import dev.panda.discord.utilities.DiscordWebHook;
import dev.panda.discord.utilities.file.ConfigFile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.awt.*;
import java.util.List;

public class QuitWebHook implements Listener {
    @EventHandler
    public void onQuitWebHook(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission(ConfigFile.getConfig().getString("QUIT.WEBHOOK-PERMISSION"))) {
            DiscordWebHook discordWebHook = new DiscordWebHook(ConfigFile.getConfig().getString("WEBHOOK-URL"));
            DiscordWebHook.EmbedObject embedObject = new DiscordWebHook.EmbedObject();

            embedObject.setTitle(ConfigFile.getConfig().getString("QUIT.WEBHOOK-TITLE")
                    .replace("{player}", event.getPlayer().getName()
                    ));
            embedObject.setDescription(discordWebHook.buildDescription(ConfigFile.getConfig().getStringList("QUIT.WEBHOOK-DESCRIPTION"))
                    .replace("{player}", event.getPlayer().getName()
                    ));
            embedObject.setColor(Color.decode(ConfigFile.getConfig().getString("QUIT.WEBHOOK-COLOR")));
            embedObject.setThumbnail(ConfigFile.getConfig().getString("QUIT.WEBHOOK-THUMBNAIL"));
            discordWebHook.addEmbed(embedObject);

            try {
                discordWebHook.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}