package com.wither.simplymartians.core.init;

import com.wither.simplymartians.SimplyMartians;
import com.wither.simplymartians.blocks.ConsoleBlock;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class InitBlocks {

	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(SimplyMartians.MODID);
	
	
	public static final DeferredBlock<Block> MARTIAN_SPACE_PLATING = BLOCKS.register("martian_space_plating",
			() -> new Block(BlockBehaviour.Properties.of().sound(SoundType.NETHERITE_BLOCK).strength(3.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> SMALL_MARTIAN_SPACE_PLATING = BLOCKS.register("small_martian_space_plating",
			() -> new Block(BlockBehaviour.Properties.of().sound(SoundType.NETHERITE_BLOCK).strength(3.0F, 1200F)
					.requiresCorrectToolForDrops()));

	public static final DeferredBlock<Block> MARTIAN_FLOORING = BLOCKS.register("martian_flooring",
			() -> new Block(BlockBehaviour.Properties.of().sound(SoundType.NETHERITE_BLOCK).strength(3.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> MARTIAN_SCREEN = BLOCKS.register("martian_screen",
			() -> new Block(BlockBehaviour.Properties.of().sound(SoundType.NETHERITE_BLOCK).strength(3.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> MARTIAN_INTERIOR = BLOCKS.register("martian_interior",
			() -> new Block(BlockBehaviour.Properties.of().sound(SoundType.NETHERITE_BLOCK).strength(3.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	

	public static final DeferredBlock<Block> MARTIAN_INTERIOR_LAMP = BLOCKS.register("martian_interior_lamp",
			() -> new RotatedPillarBlock(BlockBehaviour.Properties.of().sound(SoundType.NETHERITE_BLOCK).lightLevel((state) -> {
				return 12;}) .strength(3.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> MARTIAN_FLOOR_LIGHT = BLOCKS.register("martian_floor_light",
			() -> new Block(BlockBehaviour.Properties.of().sound(SoundType.NETHERITE_BLOCK).lightLevel((state) -> {
				return 12;}) .strength(3.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> MARTIAN_CONSOLE = BLOCKS.register("martian_console",
			() -> new ConsoleBlock(BlockBehaviour.Properties.of().sound(SoundType.NETHERITE_BLOCK).lightLevel((state) -> {
				return 4;}) .strength(3.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	//meteor blocks
	
	public static final DeferredBlock<Block> METEOR_ROCK = BLOCKS.register("meteor_rock",
			() -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(6.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> FLUXIUM_ORE = BLOCKS.register("fluxium_ore",
			() -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(6.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> TEKTITE = BLOCKS.register("tektite",
			() -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(12.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	
	
	
	
	//slabs and stairs
	
	public static final DeferredBlock<Block> MARTIAN_SPACE_PLATING_SLAB = BLOCKS.register("martian_space_plating_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.NETHERITE_BLOCK).strength(3.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> MARTIAN_SPACE_PLATING_STAIRS = BLOCKS.register("martian_space_plating_stairs",
			() -> new StairBlock(MARTIAN_SPACE_PLATING.get().defaultBlockState(),
					Block.Properties.ofFullCopy(MARTIAN_SPACE_PLATING.get())));
	
	
	public static final DeferredBlock<Block> MARTIAN_INTERIOR_SLAB = BLOCKS.register("martian_interior_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.NETHERITE_BLOCK).strength(3.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> MARTIAN_INTERIOR_STAIRS = BLOCKS.register("martian_interior_stairs",
			() -> new StairBlock(MARTIAN_SPACE_PLATING.get().defaultBlockState(),
					Block.Properties.ofFullCopy(MARTIAN_SPACE_PLATING.get())));
	
	
	public static final DeferredBlock<Block> MARTIAN_FLOORING_SLAB = BLOCKS.register("martian_flooring_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.NETHERITE_BLOCK).strength(3.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> MARTIAN_FLOORING_STAIRS = BLOCKS.register("martian_flooring_stairs",
			() -> new StairBlock(MARTIAN_SPACE_PLATING.get().defaultBlockState(),
					Block.Properties.ofFullCopy(MARTIAN_SPACE_PLATING.get())));

	
}
