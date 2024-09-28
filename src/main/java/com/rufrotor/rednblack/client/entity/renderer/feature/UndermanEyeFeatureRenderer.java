package com.rufrotor.rednblack.client.entity.renderer.feature;

import com.rufrotor.rednblack.client.entity.model.UndermanModel;
import com.rufrotor.rednblack.entity.UndermanEntity;
import com.rufrotor.rednblack.util.RNBIdentifier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;

@Environment(EnvType.CLIENT)
public class UndermanEyeFeatureRenderer<T extends UndermanEntity, M extends UndermanModel<T>> extends EyesFeatureRenderer<T, M> {
    private static final RenderLayer SKIN = RenderLayer.getEyes(RNBIdentifier.ofRNB("textures/entity/underman/underman_eye.png"));

    public UndermanEyeFeatureRenderer(FeatureRendererContext<T, M> featureRendererContext) {
        super(featureRendererContext);
    }

    @Override
    public RenderLayer getEyesTexture() {
        return SKIN;
    }
}
