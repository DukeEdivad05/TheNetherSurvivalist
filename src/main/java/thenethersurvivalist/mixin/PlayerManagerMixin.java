package thenethersurvivalist.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import thenethersurvivalist.Load;

@Mixin(PlayerManager.class)
public abstract class PlayerManagerMixin {

    @Shadow public abstract MinecraftServer getServer();

    @Redirect(method = "createPlayer", at = @At(value = "INVOKE", target = "net/minecraft/server/MinecraftServer.getOverworld()Lnet/minecraft/server/world/ServerWorld;"))
    protected ServerWorld replaceStartWorld(MinecraftServer server) {
        if (server.getGameRules().getBoolean(Load.NETHER_SPAWN)) {
            return server.getWorld(World.NETHER);
        } else {
            return server.getWorld(World.OVERWORLD);
        }
    }

    @Redirect(method = "onPlayerConnect", at = @At(value = "FIELD", target = "Lnet/minecraft/world/World;OVERWORLD:Lnet/minecraft/util/registry/RegistryKey;"))
    public RegistryKey<World> onPlayerConnect() {
        MinecraftServer server = this.getServer();
        if (server.getGameRules().getBoolean(Load.NETHER_SPAWN)) {
            return World.NETHER;
        } else {
            return World.OVERWORLD;
        }
    }

    @Redirect(method = "respawnPlayer", at = @At(value = "INVOKE", target = "net/minecraft/server/MinecraftServer.getOverworld()Lnet/minecraft/server/world/ServerWorld;"))
    protected ServerWorld respawnPlayer(MinecraftServer server) {
        if (server.getGameRules().getBoolean(Load.NETHER_SPAWN)) {
            return server.getWorld(World.NETHER);
        } else {
            return server.getWorld(World.OVERWORLD);
        }
    }

}
