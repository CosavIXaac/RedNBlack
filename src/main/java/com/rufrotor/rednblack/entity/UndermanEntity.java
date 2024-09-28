package com.rufrotor.rednblack.entity;

import com.rufrotor.rednblack.registry.RNBItems;
import com.rufrotor.rednblack.sound.RNBSoundEvents;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.NavigationConditions;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.MobNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Difficulty;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class UndermanEntity extends HostileEntity implements RangedAttackMob {
    public UndermanEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    private static final Predicate<Difficulty> DOOR_BREAK_DIFFICULTY_CHECKER = difficulty -> difficulty == Difficulty.HARD;
    private final BreakDoorGoal breakDoorsGoal = new BreakDoorGoal(this, DOOR_BREAK_DIFFICULTY_CHECKER);
    private boolean canBreakDoors;

    private static final TrackedData<Boolean> THROW_SPEAR_ATTACK = DataTracker.registerData(UndermanEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    @Override
    protected void initGoals() {
        this.goalSelector.add(3, new SwimGoal(this));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.goalSelector.add(4, new MeleeAttackGoal(this, 1.0, false));
        this.initCustomGoals();
    }

    protected void initCustomGoals() {
        this.goalSelector.add(7, new WanderAroundFarGoal(this, 1.0));
        this.targetSelector.add(3, new RevengeGoal(this).setGroupRevenge());
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, AbstractSkeletonEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, MerchantEntity.class, false));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, IronGolemEntity.class, true));
        this.goalSelector.add(2, new UndermanEntity.ThrowSpearAttackGoal(this, 1.0, 40, 10.0F));
    }

    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(THROW_SPEAR_ATTACK, false);
    }

    public static DefaultAttributeContainer.Builder createUndermanAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 15.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3F)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_ARMOR, 4.0);
    }

    public boolean canBreakDoors() {
        return this.canBreakDoors;
    }

    public boolean throwSpearAttack() {
        return this.getDataTracker().get(THROW_SPEAR_ATTACK);
    }

    public void setCanBreakDoors(boolean canBreakDoors) {
        if (this.shouldBreakDoors() && NavigationConditions.hasMobNavigation(this)) {
            if (this.canBreakDoors != canBreakDoors) {
                this.canBreakDoors = canBreakDoors;
                ((MobNavigation)this.getNavigation()).setCanPathThroughDoors(canBreakDoors);
                if (canBreakDoors) {
                    this.goalSelector.add(1, this.breakDoorsGoal);
                } else {
                    this.goalSelector.remove(this.breakDoorsGoal);
                }
            }
        } else if (this.canBreakDoors) {
            this.goalSelector.remove(this.breakDoorsGoal);
            this.canBreakDoors = false;
        }
    }

    public void setThrowSpearAttack(boolean flag){
        this.getDataTracker().set(THROW_SPEAR_ATTACK,flag);
    }

    protected boolean shouldBreakDoors() {
        return true;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return RNBSoundEvents.ENTITY_UNDERMAN_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return RNBSoundEvents.ENTITY_UNDERMAN_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return RNBSoundEvents.ENTITY_UNDERMAN_DEATH;
    }

    @Override
    protected void initEquipment(Random random, LocalDifficulty localDifficulty) {
        this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(RNBItems.STONE_SPEAR));
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("CanBreakDoors", this.canBreakDoors());
        nbt.putBoolean("ThrowSpearAttack", this.throwSpearAttack());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setCanBreakDoors(nbt.getBoolean("CanBreakDoors"));
        this.setThrowSpearAttack(nbt.getBoolean("ThrowSpearAttack"));
    }

    @Override
    protected float getSoundVolume() {
        return 0.6F;
    }

    @Nullable
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        Random random = world.getRandom();
        this.initEquipment(random, difficulty);
        this.setThrowSpearAttack(random.nextInt(4) == 1);
        EntityData entityData2 = super.initialize(world, difficulty, spawnReason, entityData);
        return entityData2;
    }

    @Override
    public void shootAt(LivingEntity target, float pullProgress) {
        SpearProjectileEntity spearProjectileEntity = new SpearProjectileEntity(this.getWorld(), this, new ItemStack(RNBItems.STONE_SPEAR));
        double d = target.getX() - this.getX();
        double e = target.getBodyY(0.3333333333333333) - spearProjectileEntity.getY();
        double f = target.getZ() - this.getZ();
        double g = Math.sqrt(d * d + f * f);
        spearProjectileEntity.setVelocity(d, e + g * 0.2F, f, 1.6F, (float)(14 - this.getWorld().getDifficulty().getId() * 4));
        this.playSound(SoundEvents.ENTITY_DROWNED_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.getWorld().spawnEntity(spearProjectileEntity);
    }

    static class ThrowSpearAttackGoal extends ProjectileAttackGoal {
        private final UndermanEntity underman;

        public ThrowSpearAttackGoal(RangedAttackMob rangedAttackMob, double d, int i, float f) {
            super(rangedAttackMob, d, i, f);
            this.underman = (UndermanEntity) rangedAttackMob;
        }

        @Override
        public boolean canStart() {
            return super.canStart() && this.underman.getMainHandStack().isOf(RNBItems.STONE_SPEAR) && this.underman.throwSpearAttack();
        }

        @Override
        public void start() {
            super.start();
            this.underman.setAttacking(true);
            this.underman.setCurrentHand(Hand.MAIN_HAND);
        }

        @Override
        public void stop() {
            super.stop();
            this.underman.clearActiveItem();
            this.underman.setAttacking(false);
        }
    }
}

