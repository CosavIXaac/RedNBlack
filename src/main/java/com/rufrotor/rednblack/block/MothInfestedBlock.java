package com.rufrotor.rednblack.block;

import com.google.common.collect.Maps;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.rufrotor.rednblack.entity.MothEntity;
import com.rufrotor.rednblack.registry.RNBEntities;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;

import java.util.Iterator;
import java.util.Map;
import java.util.function.Supplier;

public class MothInfestedBlock extends Block {
    public static final MapCodec<MothInfestedBlock> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
        return instance.group(Registries.BLOCK.getCodec().fieldOf("host").forGetter(MothInfestedBlock::getRegularBlock), createSettingsCodec()).apply(instance, MothInfestedBlock::new);
    });
    private final Block regularBlock;
    private static final Map<Block, Block> REGULAR_TO_INFESTED_BLOCK = Maps.newIdentityHashMap();
    private static final Map<BlockState, BlockState> REGULAR_TO_INFESTED_STATE = Maps.newIdentityHashMap();
    private static final Map<BlockState, BlockState> INFESTED_TO_REGULAR_STATE = Maps.newIdentityHashMap();

    public MapCodec<? extends MothInfestedBlock> getCodec() {
        return CODEC;
    }

    public MothInfestedBlock(Block regularBlock, AbstractBlock.Settings settings) {
        super(settings.hardness(regularBlock.getHardness() / 2.0F).resistance(0.75F));
        this.regularBlock = regularBlock;
        REGULAR_TO_INFESTED_BLOCK.put(regularBlock, this);
    }

    public Block getRegularBlock() {
        return this.regularBlock;
    }

    public static boolean isInfestable(BlockState block) {
        return REGULAR_TO_INFESTED_BLOCK.containsKey(block.getBlock());
    }

    private void spawnMoth(ServerWorld world, BlockPos pos) {
        MothEntity mothEntity = (MothEntity) RNBEntities.MOTH.create(world);
        if (mothEntity != null) {
            mothEntity.refreshPositionAndAngles((double)pos.getX() + 0.5, (double)pos.getY(), (double)pos.getZ() + 0.5, 0.0F, 0.0F);
            world.spawnEntity(mothEntity);
            mothEntity.playSpawnEffects();
        }
    }

    protected void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack tool, boolean dropExperience) {
        super.onStacksDropped(state, world, pos, tool, dropExperience);
        if (world.getGameRules().getBoolean(GameRules.DO_TILE_DROPS) && !EnchantmentHelper.hasAnyEnchantmentsIn(tool, EnchantmentTags.PREVENTS_INFESTED_SPAWNS)) {
            this.spawnMoth(world, pos);
        }

    }

    public static BlockState fromRegularState(BlockState regularState) {
        return copyProperties(REGULAR_TO_INFESTED_STATE, regularState, () -> {
            return ((Block)REGULAR_TO_INFESTED_BLOCK.get(regularState.getBlock())).getDefaultState();
        });
    }

    public BlockState toRegularState(BlockState infestedState) {
        return copyProperties(INFESTED_TO_REGULAR_STATE, infestedState, () -> {
            return this.getRegularBlock().getDefaultState();
        });
    }

    private static BlockState copyProperties(Map<BlockState, BlockState> stateMap, BlockState fromState, Supplier<BlockState> toStateSupplier) {
        return (BlockState)stateMap.computeIfAbsent(fromState, (infestedState) -> {
            BlockState blockState = (BlockState)toStateSupplier.get();

            Property property;
            for(Iterator var3 = infestedState.getProperties().iterator(); var3.hasNext(); blockState = blockState.contains(property) ? (BlockState)blockState.with(property, infestedState.get(property)) : blockState) {
                property = (Property)var3.next();
            }

            return blockState;
        });
    }
}
