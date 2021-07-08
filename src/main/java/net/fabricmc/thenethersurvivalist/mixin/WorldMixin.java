package net.fabricmc.thenethersurvivalist.mixin;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldProperties;
import net.minecraft.world.gen.feature.StructureFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerWorld.class)
public abstract class WorldMixin{

    @Inject(method = "getSpawnPos", at = @At(value = "HEAD"), cancellable = true)
    private void getSpawnPos(CallbackInfoReturnable<BlockPos> info) {
        ServerWorld world = (ServerWorld)(Object)this;
        WorldProperties properties = ((ServerWorld)(Object)this).getLevelProperties();
        BlockPos blockPos = new BlockPos(properties.getSpawnX(), properties.getSpawnY(), properties.getSpawnZ());
        BlockPos pos = ((ServerWorld)(Object)this).getChunkManager().getChunkGenerator().locateStructure(world, StructureFeature.RUINED_PORTAL, blockPos, 1000000, false);
        info.setReturnValue(pos);
    }
}