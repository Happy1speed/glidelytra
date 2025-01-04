package net.happyspeed.glidelytra.block.custom;

import net.happyspeed.glidelytra.block.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.function.Supplier;

import static net.minecraft.client.util.ParticleUtil.spawnParticles;

public class BaseUpdraftBlock extends BlockWithEntity implements BlockEntityProvider {

    protected static final VoxelShape SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 7.0, 16.0);

    public BaseUpdraftBlock(Settings settings) {
        super(settings);
    }


    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new UpdraftBlockEntity(pos, state);
    }
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlocks.UPDRAFT_BLOCK_ENTITY_BLOCK_ENTITY_TYPE, (world1, pos, state1, be) -> UpdraftBlockEntity.tick(world1, pos, state1, be));
    }
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (world.getTime() % 5L == 0L) {
            double d = (double) pos.getX() + 0.5;
            double e = pos.getY() + 0.1;
            double f = (double) pos.getZ() + 0.5;
            world.playSound(d, e, f, SoundEvents.BLOCK_CAMPFIRE_CRACKLE, SoundCategory.BLOCKS, 1.0f, 1.0f, false);
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
