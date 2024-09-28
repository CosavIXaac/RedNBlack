package com.rufrotor.rednblack.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class RNBFoodComponents {
    public static final FoodComponent SOUR_BERRIES = (new FoodComponent.Builder()).nutrition(2).saturationModifier(0.1F).build();
    public static final FoodComponent ORB_FRUIT = (new FoodComponent.Builder()).nutrition(2).saturationModifier(0.2F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1), 1.0F).alwaysEdible().build();
    public static final FoodComponent MOTH_PASTE = (new FoodComponent.Builder()).nutrition(2).saturationModifier(-0.5F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 300, 0), 1.0F).alwaysEdible().build();

    public static void registerFoodComponents() {}
}
