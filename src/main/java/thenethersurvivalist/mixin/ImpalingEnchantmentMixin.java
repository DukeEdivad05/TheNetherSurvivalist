package thenethersurvivalist.mixin;

import thenethersurvivalist.TheNetherSurvivalistSettings;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.ImpalingEnchantment;
import net.minecraft.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ImpalingEnchantment.class)
public class ImpalingEnchantmentMixin extends Enchantment {
    public ImpalingEnchantmentMixin(Enchantment.Rarity weight, EquipmentSlot... slotTypes) {
        super(weight, EnchantmentTarget.TRIDENT, slotTypes);
    }
    public boolean isTreasure() {
        return TheNetherSurvivalistSettings.RemovedAcquaticEnchantments;
    }
}
