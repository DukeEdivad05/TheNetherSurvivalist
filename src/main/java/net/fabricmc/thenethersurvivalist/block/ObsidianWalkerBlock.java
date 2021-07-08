package net.fabricmc.thenethersurvivalist.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class ObsidianWalkerBlock extends Block {
    public ObsidianWalkerBlock(Settings settings) {
        super(settings);
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getDimension().isUltrawarm()) {
            this.melt(state, world, pos);
        }

    }

    protected void melt(BlockState state, World world, BlockPos pos) {
        world.setBlockState(pos, Blocks.LAVA.getDefaultState());
        world.updateNeighbor(pos, Blocks.LAVA, pos);
    }
}
