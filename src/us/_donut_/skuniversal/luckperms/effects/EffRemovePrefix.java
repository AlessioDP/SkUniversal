package us._donut_.skuniversal.luckperms.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.lucko.luckperms.api.User;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us._donut_.skuniversal.luckperms.LuckPermsHook.*;

@Name("LuckPerms - Remove Prefix")
@Description("Removes prefix to player.")
@Examples({"remove \"[Owner]\" with priority 100 from the prefixes of player"})
public class EffRemovePrefix extends Effect {

    static {
        Skript.registerEffect(EffRemovePrefix.class, "remove %string% with [a] priority [of] %number% from [the] [LuckPerm[s]] prefixes of %player%");
    }

    private Expression<String> prefix;
    private Expression<Number> priority;
    private Expression<Player> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult p) {
        prefix = (Expression<String>) e[0];
        priority = (Expression<Number>) e[1];
        player = (Expression<Player>) e[2];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "remove prefix " + prefix.toString(e, b) + " with priority " + priority.toString(e, b) + " from player " + player.toString(e, b);
    }

    @Override
    protected void execute(Event e) {
        if (prefix.getSingle(e) == null || priority.getSingle(e) == null || player.getSingle(e) == null) return;
        User user = luckpermsAPI.getUser(player.getSingle(e).getUniqueId());
        if (user == null) return;
        user.unsetPermission(luckpermsAPI.getNodeFactory().makePrefixNode(priority.getSingle(e).intValue(), prefix.getSingle(e)).build());
        luckpermsAPI.getUserManager().saveUser(user);
    }

}