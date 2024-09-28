package com.rufrotor.rednblack.client.entity.renderer;


import com.rufrotor.rednblack.client.registry.RNBModelLayers;
import com.rufrotor.rednblack.client.entity.model.EmptyModel;
import com.rufrotor.rednblack.entity.DustphadeEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class DustphadeRenderer extends MobEntityRenderer<DustphadeEntity, EmptyModel<DustphadeEntity>> {
    private static final Identifier TEXTURE = Identifier.ofVanilla("textures/block/stone.png");
    public DustphadeRenderer(EntityRendererFactory.Context context) {
        super(context, new EmptyModel<>(context.getPart(RNBModelLayers.DUSTPHADE)), 0.0f);
    }
    public Identifier getTexture(DustphadeEntity entity) {
        return TEXTURE;
    }
}
