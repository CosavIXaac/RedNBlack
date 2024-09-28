package com.rufrotor.rednblack.registry;

import com.rufrotor.rednblack.block.*;
import com.rufrotor.rednblack.util.RNBIdentifier;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.BlockSoundGroup;

public class RNBBlocks {

    public static final Block DECAYED_LEAVES = register("decayed_leaves", new LeavesBlock(AbstractBlock.Settings.create().mapColor(MapColor.SPRUCE_BROWN).strength(0.2F).ticksRandomly().sounds(BlockSoundGroup.ROOTS).nonOpaque().allowsSpawning(Blocks::canSpawnOnLeaves).suffocates(Blocks::never).blockVision(Blocks::never).burnable().pistonBehavior(PistonBehavior.DESTROY).solidBlock(Blocks::never)));
    public static final Block INFESTED_DECAYED_LEAVES = register("infested_decayed_leaves",new MothInfestedBlock(DECAYED_LEAVES, AbstractBlock.Settings.create().mapColor(MapColor.SPRUCE_BROWN).nonOpaque().allowsSpawning(Blocks::canSpawnOnLeaves).suffocates(Blocks::never).blockVision(Blocks::never).sounds(BlockSoundGroup.ROOTS).pistonBehavior(PistonBehavior.DESTROY).solidBlock(Blocks::never)));
    public static final Block OAK_CHAIR = registerWoodChair("oak_chair",MapColor.OAK_TAN);
    public static final Block BIRCH_CHAIR = registerWoodChair("birch_chair",MapColor.PALE_YELLOW);
    public static final Block SPRUCE_CHAIR = registerWoodChair("spruce_chair",MapColor.SPRUCE_BROWN);
    public static final Block JUNGLE_CHAIR = registerWoodChair("jungle_chair",MapColor.DIRT_BROWN);
    public static final Block OAK_TABLE = registerWoodTable("oak_table",MapColor.OAK_TAN);
    public static final Block BIRCH_TABLE = registerWoodTable("birch_table",MapColor.PALE_YELLOW);
    public static final Block SPRUCE_TABLE = registerWoodTable("spruce_table",MapColor.SPRUCE_BROWN);
    public static final Block JUNGLE_TABLE = registerWoodTable("jungle_table",MapColor.DIRT_BROWN);
    public static final Block PLASTER = register("plaster",new Block(AbstractBlock.Settings.create().mapColor(MapColor.IRON_GRAY).strength(0.5F).sounds(BlockSoundGroup.GRAVEL)));
    public static final Block REDNBLACK_PORTAL = register((String)"rednblack_portal", new RNBPortalBlock(AbstractBlock.Settings.create().mapColor(MapColor.BLACK).noCollision().luminance((state) -> {return 15;}).strength(1.5F, 3600000.0F).dropsNothing().pistonBehavior(PistonBehavior.BLOCK)));
    public static final Block SOUR_BERRY_BUSH = register("sour_berry_bush",new SourBerryBushBlock(AbstractBlock.Settings.create().mapColor(MapColor.GREEN).ticksRandomly().noCollision().sounds(BlockSoundGroup.SWEET_BERRY_BUSH).pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block ORB_FRUIT_BLOCK = register("orb_fruit_block", new OrbFruitBlock(AbstractBlock.Settings.create().mapColor(MapColor.DARK_GREEN).strength(2.0F, 30.0F).sounds(BlockSoundGroup.WOOD).nonOpaque().pistonBehavior(PistonBehavior.BLOCK)));
    public static final Block UNDER_THORN = register(
            "under_thorn",
            new UnderThornBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.DEEPSLATE_GRAY)
                            .replaceable()
                            .noCollision()
                            .ticksRandomly()
                            .strength(0.2F)
                            .sounds(BlockSoundGroup.VINE)
                            .burnable()
                            .pistonBehavior(PistonBehavior.DESTROY)
            )
    );

    public static Block register(String id, Block block) {
        return (Block)Registry.register(Registries.BLOCK, RNBIdentifier.ofRNB(id), block);
    }

    public static Block register(RegistryKey<Block> key, Block block) {
        return (Block)Registry.register(Registries.BLOCK, key, block);
    }
    public static void assignBlocksRenderLayer(){
        BlockRenderLayerMap.INSTANCE.putBlock(SOUR_BERRY_BUSH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DECAYED_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(INFESTED_DECAYED_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(UNDER_THORN, RenderLayer.getCutout());
    }
    public static void registerBlocks() {
    }

    private static Block registerWoodChair(String id,MapColor color){
        return register(id,new ChairBlock(AbstractBlock.Settings.create().mapColor(color).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
    }

    private static Block registerWoodTable(String id,MapColor color){
        return register(id,new TableBlock(AbstractBlock.Settings.create().mapColor(color).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
    }
}
