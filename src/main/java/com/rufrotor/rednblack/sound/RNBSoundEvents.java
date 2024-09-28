package com.rufrotor.rednblack.sound;

import com.rufrotor.rednblack.util.RNBIdentifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class RNBSoundEvents {
    public static final SoundEvent ENTITY_UNDERMAN_AMBIENT = register("entity.underman.ambient");
    public static final SoundEvent ENTITY_UNDERMAN_DEATH = register("entity.underman.death");
    public static final SoundEvent ENTITY_UNDERMAN_HURT = register("entity.underman.hurt");

    public static void init(){}

    private static RegistryEntry<SoundEvent> register(Identifier id, Identifier soundId, float distanceToTravel) {
        return Registry.registerReference(Registries.SOUND_EVENT, id, SoundEvent.of(soundId, distanceToTravel));
    }

    private static SoundEvent register(String id) {
        return register(RNBIdentifier.ofRNB(id));
    }

    private static SoundEvent register(Identifier id) {
        return register(id, id);
    }

    private static RegistryEntry.Reference<SoundEvent> registerReference(String id) {
        return registerReference(RNBIdentifier.ofRNB(id));
    }

    private static RegistryEntry.Reference<SoundEvent> registerReference(Identifier id) {
        return registerReference(id, id);
    }

    private static SoundEvent register(Identifier id, Identifier soundId) {
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(soundId));
    }

    private static RegistryEntry.Reference<SoundEvent> registerReference(Identifier id, Identifier soundId) {
        return Registry.registerReference(Registries.SOUND_EVENT, id, SoundEvent.of(soundId));
    }
}
