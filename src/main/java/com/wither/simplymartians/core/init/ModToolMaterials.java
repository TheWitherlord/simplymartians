package com.wither.simplymartians.core.init;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolMaterials {

	// tag/durability/eff/attack//enchant//repair

	public static final Tier MARTIAN = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2504, 8.2F, 0f, 11,
			() -> Ingredient.of(InitItem.MARTIAN_TECH));
	

}
