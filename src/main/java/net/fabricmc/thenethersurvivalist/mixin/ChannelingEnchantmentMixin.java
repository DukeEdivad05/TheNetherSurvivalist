package net.fabricmc.thenethersurvivalist.mixin;

import net.fabricmc.thenethersurvivalist.TheNetherSurvivalistSettings;
import net.minecraft.enchantment.ChannelingEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ChannelingEnchantment.class)
public class ChannelingEnchantmentMixin extends Enchantment {
    public ChannelingEnchantmentMixin(Enchantment.Rarity weight, EquipmentSlot... slotTypes) {
        super(weight, EnchantmentTarget.TRIDENT, slotTypes);
    }

    public boolean isTreasure() {
        return TheNetherSurvivalistSettings.RemovedAcquaticEnchantments;
    }
}
