package com.rufrotor.rednblack;

import com.rufrotor.rednblack.registry.*;
import com.rufrotor.rednblack.item.RNBFoodComponents;
import com.rufrotor.rednblack.sound.RNBSoundEvents;
import com.rufrotor.rednblack.world.gen.component.RNBTreeDecoratorType;
import com.rufrotor.rednblack.world.gen.feature.RNBFeatures;
import com.rufrotor.rednblack.world.gen.feature.RNBPlacedFeatures;
import net.fabricmc.api.ModInitializer;

public class RedNBlack implements ModInitializer {
    public static final String MOD_ID = "rednblack";

    @Override
    public void onInitialize() {

        RNBSoundEvents.init();

        RNBFoodComponents.registerFoodComponents();
        RNBItems.registerItems();
        RNBBlocks.registerBlocks();
        RNBEntities.registerEntities();

        RNBItemGroups.registerGroups();
        RNBTreeDecoratorType.registerTreeDecoratorType();
        RNBPlacedFeatures.initialize();

        RNBFeatures.registerBiomeModifications();
    }

}
