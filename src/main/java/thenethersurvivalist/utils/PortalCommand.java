package thenethersurvivalist.utils;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.block.enums.WallShape;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import static net.minecraft.block.ChestBlock.FACING;
import static net.minecraft.block.LanternBlock.HANGING;
import static net.minecraft.block.PillarBlock.AXIS;
import static net.minecraft.block.SlabBlock.TYPE;
import static net.minecraft.block.StairsBlock.HALF;
import static net.minecraft.block.WallBlock.*;

public class PortalCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                CommandManager.literal("portal1")
                        .requires((source -> source.hasPermissionLevel(2)))
                        .executes((c) -> execute((ServerCommandSource)c.getSource(), new BlockPos(c.getSource().getPosition()), "Uno"))
        );
    }

    private static int execute(ServerCommandSource source, BlockPos pos, String string) {
        ServerWorld world = source.getWorld();
        place(world, pos, 1);
        return 1;
    }

    public static void place(ServerWorld world, BlockPos blockPos, int integer) {
        for (BlockPos pos : BlockPos.iterate(blockPos.add(5, -2, 4), blockPos.add(-6, 8, -4))) {
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
        }
        for (BlockPos pos : BlockPos.iterate(blockPos.add(5,-2, 4), blockPos.add(-6, -2, -4))) {
            world.setBlockState(pos, Blocks.NETHERRACK.getDefaultState());
        }
        if (integer == 1) {
            world.setBlockState(blockPos.add(3, -2,3), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(-1, -2,3), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(-2, -2,3), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(0, -2, 2), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(-1, -2, 2), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(-4, -2, 2), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(5, -2, 1), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(-6, -2, 1), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(5, -2, -1), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(4, -2, -2), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(0, -2, -2), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(-1, -2, -2), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(-5, -2, -2), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(4, -2, -3), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(2, -2, -3), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(1, -2, -3), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(-1, -2, -3), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(-2, -2, -3), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(-2, -2, -4), Blocks.CRIMSON_NYLIUM.getDefaultState());

            world.setBlockState(blockPos.add(3, -1, 3), Blocks.CRIMSON_ROOTS.getDefaultState());
            world.setBlockState(blockPos.add(3, -1, 2), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(2, -1, 2), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-1, -1, 2), Blocks.CRIMSON_ROOTS.getDefaultState());
            world.setBlockState(blockPos.add(-2, -1, 2), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-3, -1, 2), Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState());
            world.setBlockState(blockPos.add(-4, -1, 2), Blocks.CRIMSON_ROOTS.getDefaultState());
            world.setBlockState(blockPos.add(4, -1, 1), Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState());
            world.setBlockState(blockPos.add(3, -1, 1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(2, -1, 1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(1, -1, 1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(0, -1, 1), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState());
            world.setBlockState(blockPos.add(-1, -1, 1), Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState());
            world.setBlockState(blockPos.add(-2, -1, 1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-3, -1, 1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-4, -1, 1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(4, -1, 0), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(3, -1, 0), Blocks.POLISHED_BASALT.getDefaultState());
            for (BlockPos pos : BlockPos.iterate(blockPos.add(2, -1, 0), blockPos.add(-2, -1, 0))) {
                world.setBlockState(pos, Blocks.OBSIDIAN.getDefaultState());
            }
            world.setBlockState(blockPos.add(-3, -1, 0), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-4, -1, 0), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(4, -1, -1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(3, -1, -1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(2, -1, -1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(1, -1, -1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(0, -1, -1), Blocks.BLACKSTONE_STAIRS.getDefaultState().with(FACING, Direction.SOUTH));
            world.setBlockState(blockPos.add(-1, -1, -1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-2, -1, -1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-3, -1, -1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-4, -1, -1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(3, -1, -2), Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState());
            world.setBlockState(blockPos.add(2, -1, -2), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-2, -1, -2), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-3, -1, -2), Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState());
            world.setBlockState(blockPos.add(-5, -1, -2), Blocks.CRIMSON_ROOTS.getDefaultState());
            world.setBlockState(blockPos.add(4, -1, 3), Blocks.CRIMSON_ROOTS.getDefaultState());
            world.setBlockState(blockPos.add(-1, -1, 3), Blocks.CRIMSON_ROOTS.getDefaultState());

            world.setBlockState(blockPos.add(3, 0, 2), Blocks.BLACKSTONE_STAIRS.getDefaultState());
            world.setBlockState(blockPos.add(2, 0, 2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState());
            world.setBlockState(blockPos.add(-2, 0, 2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState());
            world.setBlockState(blockPos.add(-3, 0, 2), Blocks.BLACKSTONE_STAIRS.getDefaultState());
            world.setBlockState(blockPos.add(4, 0, 1), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(FACING, Direction.WEST));
            world.setBlockState(blockPos.add(3, 0, 1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(2, 0, 1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(1, 0, 1), Blocks.SOUL_LANTERN.getDefaultState());
            world.setBlockState(blockPos.add(-1, 0, 1), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(FACING, Direction.WEST));
            world.setBlockState(blockPos.add(-2, 0, 1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-3, 0, 1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-4, 0, 1), Blocks.BLACKSTONE_STAIRS.getDefaultState().with(FACING, Direction.EAST));
            world.setBlockState(blockPos.add(4, 0, 0), Blocks.POLISHED_BLACKSTONE_SLAB.getDefaultState());
            world.setBlockState(blockPos.add(3, 0, 0), Blocks.BASALT.getDefaultState().with(AXIS, Direction.Axis.X));
            world.setBlockState(blockPos.add(2, 0, 0), Blocks.OBSIDIAN.getDefaultState());
            world.setBlockState(blockPos.add(-2, 0, 0), Blocks.OBSIDIAN.getDefaultState());
            world.setBlockState(blockPos.add(-3, 0, 0), Blocks.BASALT.getDefaultState().with(AXIS, Direction.Axis.X));
            world.setBlockState(blockPos.add(-4, 0, 0), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(FACING, Direction.EAST));
            world.setBlockState(blockPos.add(4, 0, -1), Blocks.BLACKSTONE_STAIRS.getDefaultState().with(FACING, Direction.WEST));
            world.setBlockState(blockPos.add(3, 0, -1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(2, 0, -1), Blocks.BASALT.getDefaultState().with(AXIS, Direction.Axis.Z));
            world.setBlockState(blockPos.add(1, 0, -1), Blocks.BLACKSTONE_STAIRS.getDefaultState().with(FACING, Direction.EAST));
            world.setBlockState(blockPos.add(-1, 0, -1), Blocks.BLACKSTONE_STAIRS.getDefaultState().with(FACING, Direction.WEST));
            world.setBlockState(blockPos.add(-2, 0, -1), Blocks.BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-3, 0, -1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-4, 0, -1), Blocks.BLACKSTONE_STAIRS.getDefaultState().with(FACING, Direction.EAST));
            world.setBlockState(blockPos.add(3, 0, -2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(FACING, Direction.SOUTH));
            world.setBlockState(blockPos.add(2, 0, -2), Blocks.POLISHED_BLACKSTONE_SLAB.getDefaultState());
            world.setBlockState(blockPos.add(-2, 0, -2), Blocks.BLACKSTONE_SLAB.getDefaultState());
            world.setBlockState(blockPos.add(-3, 0, -2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(FACING, Direction.SOUTH));

            world.setBlockState(blockPos.add(3, 1, 1), Blocks.BASALT.getDefaultState().with(AXIS, Direction.Axis.X));
            world.setBlockState(blockPos.add(2, 1, 1), Blocks.BASALT.getDefaultState().with(AXIS, Direction.Axis.Z));
            world.setBlockState(blockPos.add(-2, 1, 1), Blocks.BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-3, 1, 1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(3, 1, 0), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(2, 1, 0), Blocks.OBSIDIAN.getDefaultState());
            world.setBlockState(blockPos.add(-2, 1, 0), Blocks.OBSIDIAN.getDefaultState());
            world.setBlockState(blockPos.add(-3, 1, 0), Blocks.BASALT.getDefaultState().with(AXIS, Direction.Axis.X));
            world.setBlockState(blockPos.add(3, 1, -1), Blocks.BASALT.getDefaultState().with(AXIS, Direction.Axis.X));
            world.setBlockState(blockPos.add(2, 1, -1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-2, 1, -1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-3, 1, -1), Blocks.BASALT.getDefaultState().with(AXIS, Direction.Axis.Z));

            world.setBlockState(blockPos.add(3, 2, 1), Blocks.BASALT.getDefaultState().with(AXIS, Direction.Axis.X));
            world.setBlockState(blockPos.add(2, 2, 1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-2, 2, 1), Blocks.BASALT.getDefaultState().with(AXIS, Direction.Axis.Z));
            world.setBlockState(blockPos.add(-3, 2, 1), Blocks.BASALT.getDefaultState().with(AXIS, Direction.Axis.X));
            world.setBlockState(blockPos.add(3, 2, 0), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(2, 2, 0), Blocks.OBSIDIAN.getDefaultState());
            world.setBlockState(blockPos.add(-2, 2, 0), Blocks.OBSIDIAN.getDefaultState());
            world.setBlockState(blockPos.add(-3, 2, 0), Blocks.BASALT.getDefaultState());
            world.setBlockState(blockPos.add(2, 2, -1), Blocks.BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-2, 2, -1), Blocks.POLISHED_BLACKSTONE_WALL.getDefaultState().with(SOUTH_SHAPE, WallShape.TALL));
            world.setBlockState(blockPos.add(-3, 2, -1), Blocks.WEEPING_VINES.getDefaultState());

            world.setBlockState(blockPos.add(3, 3, 1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(2, 3, 1), Blocks.BLACKSTONE_SLAB.getDefaultState());
            world.setBlockState(blockPos.add(-2, 3, 1), Blocks.POLISHED_BLACKSTONE_WALL.getDefaultState().with(NORTH_SHAPE, WallShape.TALL).with(WEST_SHAPE, WallShape.TALL));
            world.setBlockState(blockPos.add(-3, 3, 1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(2, 3, 0), Blocks.OBSIDIAN.getDefaultState());
            world.setBlockState(blockPos.add(-2, 3, 0), Blocks.OBSIDIAN.getDefaultState());
            world.setBlockState(blockPos.add(-3, 3, 0), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(2, 3, -1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-2, 3, -1), Blocks.BASALT.getDefaultState().with(AXIS, Direction.Axis.X));
            world.setBlockState(blockPos.add(-3, 3, -1), Blocks.POLISHED_BASALT.getDefaultState());

            world.setBlockState(blockPos.add(-3, 4, 3), Blocks.SOUL_LANTERN.getDefaultState().with(HANGING, true));
            world.setBlockState(blockPos.add(-3, 4, 2), Blocks.BLACKSTONE_STAIRS.getDefaultState().with(HALF, BlockHalf.TOP));
            world.setBlockState(blockPos.add(3, 4, 1), Blocks.POLISHED_BLACKSTONE_WALL.getDefaultState());
            world.setBlockState(blockPos.add(0, 4, 1), Blocks.WEEPING_VINES.getDefaultState());
            world.setBlockState(blockPos.add(-1, 4 , 1), Blocks.BLACKSTONE_STAIRS.getDefaultState().with(FACING, Direction.WEST).with(HALF, BlockHalf.TOP));
            world.setBlockState(blockPos.add(-2, 4, 1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-3, 4, 1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-4, 4, 1), Blocks.BLACKSTONE_STAIRS.getDefaultState().with(FACING, Direction.EAST).with(HALF, BlockHalf.TOP));
            world.setBlockState(blockPos.add(0, 4, 0), Blocks.OBSIDIAN.getDefaultState());
            world.setBlockState(blockPos.add(-1, 0, 0), Blocks.OBSIDIAN.getDefaultState());
            world.setBlockState(blockPos.add(-2, 0, 0), Blocks.OBSIDIAN.getDefaultState());
            world.setBlockState(blockPos.add(-3, 0, 0), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(2, 4, -1), Blocks.POLISHED_BLACKSTONE_WALL.getDefaultState());
            world.setBlockState(blockPos.add(-2, 4, -1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-3, 4, -1), Blocks.BASALT.getDefaultState().with(AXIS, Direction.Axis.X));
            world.setBlockState(blockPos.add(-4, 4, -1), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(FACING, Direction.EAST).with(HALF, BlockHalf.TOP));
            world.setBlockState(blockPos.add(-3, 4, -2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(FACING, Direction.SOUTH).with(HALF, BlockHalf.TOP));

            world.setBlockState(blockPos.add(-3, 5, 3), Blocks.CHAIN.getDefaultState());
            world.setBlockState(blockPos.add(0, 5, 2), Blocks.STONE_BUTTON.getDefaultState().with(FACING, Direction.SOUTH)); //Controlla se quello del ChestBlock va bene uguale
            world.setBlockState(blockPos.add(-1, 5, 2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HALF, BlockHalf.TOP));
            world.setBlockState(blockPos.add(-2, 5, 2), Blocks.STONE_BUTTON.getDefaultState().with(FACING, Direction.SOUTH));
            world.setBlockState(blockPos.add(-3, 5, 2), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(0, 5, 1), Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState());
            world.setBlockState(blockPos.add(-1, 5, 1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-2, 5, 1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-3, 5, 1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-4, 5, 1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-1, 5, 0), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-2, 5, 0), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-3, 4, 0), Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState());
            world.setBlockState(blockPos.add(-4, 4, 0), Blocks.STONE_BUTTON.getDefaultState().with(FACING, Direction.WEST));
            world.setBlockState(blockPos.add(-2, 5, -1), Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState());
            world.setBlockState(blockPos.add(-3, 5, -1), Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState());
            world.setBlockState(blockPos.add(-4, 5, -1), Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState());
            world.setBlockState(blockPos.add(-2, 5, -2), Blocks.STONE_BUTTON.getDefaultState());
            world.setBlockState(blockPos.add(-3, 5, -2), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-3, 5, -3), Blocks.CHAIN.getDefaultState());

            world.setBlockState(blockPos.add(-3, 6, 3), Blocks.POLISHED_BLACKSTONE_BRICK_WALL.getDefaultState().with(NORTH_SHAPE, WallShape.LOW));
            world.setBlockState(blockPos.add(-1, 6, 2), Blocks.CHISELED_POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-2, 6, 2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HALF, BlockHalf.TOP));
            world.setBlockState(blockPos.add(-3, 6, 2), Blocks.CHISELED_POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-1, 6, 1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-2, 6, 1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-3, 6, 1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-4, 6, 1), Blocks.CHISELED_POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-5, 6, 1), Blocks.POLISHED_BLACKSTONE_BRICK_WALL.getDefaultState().with(EAST_SHAPE, WallShape.LOW));
            world.setBlockState(blockPos.add(-2, 6, 0), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-3, 6, 0), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-4, 6, 0), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(FACING, Direction.EAST).with(HALF, BlockHalf.TOP));
            world.setBlockState(blockPos.add(-3, 6, -1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-4, 6, -1), Blocks.CHISELED_POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-5, 6, -1), Blocks.POLISHED_BLACKSTONE_BRICK_WALL.getDefaultState().with(EAST_SHAPE, WallShape.LOW));
            world.setBlockState(blockPos.add(-3, 6, -2), Blocks.CHISELED_POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-3, 6, -3), Blocks.POLISHED_BLACKSTONE_BRICK_WALL.getDefaultState().with(SOUTH_SHAPE, WallShape.LOW));

            world.setBlockState(blockPos.add(-1, 7, 2), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-2, 7, 2), Blocks.POLISHED_BLACKSTONE_WALL.getDefaultState().with(EAST_SHAPE, WallShape.LOW).with(WEST_SHAPE, WallShape.LOW));
            world.setBlockState(blockPos.add(-3, 7, 2), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-4, 7, 1), Blocks.BLACKSTONE_SLAB.getDefaultState());
            world.setBlockState(blockPos.add(-4, 7, -1), Blocks.BLACKSTONE_SLAB.getDefaultState());
            world.setBlockState(blockPos.add(-3, 7, -2), Blocks.BLACKSTONE_SLAB.getDefaultState());
        } else if (integer == 2) {
            world.setBlockState(blockPos.add(3, -2, 4), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(-1, -2, 4), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(-2, -2, 4), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(4, -2, 3), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(1, -2, 3), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(-4, -2, 3), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(5, -2, 2), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(1, -2, 2), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(0, -2, 2), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(-5, -2, 2), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(-6, -2, 2), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(5, -2, 1), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(-5, -2, 0), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(-6, -2, -1), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(5, -2, -2), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(5, -2, -3), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(0, -2, -3), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(-2, -2, -3), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(-5, -2, -3), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(3, -2, -4), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(2, -2, -4), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(0, -2, -4), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(-3, -2, -4), Blocks.CRIMSON_NYLIUM.getDefaultState());

            world.setBlockState(blockPos.add(3, -1, 4), Blocks.CRIMSON_ROOTS.getDefaultState());
            world.setBlockState(blockPos.add(-1, -1, 4), Blocks.CRIMSON_ROOTS.getDefaultState());
            world.setBlockState(blockPos.add(3, -1, 2), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(2, -1, 2), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(1, -1, 2), Blocks.CRIMSON_ROOTS.getDefaultState());
            world.setBlockState(blockPos.add(-2, -1, 2), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-3, -1, 2), Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState());
            world.setBlockState(blockPos.add(-5, -1, 2), Blocks.CRIMSON_ROOTS.getDefaultState());
            world.setBlockState(blockPos.add(4, -1, 1), Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState());
            world.setBlockState(blockPos.add(3, -1, 1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(2, -1, 1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(1, -1, 1), Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState());
            world.setBlockState(blockPos.add(0, -1, 1), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState());
            world.setBlockState(blockPos.add(-1, -1, 1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-2, -1, 1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-3, -1, 1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-4, -1, 1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(4, -1, 0), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(3, -1, 0), Blocks.POLISHED_BASALT.getDefaultState());
            for (BlockPos pos : BlockPos.iterate(blockPos.add(2, -1, 0), blockPos.add(-2, -1, 0))) {
                world.setBlockState(pos, Blocks.OBSIDIAN.getDefaultState());
            }
            world.setBlockState(blockPos.add(-3, -1, 0), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-4, -1, 0), Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState());
            world.setBlockState(blockPos.add(4, -1, -1), Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState());
            world.setBlockState(blockPos.add(3, -1, -1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(2, -1, -1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(1, -1, -1), Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState());
            world.setBlockState(blockPos.add(0, -1, -1), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(FACING, Direction.SOUTH));
            world.setBlockState(blockPos.add(-1, -1, -1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-2, -1, -1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-3, -1, -1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-4, -1, -1), Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState());
            world.setBlockState(blockPos.add(-6, -1, 1), Blocks.CRIMSON_ROOTS.getDefaultState());
            world.setBlockState(blockPos.add(3, -1, -2), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(2, -1, -2), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-2, -1, -2), Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState());
            world.setBlockState(blockPos.add(-3, -1, -2), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(0, -1, -3), Blocks.CRIMSON_ROOTS.getDefaultState());
            world.setBlockState(blockPos.add(3, -1, -4), Blocks.CRIMSON_ROOTS.getDefaultState());
            world.setBlockState(blockPos.add(-3, -1, -4), Blocks.CRIMSON_ROOTS.getDefaultState());

            world.setBlockState(blockPos.add(3, 0, 2), Blocks.BLACKSTONE_SLAB.getDefaultState());
            world.setBlockState(blockPos.add(2, 0, 2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState());
            world.setBlockState(blockPos.add(-2, 0, 2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState());
            world.setBlockState(blockPos.add(-3, 0, 2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState());
            world.setBlockState(blockPos.add(4, 0, 1), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(FACING, Direction.WEST));
            world.setBlockState(blockPos.add(3, 0, 1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(2, 0, 1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(1, 0, 1), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(FACING, Direction.EAST));
            world.setBlockState(blockPos.add(-1, 0, 1), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(FACING, Direction.WEST));
            world.setBlockState(blockPos.add(-2, 0, 1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-3, 0, 1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-4, 0, 1), Blocks.BLACKSTONE_SLAB.getDefaultState());
            world.setBlockState(blockPos.add(4, 0, 0), Blocks.BLACKSTONE_SLAB.getDefaultState());
            world.setBlockState(blockPos.add(3, 0, 0), Blocks.POLISHED_BASALT.getDefaultState().with(AXIS, Direction.Axis.X));
            world.setBlockState(blockPos.add(2, 0, 0), Blocks.OBSIDIAN.getDefaultState());
            world.setBlockState(blockPos.add(-2, 0, 0), Blocks.OBSIDIAN.getDefaultState());
            world.setBlockState(blockPos.add(-3, 0, 0), Blocks.POLISHED_BASALT.getDefaultState().with(AXIS, Direction.Axis.X));
            world.setBlockState(blockPos.add(-4, 0, 0), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(FACING, Direction.EAST));
            world.setBlockState(blockPos.add(4, 0, -1), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(FACING, Direction.WEST));
            world.setBlockState(blockPos.add(3, 0, -1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(2, 0, -1), Blocks.POLISHED_BASALT.getDefaultState().with(AXIS, Direction.Axis.Z));
            world.setBlockState(blockPos.add(1, 0, -1), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(FACING, Direction.EAST));
            world.setBlockState(blockPos.add(-1, 0, -1), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(FACING, Direction.WEST));
            world.setBlockState(blockPos.add(-2, 0, -1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-3, 0, -1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-4, 0, -1), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(FACING, Direction.EAST));
            world.setBlockState(blockPos.add(3, 0, -2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(FACING, Direction.SOUTH));
            world.setBlockState(blockPos.add(2, 0, -2), Blocks.BLACKSTONE_SLAB.getDefaultState());
            world.setBlockState(blockPos.add(-2, 0, -2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(FACING, Direction.SOUTH));
            world.setBlockState(blockPos.add(-3, 0, -2), Blocks.BLACKSTONE_SLAB.getDefaultState());

            world.setBlockState(blockPos.add(3, 1, 1), Blocks.POLISHED_BLACKSTONE_WALL.getDefaultState().with(NORTH_SHAPE, WallShape.TALL).with(WEST_SHAPE, WallShape.TALL));
            world.setBlockState(blockPos.add(2, 1, 1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-2, 1, 1), Blocks.BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-3, 1, 1), Blocks.POLISHED_BLACKSTONE_WALL.getDefaultState().with(NORTH_SHAPE, WallShape.TALL).with(EAST_SHAPE, WallShape.TALL));
            world.setBlockState(blockPos.add(3, 1, 0), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(2, 1, 0), Blocks.OBSIDIAN.getDefaultState());
            world.setBlockState(blockPos.add(-2, 1, 0), Blocks.OBSIDIAN.getDefaultState());
            world.setBlockState(blockPos.add(-3, 1, 0), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(3, 1, -1), Blocks.BASALT.getDefaultState());
            world.setBlockState(blockPos.add(2, 1, -1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-2, 1, -1), Blocks.BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-3, 1, -1), Blocks.POLISHED_BASALT.getDefaultState());

            world.setBlockState(blockPos.add(3, 2, 1), Blocks.BASALT.getDefaultState().with(AXIS, Direction.Axis.X));
            world.setBlockState(blockPos.add(2, 2, 1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-2, 2, 1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-3, 2, 1), Blocks.BASALT.getDefaultState().with(AXIS, Direction.Axis.Z));
            world.setBlockState(blockPos.add(3, 2, 0), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(2, 2, 0), Blocks.OBSIDIAN.getDefaultState());
            world.setBlockState(blockPos.add(-2, 2, 0), Blocks.OBSIDIAN.getDefaultState());
            world.setBlockState(blockPos.add(-3, 2, 0), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(3, 2, -1), Blocks.POLISHED_BLACKSTONE_WALL.getDefaultState().with(SOUTH_SHAPE, WallShape.TALL).with(WEST_SHAPE, WallShape.TALL));
            world.setBlockState(blockPos.add(2, 2, -1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-2, 2, -1), Blocks.POLISHED_BLACKSTONE_WALL.getDefaultState().with(SOUTH_SHAPE, WallShape.TALL).with(WEST_SHAPE, WallShape.TALL));
            world.setBlockState(blockPos.add(-3, 2, -1), Blocks.BASALT.getDefaultState().with(AXIS, Direction.Axis.Z));

            world.setBlockState(blockPos.add(3, 3, 1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(2, 3, 1), Blocks.BASALT.getDefaultState().with(AXIS, Direction.Axis.Z));
            world.setBlockState(blockPos.add(0, 3, 1), Blocks.WEEPING_VINES.getDefaultState());
            world.setBlockState(blockPos.add(-2, 3, 1), Blocks.POLISHED_BLACKSTONE_WALL.getDefaultState().with(NORTH_SHAPE, WallShape.TALL).with(WEST_SHAPE, WallShape.TALL));
            world.setBlockState(blockPos.add(-3, 3, 1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(3, 3, 0), Blocks.BASALT.getDefaultState().with(AXIS, Direction.Axis.X));
            world.setBlockState(blockPos.add(2, 3, 0), Blocks.OBSIDIAN.getDefaultState());
            world.setBlockState(blockPos.add(-2, 3, 0), Blocks.OBSIDIAN.getDefaultState());
            world.setBlockState(blockPos.add(-3, 3, 0), Blocks.POLISHED_BLACKSTONE_WALL.getDefaultState().with(EAST_SHAPE, WallShape.TALL).with(NORTH_SHAPE, WallShape.TALL).with(SOUTH_SHAPE, WallShape.TALL));
            world.setBlockState(blockPos.add(3, 3, -1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(2, 3, -1), Blocks.POLISHED_BLACKSTONE_WALL.getDefaultState().with(EAST_SHAPE, WallShape.TALL).with(SOUTH_SHAPE, WallShape.TALL));
            world.setBlockState(blockPos.add(0, 3, -1), Blocks.WEEPING_VINES.getDefaultState());
            world.setBlockState(blockPos.add(-2, 3, -1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-3, 3, -1), Blocks.BASALT.getDefaultState());

            world.setBlockState(blockPos.add(-3,4, 3), Blocks.SOUL_LANTERN.getDefaultState().with(HANGING, true));
            world.setBlockState(blockPos.add(3, 4, 1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(2, 4, 1), Blocks.POLISHED_BLACKSTONE_WALL.getDefaultState().with(EAST_SHAPE, WallShape.TALL).with(NORTH_SHAPE, WallShape.TALL). with(WEST_SHAPE, WallShape.TALL));
            world.setBlockState(blockPos.add(1, 4, 1), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(FACING, Direction.EAST).with(HALF, BlockHalf.TOP));
            world.setBlockState(blockPos.add(0, 4, 1), Blocks.WEEPING_VINES_PLANT.getDefaultState());
            world.setBlockState(blockPos.add(-1, 4, 1), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(FACING, Direction.WEST).with(HALF, BlockHalf.TOP));
            world.setBlockState(blockPos.add(-2, 4, 1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-3, 4, 1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(3, 4, 0), Blocks.POLISHED_BASALT.getDefaultState());
            for (BlockPos pos : BlockPos.iterate(blockPos.add(2, 4, 0), blockPos.add(-2, 4, 0))) {
                world.setBlockState(pos, Blocks.OBSIDIAN.getDefaultState());
            }
            world.setBlockState(blockPos.add(-3, 4, 0), Blocks.BASALT.getDefaultState().with(AXIS, Direction.Axis.X));
            world.setBlockState(blockPos.add(3, 4, -1), Blocks.BASALT.getDefaultState());
            world.setBlockState(blockPos.add(2, 4, -1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(1, 4, -1), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(FACING, Direction.EAST).with(HALF, BlockHalf.TOP));
            world.setBlockState(blockPos.add(0, 4, -1), Blocks.WEEPING_VINES_PLANT.getDefaultState());
            world.setBlockState(blockPos.add(-1, 4, -1), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(FACING, Direction.WEST).with(HALF, BlockHalf.TOP));
            world.setBlockState(blockPos.add(-2, 4, -1), Blocks.POLISHED_BLACKSTONE_WALL.getDefaultState().with(SOUTH_SHAPE, WallShape.TALL).with(WEST_SHAPE, WallShape.TALL).with(EAST_SHAPE, WallShape.TALL));
            world.setBlockState(blockPos.add(-3, 4, -1), Blocks.BASALT.getDefaultState().with(AXIS, Direction.Axis.Z));
            world.setBlockState(blockPos.add(-4, 4, -1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(3, 4, -3), Blocks.SOUL_LANTERN.getDefaultState().with(HANGING, true));

            world.setBlockState(blockPos.add(3, 5, 3), Blocks.SOUL_LANTERN.getDefaultState().with(HANGING, true));
            world.setBlockState(blockPos.add(-3, 5, 3), Blocks.CHAIN.getDefaultState());
            world.setBlockState(blockPos.add(3, 5, 2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HALF, BlockHalf.TOP));
            world.setBlockState(blockPos.add(2, 5, 2), Blocks.STONE_BUTTON.getDefaultState().with(FACING, Direction.SOUTH));
            world.setBlockState(blockPos.add(1, 5, 2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HALF, BlockHalf.TOP));
            world.setBlockState(blockPos.add(0, 5, 2), Blocks.STONE_BUTTON.getDefaultState().with(FACING, Direction.SOUTH));
            world.setBlockState(blockPos.add(-1, 5, 2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HALF, BlockHalf.TOP));
            world.setBlockState(blockPos.add(-2, 5, 2), Blocks.STONE_BUTTON.getDefaultState().with(FACING, Direction.SOUTH));
            world.setBlockState(blockPos.add(-3, 5, 2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HALF, BlockHalf.TOP));
            world.setBlockState(blockPos.add(4, 5, 1), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HALF, BlockHalf.TOP).with(FACING, Direction.WEST));
            world.setBlockState(blockPos.add(3, 5, 1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(2, 5, 1), Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState());
            world.setBlockState(blockPos.add(1, 5, 1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(0, 5, 1), Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState());
            world.setBlockState(blockPos.add(-1, 5, 1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-2, 5, 1), Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState());
            world.setBlockState(blockPos.add(-3, 5, 1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-4, 5, 1), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HALF, BlockHalf.TOP).with(FACING, Direction.EAST));
            world.setBlockState(blockPos.add(4, 5, 0), Blocks.STONE_BUTTON.getDefaultState().with(FACING, Direction.EAST));
            world.setBlockState(blockPos.add(3, 5, 0), Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState());
            for (BlockPos pos: BlockPos.iterate(blockPos.add(2, 5, 0), blockPos.add(-2, 5, 0))) {
                world.setBlockState(pos, Blocks.POLISHED_BLACKSTONE.getDefaultState());
            }
            world.setBlockState(blockPos.add(-3, 5, 0), Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState());
            world.setBlockState(blockPos.add(-4, 5, 0), Blocks.STONE_BUTTON.getDefaultState().with(FACING, Direction.WEST));
            world.setBlockState(blockPos.add(4, 5, -1), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HALF, BlockHalf.TOP).with(FACING, Direction.WEST));
            world.setBlockState(blockPos.add(3, 5, -1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(2, 5, -1), Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState());
            world.setBlockState(blockPos.add(1, 5, -1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(0, 5, -1), Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState());
            world.setBlockState(blockPos.add(-1, 5, -1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-2, 5, -1), Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState());
            world.setBlockState(blockPos.add(-3, 5, -1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-4, 5, -1), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HALF, BlockHalf.TOP).with(FACING, Direction.EAST));
            world.setBlockState(blockPos.add(3, 5, -2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HALF, BlockHalf.TOP).with(FACING, Direction.SOUTH));
            world.setBlockState(blockPos.add(2, 5, -2), Blocks.STONE_BUTTON.getDefaultState());
            world.setBlockState(blockPos.add(1, 5, -2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HALF, BlockHalf.TOP).with(FACING, Direction.SOUTH));
            world.setBlockState(blockPos.add(0, 5, -2), Blocks.STONE_BUTTON.getDefaultState());
            world.setBlockState(blockPos.add(-1, 5, -2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HALF, BlockHalf.TOP).with(FACING, Direction.SOUTH));
            world.setBlockState(blockPos.add(-2, 5, -2), Blocks.STONE_BUTTON.getDefaultState());
            world.setBlockState(blockPos.add(-3, 5, -2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HALF, BlockHalf.TOP).with(FACING, Direction.SOUTH));
            world.setBlockState(blockPos.add(3, 5, -3), Blocks.CHAIN.getDefaultState());
            world.setBlockState(blockPos.add(-3, 5, -3), Blocks.SOUL_LANTERN.getDefaultState().with(HANGING, true));

            world.setBlockState(blockPos.add(3, 6, 3), Blocks.POLISHED_BLACKSTONE_BRICK_WALL.getDefaultState().with(NORTH_SHAPE, WallShape.LOW));
            world.setBlockState(blockPos.add(-3, 6, 3), Blocks.POLISHED_BLACKSTONE_BRICK_WALL.getDefaultState().with(NORTH_SHAPE, WallShape.LOW));
            world.setBlockState(blockPos.add(3, 6, 2), Blocks.CHISELED_POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(2, 6, 2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HALF, BlockHalf.TOP));
            world.setBlockState(blockPos.add(1, 6, 2), Blocks.CHISELED_POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(0, 6, 2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HALF, BlockHalf.TOP));
            world.setBlockState(blockPos.add(-1, 6, 2), Blocks.CHISELED_POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-2, 6, 2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HALF, BlockHalf.TOP));
            world.setBlockState(blockPos.add(-3, 6, 2), Blocks.CHISELED_POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(4, 6, 1), Blocks.CHISELED_POLISHED_BLACKSTONE.getDefaultState());
            for (BlockPos pos: BlockPos.iterate(blockPos.add(3, 6, 1), blockPos.add(-3, 6, -1))) {
                world.setBlockState(pos, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState());
            }
            world.setBlockState(blockPos.add(-4, 6, 1), Blocks.CHISELED_POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(4, 6, 0), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(FACING, Direction.WEST).with(HALF, BlockHalf.TOP));
            world.setBlockState(blockPos.add(-4, 6, 0), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(FACING, Direction.EAST).with(HALF, BlockHalf.TOP));
            world.setBlockState(blockPos.add(4, 6, -1), Blocks.CHISELED_POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-4, 6, -1), Blocks.CHISELED_POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(3, 6, -2), Blocks.CHISELED_POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(2, 6, -2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HALF, BlockHalf.TOP).with(FACING, Direction.SOUTH));
            world.setBlockState(blockPos.add(1, 6, -2), Blocks.CHISELED_POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(0, 6, -2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HALF, BlockHalf.TOP).with(FACING, Direction.SOUTH));
            world.setBlockState(blockPos.add(-1, 6, -2), Blocks.CHISELED_POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-2, 6, -2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HALF, BlockHalf.TOP).with(FACING, Direction.SOUTH));
            world.setBlockState(blockPos.add(-3, 6, -2), Blocks.CHISELED_POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(3, 6, -3), Blocks.POLISHED_BLACKSTONE_BRICK_WALL.getDefaultState().with(SOUTH_SHAPE, WallShape.LOW));
            world.setBlockState(blockPos.add(-3, 6, -3), Blocks.POLISHED_BLACKSTONE_BRICK_WALL.getDefaultState().with(SOUTH_SHAPE, WallShape.LOW));

            world.setBlockState(blockPos.add(3, 7, 2), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(2, 7, 2), Blocks.POLISHED_BLACKSTONE_WALL.getDefaultState().with(EAST_SHAPE, WallShape.LOW).with(WEST_SHAPE, WallShape.LOW));
            world.setBlockState(blockPos.add(1, 7, 2), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(0, 7, 2), Blocks.POLISHED_BLACKSTONE_WALL.getDefaultState().with(EAST_SHAPE, WallShape.LOW).with(WEST_SHAPE, WallShape.LOW));
            world.setBlockState(blockPos.add(-1, 7, 2), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-2, 7, 2), Blocks.POLISHED_BLACKSTONE_WALL.getDefaultState().with(EAST_SHAPE, WallShape.LOW).with(WEST_SHAPE, WallShape.LOW));
            world.setBlockState(blockPos.add(-3, 7, 2), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(4, 7, 1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-4, 7, 1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(4, 7, 0), Blocks.POLISHED_BLACKSTONE_WALL.getDefaultState().with(NORTH_SHAPE, WallShape.LOW).with(SOUTH_SHAPE, WallShape.LOW));
            world.setBlockState(blockPos.add(-4, 7, 0), Blocks.POLISHED_BLACKSTONE_WALL.getDefaultState().with(NORTH_SHAPE, WallShape.LOW).with(SOUTH_SHAPE, WallShape.LOW));
            world.setBlockState(blockPos.add(4, 7, -1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-4, 7, -1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(3, 7, -2), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(2, 7, -2), Blocks.POLISHED_BLACKSTONE_WALL.getDefaultState().with(EAST_SHAPE, WallShape.LOW).with(WEST_SHAPE, WallShape.LOW));
            world.setBlockState(blockPos.add(1, 7, -2), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(0, 7, -2), Blocks.POLISHED_BLACKSTONE_WALL.getDefaultState().with(EAST_SHAPE, WallShape.LOW).with(WEST_SHAPE, WallShape.LOW));
            world.setBlockState(blockPos.add(-1, 7, -2), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-2, 7, -2), Blocks.POLISHED_BLACKSTONE_WALL.getDefaultState().with(EAST_SHAPE, WallShape.LOW).with(WEST_SHAPE, WallShape.LOW));
            world.setBlockState(blockPos.add(-3, 7, -2), Blocks.POLISHED_BLACKSTONE.getDefaultState());
        } else if (integer == 3) {
            world.setBlockState(blockPos.add(-2, -2, 3), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(-3, -2, 3), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(4,-2, 2), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(3, -2, 2), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(0,-2, 2), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(-1, -2, 2), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(-5, -2, 2), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(5, -2, 0), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(2, -2, 0), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(-1, -2, 0), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(-4, -2, 0), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(3, -2, -1), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(2, -2, -1), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(-2, -2, -1), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(-3, -2, -1), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(5, -2, -2), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(1, -2, -2), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(-1, -2, -2), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(-5, -2, -2), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(4, -2, -3), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(3, -2, -3), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(-4, -2, -3), Blocks.CRIMSON_NYLIUM.getDefaultState());
            world.setBlockState(blockPos.add(-5, -2, -3), Blocks.CRIMSON_NYLIUM.getDefaultState());

            world.setBlockState(blockPos.add(4, -1, 2), Blocks.CRIMSON_ROOTS.getDefaultState());
            world.setBlockState(blockPos.add(-1, -1, 2), Blocks.CRIMSON_ROOTS.getDefaultState());
            world.setBlockState(blockPos.add(-4, -1, 2), Blocks.CRIMSON_ROOTS.getDefaultState());
            world.setBlockState(blockPos.add(2, -1, -1), Blocks.CRIMSON_ROOTS.getDefaultState());
            world.setBlockState(blockPos.add(5, -1, -2), Blocks.CRIMSON_ROOTS.getDefaultState());
            world.setBlockState(blockPos.add(-1, -1, -2), Blocks.CRIMSON_ROOTS.getDefaultState());
            world.setBlockState(blockPos.add(-5, -1, -2), Blocks.CRIMSON_ROOTS.getDefaultState());

            world.setBlockState(blockPos.add(2, 1, 1), Blocks.SOUL_LANTERN.getDefaultState());
            world.setBlockState(blockPos.add(-2, 1, 0), Blocks.OBSIDIAN.getDefaultState());
            world.setBlockState(blockPos.add(-3, 1, 0), Blocks.CHAIN.getDefaultState());
            world.setBlockState(blockPos.add(3, 1, -1), Blocks.WEEPING_VINES.getDefaultState());
            world.setBlockState(blockPos.add(2, 1, -1), Blocks.POLISHED_BLACKSTONE_WALL.getDefaultState());
            world.setBlockState(blockPos.add(-2, 1, -1), Blocks.WEEPING_VINES.getDefaultState());

            world.setBlockState(blockPos.add(3, 2, 1), Blocks.BLACKSTONE_SLAB.getDefaultState().with(TYPE, SlabType.TOP));
            world.setBlockState(blockPos.add(2, 2, 1), Blocks.CHAIN.getDefaultState());
            world.setBlockState(blockPos.add(-2, 2, 1), Blocks.POLISHED_BLACKSTONE_WALL.getDefaultState().with(NORTH_SHAPE, WallShape.TALL));
            world.setBlockState(blockPos.add(-3, 2, 1), Blocks.WEEPING_VINES.getDefaultState());
            world.setBlockState(blockPos.add(3, 2, 0), Blocks.POLISHED_BLACKSTONE_WALL.getDefaultState());
            world.setBlockState(blockPos.add(-2, 2, 0), Blocks.OBSIDIAN.getDefaultState());
            world.setBlockState(blockPos.add(-3, 2, 0), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(3, 2, -1), Blocks.WEEPING_VINES_PLANT.getDefaultState());
            world.setBlockState(blockPos.add(2, 2, -1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-2, 2, -1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-3, 2, -1), Blocks.POLISHED_BLACKSTONE_WALL.getDefaultState().with(EAST_SHAPE, WallShape.TALL).with(SOUTH_SHAPE, WallShape.TALL));

            world.setBlockState(blockPos.add(3, 3, 1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(2, 3, 1), Blocks.POLISHED_BLACKSTONE_WALL.getDefaultState().with(NORTH_SHAPE, WallShape.TALL).with(EAST_SHAPE, WallShape.TALL));
            world.setBlockState(blockPos.add(-2, 3, 1), Blocks.BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-3, 3, 1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(3, 3, 0), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(2, 3, 0), Blocks.OBSIDIAN.getDefaultState());
            world.setBlockState(blockPos.add(-2, 3, 0), Blocks.OBSIDIAN.getDefaultState());
            world.setBlockState(blockPos.add(-3, 3, 0), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(3, 3, -1), Blocks.BASALT.getDefaultState().with(AXIS, Direction.Axis.Z));
            world.setBlockState(blockPos.add(2, 3, -1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-2, 3, -1), Blocks.BASALT.getDefaultState().with(AXIS, Direction.Axis.Z));
            world.setBlockState(blockPos.add(-3, 3, -1), Blocks.POLISHED_BASALT.getDefaultState());

            world.setBlockState(blockPos.add(3, 4, 1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(2, 4, 1), Blocks.BASALT.getDefaultState());
            world.setBlockState(blockPos.add(1, 4, 1), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(FACING, Direction.EAST).with(HALF, BlockHalf.TOP));
            world.setBlockState(blockPos.add(0, 4, 1), Blocks.WEEPING_VINES.getDefaultState());
            world.setBlockState(blockPos.add(-1, 4, 1), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(FACING, Direction.WEST).with(HALF, BlockHalf.TOP));
            world.setBlockState(blockPos.add(-2, 4, 1), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(-3, 4, 1), Blocks.POLISHED_BLACKSTONE_WALL.getDefaultState().with(NORTH_SHAPE, WallShape.TALL).with(EAST_SHAPE, WallShape.TALL));
            world.setBlockState(blockPos.add(3, 4, 0), Blocks.BASALT.getDefaultState().with(AXIS, Direction.Axis.X));
            world.setBlockState(blockPos.add(2, 4, 0), Blocks.OBSIDIAN.getDefaultState());
            world.setBlockState(blockPos.add(1, 4, 0), Blocks.OBSIDIAN.getDefaultState());
            world.setBlockState(blockPos.add(0, 4, 0), Blocks.OBSIDIAN.getDefaultState());
            world.setBlockState(blockPos.add(-1, 4, 0), Blocks.OBSIDIAN.getDefaultState());
            world.setBlockState(blockPos.add(-2, 4, 0), Blocks.OBSIDIAN.getDefaultState());
            world.setBlockState(blockPos.add(-3, 4, 0), Blocks.POLISHED_BASALT.getDefaultState());
            world.setBlockState(blockPos.add(3, 4, -1), Blocks.POLISHED_BLACKSTONE_WALL.getDefaultState().with(SOUTH_SHAPE, WallShape.TALL).with(WEST_SHAPE, WallShape.TALL));
            world.setBlockState(blockPos.add(2, 4, -1), Blocks.BASALT.getDefaultState().with(AXIS, Direction.Axis.Z));
            world.setBlockState(blockPos.add(1, 4, -1), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(FACING, Direction.EAST).with(HALF, BlockHalf.TOP));
            world.setBlockState(blockPos.add(-1, 4, -1), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(FACING, Direction.WEST).with(HALF, BlockHalf.TOP));
            world.setBlockState(blockPos.add(-2, 4, -1), Blocks.POLISHED_BLACKSTONE_WALL.getDefaultState().with(SOUTH_SHAPE, WallShape.TALL).with(WEST_SHAPE, WallShape.TALL).with(EAST_SHAPE, WallShape.TALL));
            world.setBlockState(blockPos.add(-3, 4, -1), Blocks.BASALT.getDefaultState());
            world.setBlockState(blockPos.add(3, 4, -3), Blocks.SOUL_LANTERN.getDefaultState().with(HANGING, true));

            world.setBlockState(blockPos.add(-3, 5, 3), Blocks.SOUL_LANTERN.getDefaultState().with(HANGING, true));
            world.setBlockState(blockPos.add(3, 5, 2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HALF, BlockHalf.TOP));
            world.setBlockState(blockPos.add(2, 5, 2), Blocks.STONE_BUTTON.getDefaultState().with(FACING, Direction.SOUTH));
            world.setBlockState(blockPos.add(1, 5, 2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HALF, BlockHalf.TOP));
            world.setBlockState(blockPos.add(0, 5, 2), Blocks.STONE_BUTTON.getDefaultState().with(FACING, Direction.SOUTH));
            world.setBlockState(blockPos.add(-1, 5, 2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HALF, BlockHalf.TOP));
            world.setBlockState(blockPos.add(-2, 5, 2), Blocks.STONE_BUTTON.getDefaultState().with(FACING, Direction.SOUTH));
            world.setBlockState(blockPos.add(-3, 5, 2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HALF, BlockHalf.TOP));
            world.setBlockState(blockPos.add(4, 5, 1), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HALF, BlockHalf.TOP).with(FACING, Direction.WEST));
            world.setBlockState(blockPos.add(3, 5, 1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(2, 5, 1), Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState());
            world.setBlockState(blockPos.add(1, 5, 1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(0, 5, 1), Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState());
            world.setBlockState(blockPos.add(-1, 5, 1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-2, 5, 1), Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState());
            world.setBlockState(blockPos.add(-3, 5, 1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-4, 5, 1), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HALF, BlockHalf.TOP).with(FACING, Direction.EAST));
            world.setBlockState(blockPos.add(4, 5, 0), Blocks.STONE_BUTTON.getDefaultState().with(FACING, Direction.EAST));
            world.setBlockState(blockPos.add(3, 5, 0), Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState());
            for (BlockPos pos: BlockPos.iterate(blockPos.add(2, 5, 0), blockPos.add(-2, 5, 0))) {
                world.setBlockState(pos, Blocks.POLISHED_BLACKSTONE.getDefaultState());
            }
            world.setBlockState(blockPos.add(-3, 5, 0), Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState());
            world.setBlockState(blockPos.add(-4, 5, 0), Blocks.STONE_BUTTON.getDefaultState().with(FACING, Direction.WEST));
            world.setBlockState(blockPos.add(4, 5, -1), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HALF, BlockHalf.TOP).with(FACING, Direction.WEST));
            world.setBlockState(blockPos.add(3, 5, -1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(2, 5, -1), Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState());
            world.setBlockState(blockPos.add(1, 5, -1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(0, 5, -1), Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState());
            world.setBlockState(blockPos.add(-1, 5, -1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-2, 5, -1), Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState());
            world.setBlockState(blockPos.add(-3, 5, -1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-4, 5, -1), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HALF, BlockHalf.TOP).with(FACING, Direction.EAST));
            world.setBlockState(blockPos.add(3, 5, -2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HALF, BlockHalf.TOP).with(FACING, Direction.SOUTH));
            world.setBlockState(blockPos.add(2, 5, -2), Blocks.STONE_BUTTON.getDefaultState());
            world.setBlockState(blockPos.add(1, 5, -2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HALF, BlockHalf.TOP).with(FACING, Direction.SOUTH));
            world.setBlockState(blockPos.add(0, 5, -2), Blocks.STONE_BUTTON.getDefaultState());
            world.setBlockState(blockPos.add(-1, 5, -2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HALF, BlockHalf.TOP).with(FACING, Direction.SOUTH));
            world.setBlockState(blockPos.add(-2, 5, -2), Blocks.STONE_BUTTON.getDefaultState());
            world.setBlockState(blockPos.add(-3, 5, -2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HALF, BlockHalf.TOP).with(FACING, Direction.SOUTH));
            world.setBlockState(blockPos.add(3, 5, -3), Blocks.CHAIN.getDefaultState());
            world.setBlockState(blockPos.add(-3, 5, -3), Blocks.CHAIN.getDefaultState());

            world.setBlockState(blockPos.add(3, 6, 3), Blocks.POLISHED_BLACKSTONE_BRICK_WALL.getDefaultState().with(NORTH_SHAPE, WallShape.LOW));
            world.setBlockState(blockPos.add(-3, 6, 3), Blocks.POLISHED_BLACKSTONE_BRICK_WALL.getDefaultState().with(NORTH_SHAPE, WallShape.LOW));
            world.setBlockState(blockPos.add(3, 6, 2), Blocks.CHISELED_POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(2, 6, 2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HALF, BlockHalf.TOP));
            world.setBlockState(blockPos.add(1, 6, 2), Blocks.CHISELED_POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(0, 6, 2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HALF, BlockHalf.TOP));
            world.setBlockState(blockPos.add(-1, 6, 2), Blocks.CHISELED_POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-2, 6, 2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HALF, BlockHalf.TOP));
            world.setBlockState(blockPos.add(-3, 6, 2), Blocks.CHISELED_POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(4, 6, 1), Blocks.CHISELED_POLISHED_BLACKSTONE.getDefaultState());
            for (BlockPos pos: BlockPos.iterate(blockPos.add(3, 6, 1), blockPos.add(-3, 6, -1))) {
                world.setBlockState(pos, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState());
            }
            world.setBlockState(blockPos.add(-4, 6, 1), Blocks.CHISELED_POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(4, 6, 0), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(FACING, Direction.WEST).with(HALF, BlockHalf.TOP));
            world.setBlockState(blockPos.add(-4, 6, 0), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(FACING, Direction.EAST).with(HALF, BlockHalf.TOP));
            world.setBlockState(blockPos.add(4, 6, -1), Blocks.CHISELED_POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-4, 6, -1), Blocks.CHISELED_POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(3, 6, -2), Blocks.CHISELED_POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(2, 6, -2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HALF, BlockHalf.TOP).with(FACING, Direction.SOUTH));
            world.setBlockState(blockPos.add(1, 6, -2), Blocks.CHISELED_POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(0, 6, -2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HALF, BlockHalf.TOP).with(FACING, Direction.SOUTH));
            world.setBlockState(blockPos.add(-1, 6, -2), Blocks.CHISELED_POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-2, 6, -2), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS.getDefaultState().with(HALF, BlockHalf.TOP).with(FACING, Direction.SOUTH));
            world.setBlockState(blockPos.add(-3, 6, -2), Blocks.CHISELED_POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(3, 6, -3), Blocks.POLISHED_BLACKSTONE_BRICK_WALL.getDefaultState().with(SOUTH_SHAPE, WallShape.LOW));
            world.setBlockState(blockPos.add(-3, 6, -3), Blocks.POLISHED_BLACKSTONE_BRICK_WALL.getDefaultState().with(SOUTH_SHAPE, WallShape.LOW));

            world.setBlockState(blockPos.add(3, 7, 2), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(2, 7, 2), Blocks.POLISHED_BLACKSTONE_WALL.getDefaultState().with(EAST_SHAPE, WallShape.LOW).with(WEST_SHAPE, WallShape.LOW));
            world.setBlockState(blockPos.add(1, 7, 2), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(0, 7, 2), Blocks.POLISHED_BLACKSTONE_WALL.getDefaultState().with(EAST_SHAPE, WallShape.LOW).with(WEST_SHAPE, WallShape.LOW));
            world.setBlockState(blockPos.add(-1, 7, 2), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-2, 7, 2), Blocks.POLISHED_BLACKSTONE_WALL.getDefaultState().with(EAST_SHAPE, WallShape.LOW).with(WEST_SHAPE, WallShape.LOW));
            world.setBlockState(blockPos.add(-3, 7, 2), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(4, 7, 1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-4, 7, 1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(4, 7, 0), Blocks.POLISHED_BLACKSTONE_WALL.getDefaultState().with(NORTH_SHAPE, WallShape.LOW).with(SOUTH_SHAPE, WallShape.LOW));
            world.setBlockState(blockPos.add(-4, 7, 0), Blocks.POLISHED_BLACKSTONE_WALL.getDefaultState().with(NORTH_SHAPE, WallShape.LOW).with(SOUTH_SHAPE, WallShape.LOW));
            world.setBlockState(blockPos.add(4, 7, -1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-4, 7, -1), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(3, 7, -2), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(2, 7, -2), Blocks.POLISHED_BLACKSTONE_WALL.getDefaultState().with(EAST_SHAPE, WallShape.LOW).with(WEST_SHAPE, WallShape.LOW));
            world.setBlockState(blockPos.add(1, 7, -2), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(0, 7, -2), Blocks.POLISHED_BLACKSTONE_WALL.getDefaultState().with(EAST_SHAPE, WallShape.LOW).with(WEST_SHAPE, WallShape.LOW));
            world.setBlockState(blockPos.add(-1, 7, -2), Blocks.POLISHED_BLACKSTONE.getDefaultState());
            world.setBlockState(blockPos.add(-2, 7, -2), Blocks.POLISHED_BLACKSTONE_WALL.getDefaultState().with(EAST_SHAPE, WallShape.LOW).with(WEST_SHAPE, WallShape.LOW));
            world.setBlockState(blockPos.add(-3, 7, -2), Blocks.POLISHED_BLACKSTONE.getDefaultState());
        }
    }
}