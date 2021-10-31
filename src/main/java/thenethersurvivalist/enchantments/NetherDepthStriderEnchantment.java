package thenethersurvivalist.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import thenethersurvivalist.Load;
import thenethersurvivalist.TheNetherSurvivalistSettings;

public class NetherDepthStriderEnchantment extends Enchantment{
    public NetherDepthStriderEnchantment(Enchantment.Rarity weight, EquipmentSlot... slotTypes) {
        super(weight, EnchantmentTarget.ARMOR_FEET, slotTypes);
    }

    public int getMinPower(int level) {
        return level * 10;
    }

    public int getMaxPower(int level) {
        return this.getMinPower(level) + 15;
    }

    public int getMaxLevel() {
        return 3;
    }

    public boolean isTreasure() {
        return !TheNetherSurvivalistSettings.NetherDepthStrider;
    }

    public boolean canAccept(Enchantment other) {
        return super.canAccept(other) && other != Load.OBSIDIAN_WALKER;
    }
}
