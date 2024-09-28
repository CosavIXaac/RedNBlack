package com.rufrotor.rednblack.client.registry;


import com.rufrotor.rednblack.client.entity.model.*;
import com.rufrotor.rednblack.client.entity.renderer.*;
import com.rufrotor.rednblack.registry.RNBEntities;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.ArmorEntityModel;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;

public class EntityRenderers {
    private static final Dilation HAT_DILATION = new Dilation(0.5F);
    private static final Dilation ARMOR_DILATION = new Dilation(1.0F);

    public static void registerRenderers(){
        EntityRendererRegistry.register(RNBEntities.SEAT, SeatRenderer::new);
        EntityRendererRegistry.register(RNBEntities.SLUG, SlugRenderer::new);
        EntityRendererRegistry.register(RNBEntities.MOTH, MothRenderer::new);
        EntityRendererRegistry.register(RNBEntities.SKELETON_WARRIOR, SkeletonWarriorRenderer::new);
        EntityRendererRegistry.register(RNBEntities.UNDERMAN, UndermanRenderer::new);
        EntityRendererRegistry.register(RNBEntities.DUSTPHADE, DustphadeRenderer::new);
        EntityRendererRegistry.register(RNBEntities.SPEAR_PROJECTILE, dispatcher -> new SpearProjectileRenderer(dispatcher, MinecraftClient.getInstance().getItemRenderer()));
    }

    public static void registerModelLayers(){
        TexturedModelData texturedModelData1 = TexturedModelData.of(ArmorEntityModel.getModelData(HAT_DILATION), 64, 32);
        TexturedModelData texturedModelData2 = TexturedModelData.of(ArmorEntityModel.getModelData(ARMOR_DILATION), 64, 32);
        EntityModelLayerRegistry.registerModelLayer(RNBModelLayers.EMPTY, EmptyModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(RNBModelLayers.SLUG, SlugModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(RNBModelLayers.MOTH, MothModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(RNBModelLayers.SKELETON_WARRIOR, SkeletonEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(RNBModelLayers.SKELETON_WARRIOR_INNER_ARMOR, () -> texturedModelData1);
        EntityModelLayerRegistry.registerModelLayer(RNBModelLayers.SKELETON_WARRIOR_OUTER_ARMOR, () -> texturedModelData2);
        EntityModelLayerRegistry.registerModelLayer(RNBModelLayers.UNDERMAN, UndermanModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(RNBModelLayers.UNDERMAN_INNER_ARMOR, () -> texturedModelData1);
        EntityModelLayerRegistry.registerModelLayer(RNBModelLayers.UNDERMAN_OUTER_ARMOR, () -> texturedModelData2);
        EntityModelLayerRegistry.registerModelLayer(RNBModelLayers.DUSTPHADE, EmptyModel::getTexturedModelData);
    }

    public static void ClientRegisteries(){
        registerModelLayers();
        registerRenderers();
    }
}
