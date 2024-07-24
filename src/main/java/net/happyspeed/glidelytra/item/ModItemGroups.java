package net.happyspeed.glidelytra.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.happyspeed.glidelytra.GlidelytraMod;
import net.happyspeed.glidelytra.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup GLIDELYTRA_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(GlidelytraMod.MOD_ID, "glidelytra"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.glidelytra"))
                    .icon(() -> new ItemStack(ModBlocks.AERIAL_ACCELERATOR_BLOCK)).entries((displayContext, entries) -> {
                        entries.add(ModBlocks.AERIAL_ACCELERATOR_BLOCK);
                        entries.add(ModItems.GLIDE_MAP_ONE_MUSIC_DISC);
                        entries.add(ModItems.GLIDE_MAP_TWO_MUSIC_DISC);
                        entries.add(ModItems.GLIDE_MAP_THREE_MUSIC_DISC);
                        entries.add(ModItems.GLIDE_MAP_FOUR_MUSIC_DISC);
                        entries.add(ModItems.GLIDE_MAP_FIVE_MUSIC_DISC);
                        entries.add(ModItems.GLIDE_MAP_SIX_MUSIC_DISC);
                        entries.add(ModItems.GLIDE_MAP_SEVEN_MUSIC_DISC);
                        entries.add(ModBlocks.PHANTOM_MEMBRANE_GEL_BLOCK);
                        entries.add(ModBlocks.SMOOTH_PHANTOM_MEMBRANE_GEL_BLOCK);
                        entries.add(ModBlocks.PURE_PHANTOM_MEMBRANE_GEL_BLOCK);
                    }).build());

    public static void registerItemGroups() {
        GlidelytraMod.LOGGER.info("Registering Item Groups for " + GlidelytraMod.MOD_ID);
    }
}
