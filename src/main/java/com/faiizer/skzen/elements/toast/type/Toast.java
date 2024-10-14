package com.faiizer.skzen.elements.toast.type;

import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import eu.endercentral.crazy_advancements.advancement.AdvancementDisplay;
import eu.endercentral.crazy_advancements.advancement.ToastNotification;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.nullness.qual.Nullable;

public class Toast {

    static {
        if (Classes.getExactClassInfo(Toast.class) == null) {
            Classes.registerClass(new ClassInfo<Toast>(Toast.class, "toast").user("toast").name("toast").parser(new Parser<Toast>() {

                @Override
                @Nullable
                public Toast parse(String arg0, ParseContext arg1) {
                    if (!arg0.startsWith("a new ") || !arg0.contains("toast with message") || !arg0.contains("and icon")) {
                        return null;
                    }

                    String[] parts = arg0.split(" toast with message ");
                    if (parts.length != 2) {
                        return null;
                    }

                    String typePart = parts[0].replace("a new ", "").trim();
                    String[] messageAndIcon = parts[1].split(" and icon ");

                    if (messageAndIcon.length != 2) {
                        return null;
                    }

                    String messagePart = messageAndIcon[0].replace("\"", "").trim();
                    String iconPart = messageAndIcon[1].trim();

                    ItemType itemType;
                    try {
                        itemType = new ItemType(Material.valueOf(iconPart.toUpperCase().replace(" ", "_")));
                    } catch (IllegalArgumentException e) {
                        return null;
                    }

                    return new Toast(typePart, messagePart, itemType);
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
        }
    }

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
