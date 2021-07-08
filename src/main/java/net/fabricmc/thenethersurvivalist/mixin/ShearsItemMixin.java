package net.fabricmc.thenethersurvivalist.mixin;

import net.fabricmc.thenethersurvivalist.TheNetherSurvivalistSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ShearsItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ShearsItem.class)
public class ShearsItemMixin extends Item {

    public ShearsItemMixin(Settings settings) {
        super(settings);
    }

    public int getEnchantability() {
        if(TheNetherSurvivalistSettings.EnchantableShears) {
            return 1;
        } else {
            return 0;
        }
    }
}
