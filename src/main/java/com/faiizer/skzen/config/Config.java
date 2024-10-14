package com.faiizer.skzen.config;

import com.faiizer.skzen.SkZen;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Config {

    private SkZen plugin;
    private FileConfiguration config;
    private File configFile;

    public Config(SkZen plugin) {
        this.plugin = plugin;
        loadConfigFile();
    }

    private void loadConfigFile() {
        if (configFile == null) {
            configFile = new File(plugin.getDataFolder(), "config.yml");
        }
        if (!configFile.exists()) {
            plugin.saveResource("config.yml", false);
        }
        config = YamlConfiguration.loadConfiguration(configFile);
    }

}
