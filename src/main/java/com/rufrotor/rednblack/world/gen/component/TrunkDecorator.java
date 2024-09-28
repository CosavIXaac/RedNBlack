package com.rufrotor.rednblack.world.gen.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import java.util.List;

public class TrunkDecorator extends TreeDecorator {
    public static final MapCodec<TrunkDecorator> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
        return instance.group(
                Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter((decorator) -> {
            return decorator.probability;
        }), BlockStateProvider.TYPE_CODEC.fieldOf("block_provider").forGetter((decorator) -> {
            return decorator.blockProvider;
        })).apply(instance, TrunkDecorator::new);
    });
    private final float probability;
    protected final BlockStateProvider blockProvider;

    public TrunkDecorator(float probability,BlockStateProvider blockProvider) {
        this.probability = probability;
        this.blockProvider = blockProvider;
    }

    protected TreeDecoratorType<?> getType() {
        return RNBTreeDecoratorType.TRUNK;
    }

    public void generate(TreeDecorator.Generator generator) {
        Random random = generator.getRandom();
        if (!(random.nextFloat() >= this.probability)) {
            List<BlockPos> list = generator.getLogPositions();
            int i = ((BlockPos)list.get(0)).getY();
            list.stream().filter((pos) -> {
                return pos.getY() - i <= 2;
            }).forEach((pos) -> {

                for (Direction direction : Direction.Type.HORIZONTAL) {
                    if (random.nextFloat() <= 0.25F) {
                        Direction direction2 = direction.getOpposite();
                        BlockPos blockPos = pos.add(direction2.getOffsetX(), 0, direction2.getOffsetZ());
                        if (generator.isAir(blockPos)) {
                            HorizontalFacingBlock block = (HorizontalFacingBlock) blockProvider.get(random, blockPos).getBlock();
                            generator.replace(blockPos, (BlockState) ((BlockState) blockProvider.get(random, blockPos)).with(HorizontalFacingBlock.FACING, direction));
                        }
                    }
                }

            });
        }
    }
}
