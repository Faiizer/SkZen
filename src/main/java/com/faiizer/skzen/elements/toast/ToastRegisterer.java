package com.faiizer.skzen.elements.toast;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import com.faiizer.skzen.elements.toast.effect.EffSendToastToPlayer;
import com.faiizer.skzen.elements.toast.expression.ExprToast;
import com.faiizer.skzen.elements.toast.expression.ExprToastFrame;
import com.faiizer.skzen.elements.toast.expression.ExprToastIcon;
import com.faiizer.skzen.elements.toast.expression.ExprToastMessage;
import com.faiizer.skzen.elements.toast.type.Toast;
import org.bukkit.Material;
import org.checkerframework.checker.nullness.qual.Nullable;

public class ToastRegisterer {

    public ToastRegisterer() {
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
        Skript.registerExpression(ExprToast.class, Toast.class, ExpressionType.SIMPLE, "a new (task|goal|challenge) toast with message \"%string%\" and icon %itemtype%");
        Skript.registerExpression(ExprToastMessage.class, String.class, ExpressionType.PROPERTY,
                "message of %toast%",
                "%toast%'s message");
        Skript.registerExpression(ExprToastFrame.class, String.class, ExpressionType.PROPERTY,
                "frame of %toast%",
                "%toast%'s frame");
        Skript.registerExpression(ExprToastIcon.class, ItemType.class, ExpressionType.PROPERTY,
                "icon of %toast%",
                "%toast%'s icon");
        Skript.registerEffect(EffSendToastToPlayer.class, "toast %toast% to %player%");
    }

}
