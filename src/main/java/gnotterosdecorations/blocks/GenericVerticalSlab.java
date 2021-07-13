package gnotterosdecorations.blocks;

import net.minecraft.block.*;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

public class GenericVerticalSlab extends HorizontalFacingBlock implements Waterloggable{
    public static final EnumProperty<VerticalSlabType> TYPE = EnumProperty.of("type", VerticalSlabType.class);;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public GenericVerticalSlab(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, false).with(TYPE, VerticalSlabType.SINGLE));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TYPE, WATERLOGGED, FACING);
    }

    public FluidState getFluidState(BlockState state) {
        return (Boolean)state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext ctx) {
        Direction direction = state.get(FACING);
        if(state.get(TYPE) == VerticalSlabType.DOUBLE)
            return VoxelShapes.fullCube();
        switch(direction) {
            case NORTH:
                return VoxelShapes.cuboid(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.5f);
            case SOUTH:
                return VoxelShapes.cuboid(0.0f, 0.0f, 0.5f, 1.0f, 1.0f, 1.0f);
            case EAST:
                return VoxelShapes.cuboid(0.5f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            case WEST:
                return VoxelShapes.cuboid(0.0f, 0.0f, 0.0f, 0.5f, 1.0f, 1.0f);
            default:
                return VoxelShapes.fullCube();
        }
    }

    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        ItemStack itemStack = context.getStack();
        VerticalSlabType verticalSlabType = state.get(TYPE);
        if (verticalSlabType != VerticalSlabType.DOUBLE && itemStack.getItem() == this.asItem()) {
            if (context.canReplaceExisting()) {
                Direction Side = context.getSide();
                return state.get(FACING) == context.getSide().getOpposite();
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        BlockState blockState = ctx.getWorld().getBlockState(blockPos);
        if (blockState.isOf(this)) {
            return blockState.with(WATERLOGGED, false).with(TYPE, VerticalSlabType.DOUBLE);
        } else {
            FluidState fluidState = ctx.getWorld().getFluidState(blockPos);
            Direction direction = ctx.getSide();
            if(direction.getAxis().isHorizontal())
                return this.getDefaultState().with(FACING, direction.getOpposite()).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
            else
                return this.getDefaultState().with(FACING, ctx.getPlayerFacing()).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
        }
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        if (state.get(WATERLOGGED)) {
            world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
    }

    public boolean tryFillWithFluid(WorldAccess world, BlockPos pos, BlockState state, FluidState fluidState) {
        return state.get(TYPE) != VerticalSlabType.DOUBLE ? Waterloggable.super.tryFillWithFluid(world, pos, state, fluidState) : false;
    }

    public boolean canFillWithFluid(BlockView world, BlockPos pos, BlockState state, Fluid fluid) {
        return state.get(TYPE) != VerticalSlabType.DOUBLE ? Waterloggable.super.canFillWithFluid(world, pos, state, fluid) : false;
    }

}
