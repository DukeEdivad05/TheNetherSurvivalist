package net.fabricmc.thenethersurvivalist.mixin;

import net.fabricmc.thenethersurvivalist.TheNetherSurvivalistSettings;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.LureEnchantment;
import net.minecraft.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LureEnchantment.class)
public class LureEnchantmentMixin extends Enchantment {
    protected LureEnchantmentMixin(Enchantment.Rarity rarity, EnchantmentTarget enchantmentTarget, EquipmentSlot... equipmentSlots) {
        super(rarity, enchantmentTarget, equipmentSlots);
    }
    public boolean isTreasure() {
        return TheNetherSurvivalistSettings.RemovedAcquaticEnchantments;
    }
}
