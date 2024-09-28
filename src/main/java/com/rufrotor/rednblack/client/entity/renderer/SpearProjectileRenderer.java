package com.rufrotor.rednblack.client.entity.renderer;

import com.rufrotor.rednblack.entity.SpearProjectileEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class SpearProjectileRenderer extends EntityRenderer<SpearProjectileEntity> {
    private final ItemRenderer itemRenderer;
    private final float scale;
    private final boolean lit;

    public SpearProjectileRenderer(EntityRendererFactory.Context dispatcher, ItemRenderer itemRenderer, float scale, boolean lit) {
        super(dispatcher);
        this.itemRenderer = itemRenderer;
        this.scale = scale;
        this.lit = lit;
    }

    public SpearProjectileRenderer(EntityRendererFactory.Context dispatcher, ItemRenderer itemRenderer) {
        this(dispatcher, itemRenderer, 1.0F, false);
    }

    @Override
    protected int getBlockLight(SpearProjectileEntity entity, BlockPos blockPos) {
        return this.lit ? 15 : super.getBlockLight(entity, blockPos);
    }

    @Override
    public void render(SpearProjectileEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        if (entity.age >= 2 || this.dispatcher.camera.getFocusedEntity().squaredDistanceTo(entity) >= 12.25D) {
            matrices.push();
            matrices.scale(0.9F, 0.9F, 0.9F);

            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(tickDelta, entity.prevYaw, entity.getYaw()) - 90.0F));
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(tickDelta, entity.prevPitch, entity.getPitch()) + 45.0F));
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(180));

            MinecraftClient.getInstance().getItemRenderer().renderItem(entity.getWeaponStack(), ModelTransformationMode.FIXED, light, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 0);

            matrices.pop();
            super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
        }
    }

    @Override
    public Identifier getTexture(SpearProjectileEntity entity) {
        return Identifier.ofVanilla("textures/atlas/blocks.png");
    }

    public ItemRenderer getItemRenderer() {
        return itemRenderer;
    }

    public float getScale() {
        return scale;
    }
}
