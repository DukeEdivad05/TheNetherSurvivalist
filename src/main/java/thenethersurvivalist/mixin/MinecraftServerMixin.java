package thenethersurvivalist.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.WorldGenerationProgressListener;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.SaveProperties;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thenethersurvivalist.utils.SpawnPortal;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {

    @Shadow
    public abstract ServerWorld getWorld(RegistryKey<World> key);
    @Shadow
    public abstract PlayerManager getPlayerManager();

    @Shadow public abstract SaveProperties getSaveProperties();

    @Redirect(method = "prepareStartRegion", at = @At(value = "INVOKE", target = "net/minecraft/server/MinecraftServer.getOverworld()Lnet/minecraft/server/world/ServerWorld;"))
    protected ServerWorld replaceStartWorld(MinecraftServer server) {
        return server.getWorld(World.NETHER);
    }

    @Inject(method = "createWorlds", at = @At(value = "RETURN"))
    protected void createWorlds(WorldGenerationProgressListener worldGenerationProgressListener, CallbackInfo info) {
        getPlayerManager().setMainWorld(getWorld(World.NETHER));
        SpawnPortal.setupSpawn(getWorld(World.NETHER), this.getSaveProperties().getMainWorldProperties(), this.getSaveProperties().getGeneratorOptions().hasBonusChest(), this.getSaveProperties().getGeneratorOptions().isDebugWorld(), true);
    }
}
