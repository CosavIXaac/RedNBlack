package com.rufrotor.rednblack.client.entity.model;

import com.rufrotor.rednblack.entity.UndermanEntity;
import com.rufrotor.rednblack.registry.RNBItems;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;

public class UndermanModel<T extends UndermanEntity> extends BipedEntityModel<T> {

    public UndermanModel(ModelPart root) {
        super(root);
    }

    public void animateModel(T undermanEntity, float f, float g, float h) {
        this.rightArmPose = BipedEntityModel.ArmPose.EMPTY;
        this.leftArmPose = BipedEntityModel.ArmPose.EMPTY;
        ItemStack itemStack = undermanEntity.getStackInHand(Hand.MAIN_HAND);
        if (itemStack.isOf(RNBItems.STONE_SPEAR) && undermanEntity.isAttacking() && undermanEntity.throwSpearAttack()) {
            if (undermanEntity.getMainArm() == Arm.RIGHT) {
                this.rightArmPose = BipedEntityModel.ArmPose.THROW_SPEAR;
            } else {
                this.leftArmPose = BipedEntityModel.ArmPose.THROW_SPEAR;
            }
        }

        super.animateModel(undermanEntity, f, g, h);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0.0F);
        ModelPartData modelPartData = modelData.getRoot();
        return TexturedModelData.of(modelData, 64, 64);
    }
}
