package net.fabricmc.thenethersurvivalist.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.NetherrackBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Iterator;
import java.util.Random;

@Mixin(NetherrackBlock.class)
public class NetherrackBlockMixin {
    /**
     * @author DukeEdivad05
     * @reason NyliumBoneMealSpread
     */
    @Overwrite
    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        if (!world.getBlockState(pos.up()).isTranslucent(world, pos)) {
            return false;
        } else {
            Iterator var5 = BlockPos.iterate(pos.add(-4, -4, -4), pos.add(4, 4, 4)).iterator();

            BlockPos blockPos;
            do {
                if (!var5.hasNext()) {
                    return false;
                }

                blockPos = (BlockPos)var5.next();
            } while(!world.getBlockState(blockPos).isIn(BlockTags.NYLIUM));

            return true;
        }
    }

    /**
     * @author DukeEdivad05
     * @reason NyliumBoneMealSpread
     */
    @Overwrite
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        Iterator varBone = BlockPos.iterate(pos.add(-1, -1, -1), pos.add(1, 1, 1)).iterator();

         while (varBone.hasNext()) {
             BlockPos pos1 = (BlockPos) varBone.next();
            boolean bl = false;
            boolean bl2 = false;
            Iterator var7 = BlockPos.iterate(pos1.add(-3, -3, -3), pos1.add(3, 3, 3)).iterator();

            while (var7.hasNext()) {
                BlockPos blockPos = (BlockPos) var7.next();
                BlockState blockState = world.getBlockState(blockPos);
                if (blockState.isOf(Blocks.WARPED_NYLIUM)) {
                    bl2 = true;
                }

                if (blockState.isOf(Blocks.CRIMSON_NYLIUM)) {
                    bl = true;
                }

                if (bl2 && bl) {
                    break;
                }
            }

            if (bl2 && bl && world.getBlockState(pos1).isOf(Blocks.NETHERRACK)) {
                world.setBlockState(pos1, random.nextBoolean() ? Blocks.WARPED_NYLIUM.getDefaultState() : Blocks.CRIMSON_NYLIUM.getDefaultState(), 3);
            } else if (bl2 && world.getBlockState(pos1).isOf(Blocks.NETHERRACK)) {
                world.setBlockState(pos1, Blocks.WARPED_NYLIUM.getDefaultState(), 3);
            } else if (bl && world.getBlockState(pos1).isOf(Blocks.NETHERRACK)) {
                world.setBlockState(pos1, Blocks.CRIMSON_NYLIUM.getDefaultState(), 3);
            }
        }
    }
}
