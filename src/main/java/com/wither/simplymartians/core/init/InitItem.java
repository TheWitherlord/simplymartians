package com.wither.simplymartians.core.init;

import com.wither.simplymartians.SimplyMartians;
import com.wither.simplymartians.items.AuraforceItem;
import com.wither.simplymartians.items.BlasteratorItem;
import com.wither.simplymartians.items.DecimatorItem;
import com.wither.simplymartians.items.EnergyRifleItem;
import com.wither.simplymartians.items.EnergyRipperItem;
import com.wither.simplymartians.items.EngineerGogglesItem;
import com.wither.simplymartians.items.MartianArmorItem;
import com.wither.simplymartians.items.MartianDrillItem;
import com.wither.simplymartians.items.MartianMaceItem;
import com.wither.simplymartians.items.MartianPowerCellItem;
import com.wither.simplymartians.items.MartianSwordItem;
import com.wither.simplymartians.items.MartianZapperItem;
import com.wither.simplymartians.items.MegaMartianDrillItem;
import com.wither.simplymartians.items.MeteorSummonerItem;
import com.wither.simplymartians.items.MottgunItem;
import com.wither.simplymartians.items.PlasmaCutterItem;
import com.wither.simplymartians.items.PlasmaRifleItem;
import com.wither.simplymartians.items.RocketMaceItem;
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

	public static final DeferredItem<Item> STRANGE_MARTIAN_TECH = ITEMS.register("strange_martian_tech",
			() -> new Item(itemBuilder().rarity(Rarity.EPIC).stacksTo(64)));
	
	public static final DeferredItem<Item> MARTIAN_TECH = ITEMS.register("martian_tech",
			() -> new Item(itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> MARTIAN_POWER_CELL = ITEMS.register("martian_power_cell",
			() -> new MartianPowerCellItem(itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> MARTIAN_KEY = ITEMS.register("martian_key",
			() -> new Item(itemBuilder().rarity(Rarity.RARE).stacksTo(64)));

	public static final DeferredItem<Item> RAW_COSMIUM = ITEMS.register("raw_cosmium",
			() -> new Item(itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> COSMIUM_INGOT = ITEMS.register("cosmium_ingot",
			() -> new Item(itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> COSMIUM_NUGGET = ITEMS.register("cosmium_nugget",
			() -> new Item(itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> METEORSTONE = ITEMS.register("meteorstone",
			() -> new Item(itemBuilder().stacksTo(64)));

	public static final DeferredItem<Item> UNREFINED_FLUXIUM = ITEMS.register("unrefined_fluxium",
			() -> new Item(itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> REFINED_FLUXIUM = ITEMS.register("refined_fluxium",
			() -> new Item(itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> BLUE_FLUXIUM_CRYSTAL = ITEMS.register("blue_fluxium_crystal",
			() -> new Item(itemBuilder().rarity(Rarity.UNCOMMON).stacksTo(64)));
	
	public static final DeferredItem<Item> RED_FLUXIUM_CRYSTAL = ITEMS.register("red_fluxium_crystal",
			() -> new Item(itemBuilder().rarity(Rarity.UNCOMMON).stacksTo(64)));
	
	public static final DeferredItem<Item> YELLOW_FLUXIUM_CRYSTAL = ITEMS.register("yellow_fluxium_crystal",
			() -> new Item(itemBuilder().rarity(Rarity.UNCOMMON).stacksTo(64)));
	
	public static final DeferredItem<Item> GREEN_FLUXIUM_CRYSTAL = ITEMS.register("green_fluxium_crystal",
			() -> new Item(itemBuilder().rarity(Rarity.UNCOMMON).stacksTo(64)));
	
	public static final DeferredItem<Item> PURPLE_FLUXIUM_CRYSTAL = ITEMS.register("purple_fluxium_crystal",
			() -> new Item(itemBuilder().rarity(Rarity.UNCOMMON).stacksTo(64)));
	
	public static final DeferredItem<Item> MARTIAN_SPACE_PLATES = ITEMS.register("martian_space_plates",
			() -> new Item(itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> METEOR_DEVICE = ITEMS.register("meteor_device",
			() -> new MeteorSummonerItem(itemBuilder().rarity(Rarity.EPIC).stacksTo(1)));
	
	
	
	//martian weaponry
	
	public static final DeferredItem<Item> MARTIAN_ZAPPER = ITEMS.register("martian_zapper",
			() -> new MartianZapperItem(
					itemBuilder().stacksTo(1).durability(250).rarity(Rarity.RARE).fireResistant()));
	
	public static final DeferredItem<Item> PLASMA_RIFLE = ITEMS.register("plasma_rifle",
			() -> new PlasmaRifleItem(
					itemBuilder().stacksTo(1).durability(485).rarity(Rarity.EPIC).fireResistant()));
	
	public static final DeferredItem<Item> ENERGY_RIFLE = ITEMS.register("energy_rifle",
			() -> new EnergyRifleItem(
					itemBuilder().stacksTo(1).durability(485).rarity(Rarity.EPIC).fireResistant()));
	
	public static final DeferredItem<Item> MOTTGUN = ITEMS.register("mottgun",
			() -> new MottgunItem(
					itemBuilder().stacksTo(1).durability(2420).rarity(Rarity.EPIC).fireResistant()));
	
	public static final DeferredItem<Item> BLASTERATOR = ITEMS.register("blasterator",
			() -> new BlasteratorItem(
					itemBuilder().stacksTo(1).durability(2420).rarity(Rarity.EPIC).fireResistant()));
	
	public static final DeferredItem<Item> DECIMATOR = ITEMS.register("decimator",
			() -> new DecimatorItem(
					itemBuilder().stacksTo(1).durability(2420).rarity(Rarity.EPIC).fireResistant()));
	
	public static final DeferredItem<Item> ENERGY_RIPPER = ITEMS.register("energy_ripper",
			() -> new EnergyRipperItem(ModToolMaterials.MARTIAN, itemBuilder().rarity(Rarity.EPIC).fireResistant()
							.attributes(MartianSwordItem.createAttributes(ModToolMaterials.MARTIAN, 7, -1.7F))));
	
	public static final DeferredItem<Item> VIBRAEDGE = ITEMS.register("vibraedge",
			() -> new MartianSwordItem(ModToolMaterials.MARTIAN, 7, -2.0F,
					itemBuilder().rarity(Rarity.RARE).fireResistant()
							.attributes(MartianSwordItem.createAttributes(ModToolMaterials.MARTIAN, 7, -1.7F))));
	 
	public static final DeferredItem<Item> RED_VIBRAEDGE = ITEMS.register("red_vibraedge",
			() -> new MartianSwordItem(ModToolMaterials.MARTIAN, 7, -2.0F,
					itemBuilder().rarity(Rarity.RARE).fireResistant()
							.attributes(MartianSwordItem.createAttributes(ModToolMaterials.MARTIAN, 7, -1.7F))));
	
	public static final DeferredItem<Item> YELLOW_VIBRAEDGE = ITEMS.register("yellow_vibraedge",
			() -> new MartianSwordItem(ModToolMaterials.MARTIAN, 7, -2.0F,
					itemBuilder().rarity(Rarity.RARE).fireResistant()
							.attributes(MartianSwordItem.createAttributes(ModToolMaterials.MARTIAN, 7, -1.7F))));
	
	public static final DeferredItem<Item> GREEN_VIBRAEDGE = ITEMS.register("green_vibraedge",
			() -> new MartianSwordItem(ModToolMaterials.MARTIAN, 7, -2.0F,
					itemBuilder().rarity(Rarity.RARE).fireResistant()
							.attributes(MartianSwordItem.createAttributes(ModToolMaterials.MARTIAN, 7, -1.7F))));
	
	public static final DeferredItem<Item> PURPLE_VIBRAEDGE = ITEMS.register("purple_vibraedge",
			() -> new MartianSwordItem(ModToolMaterials.MARTIAN, 7, -2.0F,
					itemBuilder().rarity(Rarity.RARE).fireResistant()
							.attributes(MartianSwordItem.createAttributes(ModToolMaterials.MARTIAN, 7, -1.7F))));
	
	public static final DeferredItem<Item> FLUXIUM_SWORD = ITEMS.register("fluxium_sword",
			() -> new VibraedgeItem(ModToolMaterials.MARTIAN, itemBuilder().rarity(Rarity.RARE).fireResistant()
							.attributes(MartianSwordItem.createAttributes(ModToolMaterials.MARTIAN, 8, -2.2f))));
	
	public static final DeferredItem<Item> AURAFORCE = ITEMS.register("auraforce",
			() -> new AuraforceItem(ModToolMaterials.MARTIAN, itemBuilder().rarity(Rarity.EPIC).fireResistant()
							.attributes(MartianSwordItem.createAttributes(ModToolMaterials.MARTIAN, 9, -2.6f))));
	
	public static final DeferredItem<Item> PLASMACUTTER = ITEMS.register("plasmacutter",
			() -> new PlasmaCutterItem(ModToolMaterials.MARTIAN, itemBuilder().rarity(Rarity.EPIC).fireResistant()
							.attributes(PlasmaCutterItem.createAttributes(ModToolMaterials.MARTIAN, 10, -2.6f))));
	
	
	public static final DeferredItem<Item> TECHNOMACE = ITEMS.register("technomace",
			() -> new MartianMaceItem(ModToolMaterials.MARTIAN, itemBuilder().rarity(Rarity.EPIC).fireResistant()
							.attributes(MartianMaceItem.createAttributes(ModToolMaterials.MARTIAN, 7, -3.2F, 0.50f, 2))));
	
	public static final DeferredItem<Item> ROCKET_MAUL = ITEMS.register("rocket_maul",
			() -> new RocketMaceItem(ModToolMaterials.MARTIAN, itemBuilder().rarity(Rarity.EPIC).fireResistant()
							.attributes(MartianMaceItem.createAttributes(ModToolMaterials.MARTIAN, 7, -3.2F, 0.50f, 3))));
	
	
	//martian tools
	
	
	
	public static final DeferredItem<Item> FLUXIUM_DRILL = ITEMS.register("fluxium_drill",
			() -> new MartianDrillItem(3, -1.2F, ModToolMaterials.MARTIAN,
					itemBuilder().rarity(Rarity.UNCOMMON).fireResistant()
							.attributes(MartianSwordItem.createAttributes(ModToolMaterials.MARTIAN, 3, -1.2F))));
	
	public static final DeferredItem<Item> RED_FLUXIUM_DRILL = ITEMS.register("red_fluxium_drill",
			() -> new MartianDrillItem(3, -1.2F, ModToolMaterials.MARTIAN,
					itemBuilder().rarity(Rarity.UNCOMMON).fireResistant()
							.attributes(MartianSwordItem.createAttributes(ModToolMaterials.MARTIAN, 3, -1.2F))));
	
	public static final DeferredItem<Item> YELLOW_FLUXIUM_DRILL = ITEMS.register("yellow_fluxium_drill",
			() -> new MartianDrillItem(3, -1.2F, ModToolMaterials.MARTIAN,
					itemBuilder().rarity(Rarity.UNCOMMON).fireResistant()
							.attributes(MartianSwordItem.createAttributes(ModToolMaterials.MARTIAN, 3, -1.2F))));
	
	public static final DeferredItem<Item> GREEN_FLUXIUM_DRILL = ITEMS.register("green_fluxium_drill",
			() -> new MartianDrillItem(3, -1.2F, ModToolMaterials.MARTIAN,
					itemBuilder().rarity(Rarity.UNCOMMON).fireResistant()
							.attributes(MartianSwordItem.createAttributes(ModToolMaterials.MARTIAN, 3, -1.2F))));
	
	public static final DeferredItem<Item> PURPLE_FLUXIUM_DRILL = ITEMS.register("purple_fluxium_drill",
			() -> new MartianDrillItem(3, -1.2F, ModToolMaterials.MARTIAN,
					itemBuilder().rarity(Rarity.UNCOMMON).fireResistant()
							.attributes(MartianSwordItem.createAttributes(ModToolMaterials.MARTIAN, 3, -1.2F))));
	
	
	public static final DeferredItem<Item> MEGA_DRILL = ITEMS.register("mega_drill",
			() -> new MegaMartianDrillItem(3, -1.2F, ModToolMaterials.MARTIAN,
					itemBuilder().rarity(Rarity.EPIC).fireResistant()
							.attributes(MartianSwordItem.createAttributes(ModToolMaterials.MARTIAN, 3, -1.2F))));
	
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
	
	public static final DeferredItem<Item> ENGINEER_GOGGLES = ITEMS.register("engineer_goggles",
			() -> new EngineerGogglesItem(ModArmorMaterials.ENGINEER, ArmorItem.Type.HELMET,
					itemBuilder().durability(328).rarity(Rarity.EPIC).stacksTo(1)));
	
	
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
	
	public static final DeferredItem<Item> HOT_METEOR_ROCK = ITEMS.register("hot_meteor_rock",
			() -> new BlockItem(InitBlocks.HOT_METEOR_ROCK.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> METEOR_ROCK = ITEMS.register("meteor_rock",
			() -> new BlockItem(InitBlocks.METEOR_ROCK.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> METEOR_ROCK_BRICKS = ITEMS.register("meteor_rock_bricks",
			() -> new BlockItem(InitBlocks.METEOR_ROCK_BRICKS.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> CHISELED_METEOR_ROCK = ITEMS.register("chiseled_meteor_rock",
			() -> new BlockItem(InitBlocks.CHISELED_METEOR_ROCK.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> POLISHED_METEOR_ROCK = ITEMS.register("polished_meteor_rock",
			() -> new BlockItem(InitBlocks.POLISHED_METEOR_ROCK.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> METEORIC_IRON_ORE = ITEMS.register("meteoric_iron_ore",
			() -> new BlockItem(InitBlocks.METEORIC_IRON_ORE.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> FLUXIUM_ORE = ITEMS.register("fluxium_ore",
			() -> new BlockItem(InitBlocks.FLUXIUM_ORE.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> TEKTITE = ITEMS.register("tektite",
			() -> new BlockItem(InitBlocks.TEKTITE.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> TEKTITE_BRICKS = ITEMS.register("tektite_bricks",
			() -> new BlockItem(InitBlocks.TEKTITE_BRICKS.get(), itemBuilder().stacksTo(64)));
	
	
	//fluxium cultivators
	
	public static final DeferredItem<Item> BLUE_FLUXIUM_CULTIVATOR = ITEMS.register("blue_fluxium_cultivator",
			() -> new BlockItem(InitBlocks.BLUE_FLUXIUM_CULTIVATOR.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> RED_FLUXIUM_CULTIVATOR = ITEMS.register("red_fluxium_cultivator",
			() -> new BlockItem(InitBlocks.RED_FLUXIUM_CULTIVATOR.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> YELLOW_FLUXIUM_CULTIVATOR = ITEMS.register("yellow_fluxium_cultivator",
			() -> new BlockItem(InitBlocks.YELLOW_FLUXIUM_CULTIVATOR.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> GREEN_FLUXIUM_CULTIVATOR = ITEMS.register("green_fluxium_cultivator",
			() -> new BlockItem(InitBlocks.GREEN_FLUXIUM_CULTIVATOR.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> PURPLE_FLUXIUM_CULTIVATOR = ITEMS.register("purple_fluxium_cultivator",
			() -> new BlockItem(InitBlocks.PURPLE_FLUXIUM_CULTIVATOR.get(), itemBuilder().stacksTo(64)));
	
	
	//fluxium crystals
	
	public static final DeferredItem<Item> SMALL_BLUE_FLUXIUM_BUD = ITEMS.register("small_blue_fluxium_bud",
			() -> new BlockItem(InitBlocks.SMALL_BLUE_FLUXIUM_BUD.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> MEDIUM_BLUE_FLUXIUM_BUD = ITEMS.register("medium_blue_fluxium_bud",
			() -> new BlockItem(InitBlocks.MEDIUM_BLUE_FLUXIUM_BUD.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> LARGE_BLUE_FLUXIUM_BUD = ITEMS.register("large_blue_fluxium_bud",
			() -> new BlockItem(InitBlocks.LARGE_BLUE_FLUXIUM_BUD.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> BLUE_FLUXIUM_CLUSTER = ITEMS.register("blue_fluxium_cluster",
			() -> new BlockItem(InitBlocks.BLUE_FLUXIUM_CLUSTER.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> SMALL_RED_FLUXIUM_BUD = ITEMS.register("small_red_fluxium_bud",
			() -> new BlockItem(InitBlocks.SMALL_RED_FLUXIUM_BUD.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> MEDIUM_RED_FLUXIUM_BUD = ITEMS.register("medium_red_fluxium_bud",
			() -> new BlockItem(InitBlocks.MEDIUM_RED_FLUXIUM_BUD.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> LARGE_RED_FLUXIUM_BUD = ITEMS.register("large_red_fluxium_bud",
			() -> new BlockItem(InitBlocks.LARGE_RED_FLUXIUM_BUD.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> RED_FLUXIUM_CLUSTER = ITEMS.register("red_fluxium_cluster",
			() -> new BlockItem(InitBlocks.RED_FLUXIUM_CLUSTER.get(), itemBuilder().stacksTo(64)));
	
	
	public static final DeferredItem<Item> SMALL_YELLOW_FLUXIUM_BUD = ITEMS.register("small_yellow_fluxium_bud",
			() -> new BlockItem(InitBlocks.SMALL_YELLOW_FLUXIUM_BUD.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> MEDIUM_YELLOW_FLUXIUM_BUD = ITEMS.register("medium_yellow_fluxium_bud",
			() -> new BlockItem(InitBlocks.MEDIUM_YELLOW_FLUXIUM_BUD.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> LARGE_YELLOW_FLUXIUM_BUD = ITEMS.register("large_yellow_fluxium_bud",
			() -> new BlockItem(InitBlocks.LARGE_YELLOW_FLUXIUM_BUD.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> YELLOW_FLUXIUM_CLUSTER = ITEMS.register("yellow_fluxium_cluster",
			() -> new BlockItem(InitBlocks.YELLOW_FLUXIUM_CLUSTER.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> SMALL_GREEN_FLUXIUM_BUD = ITEMS.register("small_green_fluxium_bud",
			() -> new BlockItem(InitBlocks.SMALL_GREEN_FLUXIUM_BUD.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> MEDIUM_GREEN_FLUXIUM_BUD = ITEMS.register("medium_green_fluxium_bud",
			() -> new BlockItem(InitBlocks.MEDIUM_GREEN_FLUXIUM_BUD.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> LARGE_GREEN_FLUXIUM_BUD = ITEMS.register("large_green_fluxium_bud",
			() -> new BlockItem(InitBlocks.LARGE_GREEN_FLUXIUM_BUD.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> GREEN_FLUXIUM_CLUSTER = ITEMS.register("green_fluxium_cluster",
			() -> new BlockItem(InitBlocks.GREEN_FLUXIUM_CLUSTER.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> SMALL_PURPLE_FLUXIUM_BUD = ITEMS.register("small_purple_fluxium_bud",
			() -> new BlockItem(InitBlocks.SMALL_PURPLE_FLUXIUM_BUD.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> MEDIUM_PURPLE_FLUXIUM_BUD = ITEMS.register("medium_purple_fluxium_bud",
			() -> new BlockItem(InitBlocks.MEDIUM_PURPLE_FLUXIUM_BUD.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> LARGE_PURPLE_FLUXIUM_BUD = ITEMS.register("large_purple_fluxium_bud",
			() -> new BlockItem(InitBlocks.LARGE_PURPLE_FLUXIUM_BUD.get(), itemBuilder().stacksTo(64)));
	
	public static final DeferredItem<Item> PURPLE_FLUXIUM_CLUSTER = ITEMS.register("purple_fluxium_cluster",
			() -> new BlockItem(InitBlocks.PURPLE_FLUXIUM_CLUSTER.get(), itemBuilder().stacksTo(64)));
	
	
	
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
	
	public static final DeferredItem<Item> ZOMBIE_MARTIAN_BRUTE_SPAWN_EGG = ITEMS.register("zombie_martian_brute_spawn_egg",
			() -> new DeferredSpawnEggItem(InitEntity.ZOMBIE_MARTIAN_BRUTE, 0XCCEBFF, 0XBAFF68, new Item.Properties()));
	
	public static final DeferredItem<Item> ZOMBIE_MARTIAN_SPAWN_EGG = ITEMS.register("zombie_martian_spawn_egg",
			() -> new DeferredSpawnEggItem(InitEntity.ZOMBIE_MARTIAN, 0XCCEBFF, 0XBAFF68, new Item.Properties()));
	
	public static final DeferredItem<Item> TESLA_BOT_SPAWN_EGG = ITEMS.register("tesla_bot_spawn_egg",
			() -> new DeferredSpawnEggItem(InitEntity.TESLA_BOT, 0XCCEBFF, 0XBAFF68, new Item.Properties()));
	
	public static final DeferredItem<Item> PROBE_SPAWN_EGG = ITEMS.register("probe_spawn_egg",
			() -> new DeferredSpawnEggItem(InitEntity.MARTIAN_PROBE, 0XCCEBFF, 0XBAFF68, new Item.Properties()));
	
	public static final DeferredItem<Item> MARTIAN_ENGINEER_SPAWN_EGG = ITEMS.register("martian_engineer_spawn_egg",
			() -> new DeferredSpawnEggItem(InitEntity.MARTIAN_ENGINEER, 0XCCEBFF, 0XBAFF68, new Item.Properties()));
	
	public static final DeferredItem<Item> MARTIAN_SURVEYOR_SPAWN_EGG = ITEMS.register("martian_surveyor_spawn_egg",
			() -> new DeferredSpawnEggItem(InitEntity.MARTIAN_SURVEYOR, 0XCCEBFF, 0XBAFF68, new Item.Properties()));

	private static Item.Properties itemBuilder() {
		return new Item.Properties();
	}
}
