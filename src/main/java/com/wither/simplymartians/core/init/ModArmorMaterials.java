package com.wither.simplymartians.core.init;

import java.util.EnumMap;
import java.util.List;

import com.wither.simplymartians.SimplyMartians;
import com.wither.simplymartians.items.MartianArmorItem;

import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModArmorMaterials {

	public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister
			.create(Registries.ARMOR_MATERIAL, SimplyMartians.MODID);
	
	

	// defense//enchant//sound//repair//layer//name//toughness//knockbackresist

	public static final DeferredHolder<ArmorMaterial, ArmorMaterial> MARTIAN = ARMOR_MATERIALS.register("martian", () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
		map.put(MartianArmorItem.Type.BOOTS, 3);
		map.put(MartianArmorItem.Type.LEGGINGS, 6);
		map.put(MartianArmorItem.Type.CHESTPLATE, 8);
		map.put(MartianArmorItem.Type.HELMET, 2);
		map.put(MartianArmorItem.Type.BODY, 11);
	}), 11, SoundEvents.ARMOR_EQUIP_IRON, () -> Ingredient.of(InitItem.MARTIAN_TECH.get()), List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(SimplyMartians.MODID,"martian"))), 1.5F, 0.0F));
}