package thenethersurvivalist.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import thenethersurvivalist.TheNetherSurvivalistSettings;

public class LavaAffinityEnchantment extends Enchantment {
    public LavaAffinityEnchantment(Enchantment.Rarity weight, EquipmentSlot... slotTypes) {
        super(weight, EnchantmentTarget.ARMOR_HEAD, slotTypes);
    }

    public int getMinPower(int level) {
        return 1;
    }

    public int getMaxPower(int level) {
        return this.getMinPower(level) + 40;
    }

    public int getMaxLevel() {
        return 1;
    }

    public boolean isTreasure() {
        return !TheNetherSurvivalistSettings.LavaAffinity;
    }
}
