package com.rufrotor.rednblack.client.entity.renderer;

import com.rufrotor.rednblack.client.registry.RNBModelLayers;
import com.rufrotor.rednblack.entity.SkeletonWarriorEntity;
import com.rufrotor.rednblack.util.RNBIdentifier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.SkeletonEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class SkeletonWarriorRenderer extends SkeletonEntityRenderer<SkeletonWarriorEntity> {
    private static final Identifier TEXTURE = RNBIdentifier.ofRNB("textures/entity/skeleton/skeleton_warrior.png");

    public SkeletonWarriorRenderer(EntityRendererFactory.Context context) {
        super(context, RNBModelLayers.SKELETON_WARRIOR, RNBModelLayers.SKELETON_WARRIOR_INNER_ARMOR, RNBModelLayers.SKELETON_WARRIOR_OUTER_ARMOR);
    }

    public Identifier getTexture(SkeletonWarriorEntity skeletonWarriorEntity) {
        return TEXTURE;
    }
}

