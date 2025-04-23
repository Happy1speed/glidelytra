package net.happyspeed.glidelytra;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.happyspeed.glidelytra.block.ModBlocks;
import net.happyspeed.glidelytra.client.particle.CAParticleBase;
import net.happyspeed.glidelytra.client.particle.RingParticleBase;
import net.minecraft.client.render.RenderLayer;

public class GlidelytraModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(GlidelytraMod.RING_PARTICLE, RingParticleBase.RingFactory::new);
        ParticleFactoryRegistry.getInstance().register(GlidelytraMod.CA_PARTICLE, CAParticleBase.CAFactory::new);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.AERIAL_ACCELERATOR_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.UPDRAFT_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CREATIVE_AERIAL_ACCELERATOR_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PHANTOM_MEMBRANE_GEL_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SMOOTH_PHANTOM_MEMBRANE_GEL_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PURE_PHANTOM_MEMBRANE_GEL_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.AERIAL_ACCELERATOR_BLOCK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PHANTOM_MEMBRANE_GEL_BLOCK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SMOOTH_PHANTOM_MEMBRANE_GEL_BLOCK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PURE_PHANTOM_MEMBRANE_GEL_BLOCK, RenderLayer.getTranslucent());
    }
}
