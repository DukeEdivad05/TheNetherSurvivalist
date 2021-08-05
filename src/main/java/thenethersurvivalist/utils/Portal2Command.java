package thenethersurvivalist.utils;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import static thenethersurvivalist.utils.PortalCommand.place;

public class Portal2Command {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                CommandManager.literal("portal2")
                        .requires((source -> source.hasPermissionLevel(2)))
                        .executes((c) -> execute((ServerCommandSource)c.getSource(), new BlockPos(c.getSource().getPosition()), "Uno"))
        );
    }

    private static int execute(ServerCommandSource source, BlockPos pos, String string) {
        ServerWorld world = source.getWorld();
        place(world, pos, 2);
        return 1;
    }
}
