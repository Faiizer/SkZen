package com.faiizer.skzen.elements.toast.type;

import ch.njol.skript.aliases.ItemType;
import eu.endercentral.crazy_advancements.advancement.AdvancementDisplay;
import eu.endercentral.crazy_advancements.advancement.ToastNotification;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Toast {

    private String frame;
    private String message;
    private ItemType icon;

    public Toast(String frame, String message, ItemType icon) {
        this.frame = frame;
        this.message = message;
        this.icon = icon;
    }

    public String toString() {
        return frame + " toast with message \"" + message + "\" and icon " + icon;
    }

    public String getName() {
        return "toast(" + frame + ":" + message + ":" + icon + ")";
    }

    public void send(Player player) {
        ToastNotification notification = new ToastNotification(new ItemStack(this.icon.getMaterial()),
                this.message,
                AdvancementDisplay.AdvancementFrame.parse(frame));
        notification.send(player);
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFrame() {
        return this.frame;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public ItemType getIcon() {
        return this.icon;
    }

    public void setIcon(ItemType icon) {
        this.icon = icon;
    }

}
