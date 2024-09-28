package com.rufrotor.rednblack.entity;

import com.rufrotor.rednblack.block.MothInfestedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class MothEntity extends HostileEntity {
    public MothEntity(EntityType<? extends MothEntity> entityType, World world) {
        super(entityType, world);
    }
    protected void initGoals() {
        this.goalSelector.add(1, new PowderSnowJumpGoal(this, this.getWorld()));
        this.goalSelector.add(4, new MeleeAttackGoal(this, 1.0, false));
        this.goalSelector.add(5, new MothEntity.WanderAndInfestGoal(this));
        this.targetSelector.add(1, (new RevengeGoal(this, new Class[0])));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    public static DefaultAttributeContainer.Builder createMothAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 8.0).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0);
    }


    protected Entity.MoveEffect getMoveEffect() {
        return MoveEffect.EVENTS;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SILVERFISH_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_SILVERFISH_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SILVERFISH_DEATH;
    }

    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.15F, 1.0F);
    }

    public boolean damage(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else {
            return super.damage(source, amount);
        }
    }

    public void tick() {
        this.bodyYaw = this.getYaw();
        super.tick();
    }

    public void setBodyYaw(float bodyYaw) {
        this.setYaw(bodyYaw);
        super.setBodyYaw(bodyYaw);
    }

    public float getPathfindingFavor(BlockPos pos, WorldView world) {
        return MothInfestedBlock.isInfestable(world.getBlockState(pos.down())) ? 10.0F : super.getPathfindingFavor(pos, world);
    }

    public static boolean canSpawn(EntityType<MothEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        if (canSpawnIgnoreLightLevel(type, world, spawnReason, pos, random)) {
            PlayerEntity playerEntity = world.getClosestPlayer((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, 5.0, true);
            return playerEntity == null;
        } else {
            return false;
        }
    }

    static class WanderAndInfestGoal extends WanderAroundGoal {
        @Nullable
        private Direction direction;
        private boolean canInfest;

        public WanderAndInfestGoal(MothEntity moth) {
            super(moth, 1.0, 10);
            this.setControls(EnumSet.of(Control.MOVE));
        }

        public boolean canStart() {
            if (this.mob.getTarget() != null) {
                return false;
            } else if (!this.mob.getNavigation().isIdle()) {
                return false;
            } else {
                Random random = this.mob.getRandom();
                if (this.mob.getWorld().getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING) && random.nextInt(toGoalTicks(10)) == 0) {
                    this.direction = Direction.random(random);
                    BlockPos blockPos = BlockPos.ofFloored(this.mob.getX(), this.mob.getY() + 0.5, this.mob.getZ()).offset(this.direction);
                    BlockState blockState = this.mob.getWorld().getBlockState(blockPos);
                    if (MothInfestedBlock.isInfestable(blockState)) {
                        this.canInfest = true;
                        return true;
                    }
                }

                this.canInfest = false;
                return super.canStart();
            }
        }

        public boolean shouldContinue() {
            return this.canInfest ? false : super.shouldContinue();
        }

        public void start() {
            if (!this.canInfest) {
                super.start();
            } else {
                WorldAccess worldAccess = this.mob.getWorld();
                BlockPos blockPos = BlockPos.ofFloored(this.mob.getX(), this.mob.getY() + 0.5, this.mob.getZ()).offset(this.direction);
                BlockState blockState = worldAccess.getBlockState(blockPos);
                if (MothInfestedBlock.isInfestable(blockState)) {
                    worldAccess.setBlockState(blockPos, MothInfestedBlock.fromRegularState(blockState), 3);
                    this.mob.playSpawnEffects();
                    this.mob.discard();
                }

            }
        }
    }
}
