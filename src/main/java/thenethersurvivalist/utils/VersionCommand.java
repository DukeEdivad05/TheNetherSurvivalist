package thenethersurvivalist.utils;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import thenethersurvivalist.TheNetherSurvivalistExtension;

import java.util.Collection;
import java.util.Collections;

public class VersionCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> literalArgumentBuilder = CommandManager.literal("version")
                .requires((serverCommandSource) -> serverCommandSource.hasPermissionLevel(2));

        literalArgumentBuilder.executes(e -> print(Collections.singleton(e.getSource().getPlayer())));

        dispatcher.register(literalArgumentBuilder);

    }

    private static int print(Collection<ServerPlayerEntity> players) {
        players.forEach(serverPlayerEntity -> serverPlayerEntity.sendMessage(new LiteralText(TheNetherSurvivalistExtension.MOD_VERSION), false));
        return players.size();
    }
}
