package com.rufrotor.rednblack.client.entity.model;

import com.rufrotor.rednblack.entity.SlugEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.math.MathHelper;

public class SlugModel<S extends AnimalEntity> extends SinglePartEntityModel<SlugEntity> {
    private final ModelPart Slug;
    private final ModelPart eyes;
    public SlugModel(ModelPart root) {
        this.Slug = root.getChild("Slug");
        this.eyes = Slug.getChild("eyes");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData Slug = modelPartData.addChild("Slug", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -2.0F, -4.0F, 2.0F, 2.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData eyes = Slug.addChild("eyes", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -2.0F, 0.0F, 4.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.0F, -4.0F, 0.3927F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        super.render(matrices, vertices, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return Slug;
    }

    @Override
    public void setAngles(SlugEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        float i = MathHelper.sin(limbAngle * 2.4F) * 3F * limbDistance;
        float j = MathHelper.cos(limbAngle * 2.4F - 150) * 5F * limbDistance;
        float k = MathHelper.cos(limbAngle * 2.4F - 150) * 30F * limbDistance;
        Slug.pivotZ = 0;
        Slug.pivotZ -= k;
        Slug.zScale = 1;
        Slug.zScale += i;
        eyes.pitch = 0;
        eyes.pitch += j;
    }
}
