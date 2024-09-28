package com.rufrotor.rednblack.client.item;

import com.rufrotor.rednblack.registry.RNBItems;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class RNBModelPredicateProviders {

    public static void registerModelPredicateProviders(){
        registerSpearPredicate(RNBItems.STONE_SPEAR);
    }

    public static void registerSpearPredicate(Item item){
        ModelPredicateProviderRegistry.register(item,
                Identifier.ofVanilla("throwing"),
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F);
    }
}
