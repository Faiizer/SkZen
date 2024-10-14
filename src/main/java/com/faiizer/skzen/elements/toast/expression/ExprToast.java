package com.faiizer.skzen.elements.toast.expression;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.faiizer.skzen.elements.toast.type.Toast;
import eu.endercentral.crazy_advancements.advancement.AdvancementDisplay;
import org.bukkit.Material;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ExprToast extends SimpleExpression<Toast> {

    static {
        Skript.registerExpression(ExprToast.class, Toast.class, ExpressionType.SIMPLE,
                "[a] [new] (task|goal|challenge) toast with message \"%string%\" and icon %itemtype%");
    }

    private String frame;
    private Expression<String> message;
    private Expression<ItemType> icon;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Toast> getReturnType() {
        return Toast.class;
    }

    @Override
    public String toString(Event event, boolean debug) {
        return frame + " toast with message \"" + message.toString(event, debug) + "\" and icon " + icon.toString(event, debug);
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        frame = parseResult.expr.substring(0, parseResult.expr.indexOf(" "));
        message = (Expression<String>) exprs[0];
        icon = (Expression<ItemType>) exprs[1];
        return true;
    }

    @Override
    @Nullable
    protected Toast[] get(Event event) {
        String message = this.message.getSingle(event);
        ItemType icon = this.icon.getSingle(event);

        return new Toast[]{new Toast(frame, message, icon)};
    }
}