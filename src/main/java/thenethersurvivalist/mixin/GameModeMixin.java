package thenethersurvivalist.mixin;

import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.world.GameMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import thenethersurvivalist.utils.FlyCommand;

@Mixin(GameMode.class)
public class GameModeMixin {

    public boolean Flying = false;

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
        Flying = abilities.flying;
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
            abilities.flying = Flying;
        }

        abilities.allowModifyWorld = !this.isBlockBreakingRestricted();
    }
}
