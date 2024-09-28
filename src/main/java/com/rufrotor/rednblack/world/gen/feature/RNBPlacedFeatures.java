package com.rufrotor.rednblack.world.gen.feature;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;

import static com.rufrotor.rednblack.util.RNBIdentifier.ofRNB;

public enum RNBPlacedFeatures {
    PATCH_SOUR_BERRY_BUSH("patch_sour_berry_bush"),

    DECAYED_TREE_RARE("decayed_tree_rare");

    private final Identifier featureIdentifier;
    private RegistryKey<ConfiguredFeature<?, ?>> configuredFeatureRegistryKey;
    private RegistryKey<PlacedFeature> featureRegistryKey;

    RNBPlacedFeatures(String featurePathName) {
        this.featureIdentifier = ofRNB(featurePathName);
    }

    public static void initialize() {
        for (RNBPlacedFeatures value : values()) {
            value.configuredFeatureRegistryKey = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, value.featureIdentifier);
            value.featureRegistryKey = RegistryKey.of(RegistryKeys.PLACED_FEATURE, value.featureIdentifier);
        }
    }

    public RegistryKey<PlacedFeature> key() {
        return featureRegistryKey;
    }
}