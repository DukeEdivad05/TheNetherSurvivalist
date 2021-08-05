package thenethersurvivalist.events;

import carpet.CarpetServer;
import carpet.script.CarpetEventServer.Event;
import carpet.script.value.BlockValue;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Collections;

public class TheNetherSurvivalistEvents extends Event {
    public static TheNetherSurvivalistEvents PLAYER_TRY_SWITCH_ON_A_PORTAL = new TheNetherSurvivalistEvents("player_try_switch_on_a_portal",1, false) {
        public void onTrySwitchOn(World world, BlockPos blockPos) {
            handler.call( () ->
                    Collections.singletonList(
                        new BlockValue(null, (ServerWorld) world, blockPos)
                ), () -> CarpetServer.minecraft_server.getCommandSource().withWorld((ServerWorld) world)
            );
        }
    };

    public static TheNetherSurvivalistEvents BEDROCK_BREAK = new TheNetherSurvivalistEvents("bedrock_break", 1, true) {
        @Override
        public void onBedrockBreak(BlockState state, BlockPos pos, ServerWorld world) {
            handler.call( () -> Collections.singletonList(
                    new BlockValue(state, world, pos)
            ), () -> CarpetServer.minecraft_server.getCommandSource());

        }
    };

    public static void noop() { }

    public void onTrySwitchOn(World world, BlockPos blockPos) { }
    public void onBedrockBreak(BlockState state, BlockPos pos, ServerWorld world) { }


    public TheNetherSurvivalistEvents(String name, int reqArgs, boolean isGlobalOnly) {
        super(name, reqArgs, isGlobalOnly);
    }


}
