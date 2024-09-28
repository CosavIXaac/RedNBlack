package com.rufrotor.rednblack.block;

import com.mojang.serialization.MapCodec;
import com.rufrotor.rednblack.registry.RNBEntities;
import com.rufrotor.rednblack.entity.SeatEntity;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ChairBlock extends RNBHorizontalFacingBlock implements Waterloggable{
    public static final MapCodec<ChairBlock> CODEC = createCodec(ChairBlock::new);
    private static final double height = 0.69;

    public static final DirectionProperty FACING;
    public static final BooleanProperty WATERLOGGED;
    private static final VoxelShape LEFT_FRONT_LEG_SHAPE;
    private static final VoxelShape LEFT_BACK_LEG_SHAPE;

    private static final VoxelShape RIGHT_FRONT_LEG_SHAPE;
    private static final VoxelShape RIGHT_BACK_LEG_SHAPE;
    private static final VoxelShape SEAT_SHAPE;
    private static final VoxelShape BACKREST_SHAPE;
    private static final VoxelShape NORTH_SHAPE;
    private static final VoxelShape SOUTH_SHAPE;
    private static final VoxelShape EAST_SHAPE;
    private static final VoxelShape WEST_SHAPE;

    public ChairBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)this.getDefaultState().with(WATERLOGGED, false));
    }

    protected MapCodec<? extends ChairBlock> getCodec(){return CODEC;}

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        FluidState fluidState = ctx.getWorld().getFluidState(blockPos);
        return (BlockState)((BlockState)this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing())).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);

    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
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

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.CONSUME;
        }

        if (player.isSpectator() || player.isSneaking()) {
            return ActionResult.FAIL;
        }

        List<SeatEntity> active = world.getEntitiesByClass(SeatEntity.class, new Box(pos), Entity::hasPassengers);
        if (active == null)
            return ActionResult.FAIL;

        List<Entity> hasPassenger = new ArrayList<>();
        active.forEach(seat -> hasPassenger.add(seat.getFirstPassenger()));
        if (!active.isEmpty() && hasPassenger.stream().anyMatch(Entity::isPlayer)) {
            return ActionResult.FAIL;
        }

        SeatEntity seat = RNBEntities.SEAT.create(world);
        Vec3d seatPos = new Vec3d(pos.getX()+ 0.5,pos.getY()+ height,pos.getZ()+ 0.5);
        float yaw = state.get(FACING).getOpposite().asRotation();
        if (seat != null){
            seat.setPosition(seatPos);
            seat.setAngles(yaw,0);
            seat.setAiDisabled(true);
            player.setYaw(yaw);
            player.setBodyYaw(yaw);
            player.setHeadYaw(yaw);
            seat.setYaw(yaw);
            seat.setBodyYaw(yaw);
            seat.setHeadYaw(yaw);
        }

        if (world.spawnEntity(seat)) {
            player.startRiding(seat, true);
            return ActionResult.SUCCESS;
        }
        return ActionResult.CONSUME;
    }

    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = (Direction)state.get(FACING);
        switch (direction){
            case SOUTH -> {return SOUTH_SHAPE;}
            case EAST -> {return EAST_SHAPE;}
            case WEST -> {return WEST_SHAPE;}
            default -> {return NORTH_SHAPE;}
        }
    }

    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return (BlockState)state.with(FACING, rotation.rotate((Direction)state.get(FACING)));
    }

    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation((Direction)state.get(FACING)));
    }

    static {
        FACING = HorizontalFacingBlock.FACING;
        WATERLOGGED = Properties.WATERLOGGED;
        LEFT_FRONT_LEG_SHAPE = createCuboidShape(2.0,0.0,2.0,4.0,10.0,4.0);
        LEFT_BACK_LEG_SHAPE = createCuboidShape(2.0,0.0,12.0,4.0,10.0,14.0);
        RIGHT_FRONT_LEG_SHAPE = createCuboidShape(12.0,0.0,2.0,14.0,10.0,4.0);
        RIGHT_BACK_LEG_SHAPE = createCuboidShape(12.0,0.0,12.0,14.0,10.0,14.0);
        SEAT_SHAPE = createCuboidShape(2.0,8.0,1.0,14.0,11.0,15.0);
        BACKREST_SHAPE = createCuboidShape(2.0,11.0,12.0,14.0,21.0,15.0);
        SOUTH_SHAPE = VoxelShapes.union(LEFT_FRONT_LEG_SHAPE,LEFT_BACK_LEG_SHAPE,RIGHT_FRONT_LEG_SHAPE,RIGHT_BACK_LEG_SHAPE,SEAT_SHAPE,BACKREST_SHAPE);
        NORTH_SHAPE = rotateShape(Direction.NORTH,SOUTH_SHAPE);
        EAST_SHAPE = rotateShape(Direction.EAST,SOUTH_SHAPE);
        WEST_SHAPE = rotateShape(Direction.WEST,SOUTH_SHAPE);
    }
}
