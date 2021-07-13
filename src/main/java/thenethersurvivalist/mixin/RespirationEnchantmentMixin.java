package thenethersurvivalist.mixin;

import thenethersurvivalist.TheNetherSurvivalistSettings;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.RespirationEnchantment;
import net.minecraft.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(RespirationEnchantment.class)
public class RespirationEnchantmentMixin extends Enchantment {
    public RespirationEnchantmentMixin(Enchantment.Rarity weight, EquipmentSlot... slotTypes) {
        super(weight, EnchantmentTarget.ARMOR_HEAD, slotTypes);
    }
    public boolean isTreasure() {
        return TheNetherSurvivalistSettings.RemovedAcquaticEnchantments;
    }
}
