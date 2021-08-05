package thenethersurvivalist.utils;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import thenethersurvivalist.TheNetherSurvivalistSettings;

import java.util.Collection;
import java.util.Collections;

public class FlyCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> literalArgumentBuilder = CommandManager.literal("fly")
                .requires((serverCommandSource) -> TheNetherSurvivalistSettings.FlyCommand);

        literalArgumentBuilder.executes(e -> toggle(Collections.singleton(e.getSource().getPlayer())));

        dispatcher.register(literalArgumentBuilder);

    }

    private static int toggle(Collection<ServerPlayerEntity> players) {
            players.forEach(serverPlayerEntity -> {
                if(!serverPlayerEntity.abilities.allowFlying){
                    serverPlayerEntity.abilities.allowFlying = true;
                    serverPlayerEntity.sendAbilitiesUpdate();
                    serverPlayerEntity.sendMessage(new LiteralText("Fly On"), false);
                } else if(serverPlayerEntity.abilities.allowFlying) {
                    serverPlayerEntity.abilities.allowFlying = false;
                    serverPlayerEntity.abilities.flying = false;
                    serverPlayerEntity.sendAbilitiesUpdate();
                    serverPlayerEntity.fallDistance = -(float) (serverPlayerEntity.getY() + 10.0D);
                    serverPlayerEntity.sendMessage(new LiteralText("Fly Off"), false);
                }
            });

        return players.size();
    }
}
