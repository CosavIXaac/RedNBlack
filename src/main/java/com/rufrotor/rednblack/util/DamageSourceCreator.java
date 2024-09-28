package com.rufrotor.rednblack.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;

public class DamageSourceCreator {
    public static DamageSource createDamageSource(Entity entity, RegistryKey<DamageType> type){
        return new DamageSource(entity.getDamageSources().registry.entryOf(type));
    }
}
