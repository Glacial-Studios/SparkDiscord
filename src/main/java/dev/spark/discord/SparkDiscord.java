package dev.spark.discord;

import dev.spark.discord.events.*;
import dev.spark.discord.events.*;
import dev.spark.discord.utilities.DiscordWebHook;
import dev.spark.discord.utilities.file.ConfigFile;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.util.List;

@Getter @Setter
public class SparkDiscord extends JavaPlugin {

    @Override
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new JoinWebHook(), this);
        pm.registerEvents(new QuitWebHook(), this);
        pm.registerEvents(new DeathWebHook(), this);
        pm.registerEvents(new ChatWebHook(), this);
        pm.registerEvents(new CMDWebHook(), this);

        DiscordWebHook discordWebHook = new DiscordWebHook(ConfigFile.getConfig().getString("WEBHOOK-URL"));
        DiscordWebHook.EmbedObject embedObject = new DiscordWebHook.EmbedObject();

        embedObject.setTitle(ConfigFile.getConfig().getString("START.WEBHOOK-TITLE"));
        embedObject.setDescription(buildDescription(ConfigFile.getConfig().getStringList("START.WEBHOOK-DESCRIPTION")));
        embedObject.setColor(Color.decode(ConfigFile.getConfig().getString("START.WEBHOOK-COLOR")));
        embedObject.setThumbnail(ConfigFile.getConfig().getString("START.WEBHOOK-THUMBNAIL"));
        discordWebHook.addEmbed(embedObject);

        try {
            discordWebHook.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        DiscordWebHook discordWebHook = new DiscordWebHook(ConfigFile.getConfig().getString("WEBHOOK-URL"));
        DiscordWebHook.EmbedObject embedObject = new DiscordWebHook.EmbedObject();

        embedObject.setTitle(ConfigFile.getConfig().getString("STOP.WEBHOOK-TITLE"));
        embedObject.setDescription(buildDescription(ConfigFile.getConfig().getStringList("STOP.WEBHOOK-DESCRIPTION")));
        embedObject.setColor(Color.decode(ConfigFile.getConfig().getString("STOP.WEBHOOK-COLOR")));
        embedObject.setThumbnail(ConfigFile.getConfig().getString("STOP.WEBHOOK-THUMBNAIL"));
        discordWebHook.addEmbed(embedObject);

        try {
            discordWebHook.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String buildDescription(List<String> description) {
        String toReturn = description.toString();
        return toReturn.substring(1, toReturn.length() - 1).replace(", ", "%line%");
    }

    public static SparkDiscord get() {
        return getPlugin(SparkDiscord.class);
    }
}