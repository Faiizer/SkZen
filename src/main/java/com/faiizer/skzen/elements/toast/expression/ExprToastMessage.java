package com.faiizer.skzen.elements.toast.expression;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import com.faiizer.skzen.elements.toast.type.Toast;
import org.bukkit.event.Event;

public class ExprToastMessage extends SimplePropertyExpression<Toast, String> {

    @Override
    protected String getPropertyName() {
        return "toast message";
    }

    @Override
    public String convert(Toast toast) {
        return toast.getMessage();
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET && delta != null && delta.length > 0) {
            Toast toast = getExpr().getSingle(e);
            if (toast != null) {
                String newMessage = (String) delta[0];
                toast.setMessage(newMessage);
            }
        }
    }

    @Override
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return new Class[]{String.class};
        }
        return null;
    }
}