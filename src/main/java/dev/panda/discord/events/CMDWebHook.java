package dev.panda.discord.events;

import dev.panda.discord.utilities.DiscordWebHook;
import dev.panda.discord.utilities.file.ConfigFile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.awt.*;
import java.util.List;

public class CMDWebHook implements Listener {
    @EventHandler
    private void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission(ConfigFile.getConfig().getString("CMD.WEBHOOK-PERMISSION"))) {
            DiscordWebHook discordWebHook = new DiscordWebHook(ConfigFile.getConfig().getString("WEBHOOK-URL"));
            DiscordWebHook.EmbedObject embedObject = new DiscordWebHook.EmbedObject();

            embedObject.setTitle(ConfigFile.getConfig().getString("CMD.WEBHOOK-TITLE")
                    .replace("{player}", event.getPlayer().getName())
                    .replace("{command}", event.getMessage()));
            embedObject.setDescription(discordWebHook.buildDescription(ConfigFile.getConfig().getStringList("CMD.WEBHOOK-DESCRIPTION"))
                    .replace("{player}", event.getPlayer().getName())
                    .replace("{command}", event.getMessage()));
            embedObject.setColor(Color.decode(ConfigFile.getConfig().getString("CMD.WEBHOOK-COLOR")));
            embedObject.setThumbnail(ConfigFile.getConfig().getString("CMD.WEBHOOK-THUMBNAIL"));
            discordWebHook.addEmbed(embedObject);

            try {
                discordWebHook.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}