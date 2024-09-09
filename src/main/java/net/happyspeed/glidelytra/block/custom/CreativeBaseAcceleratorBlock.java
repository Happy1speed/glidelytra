package net.happyspeed.glidelytra.block.custom;

import net.happyspeed.glidelytra.GlidelytraMod;
import net.happyspeed.glidelytra.block.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class CreativeBaseAcceleratorBlock extends BlockWithEntity implements BlockEntityProvider {

    public CreativeBaseAcceleratorBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CreativeAcceleratorBlockEntity(pos, state);
    }
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlocks.CREATIVE_ACCELERATOR_BLOCK_ENTITY_BLOCK_ENTITY_TYPE, (world1, pos, state1, be) -> CreativeAcceleratorBlockEntity.tick(world1, pos, state1, be));
    }
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        world.addParticle(GlidelytraMod.CA_PARTICLE, true, (double) pos.getX() + 0.5, (double) pos.getY() + 0.5, (double) pos.getZ() + 0.5, 0.0, 0.0, 0.0);
        if (world.getTime() % 20L == 0L) {
            double d = (double) pos.getX() + 0.5;
            double e = pos.getY() + 0.1;
            double f = (double) pos.getZ() + 0.5;
            world.playSound(d, e, f, SoundEvents.BLOCK_BEACON_AMBIENT, SoundCategory.BLOCKS, 1.0f, 0.9f, false);
        }
    }

    @Override
    public boolean hasDynamicBounds() {
        return super.hasDynamicBounds();
    }
    @Override
    public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        if (stateFrom.isOf(this)) {
            return true;
        }
        return super.isSideInvisible(state, stateFrom, direction);
    }
}
