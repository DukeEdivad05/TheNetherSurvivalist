package thenethersurvivalist.mixin;

import thenethersurvivalist.TheNetherSurvivalistSettings;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.LuckEnchantment;
import net.minecraft.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LuckEnchantment.class)
public class LuckEnchantmentMixin extends Enchantment {
    protected LuckEnchantmentMixin(Enchantment.Rarity rarity, EnchantmentTarget enchantmentTarget, EquipmentSlot... equipmentSlots) {
        super(rarity, enchantmentTarget, equipmentSlots);
    }
    public boolean isTreasure() {
        return TheNetherSurvivalistSettings.RemovedAcquaticEnchantments;
    }
}
