package thenethersurvivalist.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PistonBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.block.FacingBlock.FACING;
import static thenethersurvivalist.events.TheNetherSurvivalistEvents.BEDROCK_BREAK;

@Mixin(PistonBlock.class)
public class PistonBlockMixin {
    @Inject(
            method = "onSyncedBlockEvent(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;II)Z",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;removeBlock(Lnet/minecraft/util/math/BlockPos;Z)Z"
            )
    )
    void print(BlockState state, World world, BlockPos pos, int type, int data, CallbackInfoReturnable<Boolean> cir){
        Direction direction = state.get(FACING);
        BlockPos pos1 = pos.offset(direction);
        BlockState blockState = world.getBlockState(pos1);
        if(!world.isClient() && blockState.isOf(Blocks.BEDROCK) && BEDROCK_BREAK.isNeeded())
            BEDROCK_BREAK.onBedrockBreak(blockState, pos1, (ServerWorld) world);
    }
}
