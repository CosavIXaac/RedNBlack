package com.rufrotor.rednblack.world.gen.feature;

import com.rufrotor.rednblack.registry.RNBBlocks;
import com.rufrotor.rednblack.world.gen.component.TrunkDecorator;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class RNBTreeDecorators {
    public static final TrunkDecorator ORB_FRUIT = new TrunkDecorator(0.1F,  BlockStateProvider.of(RNBBlocks.ORB_FRUIT_BLOCK));
}
