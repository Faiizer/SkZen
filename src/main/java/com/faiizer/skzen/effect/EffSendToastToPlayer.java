package com.faiizer.skzen.effect;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.faiizer.skzen.toast.Toast;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffSendToastToPlayer extends Effect {

    private Expression<Toast> toast;
    private Expression<Player> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean, SkriptParser.ParseResult paramParseResult) {
        toast = (Expression<Toast>) expr[0];
        player = (Expression<Player>) expr[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "send toast to player";
    }

    @Override
    protected void execute(Event e) {
        toast.getSingle(e).send(player.getSingle(e));
        Bukkit.broadcastMessage(String.valueOf(toast));
    }
}