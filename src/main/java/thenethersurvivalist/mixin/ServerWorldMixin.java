package thenethersurvivalist.mixin;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {
    public boolean start = true;

    /**
     * @author DukeEdivad05
     * @reason SpawnPortal
     */
    @Overwrite
    public BlockPos getSpawnPos() {
        ServerWorld world = (ServerWorld)(Object)this;
        WorldProperties properties = ((ServerWorld)(Object)this).getLevelProperties();
        BlockPos blockPos = new BlockPos(properties.getSpawnX(), properties.getSpawnY(), properties.getSpawnZ());

//        Integer witch = new Random().nextInt(3);
//
//        if (witch == 0) {
//            for (int a = 5; a > -7; a--) {
//                world.setBlockState(blockPos.add(a, -2, 4), Blocks.NETHERRACK.getDefaultState());
//                for (int b = -2; b < 9; b++) {
//                    world.setBlockState(blockPos.add(a, b, 4), Blocks.AIR.getDefaultState());
//                }
//            }
//
//            world.setBlockState(blockPos.add(5, -2,3), Blocks.NETHERRACK.getDefaultState());
//            world.setBlockState(blockPos.add(4, -2,3), Blocks.NETHERRACK.getDefaultState());
//            world.setBlockState(blockPos.add(3, -2,3), Blocks.CRIMSON_NYLIUM.getDefaultState());
//            world.setBlockState(blockPos.add(2, -2,3), Blocks.NETHERRACK.getDefaultState());
//            world.setBlockState(blockPos.add(1, -2,3), Blocks.NETHERRACK.getDefaultState());
//            world.setBlockState(blockPos.add(0, -2,3), Blocks.NETHERRACK.getDefaultState());
//            world.setBlockState(blockPos.add(-1, -2,3), Blocks.CRIMSON_NYLIUM.getDefaultState());
//            world.setBlockState(blockPos.add(-2, -2,3), Blocks.CRIMSON_NYLIUM.getDefaultState());
//            world.setBlockState(blockPos.add(-3, -2,3), Blocks.NETHERRACK.getDefaultState());
//            world.setBlockState(blockPos.add(-4, -2,3), Blocks.NETHERRACK.getDefaultState());
//            world.setBlockState(blockPos.add(-5, -2,3), Blocks.NETHERRACK.getDefaultState());
//            world.setBlockState(blockPos.add(-6, -2,3), Blocks.NETHERRACK.getDefaultState());
//
//            for (int a = 5; a > -7; a--) {
//                for (int b = -2; b < 9; b++) {
//                    world.setBlockState(blockPos.add(a, b, 3), Blocks.AIR.getDefaultState());
//                }
//            }
//            world.setBlockState(blockPos.add(3, -1, 3), Blocks.CRIMSON_ROOTS.getDefaultState());
//            world.setBlockState(blockPos.add(-3, 6, 3), Blocks.POLISHED_BLACKSTONE_BRICK_WALL.getDefaultState().with(NORTH_SHAPE, WallShape.LOW));
//            world.setBlockState(blockPos.add(-3, 5, 3), Blocks.CHAIN.getDefaultState());
//            world.setBlockState(blockPos.add(-3, 4, 3), Blocks.SOUL_LANTERN.getDefaultState().with(HANGING, true));
//        }

        return blockPos;
    }
}
