package net.happyspeed.glidelytra.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.happyspeed.glidelytra.GlidelytraMod;
import net.happyspeed.glidelytra.block.custom.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block AERIAL_ACCELERATOR_BLOCK = registerBlock("aerial_accelerator",
            new BaseAcceleratorBlock(FabricBlockSettings.copyOf(Blocks.DIRT).hardness(0.3f).sounds(BlockSoundGroup.NETHERITE).notSolid().nonOpaque().suffocates(Blocks::never).blockVision(Blocks::never)));
    public static final Block CREATIVE_AERIAL_ACCELERATOR_BLOCK = registerBlock("creative_aerial_accelerator",
            new CreativeBaseAcceleratorBlock(FabricBlockSettings.copyOf(Blocks.DIRT).hardness(0.5f).sounds(BlockSoundGroup.GLASS).notSolid().nonOpaque().suffocates(Blocks::never).collidable(false).blockVision(Blocks::never)));
    public static final BlockEntityType <AcceleratorBlockEntity> ACCELERATOR_BLOCK_ENTITY_BLOCK_ENTITY_TYPE = registerBlockEntity("accelerator_block_entity", FabricBlockEntityTypeBuilder.create(AcceleratorBlockEntity::new, AERIAL_ACCELERATOR_BLOCK));
    public static final BlockEntityType <CreativeAcceleratorBlockEntity> CREATIVE_ACCELERATOR_BLOCK_ENTITY_BLOCK_ENTITY_TYPE = registerBlockEntity("creative_accelerator_block_entity", FabricBlockEntityTypeBuilder.create(CreativeAcceleratorBlockEntity::new, CREATIVE_AERIAL_ACCELERATOR_BLOCK));
    public static final Block PHANTOM_MEMBRANE_GEL_BLOCK = registerBlock("phantom_membrane_gel_block", new TransparentBlock(FabricBlockSettings.copyOf(Blocks.SLIME_BLOCK).collidable(false).nonOpaque()));
    public static final Block SMOOTH_PHANTOM_MEMBRANE_GEL_BLOCK = registerBlock("smooth_phantom_membrane_gel_block", new TransparentBlock(FabricBlockSettings.copyOf(Blocks.SLIME_BLOCK).collidable(false).nonOpaque()));
    public static final Block PURE_PHANTOM_MEMBRANE_GEL_BLOCK = registerBlock("pure_phantom_membrane_gel_block", new TransparentBlock(FabricBlockSettings.copyOf(Blocks.SLIME_BLOCK).collidable(false).nonOpaque().luminance(8)));

    public static final Block UPDRAFT_BLOCK = registerBlock("updraft_block",
            new BaseUpdraftBlock(FabricBlockSettings.copyOf(Blocks.DIRT).hardness(0.3f).sounds(BlockSoundGroup.WOOD).notSolid().nonOpaque().suffocates(Blocks::never).blockVision(Blocks::never).luminance(state -> 15)));
    public static final BlockEntityType <UpdraftBlockEntity> UPDRAFT_BLOCK_ENTITY_BLOCK_ENTITY_TYPE = registerBlockEntity("updraft_block_entity", FabricBlockEntityTypeBuilder.create(UpdraftBlockEntity::new, UPDRAFT_BLOCK));

    //todo: No Collision Updraft Block

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(GlidelytraMod.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(GlidelytraMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        GlidelytraMod.LOGGER.info("Registering ModBlocks for " + GlidelytraMod.MOD_ID);
    }
    public static <T extends BlockEntity> BlockEntityType<T> registerBlockEntity(String name, FabricBlockEntityTypeBuilder<T> factory) {
        return Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier(GlidelytraMod.MOD_ID, name),
                factory.build()
        );
    }
}
