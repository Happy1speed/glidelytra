package net.happyspeed.glidelytra.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.happyspeed.glidelytra.GlidelytraMod;
import net.happyspeed.glidelytra.sound.ModSounds;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {

    public static final Item GLIDE_MAP_ONE_MUSIC_DISC = registerItem("glide_map_one_music_disc",
            new MusicDiscItem(1, ModSounds.MAP_ONE_DISC_SOUND, new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 131));

    public static final Item GLIDE_MAP_TWO_MUSIC_DISC = registerItem("glide_map_two_music_disc",
            new MusicDiscItem(2, ModSounds.MAP_TWO_DISC_SOUND, new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 125));

    public static final Item GLIDE_MAP_THREE_MUSIC_DISC = registerItem("glide_map_three_music_disc",
            new MusicDiscItem(3, ModSounds.MAP_THREE_DISC_SOUND, new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 146));

    public static final Item GLIDE_MAP_FOUR_MUSIC_DISC = registerItem("glide_map_four_music_disc",
            new MusicDiscItem(4, ModSounds.MAP_FOUR_DISC_SOUND, new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 126));

    public static final Item GLIDE_MAP_FIVE_MUSIC_DISC = registerItem("glide_map_five_music_disc",
            new MusicDiscItem(5, ModSounds.MAP_FIVE_DISC_SOUND, new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 124));

    public static final Item GLIDE_MAP_SIX_MUSIC_DISC = registerItem("glide_map_six_music_disc",
            new MusicDiscItem(6, ModSounds.MAP_SIX_DISC_SOUND, new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 126));

    public static final Item GLIDE_MAP_SEVEN_MUSIC_DISC = registerItem("glide_map_seven_music_disc",
            new MusicDiscItem(7, ModSounds.MAP_SEVEN_DISC_SOUND, new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 120));

    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(GlidelytraMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        GlidelytraMod.LOGGER.info("Registering Mod Items for " + GlidelytraMod.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }
}
