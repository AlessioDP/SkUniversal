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
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("Shopkeepers - All Shopkeepers")
@Description("Returns the names of all shopkeepers.")
@Examples({"send \"%the IDs of all shopkeepers\""})
public class ExprKeepers extends SimpleExpression<Integer> {

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "all the shopkeepers";
    }

    @Override
    @Nullable
    protected Integer[] get(Event e) {
        return ShopkeepersAPI.getShopkeeperRegistry().getAllShopkeepers().stream().map(Shopkeeper::getId).toArray(Integer[]::new);
    }
}
