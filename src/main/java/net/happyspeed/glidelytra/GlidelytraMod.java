package net.happyspeed.glidelytra;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.fabricmc.loader.api.FabricLoader;
import net.happyspeed.glidelytra.block.ModBlocks;
import net.happyspeed.glidelytra.block.custom.AcceleratorBlockEntity;
import net.happyspeed.glidelytra.config.ModConfigs;
import net.happyspeed.glidelytra.item.ModItemGroups;
import net.happyspeed.glidelytra.item.ModItems;
import net.happyspeed.glidelytra.sound.ModSounds;
import net.happyspeed.glidelytra.status_effects.BoosingEffect;
import net.happyspeed.glidelytra.status_effects.FastGlideEffect;
import net.happyspeed.glidelytra.status_effects.SkippingEffect;
import net.happyspeed.glidelytra.status_effects.UpdraftEffect;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GlidelytraMod implements ModInitializer {
	public static final String MOD_ID = "glidelytra";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final DefaultParticleType RING_PARTICLE = FabricParticleTypes.simple(true);
	public static final DefaultParticleType CA_PARTICLE = FabricParticleTypes.simple(true);

	public static StatusEffect FAST_GLIDE_EFFECT = new FastGlideEffect();
	public static StatusEffect BOOST_COOLDOWN_EFFECT = new FastGlideEffect();
	public static StatusEffect BOOSTING_EFFECT = new BoosingEffect();
	public static StatusEffect SKIPPING_EFFECT = new SkippingEffect();
	public static StatusEffect UPDRAFT_EFFECT = new UpdraftEffect();

	@Override
	public void onInitialize() {
		LOGGER.info("Glidelytra Mod Loading!");
		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModSounds.registerSounds();
		Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "ring_particle"), RING_PARTICLE);
		Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "ca_particle"), CA_PARTICLE);

		Registry.register(Registries.STATUS_EFFECT, new Identifier(MOD_ID, "fast_glide_effect"), FAST_GLIDE_EFFECT);
		Registry.register(Registries.STATUS_EFFECT, new Identifier(MOD_ID, "boost_cooldown_effect"), BOOST_COOLDOWN_EFFECT);
		Registry.register(Registries.STATUS_EFFECT, new Identifier(MOD_ID, "boosting_effect"), BOOSTING_EFFECT);
		Registry.register(Registries.STATUS_EFFECT, new Identifier(MOD_ID, "updraft_effect"), UPDRAFT_EFFECT);
		Registry.register(Registries.STATUS_EFFECT, new Identifier(MOD_ID, "skipping_effect"), SKIPPING_EFFECT);
		ModConfigs.registerConfigs();
	}
}