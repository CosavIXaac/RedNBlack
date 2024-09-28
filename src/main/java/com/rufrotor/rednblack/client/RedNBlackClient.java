package com.rufrotor.rednblack.client;

import com.rufrotor.rednblack.client.item.RNBModelPredicateProviders;
import com.rufrotor.rednblack.client.registry.EntityRenderers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import static com.rufrotor.rednblack.registry.RNBBlocks.assignBlocksRenderLayer;

@Environment(EnvType.CLIENT)
public class RedNBlackClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        assignBlocksRenderLayer();
        RNBModelPredicateProviders.registerModelPredicateProviders();

        EntityRenderers.ClientRegisteries();
    }
}
