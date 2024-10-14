package com.faiizer.skzen;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import ch.njol.skript.util.Version;
import com.faiizer.skzen.config.Config;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.io.IOException;
import java.util.logging.Logger;


public class AddonLoader {

    private final SkZen plugin;
    private final PluginManager pluginManager;
    private final Config config;
    private final Plugin skriptPlugin;
    private SkriptAddon addon;
    private final Logger logger;

    public AddonLoader(SkZen plugin) {
        this.plugin = plugin;
        this.pluginManager = plugin.getServer().getPluginManager();
        this.config = plugin.getPluginConfig();
        this.skriptPlugin = pluginManager.getPlugin("Skript");
        this.logger = plugin.getLogger();
    }

    boolean canLoadPlugin() {
        if (skriptPlugin == null) {
            logger.warning("&cDependency Skript was not found, plugin disabling.");
            return false;
        }
        if (!skriptPlugin.isEnabled()) {
            logger.warning("&cDependency Skript is not enabled, plugin disabling.");
            logger.warning("&cThis could mean SkZen is being forced to load before Skript.");
            return false;
        }
        Version skriptVersion = Skript.getVersion();
        if (skriptVersion.isSmallerThan(new Version(2, 9))) {
            logger.warning("&cDependency Skript outdated, plugin disabling.");
            logger.warning("&eSkBee requires Skript 2.9+ but found Skript " + skriptVersion);
            return false;
        }
        if (!Skript.isAcceptRegistrations()) {
            logger.warning("&cSkript is no longer accepting registrations, addons can no longer be loaded!");
            return false;
        }
        loadSkriptElements();
        return true;
    }

    private void loadSkriptElements() {
        this.addon = Skript.registerAddon(this.plugin);

        // Load Elements
        loadToastElements();
    }


    private void loadToastElements() {
        if (!this.config.ELEMENTS_TOAST) {
            logger.info("&5Toast Elements &cdisabled via config");
            return;
        }
        try {
            addon.loadClasses("com.faiizer.skzen.elements.toast");
            logger.info("&5Toast Elements &asuccessfully loaded");
        } catch (IOException ex) {
            logger.severe(ex.toString());
            pluginManager.disablePlugin(this.plugin);
        }
    }
}