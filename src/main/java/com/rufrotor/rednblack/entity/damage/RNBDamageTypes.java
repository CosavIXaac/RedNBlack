package com.rufrotor.rednblack.entity.damage;

import com.rufrotor.rednblack.util.RNBIdentifier;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class RNBDamageTypes {
    public static final RegistryKey<DamageType> UNDER_THORN = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, RNBIdentifier.ofRNB("under_thorn"));
}
