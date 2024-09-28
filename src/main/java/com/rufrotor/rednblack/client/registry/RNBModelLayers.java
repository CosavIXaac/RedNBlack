package com.rufrotor.rednblack.client.registry;

import com.rufrotor.rednblack.util.RNBIdentifier;
import net.minecraft.client.render.entity.model.EntityModelLayer;

public class RNBModelLayers {
    public static final EntityModelLayer EMPTY = registerMain("empty");
    public static final EntityModelLayer SLUG = registerMain("slug");
    public static final EntityModelLayer MOTH = registerMain("moth");
    public static final EntityModelLayer SKELETON_WARRIOR = registerMain("skeleton_warrior");
    public static final EntityModelLayer SKELETON_WARRIOR_INNER_ARMOR = createInnerArmor("skeleton_warrior");
    public static final EntityModelLayer SKELETON_WARRIOR_OUTER_ARMOR = createOuterArmor("skeleton_warrior");
    public static final EntityModelLayer UNDERMAN = registerMain("underman");
    public static final EntityModelLayer UNDERMAN_INNER_ARMOR = createInnerArmor("underman");
    public static final EntityModelLayer UNDERMAN_OUTER_ARMOR = createOuterArmor("underman");
    public static final EntityModelLayer DUSTPHADE = registerMain("dustphade");

    private static EntityModelLayer registerMain(String id){
        return new EntityModelLayer(RNBIdentifier.ofRNB(id), "main");
    }

    private static EntityModelLayer createInnerArmor(String id) {
        return new EntityModelLayer(RNBIdentifier.ofRNB(id), "inner_armor");
    }

    private static EntityModelLayer createOuterArmor(String id) {
        return new EntityModelLayer(RNBIdentifier.ofRNB(id), "outer_armor");
    }
}
