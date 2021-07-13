package thenethersurvivalist.mixin;

import thenethersurvivalist.utils.FlyCommand;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.world.GameMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GameMode.class)
public class GameModeMixin {

    @Shadow
    public boolean isBlockBreakingRestricted() {
        return false;
    }
    /**
     * @author DukeEdivad05
     * @reason FlyCommand
     */
    @Overwrite
    public void setAbilities(PlayerAbilities abilities) {
        if (((GameMode) (Object)this) == GameMode.CREATIVE) {
            abilities.allowFlying = true;
            abilities.creativeMode = true;
            abilities.invulnerable = true;
        } else if (((GameMode) (Object)this) == GameMode.SPECTATOR) {
            abilities.allowFlying = true;
            abilities.creativeMode = false;
            abilities.invulnerable = true;
            abilities.flying = true;
        } else {
            abilities.allowFlying = FlyCommand.Fly;
            abilities.creativeMode = false;
            abilities.invulnerable = false;
            abilities.flying = false;
        }

        abilities.allowModifyWorld = !this.isBlockBreakingRestricted();
    }
}
