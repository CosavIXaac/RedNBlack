package com.rufrotor.rednblack.world.gen.feature;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class RNBFeatures {
    public static void registerBiomeModifications() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.JUNGLE,BiomeKeys.SPARSE_JUNGLE,BiomeKeys.DARK_FOREST), GenerationStep.Feature.VEGETAL_DECORATION,
                RNBPlacedFeatures.PATCH_SOUR_BERRY_BUSH.key());
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.TAIGA,BiomeKeys.FOREST,BiomeKeys.BIRCH_FOREST,BiomeKeys.OLD_GROWTH_BIRCH_FOREST,BiomeKeys.PLAINS), GenerationStep.Feature.VEGETAL_DECORATION,
                RNBPlacedFeatures.DECAYED_TREE_RARE.key());
    }
}
