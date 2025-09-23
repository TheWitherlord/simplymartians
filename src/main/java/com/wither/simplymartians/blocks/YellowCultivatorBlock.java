package com.wither.simplymartians.blocks;

import com.mojang.serialization.MapCodec;
import com.wither.simplymartians.core.init.InitBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

public class YellowCultivatorBlock extends Block {
    public static final MapCodec<YellowCultivatorBlock> CODEC = simpleCodec(YellowCultivatorBlock::new);
    public static final int GROWTH_CHANCE = 5;
    private static final Direction[] DIRECTIONS = Direction.values();

    @Override
    public MapCodec<YellowCultivatorBlock> codec() {
        return CODEC;
    }

    public YellowCultivatorBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    /**
     * Performs a random tick on a block.
     */
    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextInt(5) == 0) {
            Direction direction = DIRECTIONS[random.nextInt(DIRECTIONS.length)];
            BlockPos blockpos = pos.relative(direction);
            BlockState blockstate = level.getBlockState(blockpos);
            Block block = null;
            if (canClusterGrowAtState(blockstate)) {
                block = InitBlocks.SMALL_YELLOW_FLUXIUM_BUD.get();
            } else if (blockstate.is(InitBlocks.SMALL_YELLOW_FLUXIUM_BUD.get()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = InitBlocks.MEDIUM_YELLOW_FLUXIUM_BUD.get();
            } else if (blockstate.is(InitBlocks.MEDIUM_YELLOW_FLUXIUM_BUD.get()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = InitBlocks.LARGE_YELLOW_FLUXIUM_BUD.get();
            } else if (blockstate.is(InitBlocks.LARGE_BLUE_FLUXIUM_BUD.get()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = InitBlocks.YELLOW_FLUXIUM_CLUSTER.get();
            }

            if (block != null) {
                BlockState blockstate1 = block.defaultBlockState()
                    .setValue(AmethystClusterBlock.FACING, direction)
                    .setValue(AmethystClusterBlock.WATERLOGGED, Boolean.valueOf(blockstate.getFluidState().getType() == Fluids.WATER));
                level.setBlockAndUpdate(blockpos, blockstate1);
            }
        }
    }

    public static boolean canClusterGrowAtState(BlockState state) {
        return state.isAir() || state.is(Blocks.WATER) && state.getFluidState().getAmount() == 8;
    }
}
