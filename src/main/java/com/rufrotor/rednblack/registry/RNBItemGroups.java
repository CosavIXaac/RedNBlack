package com.rufrotor.rednblack.registry;

import com.rufrotor.rednblack.util.RNBIdentifier;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

public class RNBItemGroups {
    public static final ItemGroup RNB_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(RNBItems.DECAYED_LEAVES))
            .displayName(Text.translatable("itemGroup.rednblack.rednblack_group"))
            .entries((context, entries) -> {
                entriesFunctionalItems(entries);
                entriesBuildingItems(entries);
                entriesFurnitureItems(entries);
                entriesNatureItems(entries);
                entriesWeaponItems(entries);
                entriesEquipmentItems(entries);
                entriesFoodItems(entries);
                entriesIngredientItems(entries);
                entriesSpawnEggItems(entries);
            })
            .build();
    public static void registerGroups(){
        Registry.register(Registries.ITEM_GROUP, RNBIdentifier.ofRNB("rednblack"), RNB_GROUP);
    }

    public static void entriesFoodItems(ItemGroup.Entries entries){
        entries.add(RNBItems.SOUR_BERRIES);
        entries.add(RNBItems.ORB_FRUIT);
        entries.add(RNBItems.MOTH_PASTE);
    }

    public static void entriesBuildingItems(ItemGroup.Entries entries){
        entries.add(RNBItems.PLASTER);
    }
    public static void entriesFurnitureItems(ItemGroup.Entries entries){
        entries.add(RNBItems.OAK_CHAIR);
        entries.add(RNBItems.BIRCH_CHAIR);
        entries.add(RNBItems.SPRUCE_CHAIR);
        entries.add(RNBItems.JUNGLE_CHAIR);
        entries.add(RNBItems.OAK_TABLE);
        entries.add(RNBItems.BIRCH_TABLE);
        entries.add(RNBItems.SPRUCE_TABLE);
        entries.add(RNBItems.JUNGLE_TABLE);
    }

    public static void entriesNatureItems(ItemGroup.Entries entries){
        entries.add(RNBItems.DECAYED_LEAVES);
        entries.add(RNBItems.INFESTED_DECAYED_LEAVES);
        entries.add(RNBItems.ORB_FRUIT_BLOCK);
        entries.add(RNBItems.UNDER_THORN);
    }

    public static void entriesSpawnEggItems(ItemGroup.Entries entries){
        entries.add(RNBItems.SLUG_SPAWN_EGG);
        entries.add(RNBItems.MOTH_SPAWN_EGG);
        entries.add(RNBItems.SKELETON_WARRIOR_SPAWN_EGG);
        entries.add(RNBItems.UNDERMAN_SPAWN_EGG);
        entries.add(RNBItems.DUSTPHADE_SPAWN_EGG);
    }
    public static void entriesWeaponItems(ItemGroup.Entries entries){
        entries.add(RNBItems.IRON_MACE);
        entries.add(RNBItems.STONE_SPEAR);
        entries.add(RNBItems.IRON_CLUB);
    }
    public static void entriesEquipmentItems(ItemGroup.Entries entries){
        entries.add(RNBItems.MOTHSHELL_HELMET);
        entries.add(RNBItems.MOTHSHELL_CHESTPLATE);
        entries.add(RNBItems.MOTHSHELL_LEGGINGS);
        entries.add(RNBItems.MOTHSHELL_BOOTS);
        entries.add(RNBItems.BLACK_IRON_HELMET);
    }

    public static void entriesFunctionalItems(ItemGroup.Entries entries){
    }

    public static void entriesIngredientItems(ItemGroup.Entries entries){
        entries.add(RNBItems.MOTHSHELL);
        entries.add(RNBItems.IRON_STICK);
    }
}
