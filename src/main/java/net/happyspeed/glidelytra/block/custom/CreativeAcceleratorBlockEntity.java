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
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class CreativeAcceleratorBlockEntity extends BlockEntity  {
    public int soundTimer = 0;
    public CreativeAcceleratorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.CREATIVE_ACCELERATOR_BLOCK_ENTITY_BLOCK_ENTITY_TYPE, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, CreativeAcceleratorBlockEntity acc) {
        if (acc.soundTimer < 40) {
            acc.soundTimer++;
        }
        if (world.getTime() % 20 == 0) {
            if (!world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())).isOf(Blocks.GLASS)) {
                if (world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())).isOf(Blocks.CYAN_STAINED_GLASS)) {
                    world.addParticle(GlidelytraMod.RING_PARTICLE, true, (double) pos.getX() + 0.5, (double) pos.getY() + 6, (double) pos.getZ() + 0.5, 0.0, 1.0, 0.0);
                }
                else if (world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())).isOf(Blocks.RED_STAINED_GLASS)) {
                    world.addParticle(GlidelytraMod.RING_PARTICLE, true, (double) pos.getX() + 0.5, (double) pos.getY() + 6, (double) pos.getZ() + 0.5, 0.0, 2.0, 0.0);
                }
                else if (world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())).isOf(Blocks.GREEN_STAINED_GLASS)) {
                    world.addParticle(GlidelytraMod.RING_PARTICLE, true, (double) pos.getX() + 0.5, (double) pos.getY() + 6, (double) pos.getZ() + 0.5, 0.0, 3.0, 0.0);
                }
                else if (world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())).isOf(Blocks.BLUE_STAINED_GLASS)) {
                    world.addParticle(GlidelytraMod.RING_PARTICLE, true, (double) pos.getX() + 0.5, (double) pos.getY() + 6, (double) pos.getZ() + 0.5, 0.0, 4.0, 0.0);
                }
                else if (world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())).isOf(Blocks.LIME_STAINED_GLASS)) {
                    world.addParticle(GlidelytraMod.RING_PARTICLE, true, (double) pos.getX() + 0.5, (double) pos.getY() + 6, (double) pos.getZ() + 0.5, 0.0, 8.0, 0.0);

                }
                else if (world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())).isOf(Blocks.PINK_STAINED_GLASS)) {
                    world.addParticle(GlidelytraMod.RING_PARTICLE, true, (double) pos.getX() + 0.5, (double) pos.getY() + 6, (double) pos.getZ() + 0.5, 0.0, 7.0, 0.0);

                }
                else if (world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())).isOf(Blocks.LIGHT_BLUE_STAINED_GLASS)) {
                    world.addParticle(GlidelytraMod.RING_PARTICLE, true, (double) pos.getX() + 0.5, (double) pos.getY() + 6, (double) pos.getZ() + 0.5, 0.0, 5.0, 0.0);

                }
                else if (world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())).isOf(Blocks.PURPLE_STAINED_GLASS)) {
                    world.addParticle(GlidelytraMod.RING_PARTICLE, true, (double) pos.getX() + 0.5, (double) pos.getY() + 6, (double) pos.getZ() + 0.5, 0.0, 6.0, 0.0);
                }
                else if (world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())).isOf(Blocks.YELLOW_STAINED_GLASS)) {
                    world.addParticle(GlidelytraMod.RING_PARTICLE, true, (double) pos.getX() + 0.5, (double) pos.getY() + 6, (double) pos.getZ() + 0.5, 0.0, 9.0, 0.0);
                }
                else if (world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())).isOf(Blocks.MAGENTA_STAINED_GLASS)) {
                    world.addParticle(GlidelytraMod.RING_PARTICLE, true, (double) pos.getX() + 0.5, (double) pos.getY() + 6, (double) pos.getZ() + 0.5, 0.0, 13.0, 0.0);
                }
                else if (world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())).isOf(Blocks.GRAY_STAINED_GLASS)) {
                    world.addParticle(GlidelytraMod.RING_PARTICLE, true, (double) pos.getX() + 0.5, (double) pos.getY() + 6, (double) pos.getZ() + 0.5, 0.0, 10.0, 0.0);

                }
                else if (world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())).isOf(Blocks.LIGHT_GRAY_STAINED_GLASS)) {
                    world.addParticle(GlidelytraMod.RING_PARTICLE, true, (double) pos.getX() + 0.5, (double) pos.getY() + 6, (double) pos.getZ() + 0.5, 0.0, 11.0, 0.0);

                }
                else if (world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())).isOf(Blocks.BLACK_STAINED_GLASS)) {
                    world.addParticle(GlidelytraMod.RING_PARTICLE, true, (double) pos.getX() + 0.5, (double) pos.getY() + 6, (double) pos.getZ() + 0.5, 0.0, 12.0, 0.0);

                }
                else if (world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())).isOf(Blocks.BROWN_STAINED_GLASS)) {
                    world.addParticle(GlidelytraMod.RING_PARTICLE, true, (double) pos.getX() + 0.5, (double) pos.getY() + 6, (double) pos.getZ() + 0.5, 0.0, 14.0, 0.0);

                }
                else if (world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())).isOf(Blocks.ORANGE_STAINED_GLASS)) {
                    world.addParticle(GlidelytraMod.RING_PARTICLE, true, (double) pos.getX() + 0.5, (double) pos.getY() + 6, (double) pos.getZ() + 0.5, 0.0, 15.0, 0.0);

                }
                else {
                    world.addParticle(GlidelytraMod.RING_PARTICLE, true, (double) pos.getX() + 0.5, (double) pos.getY() + 6, (double) pos.getZ() + 0.5, 0.0, 0.0, 0.0);
                }
            }
        }
        BlockPos negPos = new BlockPos((pos.getX() - 5), pos.getY() + 1, pos.getZ() - 5);
        BlockPos pozPos = new BlockPos(pos.getX() + 5, pos.getY() + 10, pos.getZ() + 5);
        List<PlayerEntity> list = world.getNonSpectatingEntities(PlayerEntity.class, new Box(negPos, pozPos));
        for (PlayerEntity player : list) {
            Vec3d playerPosition = new Vec3d(player.getX(), player.getY(), player.getZ());
            if (player.getWorld().isClient()) {
                continue;
            }
            if (player.isFallFlying() || player.isUsingRiptide() || player.isSwimming()) {
                if (!player.hasStatusEffect(GlidelytraMod.BOOST_COOLDOWN_EFFECT)) {
                    if (acc.soundTimer > 39) {
                        world.playSound(null, playerPosition.x, playerPosition.y, playerPosition.z, ModSounds.PLING_SOUND, SoundCategory.PLAYERS, 1.0f, 1.0f);
                        acc.soundTimer = 0;
                    }
                    float boostSpeed;
                    Vec3d vec3d = player.getRotationVector();
                    Vec3d vec3d2 = player.getVelocity();
                    boostSpeed = (float) ModConfigs.CONFIGBOOSTBLOCKAMPLIFIER;
                    if (ModConfigs.CONFIGBOOSTBLOCKHEIGHTMODIFIER) {
                        boostSpeed += (float) Math.min(Math.max(50, pos.getY()), 180) * 0.02f;
                    }
                    if (ModConfigs.CONFIGGIVEFASTGLIDEEFFECT) {
                        player.setStatusEffect(new StatusEffectInstance(GlidelytraMod.FAST_GLIDE_EFFECT, 300, 0, false, false, true), player);
                    }
                    player.setVelocity(vec3d2.add((vec3d.x + (vec3d.x * boostSpeed - vec3d2.x)) * 0.05f, (vec3d.y + (vec3d.y * boostSpeed - vec3d2.y) * 0.05f),  (vec3d.z + (vec3d.z * boostSpeed - vec3d2.z)) * 0.05f));
                    player.velocityModified = true;
                    player.setStatusEffect(new StatusEffectInstance(GlidelytraMod.BOOST_COOLDOWN_EFFECT, 20, 0, false, false, false), player);
                }
            }
        }
    }
}
