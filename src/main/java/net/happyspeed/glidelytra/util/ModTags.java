package net.happyspeed.glidelytra.util;

import net.happyspeed.glidelytra.GlidelytraMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> BOOST_BASE_BLOCKS_TAG =
                createTag("boost_base_blocks");
        public static final TagKey<Block> BOOST_CHISELED_BLOCKS_TAG =
                createTag("boost_chiseled_blocks");
        public static final TagKey<Block> BOOST_FOCUS_BLOCKS_TAG =
                createTag("boost_focus_blocks");


        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(GlidelytraMod.MOD_ID, name));
        }
    }

    public static class Items {
        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(GlidelytraMod.MOD_ID, name));
        }
    }
}
