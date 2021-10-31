package thenethersurvivalist.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.WorldGenerationProgressListener;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.SaveProperties;
import net.minecraft.world.World;
import net.minecraft.world.level.ServerWorldProperties;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thenethersurvivalist.Load;
import thenethersurvivalist.utils.SpawnPortal;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {

    @Shadow
    public abstract ServerWorld getWorld(RegistryKey<World> key);
    @Shadow
    public abstract PlayerManager getPlayerManager();

    @Shadow
    public abstract SaveProperties getSaveProperties();

    @Shadow
    private void setToDebugWorldProperties(SaveProperties properties) {}

    @Shadow @Final private static Logger LOGGER;

    @Redirect(method = "prepareStartRegion", at = @At(value = "INVOKE", target = "net/minecraft/server/MinecraftServer.getOverworld()Lnet/minecraft/server/world/ServerWorld;"))
    protected ServerWorld replaceStartWorld(MinecraftServer server) {
        if (server.getGameRules().getBoolean(Load.NETHER_SPAWN)) {
            return server.getWorld(World.NETHER);
        } else {
            return server.getWorld(World.OVERWORLD);
        }
    }

    @Redirect(method = "createWorlds", at =  @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/ServerWorldProperties;setInitialized(Z)V"
    ))
    private void SetFalse(ServerWorldProperties serverWorldProperties, boolean initialized) {
        if (serverWorldProperties.getGameRules().getBoolean(Load.NETHER_SPAWN)) {
            LOGGER.info("Portal Creation");
        } else {
            serverWorldProperties.setInitialized(initialized);
        }
    }

    @Inject(method = "createWorlds", at = @At(value = "RETURN"))
    protected void createWorlds(WorldGenerationProgressListener worldGenerationProgressListener, CallbackInfo info) {
        MinecraftServer server = (MinecraftServer) (Object) this;
        if (server.getGameRules().getBoolean(Load.NETHER_SPAWN)) {
            ServerWorld serverWorld = getWorld(World.NETHER);
            getPlayerManager().setMainWorld(serverWorld);
            boolean bl = this.getSaveProperties().getGeneratorOptions().isDebugWorld();
            ServerWorldProperties serverWorldProperties = this.getSaveProperties().getMainWorldProperties();
            if (!serverWorldProperties.isInitialized()) {
                try {
                    SpawnPortal.setupSpawn(serverWorld, serverWorldProperties, this.getSaveProperties().getGeneratorOptions().hasBonusChest(), bl, true);
                    serverWorldProperties.setInitialized(true);
                    if (bl) {
                        this.setToDebugWorldProperties(this.getSaveProperties());
                    }
                } catch (Throwable var26) {
                    CrashReport crashReport = CrashReport.create(var26, "Exception initializing level");

                    try {
                        serverWorld.addDetailsToCrashReport(crashReport);
                    } catch (Throwable var25) {
                    }

                    throw new CrashException(crashReport);
                }

                serverWorldProperties.setInitialized(true);
            }
        }
    }
}
