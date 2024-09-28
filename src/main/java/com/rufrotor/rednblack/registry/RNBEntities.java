package com.rufrotor.rednblack.registry;

import com.rufrotor.rednblack.entity.*;
import com.rufrotor.rednblack.util.RNBIdentifier;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class RNBEntities {
    public static final EntityType<SeatEntity> SEAT = register("seat",
            EntityType.Builder.create(SeatEntity::new,SpawnGroup.MISC)
                    .dimensions(0, 0));

    public static final EntityType<SlugEntity> SLUG = register("slug",
            EntityType.Builder.create(SlugEntity::new,SpawnGroup.AMBIENT)
                    .dimensions(0.3F, 0.3F).eyeHeight(0.45F).maxTrackingRange(5));

    public static final EntityType<MothEntity> MOTH = register("moth",
            EntityType.Builder.create(MothEntity::new,SpawnGroup.MONSTER)
                    .dimensions(0.7F, 0.3F).eyeHeight(0.13F).passengerAttachments(0.2375F).maxTrackingRange(8));

    public static final EntityType<SkeletonWarriorEntity> SKELETON_WARRIOR = register(
            "skeleton_warrior",
            EntityType.Builder.create(SkeletonWarriorEntity::new, SpawnGroup.MONSTER)
                    .dimensions(0.6F, 1.99F).eyeHeight(1.74F).vehicleAttachment(-0.7F).maxTrackingRange(8));

    public static final EntityType<UndermanEntity> UNDERMAN = register(
            "underman",
            EntityType.Builder.create(UndermanEntity::new, SpawnGroup.MONSTER)
                    .dimensions(0.6F, 1.87F).eyeHeight(1.62F).vehicleAttachment(-0.7F).maxTrackingRange(8));

    public static final EntityType<DustphadeEntity> DUSTPHADE = register(
            "dustphade",
            EntityType.Builder.create(DustphadeEntity::new, SpawnGroup.MONSTER)
                    .dimensions(0.6F, 1.4F).eyeHeight(1.2F).maxTrackingRange(8));

    public static final EntityType<SpearProjectileEntity> SPEAR_PROJECTILE = register(
            "spear_projectile",
            EntityType.Builder.<SpearProjectileEntity>create(SpearProjectileEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5F, 0.5F).eyeHeight(0.13F).maxTrackingRange(4).trackingTickInterval(20));

    public static void registerEntities(){
        FabricDefaultAttributeRegistry.register(SEAT, SeatEntity.createMobAttributes());
        FabricDefaultAttributeRegistry.register(SLUG, SlugEntity.createSlugAttributes());
        FabricDefaultAttributeRegistry.register(MOTH, MothEntity.createMothAttributes());
        FabricDefaultAttributeRegistry.register(SKELETON_WARRIOR, SkeletonWarriorEntity.createAbstractSkeletonAttributes());
        FabricDefaultAttributeRegistry.register(UNDERMAN, UndermanEntity.createUndermanAttributes());
        FabricDefaultAttributeRegistry.register(DUSTPHADE, DustphadeEntity.createDustphadeAttributes());
    }

    private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> type) {
        return (EntityType)Registry.register(Registries.ENTITY_TYPE, RNBIdentifier.ofRNB(id), type.build());
    }
}
