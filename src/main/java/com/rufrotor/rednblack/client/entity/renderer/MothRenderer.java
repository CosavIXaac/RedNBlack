package com.rufrotor.rednblack.client.entity.renderer;


import com.rufrotor.rednblack.client.registry.RNBModelLayers;
import com.rufrotor.rednblack.client.entity.model.MothModel;
import com.rufrotor.rednblack.entity.MothEntity;
import com.rufrotor.rednblack.util.RNBIdentifier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class MothRenderer extends MobEntityRenderer<MothEntity, MothModel<MothEntity>> {
    private static final Identifier MOTH_TEXTURE = RNBIdentifier.ofRNB("textures/entity/moth/moth.png");
    public MothRenderer(EntityRendererFactory.Context context) {
        super(context, new MothModel<>(context.getPart(RNBModelLayers.MOTH)), 0.25f);
    }
    public Identifier getTexture(MothEntity entity) {
        return MOTH_TEXTURE;
    }
}
