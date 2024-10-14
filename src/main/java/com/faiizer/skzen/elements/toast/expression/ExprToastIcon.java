package com.faiizer.skzen.elements.toast.expression;

import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import com.faiizer.skzen.elements.toast.type.Toast;
import org.bukkit.event.Event;

public class ExprToastIcon extends SimplePropertyExpression<Toast, ItemType> {

    @Override
    protected String getPropertyName() {
        return "toast icon";
    }

    @Override
    public ItemType convert(Toast toast) {
        return toast.getIcon();
    }

    @Override
    public Class<? extends ItemType> getReturnType() {
        return ItemType.class;
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET && delta != null && delta.length > 0) {
            Toast toast = getExpr().getSingle(e);
            if (toast != null) {
                ItemType newIcon = (ItemType) delta[0];
                toast.setIcon(newIcon);
            }
        }
    }

    @Override
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return new Class[]{ItemType.class};
        }
        return null;
    }
}