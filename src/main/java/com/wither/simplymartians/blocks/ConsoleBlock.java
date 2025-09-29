package com.wither.simplymartians.blocks;

import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.mojang.serialization.MapCodec;
import com.wither.simplymartians.core.init.InitBlocks;
import com.wither.simplymartians.core.init.InitEntity;
import com.wither.simplymartians.core.init.InitItem;
import com.wither.simplymartians.entities.MartianEngineerEntity;
import com.wither.simplymartians.entities.UFOBossEntity;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public class ConsoleBlock extends HorizontalDirectionalBlock {
   public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
  
   @Nullable
   private BlockPattern martianSummonBase;
   @Nullable
   private BlockPattern martianSummonFull;
   private static final Predicate<BlockState> CONSOLE_PREDICATE = p_51396_ -> p_51396_ != null
           && (p_51396_.is(InitBlocks.MARTIAN_CONSOLE));

   public ConsoleBlock(BlockBehaviour.Properties p_51375_) {
      super(p_51375_);
      this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
   }

   @Override
   protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
       if (!oldState.is(state.getBlock())) {
           this.trySpawnGolem(level, pos);
       }
   }

   public boolean canSpawnGolem(LevelReader level, BlockPos pos) {
       return  this.getOrCreateIronGolemBase().find(level, pos) != null;
   }

   private void trySpawnGolem(Level level, BlockPos pos) {
      
           BlockPattern.BlockPatternMatch blockpattern$blockpatternmatch1 = this.getOrCreateIronGolemFull().find(level, pos);
           if (blockpattern$blockpatternmatch1 != null) {
        	   UFOBossEntity ufo = new UFOBossEntity(InitEntity.MARTIAN_SURVEYOR.get(), level);
        	   MartianEngineerEntity martian = new MartianEngineerEntity(InitEntity.MARTIAN_ENGINEER.get(), level);

               if (ufo != null) {
                   spawnGolemInWorld(level, blockpattern$blockpatternmatch1, ufo, martian, blockpattern$blockpatternmatch1.getBlock(1, 2, 0).getPos());
               }
           }
       }
   

   private static void spawnGolemInWorld(Level level, BlockPattern.BlockPatternMatch patternMatch, Entity golem, MartianEngineerEntity martian, BlockPos pos) {
       clearPatternBlocks(level, patternMatch);
       golem.moveTo((double)pos.getX() + 0.5, (double)pos.getY() + 0.05, (double)pos.getZ() + 0.5, 0.0F, 0.0F);
       level.addFreshEntity(golem);
       level.addFreshEntity(martian);

       martian.startRiding(golem);
       martian.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(InitItem.PLASMACUTTER.get()));
       martian.setDropChance(EquipmentSlot.MAINHAND, 0.0F);       
       

       for (ServerPlayer serverplayer : level.getEntitiesOfClass(ServerPlayer.class, golem.getBoundingBox().inflate(5.0))) {
           CriteriaTriggers.SUMMONED_ENTITY.trigger(serverplayer, golem);
       }

       updatePatternBlocks(level, patternMatch);
   }

   public static void clearPatternBlocks(Level level, BlockPattern.BlockPatternMatch patternMatch) {
       for (int i = 0; i < patternMatch.getWidth(); i++) {
           for (int j = 0; j < patternMatch.getHeight(); j++) {
               BlockInWorld blockinworld = patternMatch.getBlock(i, j, 0);
               level.setBlock(blockinworld.getPos(), Blocks.AIR.defaultBlockState(), 2);
               level.levelEvent(2001, blockinworld.getPos(), Block.getId(blockinworld.getState()));
           }
       }
   }

   public static void updatePatternBlocks(Level level, BlockPattern.BlockPatternMatch patternMatch) {
       for (int i = 0; i < patternMatch.getWidth(); i++) {
           for (int j = 0; j < patternMatch.getHeight(); j++) {
               BlockInWorld blockinworld = patternMatch.getBlock(i, j, 0);
               level.blockUpdated(blockinworld.getPos(), Blocks.AIR);
           }
       }
   }

   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
       return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
   }

   @Override
   protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
       builder.add(FACING);
   }

   private BlockPattern getOrCreateIronGolemBase() {
       if (this.martianSummonBase == null) {
           this.martianSummonBase = BlockPatternBuilder.start()
                   .aisle("~l~", "#b#", "# #")

               .where('l', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.LIGHTNING_ROD)))
               .where('b', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.COPPER_BULB)))
               .where('c', BlockInWorld.hasState(CONSOLE_PREDICATE))
               .where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(InitBlocks.MARTIAN_SPACE_PLATING.get())))
               .where('~', p_284869_ -> p_284869_.getState().isAir())
               .build();
       }

       return this.martianSummonBase;
   }

   private BlockPattern getOrCreateIronGolemFull() {
       if (this.martianSummonFull == null) {
           this.martianSummonFull = BlockPatternBuilder.start()
                   .aisle("~l~", "#b#", "#c#")

                   .where('l', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.LIGHTNING_ROD)))
                   .where('b', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.COPPER_BULB)))
                   .where('c', BlockInWorld.hasState(CONSOLE_PREDICATE))
               .where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(InitBlocks.MARTIAN_SPACE_PLATING.get())))
               .where('~', p_284868_ -> p_284868_.getState().isAir())
               .build();
       }

       return this.martianSummonFull;
   }

  



@Override
protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
	// TODO Auto-generated method stub
	return null;
}
   
   
  

  }
     