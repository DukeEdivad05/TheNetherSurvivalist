package net.fabricmc.thenethersurvivalist.mixin;

import net.fabricmc.thenethersurvivalist.TheNetherSurvivalistSettings;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.screen.AnvilScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AnvilScreenHandler.class)
public class AnvilContainerMixin {

    @ModifyConstant(method = "updateResult", constant = @Constant(intValue = 40, ordinal = 2))
    private int modifyInt(int input) {
        return 999999999;
    }

    @Redirect(method = "updateResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/Enchantment;getMaxLevel()I"))
    private int getMaximumLevelProxy(Enchantment enchantment) {
        if (TheNetherSurvivalistSettings.anvilLimit == 0) {
            return enchantment.getMaxLevel();
        }  else {
            return TheNetherSurvivalistSettings.anvilLimit;
        }
    }
}