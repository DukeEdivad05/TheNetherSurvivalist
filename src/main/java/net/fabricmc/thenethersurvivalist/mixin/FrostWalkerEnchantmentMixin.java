package net.fabricmc.thenethersurvivalist.mixin;

import net.fabricmc.thenethersurvivalist.Load;
import net.minecraft.block.*;
import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Iterator;

@Mixin(FrostWalkerEnchantment.class)
public class FrostWalkerEnchantmentMixin {
    /**
     * @author DukeEdivad05
     * @reason ObsidianWalking
     */
    @Overwrite
    public static void freezeWater(LivingEntity entity, World world, BlockPos blockPos, int level) {
        if (entity.isOnGround()) {
            BlockState blockState = Load.OBSIDIAN_WALKER_BLOCK.getDefaultState();
            float f = (float)Math.min(16, 2 + level);
            BlockPos.Mutable mutable = new BlockPos.Mutable();
            Iterator var7 = BlockPos.iterate(blockPos.add((double)(-f), -1.0D, (double)(-f)), blockPos.add((double)f, -1.0D, (double)f)).iterator();

            while(var7.hasNext()) {
                BlockPos blockPos2 = (BlockPos)var7.next();
                if (blockPos2.isWithinDistance(entity.getPos(), (double)f)) {
                    mutable.set(blockPos2.getX(), blockPos2.getY() + 1, blockPos2.getZ());
                    BlockState blockState2 = world.getBlockState(mutable);
                    if (blockState2.isAir()) {
                        BlockState blockState3 = world.getBlockState(blockPos2);
                        if (blockState3.getMaterial() == Material.LAVA && (Integer)blockState3.get(FluidBlock.LEVEL) == 0 && blockState.canPlaceAt(world, blockPos2) && world.canPlace(blockState, blockPos2, ShapeContext.absent())) {
                            world.setBlockState(blockPos2, blockState);
                            world.getBlockTickScheduler().schedule(blockPos2, Load.OBSIDIAN_WALKER_BLOCK, MathHelper.nextInt(entity.getRandom(), 60, 120));
                        }
                    }
                }
            }

        }
    }
}
