package com.rufrotor.rednblack.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class TableBlock extends Block implements Waterloggable {
    public static final MapCodec<TableBlock> CODEC = createCodec(TableBlock::new);
    public TableBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)this.getDefaultState().with(WATERLOGGED, false));
    }
    public static final BooleanProperty WATERLOGGED;
    private static final VoxelShape LEFT_FRONT_LEG_SHAPE;
    private static final VoxelShape LEFT_BACK_LEG_SHAPE;
    private static final VoxelShape RIGHT_FRONT_LEG_SHAPE;
    private static final VoxelShape RIGHT_BACK_LEG_SHAPE;
    private static final VoxelShape TABLE_TOP_SHAPE;
    private static final VoxelShape WHOLE_SHAPE;

    protected MapCodec<? extends TableBlock> getCodec(){return CODEC;}

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        FluidState fluidState = ctx.getWorld().getFluidState(blockPos);
        return (BlockState)this.getDefaultState().with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
    }

    protected FluidState getFluidState(BlockState state) {
        return (Boolean)state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    public boolean tryFillWithFluid(WorldAccess world, BlockPos pos, BlockState state, FluidState fluidState) {
        return Waterloggable.super.tryFillWithFluid(world, pos, state, fluidState);
    }

    public boolean canFillWithFluid(@Nullable PlayerEntity player, BlockView world, BlockPos pos, BlockState state, Fluid fluid) {
        return Waterloggable.super.canFillWithFluid(player, world, pos, state, fluid);
    }

    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if ((Boolean)state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return WHOLE_SHAPE;
    }

    static {
        WATERLOGGED = Properties.WATERLOGGED;
        LEFT_FRONT_LEG_SHAPE = createCuboidShape(1.0,0.0,1.0,4.0,12.0,4.0);
        LEFT_BACK_LEG_SHAPE = createCuboidShape(1.0,0.0,12.0,4.0,12.0,15.0);
        RIGHT_FRONT_LEG_SHAPE = createCuboidShape(12.0,0.0,1.0,15.0,12.0,4.0);
        RIGHT_BACK_LEG_SHAPE = createCuboidShape(12.0,0.0,12.0,15.0,12.0,15.0);
        TABLE_TOP_SHAPE = createCuboidShape(0.0,12.0,0.0,16.0,16.0,16.0);
        WHOLE_SHAPE = VoxelShapes.union(LEFT_FRONT_LEG_SHAPE,LEFT_BACK_LEG_SHAPE,RIGHT_FRONT_LEG_SHAPE,RIGHT_BACK_LEG_SHAPE,TABLE_TOP_SHAPE);
    }
}
