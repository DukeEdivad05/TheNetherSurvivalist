package thenethersurvivalist.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerManager.class)
public abstract class PlayerManagerMixin {

    @Redirect(method = "createPlayer", at = @At(value = "INVOKE", target = "net/minecraft/server/MinecraftServer.getOverworld()Lnet/minecraft/server/world/ServerWorld;"))
    protected ServerWorld replaceStartWorld(MinecraftServer server) {
        return server.getWorld(World.NETHER);
    }

    @Redirect(method = "onPlayerConnect", at = @At(value = "FIELD", target = "Lnet/minecraft/world/World;OVERWORLD:Lnet/minecraft/util/registry/RegistryKey;"))
    public RegistryKey<World> onPlayerConnect() {
        return World.NETHER;
    }

    @Redirect(method = "respawnPlayer", at = @At(value = "INVOKE", target = "net/minecraft/server/MinecraftServer.getOverworld()Lnet/minecraft/server/world/ServerWorld;"))
    protected ServerWorld respawnPlayer(MinecraftServer server) {
        return server.getWorld(World.NETHER);
    }

}
