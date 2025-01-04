package net.happyspeed.glidelytra.block.custom;

import net.happyspeed.glidelytra.GlidelytraMod;
import net.happyspeed.glidelytra.block.ModBlocks;
import net.happyspeed.glidelytra.config.ModConfigs;
import net.happyspeed.glidelytra.sound.ModSounds;
import net.happyspeed.glidelytra.util.ModTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.List;

public class UpdraftBlockEntity extends BlockEntity  {
    public UpdraftBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.UPDRAFT_BLOCK_ENTITY_BLOCK_ENTITY_TYPE, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, UpdraftBlockEntity acc) {
        BlockPos negPos = new BlockPos((pos.getX() - 1), pos.getY(), pos.getZ() - 1);
        BlockPos pozPos = new BlockPos(pos.getX() + 1, pos.getY() + 9, pos.getZ() + 1);
        List<PlayerEntity> list = world.getNonSpectatingEntities(PlayerEntity.class, new Box(negPos, pozPos));
        for (PlayerEntity player : list) {
            if (player.getWorld().isClient()) {
                continue;
            }
            if (player.isFallFlying() || player.isUsingRiptide() || player.isSwimming()) {
                player.setStatusEffect(new StatusEffectInstance(GlidelytraMod.UPDRAFT_EFFECT, 15, 0, false, true), null);
            }
        }
        if (world.getTime() % 5L == 0L) {
            Random random = world.getRandom();
            world.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, (double) pos.getX() + 0.5 + random.nextDouble() / 4.0 * (double) (random.nextBoolean() ? 1 : -1), (double) pos.getY() + 0.4, (double) pos.getZ() + 0.5 + random.nextDouble() / 4.0 * (double) (random.nextBoolean() ? 1 : -1), 0.0, 0.1, 0.0);
        }
    }
}
