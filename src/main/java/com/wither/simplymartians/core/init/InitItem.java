package com.wither.simplymartians.core.init;

import com.wither.simplymartians.SimplyMartians;
import com.wither.simplymartians.items.AuraforceItem;
import com.wither.simplymartians.items.MartianArmorItem;
import com.wither.simplymartians.items.MartianMaceItem;
import com.wither.simplymartians.items.MartianPowerCellItem;
import com.wither.simplymartians.items.MartianSwordItem;
import com.wither.simplymartians.items.MartianZapperItem;
import com.wither.simplymartians.items.VibraedgeItem;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class InitItem {
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SimplyMartians.MODID);


	
	public static final DeferredItem<Item> MARTIAN_TECH = ITEMS.register("martian_tech",
			() -> new Item(itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> MARTIAN_POWER_CELL = ITEMS.register("martian_power_cell",
			() -> new MartianPowerCellItem(itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> MARTIAN_KEY = ITEMS.register("martian_key",
			() -> new Item(itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> BLUE_FLUXIUM_CRYSTAL = ITEMS.register("blue_fluxium_crystal",
			() -> new Item(itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> RED_FLUXIUM_CRYSTAL = ITEMS.register("red_fluxium_crystal",
			() -> new Item(itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> YELLOW_FLUXIUM_CRYSTAL = ITEMS.register("yellow_fluxium_crystal",
			() -> new Item(itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> GREEN_FLUXIUM_CRYSTAL = ITEMS.register("green_fluxium_crystal",
			() -> new Item(itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> PURPLE_FLUXIUM_CRYSTAL = ITEMS.register("purple_fluxium_crystal",
			() -> new Item(itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> MARTIAN_SPACE_PLATES = ITEMS.register("martian_space_plates",
			() -> new Item(itemBuilder().stacksTo(64)));
	
	//martian weaponry
	
	public static final DeferredItem<Item> MARTIAN_ZAPPER = ITEMS.register("martian_zapper",
			() -> new MartianZapperItem(
					itemBuilder().stacksTo(1).durability(250).rarity(Rarity.RARE).fireResistant()));
	
	public static final DeferredItem<Item> VIBRAEDGE = ITEMS.register("vibraedge",
			() -> new MartianSwordItem(ModToolMaterials.MARTIAN, 6, -1.7F,
					itemBuilder().rarity(Rarity.UNCOMMON).fireResistant()
							.attributes(MartianSwordItem.createAttributes(ModToolMaterials.MARTIAN, 6, -1.7F))));
	
	public static final DeferredItem<Item> FLUXIUM_SWORD = ITEMS.register("fluxium_sword",
			() -> new VibraedgeItem(ModToolMaterials.MARTIAN, itemBuilder().rarity(Rarity.RARE).fireResistant()
							.attributes(MartianSwordItem.createAttributes(ModToolMaterials.MARTIAN, 7, -2.0f))));
	
	public static final DeferredItem<Item> AURAFORCE = ITEMS.register("auraforce",
			() -> new AuraforceItem(ModToolMaterials.MARTIAN, itemBuilder().rarity(Rarity.EPIC).fireResistant()
							.attributes(MartianSwordItem.createAttributes(ModToolMaterials.MARTIAN, 9, -2.6f))));
	
	
	public static final DeferredItem<Item> TECHNOMACE = ITEMS.register("technomace",
			() -> new MartianMaceItem(ModToolMaterials.MARTIAN, itemBuilder().rarity(Rarity.EPIC).fireResistant()
							.attributes(MartianMaceItem.createAttributes(ModToolMaterials.MARTIAN, 7, -3.2F, 0.50f, 2))));
	
	//armor
	public static final DeferredItem<Item> MARTIAN_HELMET = ITEMS.register("martian_helmet",
			() -> new MartianArmorItem(ModArmorMaterials.MARTIAN, ArmorItem.Type.HELMET,
					itemBuilder().durability(407).rarity(Rarity.RARE).stacksTo(1)));
	public static final DeferredItem<Item> MARTIAN_CHESTPLATE = ITEMS.register("martian_chestplate",
			() -> new MartianArmorItem(ModArmorMaterials.MARTIAN, ArmorItem.Type.CHESTPLATE,
					itemBuilder().durability(592).rarity(Rarity.RARE).stacksTo(1)));
	public static final DeferredItem<Item> MARTIAN_LEGGINGS = ITEMS.register("martian_leggings",
			() -> new MartianArmorItem(ModArmorMaterials.MARTIAN, ArmorItem.Type.LEGGINGS,
					itemBuilder().durability(555).rarity(Rarity.RARE).stacksTo(1)));
	public static final DeferredItem<Item> MARTIAN_BOOTS = ITEMS.register("martian_boots",
			() -> new MartianArmorItem(ModArmorMaterials.MARTIAN, ArmorItem.Type.BOOTS,
					itemBuilder().durability(481).rarity(Rarity.RARE).stacksTo(1)));
	
	
	//blocks
	
	public static final DeferredItem<Item> MARTIAN_SPACE_PLATING = ITEMS.register("martian_space_plating",
			() -> new BlockItem(InitBlocks.MARTIAN_SPACE_PLATING.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> SMALL_MARTIAN_SPACE_PLATING = ITEMS.register("small_martian_space_plating",
			() -> new BlockItem(InitBlocks.SMALL_MARTIAN_SPACE_PLATING.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> MARTIAN_FLOORING = ITEMS.register("martian_flooring",
			() -> new BlockItem(InitBlocks.MARTIAN_FLOORING.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> MARTIAN_SCREEN = ITEMS.register("martian_screen",
			() -> new BlockItem(InitBlocks.MARTIAN_SCREEN.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> MARTIAN_INTERIOR = ITEMS.register("martian_interior",
			() -> new BlockItem(InitBlocks.MARTIAN_INTERIOR.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> MARTIAN_INTERIOR_LAMP = ITEMS.register("martian_interior_lamp",
			() -> new BlockItem(InitBlocks.MARTIAN_INTERIOR_LAMP.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> MARTIAN_FLOOR_LIGHT = ITEMS.register("martian_floor_light",
			() -> new BlockItem(InitBlocks.MARTIAN_FLOOR_LIGHT.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> MARTIAN_CONSOLE = ITEMS.register("martian_console",
			() -> new BlockItem(InitBlocks.MARTIAN_CONSOLE.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> METEOR_ROCK = ITEMS.register("meteor_rock",
			() -> new BlockItem(InitBlocks.METEOR_ROCK.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> FLUXIUM_ORE = ITEMS.register("fluxium_ore",
			() -> new BlockItem(InitBlocks.FLUXIUM_ORE.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> TEKTITE = ITEMS.register("tektite",
			() -> new BlockItem(InitBlocks.TEKTITE.get(), itemBuilder().stacksTo(64)));
	
	//slabs and stairs
	
	public static final DeferredItem<Item> MARTIAN_SPACE_PLATING_SLAB = ITEMS.register("martian_space_plating_slab",
			() -> new BlockItem(InitBlocks.MARTIAN_SPACE_PLATING_SLAB.get(), itemBuilder().stacksTo(64)));

	public static final DeferredItem<Item> MARTIAN_SPACE_PLATING_STAIRS = ITEMS.register("martian_space_plating_stairs",
			() -> new BlockItem(InitBlocks.MARTIAN_SPACE_PLATING_STAIRS.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> MARTIAN_FLOORING_SLAB = ITEMS.register("martian_flooring_slab",
			() -> new BlockItem(InitBlocks.MARTIAN_FLOORING_SLAB.get(), itemBuilder().stacksTo(64)));

	public static final DeferredItem<Item> MARTIAN_FLOORING_STAIRS = ITEMS.register("martian_flooring_stairs",
			() -> new BlockItem(InitBlocks.MARTIAN_FLOORING_STAIRS.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> MARTIAN_INTERIOR_SLAB = ITEMS.register("martian_interior_slab",
			() -> new BlockItem(InitBlocks.MARTIAN_INTERIOR_SLAB.get(), itemBuilder().stacksTo(64)));

	public static final DeferredItem<Item> MARTIAN_INTERIOR_STAIRS = ITEMS.register("martian_interior_stairs",
			() -> new BlockItem(InitBlocks.MARTIAN_INTERIOR_STAIRS.get(), itemBuilder().stacksTo(64)));
	
	
	
	public static final DeferredItem<Item> SIMPLE_MARTIAN_SPAWN_EGG = ITEMS.register("martian_spawn_egg",
			() -> new DeferredSpawnEggItem(InitEntity.MARTIAN, 0XCCEBFF, 0XBAFF68, new Item.Properties()));
	
	public static final DeferredItem<Item> MARTIAN_BRUTE_SPAWN_EGG = ITEMS.register("martian_brute_spawn_egg",
			() -> new DeferredSpawnEggItem(InitEntity.MARTIAN_BRUTE, 0XCCEBFF, 0XBAFF68, new Item.Properties()));

	private static Item.Properties itemBuilder() {
		return new Item.Properties();
	}
}
