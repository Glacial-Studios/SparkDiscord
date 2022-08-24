package dev.panda.discord.events;

import dev.panda.discord.utilities.DiscordWebHook;
import dev.panda.discord.utilities.file.ConfigFile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.awt.*;

public class DeathWebHook implements Listener {
    @EventHandler
    public void onDeathWeHook(PlayerDeathEvent event) {
        Player player = event.getEntity().getPlayer();
        if (player.hasPermission(ConfigFile.getConfig().getString("DEATH.WEBHOOK-PERMISSION"))) {
            DiscordWebHook discordWebHook = new DiscordWebHook(ConfigFile.getConfig().getString("WEBHOOK-URL"));
            DiscordWebHook.EmbedObject embedObject = new DiscordWebHook.EmbedObject();

            embedObject.setTitle(ConfigFile.getConfig().getString("DEATH.WEBHOOK-TITLE")
                    .replace("{player}", player.getName())
                    .replace("{cause}", event.getDeathMessage()
                            .replace(player.getName(), "")
                    ));
            embedObject.setDescription(discordWebHook.buildDescription(ConfigFile.getConfig().getStringList("DEATH.WEBHOOK-DESCRIPTION"))
                    .replace("{player}", player.getName())
                    .replace("{cause}", event.getDeathMessage()
                            .replace(player.getName(), "")
                    ));
            embedObject.setColor(Color.decode(ConfigFile.getConfig().getString("DEATH.WEBHOOK-COLOR")));
            embedObject.setThumbnail(ConfigFile.getConfig().getString("DEATH.WEBHOOK-THUMBNAIL"));
            discordWebHook.addEmbed(embedObject);

            try {
                discordWebHook.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}