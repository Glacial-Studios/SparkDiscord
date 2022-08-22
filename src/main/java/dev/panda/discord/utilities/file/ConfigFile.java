package dev.panda.discord.utilities.file;

import java.io.File;

import dev.panda.discord.PandaDiscord;
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
        return PandaDiscord.get();
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
