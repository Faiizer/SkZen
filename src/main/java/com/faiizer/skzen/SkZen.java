package com.faiizer.skzen;

import com.faiizer.skzen.config.Config;
import eu.endercentral.crazy_advancements.advancement.AdvancementDisplay;
import eu.endercentral.crazy_advancements.advancement.ToastNotification;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main class of SkZen.
 */
public final class SkZen extends JavaPlugin implements Listener {

    private static SkZen instance;
    private Config config;
    private AddonLoader addonLoader;


    @Override
    public void onEnable() {
        instance = this;
        config = new Config(this);

        PluginManager pm = getServer().getPluginManager();
        this.addonLoader = new AddonLoader(this);
        if (!addonLoader.canLoadPlugin()) {
            pm.disablePlugin(this);
            return;
        }

        pm.registerEvents(this, this);

        getLogger().info("&aSuccessfully enabled !");
    }

    @EventHandler
    public void onChat(PlayerChatEvent event) {
        ToastNotification notification = new ToastNotification(new ItemStack(Material.BREAD),
                "SALUUUUT ceci est le message du toast",
                AdvancementDisplay.AdvancementFrame.TASK);
        notification.send(event.getPlayer());
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }



    /**
     * Get an instance of this plugin.
     *
     * @return Instance of this plugin.
     */
    public static SkZen getPlugin() {
        return instance;
    }

    /**
     * Get an instance of this plugin's {@link Config}.
     *
     * @return Instance of this plugin's config.
     */
    public Config getPluginConfig() {
        return this.config;
    }

    /**
     * Get an instance of this plugin's {@link AddonLoader}.
     *
     * @return Instance of this plugin's addonLoader.
     */
    public AddonLoader getAddonLoader() {
        return addonLoader;
    }
}
