package com.rufrotor.rednblack.world;

import com.rufrotor.rednblack.util.RNBIdentifier;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class RNBWorld {
    public static final RegistryKey<World> REDNBLACK;

    static {
        REDNBLACK = RegistryKey.of(RegistryKeys.WORLD, RNBIdentifier.ofRNB("rednblack"));
    }
}
