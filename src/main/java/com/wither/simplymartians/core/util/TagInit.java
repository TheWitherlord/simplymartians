package com.wither.simplymartians.core.util;

import com.wither.simplymartians.SimplyMartians;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.Structure;

public class TagInit
{
	
    
    public static final TagKey<EntityType<?>> MARTIAN = TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(SimplyMartians.MODID, "martian"));
   
    public static final TagKey<Block> MARTIAN_MINEABLE = BlockTags.create(ResourceLocation.fromNamespaceAndPath(SimplyMartians.MODID, "martian_mineable"));



}
	



