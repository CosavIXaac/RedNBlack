package com.rufrotor.rednblack.client.entity.model;

import com.rufrotor.rednblack.entity.MothEntity;
import com.rufrotor.rednblack.util.AnimationUtil;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class MothModel<T extends MothEntity> extends SinglePartEntityModel<T> {
    private final ModelPart Moth;
    private final ModelPart Head;
    private final ModelPart Neck;
    private final ModelPart LeftLeg3;
    private final ModelPart RightLeg3;
    private final ModelPart Body;
    private final ModelPart LeftLeg1;
    private final ModelPart LeftLeg2;
    private final ModelPart RightLeg1;
    private final ModelPart RightLeg2;
    public MothModel(ModelPart root) {
        this.Moth = root.getChild("Moth");
        this.Head = Moth.getChild("Head");
        this.Neck = Moth.getChild("Neck");
        this.LeftLeg3 = Neck.getChild("LeftLeg3");
        this.RightLeg3 = Neck.getChild("RightLeg3");
        this.Body = Moth.getChild("Body");
        this.LeftLeg1 = Body.getChild("LeftLeg1");
        this.LeftLeg2 = Body.getChild("LeftLeg2");
        this.RightLeg1 = Body.getChild("RightLeg1");
        this.RightLeg2 = Body.getChild("RightLeg2");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData Moth = modelPartData.addChild("Moth", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 22.0F, -2.0F));

        ModelPartData Head = Moth.addChild("Head", ModelPartBuilder.create().uv(12, 20).cuboid(-1.5F, -1.0F, -2.0F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.0F, -3.0F));

        ModelPartData cube_r1 = Head.addChild("cube_r1", ModelPartBuilder.create().uv(14, 12).cuboid(-3.0F, -3.0F, 0.0F, 7.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, -1.0F, -2.0F, 0.6981F, 0.0F, 0.0F));

        ModelPartData Neck = Moth.addChild("Neck", ModelPartBuilder.create().uv(0, 12).cuboid(-2.5F, -2.0F, -4.0F, 5.0F, 3.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.0F, 1.0F));

        ModelPartData LeftLeg3 = Neck.addChild("LeftLeg3", ModelPartBuilder.create(), ModelTransform.pivot(2.5F, 0.0F, -0.5F));

        ModelPartData cube_r2 = LeftLeg3.addChild("cube_r2", ModelPartBuilder.create().uv(20, 0).cuboid(-0.5F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.5F, 0.0F, 0.0F, 0.0F, 0.3927F));

        ModelPartData RightLeg3 = Neck.addChild("RightLeg3", ModelPartBuilder.create(), ModelTransform.pivot(-2.5F, 0.0F, -0.5F));

        ModelPartData cube_r3 = RightLeg3.addChild("cube_r3", ModelPartBuilder.create().uv(18, 15).cuboid(-5.5F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.5F, 0.0F, 0.0F, 0.0F, -0.3927F));

        ModelPartData Body = Moth.addChild("Body", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -3.5F, 0.0F, 6.0F, 4.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 1.0F));

        ModelPartData LeftLeg1 = Body.addChild("LeftLeg1", ModelPartBuilder.create(), ModelTransform.pivot(3.0F, -1.0F, 5.5F));

        ModelPartData cube_r4 = LeftLeg1.addChild("cube_r4", ModelPartBuilder.create().uv(20, 4).cuboid(-1.0F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.5F, 0.0F, 0.0F, 0.0F, 0.3927F));

        ModelPartData LeftLeg2 = Body.addChild("LeftLeg2", ModelPartBuilder.create(), ModelTransform.pivot(3.0F, -1.0F, 1.5F));

        ModelPartData cube_r5 = LeftLeg2.addChild("cube_r5", ModelPartBuilder.create().uv(20, 2).cuboid(-1.0F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.5F, 0.0F, 0.0F, 0.0F, 0.3927F));

        ModelPartData RightLeg1 = Body.addChild("RightLeg1", ModelPartBuilder.create(), ModelTransform.pivot(-3.0F, -1.0F, 5.5F));

        ModelPartData cube_r6 = RightLeg1.addChild("cube_r6", ModelPartBuilder.create().uv(0, 19).cuboid(-5.0F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.5F, 0.0F, 0.0F, 0.0F, -0.3927F));

        ModelPartData RightLeg2 = Body.addChild("RightLeg2", ModelPartBuilder.create(), ModelTransform.pivot(-3.0F, -1.0F, 1.5F));

        ModelPartData cube_r7 = RightLeg2.addChild("cube_r7", ModelPartBuilder.create().uv(17, 18).cuboid(-5.0F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.5F, 0.0F, 0.0F, 0.0F, -0.3927F));
        return TexturedModelData.of(modelData, 64, 64);
    }


    @Override
    public ModelPart getPart() {
        return Moth;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        super.render(matrices, vertices, light, overlay, color);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        AnimationUtil.rotateHead(headYaw,headPitch,this.Head);
        float i = MathHelper.cos(limbAngle * 1.4F) * -0.8F * limbDistance;
        float j = MathHelper.cos(limbAngle * 1.4F - 60) * -0.2F * limbDistance;
        float k = MathHelper.cos(limbAngle * 2.8F - 60) * -0.2F * limbDistance;
        LeftLeg3.yaw = 0.8F;
        RightLeg3.yaw = -0.8F;
        LeftLeg2.yaw = 0;
        RightLeg2.yaw = 0;
        LeftLeg1.yaw = -0.4F;
        RightLeg1.yaw = 0.4F;
        Body.pitch = 0;
        Body.roll = 0;
        LeftLeg3.yaw += i;
        RightLeg3.yaw += i;
        LeftLeg2.yaw -= i;
        RightLeg2.yaw -= i;
        LeftLeg1.yaw += i;
        RightLeg1.yaw += i;
        Body.pitch += j;
        Body.roll += k;
    }
}