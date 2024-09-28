package com.rufrotor.rednblack.util;

import net.minecraft.client.model.ModelPart;

import static net.minecraft.util.math.MathHelper.RADIANS_PER_DEGREE;

public class AnimationUtil {
    public static void rotateHead(float headYaw, float headPitch, ModelPart head) {
        head.yaw = headYaw * RADIANS_PER_DEGREE;
        head.pitch = headPitch * RADIANS_PER_DEGREE;
    }
}
