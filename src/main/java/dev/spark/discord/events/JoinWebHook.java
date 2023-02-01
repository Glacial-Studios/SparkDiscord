package dev.spark.discord.events;

import dev.spark.discord.utilities.DiscordWebHook;
import dev.spark.discord.utilities.file.ConfigFile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.awt.*;

public class JoinWebHook implements Listener {
    @EventHandler
    public void onJoinWebHook(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission(ConfigFile.getConfig().getString("JOIN.WEBHOOK-PERMISSION"))) {
            DiscordWebHook discordWebHook = new DiscordWebHook(ConfigFile.getConfig().getString("WEBHOOK-URL"));
            DiscordWebHook.EmbedObject embedObject = new DiscordWebHook.EmbedObject();

            embedObject.setTitle(ConfigFile.getConfig().getString("JOIN.WEBHOOK-TITLE")
                    .replace("{player}", event.getPlayer().getName()
                    ));
            embedObject.setDescription(discordWebHook.buildDescription(ConfigFile.getConfig().getStringList("JOIN.WEBHOOK-DESCRIPTION"))
                    .replace("{player}", event.getPlayer().getName()
                    ));
            embedObject.setColor(Color.decode(ConfigFile.getConfig().getString("JOIN.WEBHOOK-COLOR")));
            embedObject.setThumbnail(ConfigFile.getConfig().getString("JOIN.WEBHOOK-THUMBNAIL"));
            discordWebHook.addEmbed(embedObject);

            try {
                discordWebHook.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}