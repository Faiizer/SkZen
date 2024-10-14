package com.faiizer.skzen.elements.toast.expression;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import com.faiizer.skzen.SkZen;
import com.faiizer.skzen.elements.toast.type.Toast;
import org.bukkit.event.Event;

import java.util.Arrays;
import java.util.List;

public class ExprToastFrame extends SimplePropertyExpression<Toast, String> {

    @Override
    protected String getPropertyName() {
        return "toast frame";
    }

    @Override
    public String convert(Toast toast) {
        return toast.getFrame();
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
                String newFrame = ((String) delta[0]).toLowerCase();
                List<String> frames = Arrays.asList("task", "goal", "challenge");
                if (frames.contains(newFrame)) {
                    toast.setFrame(newFrame);
                } else {
                    SkZen.getPlugin().getLogger().warning("A Toast Frame must be 'task', 'goal' or 'challenge'");
                }
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