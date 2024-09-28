package com.rufrotor.rednblack.item.rnbitem;

import com.rufrotor.rednblack.world.RNBWorld;
import net.minecraft.block.Portal;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;

import java.util.Timer;
import java.util.TimerTask;

public class OrbFruitItem extends Item implements Portal {

    public OrbFruitItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        FoodComponent foodComponent = stack.get(DataComponentTypes.FOOD);
        if (user.canUsePortals(false)) {
            Timer timer = new Timer();
            Portal portal = this;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    user.tryUsePortal(portal, user.getBlockPos());
                }
            }, 500);
        }
        return foodComponent != null ? user.eatFood(world, stack, foodComponent) : stack;
    }

    public TeleportTarget createTeleportTarget(ServerWorld world, Entity entity, BlockPos pos) {
        RegistryKey<World> registryKey = world.getRegistryKey() == RNBWorld.REDNBLACK ? World.OVERWORLD : RNBWorld.REDNBLACK;
        ServerWorld serverWorld = world.getServer().getWorld(registryKey);
        if (serverWorld == null) {
            return null;
        } else {
            boolean bl = registryKey == RNBWorld.REDNBLACK;
            Vec3d entityPos = entity.getPos();
            BlockPos blockPos = new BlockPos((int)Math.round(entityPos.x),serverWorld.getSeaLevel(),(int)Math.round(entityPos.z));
            while(
                    (isSuitable(serverWorld,blockPos.up())
                            || isSuitable(serverWorld,blockPos)
                            || serverWorld.getBlockState(blockPos.down()).isAir())
                            && blockPos.getY() < serverWorld.getTopY() - 120
            ){
                blockPos = blockPos.up();
            }
            Vec3d vec3d = new Vec3d(entity.getPos().x,blockPos.getY(),entity.getPos().z);
            return new TeleportTarget(serverWorld, vec3d, entity.getVelocity(), entity.getYaw(), entity.getPitch(), TeleportTarget.SEND_TRAVEL_THROUGH_PORTAL_PACKET.then(TeleportTarget.ADD_PORTAL_CHUNK_TICKET));
        }
    }

    private static boolean isSuitable(World world,BlockPos pos){
        return world.getBlockState(pos).isFullCube(world,pos) && world.getBlockState(pos).isSolidBlock(world,pos);
    }
}
