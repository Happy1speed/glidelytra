package net.happyspeed.glidelytra.sound;

import net.happyspeed.glidelytra.GlidelytraMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {

    public static final SoundEvent MAP_ONE_DISC_SOUND = registerSoundEvent("map_one_disc_sound");

    public static final SoundEvent MAP_TWO_DISC_SOUND = registerSoundEvent("map_two_disc_sound");

    public static final SoundEvent MAP_THREE_DISC_SOUND = registerSoundEvent("map_three_disc_sound");

    public static final SoundEvent MAP_FOUR_DISC_SOUND = registerSoundEvent("map_four_disc_sound");

    public static final SoundEvent MAP_FIVE_DISC_SOUND = registerSoundEvent("map_five_disc_sound");

    public static final SoundEvent MAP_SIX_DISC_SOUND = registerSoundEvent("map_six_disc_sound");

    public static final SoundEvent MAP_SEVEN_DISC_SOUND = registerSoundEvent("map_seven_disc_sound");

    public static final SoundEvent PLING_SOUND = registerSoundEvent("pling_sound");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(GlidelytraMod.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        GlidelytraMod.LOGGER.info("Registering Sounds for " + GlidelytraMod.MOD_ID);
    }
}
