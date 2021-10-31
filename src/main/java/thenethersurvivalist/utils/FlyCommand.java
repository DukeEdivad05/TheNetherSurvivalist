package thenethersurvivalist.utils;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import thenethersurvivalist.TheNetherSurvivalistSettings;

import java.util.Collection;

import static net.minecraft.command.argument.EntityArgumentType.getPlayers;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class FlyCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("fly")
                .requires(source -> TheNetherSurvivalistSettings.FlyCommand)
                .then(argument("targets", EntityArgumentType.players())
                        .executes(ctx -> fly(ctx.getSource(), getPlayers(ctx, "targets"))))
                .executes(ctx -> fly(ctx.getSource(), null)));
    }

    private static int fly(ServerCommandSource source, Collection<ServerPlayerEntity> targets) throws CommandSyntaxException {

        if(targets == null) {
            handlePlayers(source.getPlayer(), source);
        } else {
            targets.forEach(target -> handlePlayers(target, source));
        }

        return 1;
    }

    private static void handlePlayers(ServerPlayerEntity player, ServerCommandSource source) {
        final Text name = player.getName();

        if (!player.abilities.allowFlying) {
            player.abilities.allowFlying = true;
            player.sendMessage(new LiteralText("Fly On"), false);
        } else {
            player.abilities.allowFlying = false;
            player.abilities.flying = false;
            player.fallDistance = -(float) (player.getY() + 10.0D);
            player.sendMessage(new LiteralText("Fly Off"), false);
        }

        player.sendAbilitiesUpdate();
    }
}
