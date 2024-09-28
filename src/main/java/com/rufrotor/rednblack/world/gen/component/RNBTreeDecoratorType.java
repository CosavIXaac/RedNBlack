package com.rufrotor.rednblack.world.gen.component;

import com.mojang.serialization.MapCodec;
import com.rufrotor.rednblack.util.RNBIdentifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.treedecorator.*;

public record RNBTreeDecoratorType<P extends TreeDecorator>(MapCodec<P> codec) {
    public static final TreeDecoratorType<TrunkDecorator> TRUNK;

    private static <P extends TreeDecorator> TreeDecoratorType<P> register(String id, MapCodec<P> codec) {
        return (TreeDecoratorType) Registry.register(Registries.TREE_DECORATOR_TYPE, RNBIdentifier.ofRNB(id), new TreeDecoratorType(codec));
    }

    static {
        TRUNK = register("trunk", TrunkDecorator.CODEC);
    }

    public static void registerTreeDecoratorType() {

    }
}
