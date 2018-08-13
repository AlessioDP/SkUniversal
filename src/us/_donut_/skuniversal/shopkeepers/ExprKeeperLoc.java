package us._donut_.skuniversal.shopkeepers;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.nisovin.shopkeepers.api.ShopkeepersAPI;
import com.nisovin.shopkeepers.api.shopkeeper.Shopkeeper;
import org.bukkit.Location;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("Shopkeepers - Shopkeeper Location")
@Description("Returns the location of a shopkeeper.")
@Examples({"send \"%the location of the shopkeeper with id 1\""})
public class ExprKeeperLoc extends SimpleExpression<Location> {

    private Expression<Integer> id;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Location> getReturnType() {
        return Location.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        id = (Expression<Integer>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "location of shopkeeper with ID " + id.toString(e, b);
    }

    @Override
    @Nullable
    protected Location[] get(Event e) {
        Shopkeeper shopkeeper = ShopkeepersAPI.getShopkeeperRegistry().getShopkeeperById(id.getSingle(e));
        return new Location[]{shopkeeper == null ? null : shopkeeper.getLocation()};
    }
}