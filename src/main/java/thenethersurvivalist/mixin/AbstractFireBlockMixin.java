package thenethersurvivalist.mixin;

import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.dimension.AreaHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import thenethersurvivalist.TheNetherSurvivalistSettings;

import java.util.Optional;

import static thenethersurvivalist.events.TheNetherSurvivalistEvents.PLAYER_TRY_SWITCH_ON_A_PORTAL;

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

	/**
	 * @author DukeEdivad05
	 * @reason UnlockAdvancements
	 */
	@Overwrite
	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
		if (!oldState.isOf(state.getBlock())) {

			Optional<AreaHelper> optional = AreaHelper.method_30485(world, pos, Direction.Axis.X);
			if (optional.isPresent()) {
				if (!world.isClient && PLAYER_TRY_SWITCH_ON_A_PORTAL.isNeeded()) {
					PLAYER_TRY_SWITCH_ON_A_PORTAL.onTrySwitchOn(world, pos);
				}
				if (method_30366(world)) {
					((AreaHelper) optional.get()).createPortal();
					return;
				}
			}


			if (!state.canPlaceAt(world, pos)) {
				world.removeBlock(pos, false);
			}

		}
	}

	/**
	 * @author DukeEdivad05
	 * @reason UnlockAdvancements
	 */
	@Overwrite
	private static boolean method_30033(World world, BlockPos blockPos, Direction direction) {
		boolean end = false;
		BlockPos.Mutable mutable = blockPos.mutableCopy();
		boolean bl = false;
		Direction[] var5 = Direction.values();
		int var6 = var5.length;

		for(int var7 = 0; var7 < var6; ++var7) {
			Direction direction2 = var5[var7];
			if (world.getBlockState(mutable.set(blockPos).move(direction2)).isOf(Blocks.OBSIDIAN)) {
				bl = true;
				break;
			}
		}

		if (!bl) {
			end = false;
		} else {
			Direction.Axis axis = direction.getAxis().isHorizontal() ? direction.rotateYCounterclockwise().getAxis() : Direction.Type.HORIZONTAL.randomAxis(world.random);
			if (AreaHelper.method_30485(world, blockPos, axis).isPresent()) {
				if (!world.isClient && PLAYER_TRY_SWITCH_ON_A_PORTAL.isNeeded()) {
					PLAYER_TRY_SWITCH_ON_A_PORTAL.onTrySwitchOn(world, blockPos);
				}
				if (method_30366(world)) {
					end = true;
				}
			} else {
				end = false;
			}
		}
		return end ;
	}
}