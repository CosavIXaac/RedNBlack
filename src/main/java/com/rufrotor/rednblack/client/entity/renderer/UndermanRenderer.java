package com.rufrotor.rednblack.client.entity.renderer;

import com.rufrotor.rednblack.client.registry.RNBModelLayers;
import com.rufrotor.rednblack.client.entity.model.UndermanModel;
import com.rufrotor.rednblack.client.entity.renderer.feature.UndermanEyeFeatureRenderer;
import com.rufrotor.rednblack.entity.UndermanEntity;
import com.rufrotor.rednblack.util.RNBIdentifier;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.util.Identifier;

public class UndermanRenderer extends BipedEntityRenderer<UndermanEntity,UndermanModel<UndermanEntity>> {
    private static final Identifier TEXTURE = RNBIdentifier.ofRNB("textures/entity/underman/underman.png");

    public UndermanRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new UndermanModel<UndermanEntity>(ctx.getPart(RNBModelLayers.UNDERMAN)), 0.5F);
        this.addFeature(new ArmorFeatureRenderer<>(this, new UndermanModel<UndermanEntity>(ctx.getPart(RNBModelLayers.UNDERMAN_INNER_ARMOR)), new UndermanModel<UndermanEntity>(ctx.getPart(RNBModelLayers.UNDERMAN_OUTER_ARMOR)), ctx.getModelManager()));
        this.addFeature(new UndermanEyeFeatureRenderer<>(this));
    }

    @Override
    public Identifier getTexture(UndermanEntity entity) {
        return TEXTURE;
    }
}
