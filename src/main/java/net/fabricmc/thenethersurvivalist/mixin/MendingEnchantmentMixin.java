package net.fabricmc.thenethersurvivalist.mixin;

import net.fabricmc.thenethersurvivalist.TheNetherSurvivalistSettings;
import net.minecraft.enchantment.MendingEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MendingEnchantment.class)
public class MendingEnchantmentMixin {
	@Inject(at = @At("HEAD"), method = "isTreasure()Z", cancellable = true)
	private void isTreasureMixin(CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(!TheNetherSurvivalistSettings.MendingFromEnchanting);
	}
}
