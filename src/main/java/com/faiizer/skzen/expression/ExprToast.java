package com.faiizer.skzen.expression;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.faiizer.skzen.toast.Toast;
import org.bukkit.Material;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ExprToast extends SimpleExpression<Toast> {

    private Expression<String> message;
    private Expression<Material> icon;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Toast> getReturnType() {
        return Toast.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "toast";
    }

    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        this.message = (Expression<String>) expr[0];
        this.icon = (Expression<Material>) expr[1];
        return true;
    }

    @Nullable
    @Override
    protected Toast[] get(Event e) {
        String msg = this.message.getSingle(e);
        Material icon = this.icon.getSingle(e);

        return new Toast[]{new Toast(msg, icon)};
    }
}