package com.rufrotor.rednblack.entity;


import com.rufrotor.rednblack.registry.RNBItems;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SkeletonWarriorEntity extends AbstractSkeletonEntity {
    public SkeletonWarriorEntity(EntityType<? extends AbstractSkeletonEntity> entityType, World world) {
        super(entityType, world);
    }

    private static final TrackedData<Boolean> HAS_SHIELD = DataTracker.registerData(SkeletonWarriorEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    protected void initGoals() {
        super.initGoals();
    }

    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(HAS_SHIELD, false);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SKELETON_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_SKELETON_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SKELETON_DEATH;
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.ENTITY_SKELETON_STEP;
    }

    @Override
    public boolean damage(DamageSource damageSource, float amount) {

        Entity attacker = damageSource.getAttacker();

        if (attacker != null) {
            double dx = this.getX() - attacker.getX();
            double dz = this.getZ() - attacker.getZ();
            float angle = (float) ((Math.atan2(dz, dx) * 180D) / Math.PI) - 90;

            float difference = MathHelper.abs((this.bodyYaw - angle) % 360);

            if (this.hasShield()) {
                if (isLeftHanded() && difference > 35 && difference < 160){
                    this.playSound(SoundEvents.ITEM_SHIELD_BLOCK, 1.0F, 0.6F + this.getWorld().getRandom().nextFloat() * 0.8F);
                    return false;
                } else if (difference > 200 && difference < 325){
                    this.playSound(SoundEvents.ITEM_SHIELD_BLOCK, 1.0F, 0.6F + this.getWorld().getRandom().nextFloat() * 0.8F);
                    return false;
                }
            }
        }

        return super.damage(damageSource, amount);
    }

    public boolean hasShield() {
        return this.getDataTracker().get(HAS_SHIELD);
    }

    public void setHasShield(boolean dir) {
        this.getDataTracker().set(HAS_SHIELD,dir);
        if (dir){
            this.equipStack(EquipmentSlot.OFFHAND, new ItemStack(Items.SHIELD));
        } else {
            this.equipStack(EquipmentSlot.OFFHAND, new ItemStack(Items.AIR));
        }
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("HasShield", this.hasShield());
    }


    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setHasShield(nbt.getBoolean("HasShield"));
    }


    protected void initEquipment(Random random, LocalDifficulty localDifficulty) {
        this.randomEquipment(random);
        this.equipStack(EquipmentSlot.FEET, new ItemStack(Items.IRON_BOOTS));
    }

    protected void randomEquipment(Random random){
        int weaponType = random.nextInt(3);
        switch (weaponType){
            case 1 -> this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
            case 2 -> this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(RNBItems.IRON_MACE));
            case 3 -> this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
        }
        int shieldType = random.nextInt(3);
        if (shieldType == 2) {
            this.setHasShield(true);
        }
        int helmetType = random.nextInt(5);
        switch (helmetType){
            case 1 -> this.equipStack(EquipmentSlot.HEAD, new ItemStack(RNBItems.BLACK_IRON_HELMET));
            case 2 -> this.equipStack(EquipmentSlot.HEAD, new ItemStack(Items.LEATHER_HELMET));
        }
    }

    @Nullable
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        Random random = world.getRandom();
        initEquipment(random,difficulty);
        EntityData entityData2 = super.initialize(world, difficulty, spawnReason, entityData);
        this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(2.0);
        this.updateAttackType();
        return entityData2;
    }

    public boolean tryAttack(Entity target) {
        return super.tryAttack(target);
    }

    protected PersistentProjectileEntity createArrowProjectile(ItemStack arrow, float damageModifier, @Nullable ItemStack shotFrom) {
        PersistentProjectileEntity persistentProjectileEntity = super.createArrowProjectile(arrow, damageModifier, shotFrom);
        return persistentProjectileEntity;
    }
}
