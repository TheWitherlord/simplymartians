package com.wither.simplymartians.core.init;

import com.wither.simplymartians.SimplyMartians;
import com.wither.simplymartians.blocks.BlueCultivatorBlock;
import com.wither.simplymartians.blocks.ConsoleBlock;
import com.wither.simplymartians.blocks.GreenCultivatorBlock;
import com.wither.simplymartians.blocks.PurpleCultivatorBlock;
import com.wither.simplymartians.blocks.RedCultivatorBlock;
import com.wither.simplymartians.blocks.YellowCultivatorBlock;

import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.MagmaBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
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
			() -> new Block(BlockBehaviour.Properties.of().sound(SoundType.GLASS).strength(2.0F, 1200F)
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
				return 6;}) .strength(3.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	//cosmium
	
	public static final DeferredBlock<Block> COSMIUM_SPACE_PLATING = BLOCKS.register("cosmium_space_plating",
			() -> new Block(BlockBehaviour.Properties.of().sound(SoundType.NETHERITE_BLOCK).strength(3.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> SMALL_COSMIUM_SPACE_PLATING = BLOCKS.register("small_cosmium_space_plating",
			() -> new Block(BlockBehaviour.Properties.of().sound(SoundType.NETHERITE_BLOCK).strength(3.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> COSMIUM_INTERIOR = BLOCKS.register("cosmium_interior",
			() -> new Block(BlockBehaviour.Properties.of().sound(SoundType.NETHERITE_BLOCK).strength(3.0F, 1200F)
					.requiresCorrectToolForDrops()));

	public static final DeferredBlock<Block> COSMIUM_INTERIOR_LAMP = BLOCKS.register("cosmium_interior_lamp",
			() -> new RotatedPillarBlock(BlockBehaviour.Properties.of().sound(SoundType.NETHERITE_BLOCK).lightLevel((state) -> {
				return 12;}) .strength(3.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> COSMIUM_BLOCK = BLOCKS.register("cosmium_block",
			() -> new Block(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(3.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> RAW_COSMIUM_BLOCK = BLOCKS.register("raw_cosmium_block",
			() -> new Block(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(3.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> METEORSTONE_BLOCK = BLOCKS.register("meteorstone_block",
			() -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(3.5F, 1200F)
					.requiresCorrectToolForDrops()));
	
	
	
	//meteor blocks
	
	public static final DeferredBlock<Block> HOT_METEOR_ROCK = BLOCKS.register("hot_meteor_rock",
			() -> new MagmaBlock(BlockBehaviour.Properties.of().sound(SoundType.STONE).lightLevel((state) -> {
				return 10;}).strength(6.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> METEOR_ROCK = BLOCKS.register("meteor_rock",
			() -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(6.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> POLISHED_METEOR_ROCK = BLOCKS.register("polished_meteor_rock",
			() -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(6.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> CHISELED_METEOR_ROCK = BLOCKS.register("chiseled_meteor_rock",
			() -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(6.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> METEOR_ROCK_BRICKS = BLOCKS.register("meteor_rock_bricks",
			() -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(6.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> METEORIC_IRON_ORE = BLOCKS.register("meteoric_iron_ore",
			() -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(6.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> FLUXIUM_ORE = BLOCKS.register("fluxium_ore",
			() -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE).lightLevel((state) -> {
				return 12;}).strength(6.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> TEKTITE = BLOCKS.register("tektite",
			() -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(12.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> TEKTITE_BRICKS = BLOCKS.register("tektite_bricks",
			() -> new Block(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(12.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	
	//fluxium cultivators
	
	public static final DeferredBlock<Block> BLUE_FLUXIUM_CULTIVATOR = BLOCKS.register("blue_fluxium_cultivator",
			() -> new BlueCultivatorBlock(BlockBehaviour.Properties.of().sound(SoundType.STONE).randomTicks().strength(6.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> RED_FLUXIUM_CULTIVATOR = BLOCKS.register("red_fluxium_cultivator",
			() -> new RedCultivatorBlock(BlockBehaviour.Properties.of().sound(SoundType.STONE).randomTicks().strength(6.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> YELLOW_FLUXIUM_CULTIVATOR = BLOCKS.register("yellow_fluxium_cultivator",
			() -> new YellowCultivatorBlock(BlockBehaviour.Properties.of().sound(SoundType.STONE).randomTicks().strength(6.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> GREEN_FLUXIUM_CULTIVATOR = BLOCKS.register("green_fluxium_cultivator",
			() -> new GreenCultivatorBlock(BlockBehaviour.Properties.of().sound(SoundType.STONE).randomTicks().strength(6.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> PURPLE_FLUXIUM_CULTIVATOR = BLOCKS.register("purple_fluxium_cultivator",
			() -> new PurpleCultivatorBlock(BlockBehaviour.Properties.of().sound(SoundType.STONE).randomTicks().strength(6.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	
	//fluxium crystals and buds
	
	public static final DeferredBlock<Block> SMALL_BLUE_FLUXIUM_BUD = BLOCKS.register("small_blue_fluxium_bud",
			() -> new AmethystClusterBlock(3.0F, 4.0F, BlockBehaviour.Properties.of().noOcclusion().lightLevel((state) -> {
				return 12;}).sound(SoundType.GLASS)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> MEDIUM_BLUE_FLUXIUM_BUD = BLOCKS.register("medium_blue_fluxium_bud",
			() -> new AmethystClusterBlock(4.0F, 3.0F, BlockBehaviour.Properties.of().noOcclusion().lightLevel((state) -> {
				return 12;}).sound(SoundType.GLASS)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> LARGE_BLUE_FLUXIUM_BUD = BLOCKS.register("large_blue_fluxium_bud",
			() -> new AmethystClusterBlock(5.0F, 3.0F, BlockBehaviour.Properties.of().noOcclusion().lightLevel((state) -> {
				return 12;}).sound(SoundType.GLASS)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> BLUE_FLUXIUM_CLUSTER = BLOCKS.register("blue_fluxium_cluster",
			() -> new AmethystClusterBlock(7.0F, 3.0F, BlockBehaviour.Properties.of().forceSolidOn()
	                .noOcclusion().lightLevel((state) -> {
				return 12;}).sound(SoundType.GLASS)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> SMALL_RED_FLUXIUM_BUD = BLOCKS.register("small_red_fluxium_bud",
			() -> new AmethystClusterBlock(3.0F, 4.0F, BlockBehaviour.Properties.of().noOcclusion().lightLevel((state) -> {
				return 12;}).sound(SoundType.GLASS)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> MEDIUM_RED_FLUXIUM_BUD = BLOCKS.register("medium_red_fluxium_bud",
			() -> new AmethystClusterBlock(4.0F, 3.0F, BlockBehaviour.Properties.of().noOcclusion().lightLevel((state) -> {
				return 12;}).sound(SoundType.GLASS)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> LARGE_RED_FLUXIUM_BUD = BLOCKS.register("large_red_fluxium_bud",
			() -> new AmethystClusterBlock(5.0F, 3.0F, BlockBehaviour.Properties.of().noOcclusion().lightLevel((state) -> {
				return 12;}).sound(SoundType.GLASS)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> RED_FLUXIUM_CLUSTER = BLOCKS.register("red_fluxium_cluster",
			() -> new AmethystClusterBlock(7.0F, 3.0F, BlockBehaviour.Properties.of().noOcclusion().forceSolidOn()
	                .noOcclusion().lightLevel((state) -> {
				return 12;}).sound(SoundType.GLASS)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> SMALL_YELLOW_FLUXIUM_BUD = BLOCKS.register("small_yellow_fluxium_bud",
			() -> new AmethystClusterBlock(3.0F, 4.0F, BlockBehaviour.Properties.of().noOcclusion().lightLevel((state) -> {
				return 12;}).sound(SoundType.GLASS)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> MEDIUM_YELLOW_FLUXIUM_BUD = BLOCKS.register("medium_yellow_fluxium_bud",
			() -> new AmethystClusterBlock(4.0F, 3.0F, BlockBehaviour.Properties.of().noOcclusion().lightLevel((state) -> {
				return 12;}).sound(SoundType.GLASS)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> LARGE_YELLOW_FLUXIUM_BUD = BLOCKS.register("large_yellow_fluxium_bud",
			() -> new AmethystClusterBlock(5.0F, 3.0F, BlockBehaviour.Properties.of().noOcclusion().lightLevel((state) -> {
				return 12;}).sound(SoundType.GLASS)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> YELLOW_FLUXIUM_CLUSTER = BLOCKS.register("yellow_fluxium_cluster",
			() -> new AmethystClusterBlock(7.0F, 3.0F, BlockBehaviour.Properties.of().forceSolidOn()
	                .noOcclusion().lightLevel((state) -> {
				return 12;}).sound(SoundType.GLASS)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> SMALL_GREEN_FLUXIUM_BUD = BLOCKS.register("small_green_fluxium_bud",
			() -> new AmethystClusterBlock(3.0F, 4.0F, BlockBehaviour.Properties.of().noOcclusion().lightLevel((state) -> {
				return 12;}).sound(SoundType.GLASS)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> MEDIUM_GREEN_FLUXIUM_BUD = BLOCKS.register("medium_green_fluxium_bud",
			() -> new AmethystClusterBlock(4.0F, 3.0F, BlockBehaviour.Properties.of().noOcclusion().lightLevel((state) -> {
				return 12;}).sound(SoundType.GLASS)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> LARGE_GREEN_FLUXIUM_BUD = BLOCKS.register("large_green_fluxium_bud",
			() -> new AmethystClusterBlock(5.0F, 3.0F, BlockBehaviour.Properties.of().noOcclusion().lightLevel((state) -> {
				return 12;}).sound(SoundType.GLASS)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> GREEN_FLUXIUM_CLUSTER = BLOCKS.register("green_fluxium_cluster",
			() -> new AmethystClusterBlock(7.0F, 3.0F, BlockBehaviour.Properties.of().forceSolidOn()
	                .noOcclusion().lightLevel((state) -> {
				return 12;}).sound(SoundType.GLASS)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> SMALL_PURPLE_FLUXIUM_BUD = BLOCKS.register("small_purple_fluxium_bud",
			() -> new AmethystClusterBlock(3.0F, 4.0F, BlockBehaviour.Properties.of().noOcclusion().lightLevel((state) -> {
				return 12;}).sound(SoundType.GLASS)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> MEDIUM_PURPLE_FLUXIUM_BUD = BLOCKS.register("medium_purple_fluxium_bud",
			() -> new AmethystClusterBlock(4.0F, 3.0F, BlockBehaviour.Properties.of().noOcclusion().lightLevel((state) -> {
				return 12;}).sound(SoundType.GLASS)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> LARGE_PURPLE_FLUXIUM_BUD = BLOCKS.register("large_purple_fluxium_bud",
			() -> new AmethystClusterBlock(5.0F, 3.0F, BlockBehaviour.Properties.of().noOcclusion().lightLevel((state) -> {
				return 12;}).sound(SoundType.GLASS)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> PURPLE_FLUXIUM_CLUSTER = BLOCKS.register("purple_fluxium_cluster",
			() -> new AmethystClusterBlock(7.0F, 3.0F, BlockBehaviour.Properties.of().forceSolidOn()
	                .noOcclusion().noOcclusion().lightLevel((state) -> {
				return 12;}).sound(SoundType.GLASS)
					.requiresCorrectToolForDrops()));
	
	
	
	//slabs, walls, and stairs
	
	public static final DeferredBlock<Block> METEOR_ROCK_SLAB = BLOCKS.register("meteor_rock_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.NETHERITE_BLOCK).strength(3.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> METEOR_ROCK_STAIRS = BLOCKS.register("meteor_rock_stairs",
			() -> new StairBlock(MARTIAN_SPACE_PLATING.get().defaultBlockState(),
					Block.Properties.ofFullCopy(MARTIAN_SPACE_PLATING.get())));
	
	public static final DeferredBlock<Block> METEOR_ROCK_WALL = BLOCKS.register("meteor_rock_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(3.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	
	public static final DeferredBlock<Block> POLISHED_METEOR_ROCK_SLAB = BLOCKS.register("polished_meteor_rock_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.NETHERITE_BLOCK).strength(3.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> POLISHED_METEOR_ROCK_STAIRS = BLOCKS.register("polished_meteor_rock_stairs",
			() -> new StairBlock(MARTIAN_SPACE_PLATING.get().defaultBlockState(),
					Block.Properties.ofFullCopy(MARTIAN_SPACE_PLATING.get())));
	
	public static final DeferredBlock<Block> POLISHED_METEOR_ROCK_WALL = BLOCKS.register("polished_meteor_rock_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(3.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> METEOR_ROCK_BRICK_SLAB = BLOCKS.register("meteor_rock_brick_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(3.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> METEOR_ROCK_BRICK_STAIRS = BLOCKS.register("meteor_rock_brick_stairs",
			() -> new StairBlock(METEOR_ROCK_BRICKS.get().defaultBlockState(),
					Block.Properties.ofFullCopy(METEOR_ROCK_BRICKS.get())));
	
	public static final DeferredBlock<Block> METEOR_ROCK_BRICK_WALL = BLOCKS.register("meteor_rock_brick_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(3.0F, 1200F)
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
	
	
	public static final DeferredBlock<Block> MARTIAN_SPACE_PLATING_SLAB = BLOCKS.register("martian_space_plating_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.NETHERITE_BLOCK).strength(3.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> MARTIAN_SPACE_PLATING_STAIRS = BLOCKS.register("martian_space_plating_stairs",
			() -> new StairBlock(MARTIAN_SPACE_PLATING.get().defaultBlockState(),
					Block.Properties.ofFullCopy(MARTIAN_SPACE_PLATING.get())));
	
	
	public static final DeferredBlock<Block> MARTIAN_INTERIOR_SLAB = BLOCKS.register("martian_interior_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.NETHERITE_BLOCK).strength(3.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	
	public static final DeferredBlock<Block> COSMIUM_SPACE_PLATING_SLAB = BLOCKS.register("cosmium_space_plating_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.NETHERITE_BLOCK).strength(3.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> COSMIUM_SPACE_PLATING_STAIRS = BLOCKS.register("cosmium_space_plating_stairs",
			() -> new StairBlock(COSMIUM_SPACE_PLATING.get().defaultBlockState(),
					Block.Properties.ofFullCopy(COSMIUM_SPACE_PLATING.get())));
	
	
	public static final DeferredBlock<Block> COSMIUM_INTERIOR_SLAB = BLOCKS.register("cosmium_interior_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().sound(SoundType.NETHERITE_BLOCK).strength(3.0F, 1200F)
					.requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<Block> COSMIUM_INTERIOR_STAIRS = BLOCKS.register("cosmium_interior_stairs",
			() -> new StairBlock(COSMIUM_SPACE_PLATING.get().defaultBlockState(),
					Block.Properties.ofFullCopy(COSMIUM_SPACE_PLATING.get())));

	
}
