package com.rufrotor.rednblack.entity;

import com.rufrotor.rednblack.block.ChairBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SeatEntity extends MobEntity {

    public SeatEntity(EntityType<? extends SeatEntity> type, World world) {
        super(type, world);
    }

    @Override
    public void tick()
    {
        if (!this.hasPassengers()) {
            if (!this.getWorld().isClient){
                this.discard();
            }
        }
        else if (this.getWorld().getBlockState(this.getBlockPos()).getBlock() instanceof ChairBlock){
            super.tick();
        }
        else {
            if (!this.getWorld().isClient){
                this.removeAllPassengers();
                this.discard();
            }
        }
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        this.setVelocity(Vec3d.ZERO);
    }

    public static DefaultAttributeContainer.Builder createMobAttributes(){
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 0);
    }

}
