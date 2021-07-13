package thenethersurvivalist.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class ObsidianWalkerBlock extends Block {
    public static final IntProperty AGE;

    public ObsidianWalkerBlock(Settings settings) {
        super(settings);
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        this.scheduledTick(state, world, pos, random);
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if ((random.nextInt(3) == 0) && world.getDimension().isUltrawarm() && this.increaseAge(state, world, pos)) {
            BlockPos.Mutable mutable = new BlockPos.Mutable();
            Direction[] var6 = Direction.values();
            int var7 = var6.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                Direction direction = var6[var8];
                mutable.set(pos, direction);
                BlockState blockState = world.getBlockState(mutable);
                if (blockState.isOf(this) && !this.increaseAge(blockState, world, mutable)) {
                    world.getBlockTickScheduler().schedule(mutable, this, MathHelper.nextInt(random, 20, 40));
                }
            }

        } else {
            world.getBlockTickScheduler().schedule(pos, this, MathHelper.nextInt(random, 20, 40));
        }
    }

    private boolean increaseAge(BlockState state, World world, BlockPos pos) {
        int i = (Integer)state.get(AGE);
        if (i < 3) {
            world.setBlockState(pos, (BlockState)state.with(AGE, i + 1), 2);
            return false;
        } else {
            this.melt(state, world, pos);
            return true;
        }
    }

    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (block == this) {
            this.melt(state, world, pos);
        }

        super.neighborUpdate(state, world, pos, block, fromPos, notify);
    }

    public void melt(BlockState state, World world, BlockPos pos) {
        world.setBlockState(pos, Blocks.LAVA.getDefaultState());
        world.updateNeighbor(pos, Blocks.LAVA, pos);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        this.melt(state, world, pos);
    }

    static {
        AGE = Properties.AGE_3;
    }
}
