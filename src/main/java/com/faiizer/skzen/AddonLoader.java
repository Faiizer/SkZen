package com.faiizer.skzen;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import ch.njol.skript.util.Version;
import com.faiizer.skzen.effect.EffSendToastToPlayer;
import com.faiizer.skzen.expression.ExprToast;
import com.faiizer.skzen.toast.Toast;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.logging.Logger;

/**
 * @hidden
 */
public class AddonLoader {

    private final SkZen plugin;
    private final PluginManager pluginManager;
    private final FileConfiguration config;
    private final Plugin skriptPlugin;
    private SkriptAddon addon;
    private final Logger logger;

    public AddonLoader(SkZen plugin) {
        this.plugin = plugin;
        this.pluginManager = plugin.getServer().getPluginManager();
        this.config = plugin.getConfig();
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


        Classes.registerClass(new ClassInfo<Toast>(Toast.class, "toast").user("toast").name("toast").parser(new Parser<Toast>() {

            @Override
            @Nullable
            public Toast parse(String arg0, ParseContext arg1) {
                String[] parts = arg0.split(" and icon ");

                if (parts.length != 2) {
                    return null;
                }

                String messagePart = parts[0].replace("toast with message ", "").trim();
                String iconPart = parts[1].trim();

                Material iconMaterial;
                try {
                    iconMaterial = Material.valueOf(iconPart.toUpperCase());
                } catch (IllegalArgumentException e) {
                    return null;
                }

                return new Toast(messagePart, iconMaterial);
            }

            @Override
            public String toString(Toast arg0, int arg1) {
                return arg0.toString();
            }

            @Override
            public String toVariableNameString(Toast arg0) {
                return arg0.getName();
            }

        }));
        Skript.registerExpression(ExprToast.class, Toast.class, ExpressionType.SIMPLE, "toast with message %string% and icon %material%");
        Skript.registerEffect(EffSendToastToPlayer.class, "toast %toast% to %player%");
    }
}