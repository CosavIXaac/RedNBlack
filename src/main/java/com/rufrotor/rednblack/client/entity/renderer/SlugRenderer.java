package com.rufrotor.rednblack.client.entity.renderer;

import com.rufrotor.rednblack.client.registry.RNBModelLayers;
import com.rufrotor.rednblack.client.entity.model.SlugModel;
import com.rufrotor.rednblack.entity.SlugEntity;
import com.rufrotor.rednblack.util.RNBIdentifier;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class SlugRenderer extends MobEntityRenderer<SlugEntity, SlugModel<SlugEntity>> {
    private static final Identifier TEXTURE = RNBIdentifier.ofRNB("textures/entity/slug/slug.png");

    public SlugRenderer(EntityRendererFactory.Context context) {
        super(context, new SlugModel<SlugEntity>(context.getPart(RNBModelLayers.SLUG)), 0.0F);
    }

    @Override
    public void render(SlugEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(SlugEntity entity) {
        return TEXTURE;
    }
}
