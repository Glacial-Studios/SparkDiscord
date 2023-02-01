package dev.spark.discord.utilities.file;

import java.io.File;

import dev.spark.discord.SparkDiscord;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class ConfigFile extends YamlConfiguration {

    private static ConfigFile config;
    private final Plugin plugin;
    private final File configFile;

    public static ConfigFile getConfig() {
        if (ConfigFile.config == null) {
            ConfigFile.config = new ConfigFile();
        }
        return ConfigFile.config;
    }

    private Plugin main() {
        return SparkDiscord.get();
    }

    public ConfigFile() {
        this.plugin = this.main();
        this.configFile = new File(this.plugin.getDataFolder(), "settings.yml");
        this.saveDefault();
        this.reload();
    }

    public void reload() {
        try {
            super.load(this.configFile);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            super.save(this.configFile);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveDefault() {
        this.plugin.saveResource("settings.yml", false);
    }
}
