package com.rufrotor.rednblack.item.armor;

import com.rufrotor.rednblack.registry.RNBItems;
import com.rufrotor.rednblack.util.RNBIdentifier;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class RNBArmorMaterials {
    public static final int MOTHSHELL_DURABILITY_MULTIPLIER = 3;
    public static final int BLACK_IRON_DURABILITY_MULTIPLIER = 15;

    public static final RegistryEntry<ArmorMaterial> MOTHSHELL = registerMaterial("mothshell",
            Map.of(
                    ArmorItem.Type.HELMET, 0,
                    ArmorItem.Type.CHESTPLATE, 3,
                    ArmorItem.Type.LEGGINGS, 3,
                    ArmorItem.Type.BOOTS, 0
            ),
            30, SoundEvents.ITEM_ARMOR_EQUIP_WOLF, () -> Ingredient.ofItems(RNBItems.MOTHSHELL), 1.0F, 0.05F, false);

    public static final RegistryEntry<ArmorMaterial> BLACK_IRON = registerMaterial("black_iron",
            Map.of(
                    ArmorItem.Type.HELMET, 1,
                    ArmorItem.Type.CHESTPLATE, 3,
                    ArmorItem.Type.LEGGINGS, 3,
                    ArmorItem.Type.BOOTS, 1
            ),
            25, SoundEvents.ITEM_ARMOR_EQUIP_IRON, () -> Ingredient.ofItems(Items.IRON_INGOT), 0.0F, 0.0F, false);

    public static RegistryEntry<ArmorMaterial> registerMaterial(String id, Map<ArmorItem.Type, Integer> defensePoints, int enchantability, RegistryEntry<SoundEvent> equipSound, Supplier<Ingredient> repairIngredientSupplier, float toughness, float knockbackResistance, boolean dyeable) {
        List<ArmorMaterial.Layer> layers = List.of(
                new ArmorMaterial.Layer(RNBIdentifier.ofRNB(id), "", dyeable)
        );

        ArmorMaterial material = new ArmorMaterial(defensePoints, enchantability, equipSound, repairIngredientSupplier, layers, toughness, knockbackResistance);
        material = Registry.register(Registries.ARMOR_MATERIAL, RNBIdentifier.ofRNB(id), material);

        return RegistryEntry.of(material);
    }
}
