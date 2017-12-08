package us._donut_.skuniversal.cannons;

import at.pavlov.cannons.Cannons;
import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.event.Event;
import javax.annotation.Nullable;
import java.util.UUID;

public class CannonTemp extends SimpleExpression<Number> {

    private Expression<String> id;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        id = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "the temperature of the cannon with ID " + id.getSingle(e);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        if (id.getSingle(e) != null) {
            if (Cannons.getPlugin().getCannon(UUID.fromString(id.getSingle(e))) != null) {
                return new Number[]{ Cannons.getPlugin().getCannon(UUID.fromString(id.getSingle(e))).getTemperature() };
            } else {
                return null;
            }
        } else {
            Skript.error("Must provide a string, please refer to the syntax");
            return null;
        }
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        Number newTemp = (Number) delta[0];
        if (mode == Changer.ChangeMode.SET) {
            Cannons.getPlugin().getCannon(UUID.fromString(id.getSingle(e))).setTemperature(newTemp.doubleValue());
        } else if (mode == Changer.ChangeMode.ADD) {
            Cannons.getPlugin().getCannon(UUID.fromString(id.getSingle(e))).setTemperature(Cannons.getPlugin().getCannon(UUID.fromString(id.getSingle(e))).getTemperature() + newTemp.doubleValue());
        } else if (mode == Changer.ChangeMode.REMOVE) {
            Cannons.getPlugin().getCannon(UUID.fromString(id.getSingle(e))).setTemperature(Cannons.getPlugin().getCannon(UUID.fromString(id.getSingle(e))).getTemperature() - newTemp.doubleValue());
        }
    }
    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.ADD || mode == Changer.ChangeMode.REMOVE) {
            return CollectionUtils.array(Number.class);
        }
        return null;
    }

}