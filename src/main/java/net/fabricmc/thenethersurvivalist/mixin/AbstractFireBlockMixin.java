package net.fabricmc.thenethersurvivalist.mixin;

import net.fabricmc.thenethersurvivalist.TheNetherSurvivalistSettings;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(AbstractFireBlock.class)
public class AbstractFireBlockMixin {
	/**
	 * @author Edivad05
	 * @reason NoNetherPortal
	 */
	@Overwrite
	private static boolean method_30366(World world) {
		return !TheNetherSurvivalistSettings.NoNetherPortal;
	}
}