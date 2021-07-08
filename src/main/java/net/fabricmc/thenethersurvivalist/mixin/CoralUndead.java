package net.fabricmc.thenethersurvivalist.mixin;

import net.fabricmc.thenethersurvivalist.TheNetherSurvivalistSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CoralParentBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CoralParentBlock.class)
public class CoralUndead {
    @Inject(at = @At("HEAD"), method = "isInWater", cancellable = true)
    private static void isInWaterMixin(BlockState state, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        BlockState block2 = world.getBlockState(pos.down());
        if (block2.isOf(Blocks.CRIMSON_NYLIUM) || block2.isOf(Blocks.WARPED_NYLIUM) && TheNetherSurvivalistSettings.undeadCoral) {
            cir.setReturnValue(true);
        }
    }
}
