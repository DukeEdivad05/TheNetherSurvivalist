package net.fabricmc.thenethersurvivalist.mixin;

import net.fabricmc.thenethersurvivalist.TheNetherSurvivalistSettings;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.LoyaltyEnchantment;
import net.minecraft.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LoyaltyEnchantment.class)
public class LoyaltyEnchantmentMixin extends Enchantment {
    public LoyaltyEnchantmentMixin(Enchantment.Rarity weight, EquipmentSlot... slotTypes) {
        super(weight, EnchantmentTarget.TRIDENT, slotTypes);
    }
    public boolean isTreasure() {
        return TheNetherSurvivalistSettings.RemovedAcquaticEnchantments;
    }
}
