package com.faiizer.skzen.toast;

import eu.endercentral.crazy_advancements.advancement.AdvancementDisplay;
import eu.endercentral.crazy_advancements.advancement.ToastNotification;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Toast {

    private String message;
    private Material icon;

    public Toast(String message, Material icon) {
        this.message = message;
        this.icon = icon;
    }

    public String toString() {
        return "un toast";
    }

    public String getName() {
        return "toujours un toast";
    }

    public void send(Player player) {
        ToastNotification notification = new ToastNotification(new ItemStack(this.icon),
                this.message,
                AdvancementDisplay.AdvancementFrame.TASK);
        notification.send(player);
    }

}
