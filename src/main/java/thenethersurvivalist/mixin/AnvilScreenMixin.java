package thenethersurvivalist.mixin;

import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import thenethersurvivalist.TheNetherSurvivalistSettings;

@Mixin(AnvilScreen.class)
public class AnvilScreenMixin {
    @ModifyConstant(method = "drawForeground", constant = @Constant(intValue = 40, ordinal = 0))
    private int modifyInt(int input) {
        if (TheNetherSurvivalistSettings.FairAnvil) {
            return 999999999;
        } else {
            return 40;
        }
    }
}
