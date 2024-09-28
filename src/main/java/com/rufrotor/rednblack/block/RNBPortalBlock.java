package com.rufrotor.rednblack.block;

import com.rufrotor.rednblack.world.RNBWorld;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;

public class RNBPortalBlock extends Block implements Portal {
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0);
    }
    public RNBPortalBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity.canUsePortals(false)) {
            entity.tryUsePortal(this, pos);
        }

    }

    public TeleportTarget createTeleportTarget(ServerWorld world, Entity entity, BlockPos pos) {
        RegistryKey<World> registryKey = world.getRegistryKey() == RNBWorld.REDNBLACK ? World.OVERWORLD : RNBWorld.REDNBLACK;
        ServerWorld serverWorld = world.getServer().getWorld(registryKey);
        if (serverWorld == null) {
            return null;
        } else {
            boolean bl = registryKey == RNBWorld.REDNBLACK;
            Vec3d entityPos = entity.getPos();
            BlockPos blockPos = new BlockPos((int)Math.round(entityPos.x),serverWorld.getBottomY(),(int)Math.round(entityPos.z));
            System.out.println(serverWorld.getBottomY());
            while(
                    (!isAir(blockPos.up(),serverWorld)
                            || !serverWorld.getBlockState(blockPos).isFullCube(serverWorld,blockPos)
                            || !serverWorld.getBlockState(blockPos.down()).isFullCube(serverWorld,blockPos.down()))
                            && blockPos.getY() < serverWorld.getTopY()
            ){
                blockPos = blockPos.up();
                System.out.println(blockPos.getY());
            }
            Vec3d vec3d = new Vec3d(entity.getPos().x,blockPos.getY(),entity.getPos().z);
            return new TeleportTarget(serverWorld, vec3d, entity.getVelocity(), entity.getYaw(), entity.getPitch(), TeleportTarget.SEND_TRAVEL_THROUGH_PORTAL_PACKET.then(TeleportTarget.ADD_PORTAL_CHUNK_TICKET));
        }
    }

    public boolean isAir(BlockPos blockPos,World world){
        return world.getBlockState(blockPos).isAir();
    }
}
