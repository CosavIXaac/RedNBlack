package com.rufrotor.rednblack.client.entity.renderer;


import com.rufrotor.rednblack.client.registry.RNBModelLayers;
import com.rufrotor.rednblack.entity.SeatEntity;
import com.rufrotor.rednblack.client.entity.model.EmptyModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class SeatRenderer extends MobEntityRenderer<SeatEntity, EmptyModel<SeatEntity>> {
    private static final Identifier EMPTY_TEXTURE = Identifier.ofVanilla("textures/block/stone.png");
    public SeatRenderer(EntityRendererFactory.Context context) {
        super(context, new EmptyModel<>(context.getPart(RNBModelLayers.EMPTY)), 0f);
    }
    public Identifier getTexture(SeatEntity entity) {
        return EMPTY_TEXTURE;
    }
}
