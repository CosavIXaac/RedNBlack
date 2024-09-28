package com.rufrotor.rednblack.registry;

import com.rufrotor.rednblack.item.RNBFoodComponents;
import com.rufrotor.rednblack.item.armor.RNBArmorMaterials;
import com.rufrotor.rednblack.item.rnbitem.ClubItem;
import com.rufrotor.rednblack.item.rnbitem.MaceItem;
import com.rufrotor.rednblack.item.rnbitem.OrbFruitItem;
import com.rufrotor.rednblack.item.rnbitem.SpearItem;
import com.rufrotor.rednblack.util.RNBIdentifier;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.block.Block;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class RNBItems {
    public static final Item DECAYED_LEAVES = register(RNBBlocks.DECAYED_LEAVES);
    public static final Item INFESTED_DECAYED_LEAVES = register(RNBBlocks.INFESTED_DECAYED_LEAVES);
    public static final Item PLASTER = register(RNBBlocks.PLASTER);
    public static final Item OAK_CHAIR = register(RNBBlocks.OAK_CHAIR);
    public static final Item BIRCH_CHAIR = register(RNBBlocks.BIRCH_CHAIR);
    public static final Item SPRUCE_CHAIR = register(RNBBlocks.SPRUCE_CHAIR);
    public static final Item JUNGLE_CHAIR = register(RNBBlocks.JUNGLE_CHAIR);
    public static final Item OAK_TABLE = register(RNBBlocks.OAK_TABLE);
    public static final Item BIRCH_TABLE = register(RNBBlocks.BIRCH_TABLE);
    public static final Item SPRUCE_TABLE = register(RNBBlocks.SPRUCE_TABLE);
    public static final Item JUNGLE_TABLE = register(RNBBlocks.JUNGLE_TABLE);
    public static final Item REDNBLACK_PORTAL = register(RNBBlocks.REDNBLACK_PORTAL);
    public static final Item ORB_FRUIT_BLOCK = register(RNBBlocks.ORB_FRUIT_BLOCK);
    public static final Item UNDER_THORN = register(RNBBlocks.UNDER_THORN);

    public static final Item SOUR_BERRIES = register("sour_berries",new AliasedBlockItem(RNBBlocks.SOUR_BERRY_BUSH, new Item.Settings().food(RNBFoodComponents.SOUR_BERRIES)));
    public static final Item ORB_FRUIT = register("orb_fruit",new OrbFruitItem(new Item.Settings().maxCount(1).food(RNBFoodComponents.ORB_FRUIT)));
    public static final Item MOTH_PASTE = register("moth_paste",new Item(new Item.Settings().maxCount(1).food(RNBFoodComponents.MOTH_PASTE)));
    public static final Item MOTHSHELL = register("mothshell", new Item(new Item.Settings()));
    public static final Item IRON_STICK = register("iron_stick", new Item(new Item.Settings()));

    public static final Item IRON_MACE = register("iron_mace", new MaceItem(ToolMaterials.IRON, (new Item.Settings()).attributeModifiers(MaceItem.createAttributeModifiers(ToolMaterials.IRON, 5, -2.7F))));
    public static final Item STONE_SPEAR = register("stone_spear", (new SpearItem(ToolMaterials.STONE,(new Item.Settings()).maxDamage(250).attributeModifiers(SpearItem.createAttributeModifiers(ToolMaterials.STONE, 3, -2.7F)).component(DataComponentTypes.TOOL, TridentItem.createToolComponent()))));
    public static final Item IRON_CLUB = register("iron_club", new ClubItem(ToolMaterials.IRON, (new Item.Settings()).attributeModifiers(MaceItem.createAttributeModifiers(ToolMaterials.IRON, 5, -3.1F))));

    public static final Item MOTHSHELL_HELMET = register("mothshell_helmet", new ArmorItem(RNBArmorMaterials.MOTHSHELL,ArmorItem.Type.HELMET,new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(RNBArmorMaterials.MOTHSHELL_DURABILITY_MULTIPLIER))));
    public static final Item MOTHSHELL_CHESTPLATE = register("mothshell_chestplate", new ArmorItem(RNBArmorMaterials.MOTHSHELL,ArmorItem.Type.CHESTPLATE,new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(RNBArmorMaterials.MOTHSHELL_DURABILITY_MULTIPLIER))));
    public static final Item MOTHSHELL_LEGGINGS = register("mothshell_leggings", new ArmorItem(RNBArmorMaterials.MOTHSHELL,ArmorItem.Type.LEGGINGS,new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(RNBArmorMaterials.MOTHSHELL_DURABILITY_MULTIPLIER))));
    public static final Item MOTHSHELL_BOOTS = register("mothshell_boots", new ArmorItem(RNBArmorMaterials.MOTHSHELL,ArmorItem.Type.BOOTS,new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(RNBArmorMaterials.MOTHSHELL_DURABILITY_MULTIPLIER))));
    public static final Item BLACK_IRON_HELMET = register("black_iron_helmet", new ArmorItem(RNBArmorMaterials.BLACK_IRON,ArmorItem.Type.HELMET,new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(RNBArmorMaterials.BLACK_IRON_DURABILITY_MULTIPLIER))));

    public static final Item SLUG_SPAWN_EGG = register("slug_spawn_egg", new SpawnEggItem(RNBEntities.SLUG, 9145227, 6118749, new Item.Settings()));
    public static final Item MOTH_SPAWN_EGG = register("moth_spawn_egg", new SpawnEggItem(RNBEntities.MOTH, 9011013, 6896439, new Item.Settings()));
    public static final Item SKELETON_WARRIOR_SPAWN_EGG = register("skeleton_warrior_spawn_egg", new SpawnEggItem(RNBEntities.SKELETON_WARRIOR, 12698049, 2039583, new Item.Settings()));
    public static final Item UNDERMAN_SPAWN_EGG = register("underman_spawn_egg", new SpawnEggItem(RNBEntities.UNDERMAN, 1645085, 16713222, new Item.Settings()));
    public static final Item DUSTPHADE_SPAWN_EGG = register("dustphade_spawn_egg", new SpawnEggItem(RNBEntities.DUSTPHADE, 1644825, 4210752, new Item.Settings()));

    public static Item register(Block block) {
        return register(new BlockItem(block, new Item.Settings()));
    }

    public static Item register(Block block, UnaryOperator<Item.Settings> settingsOperator) {
        return register(new BlockItem(block, (Item.Settings)settingsOperator.apply(new Item.Settings())));
    }

    public static Item register(Block block, Block... blocks) {
        BlockItem blockItem = new BlockItem(block, new Item.Settings());
        Block[] var3 = blocks;
        int var4 = blocks.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Block block2 = var3[var5];
            Item.BLOCK_ITEMS.put(block2, blockItem);
        }

        return register(blockItem);
    }

    public static Item register(BlockItem item) {
        return register((Block)item.getBlock(), (Item)item);
    }

    public static Item register(Block block, Item item) {
        return register(Registries.BLOCK.getId(block), item);
    }

    public static Item register(String id, Item item) {
        return register(RNBIdentifier.ofRNB(id), item);
    }

    public static Item register(Identifier id, Item item) {
        return register(RegistryKey.of(Registries.ITEM.getKey(), id), item);
    }

    public static Item register(RegistryKey<Item> key, Item item) {
        if (item instanceof BlockItem) {
            ((BlockItem)item).appendBlocks(Item.BLOCK_ITEMS, item);
        }

        return (Item)Registry.register(Registries.ITEM, key, item);
    }
    public static void registerItems() {
        registerCompostingChance();
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register((itemGroup) -> itemGroup.add(PLASTER));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register((itemGroup) -> itemGroup.add(REDNBLACK_PORTAL));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register((itemGroup) -> itemGroup.addAfter(Items.SWEET_BERRIES,SOUR_BERRIES));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register((itemGroup) -> itemGroup.addAfter(Items.SWEET_BERRIES,SOUR_BERRIES));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register((itemGroup) -> itemGroup.addAfter(Items.MANGROVE_LEAVES,DECAYED_LEAVES));
    }

    public static void registerCompostingChance() {
        CompostingChanceRegistry.INSTANCE.add(SOUR_BERRIES,0.3F);
    }

}
