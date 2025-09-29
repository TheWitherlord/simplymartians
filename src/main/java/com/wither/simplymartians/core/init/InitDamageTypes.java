package com.wither.simplymartians.core.init;

import com.wither.simplymartians.SimplyMartians;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

public class InitDamageTypes {
	public static final ResourceKey<DamageType> MARTIAN_SHOCK = ResourceKey.create(
	        Registries.DAMAGE_TYPE,  ResourceLocation.fromNamespaceAndPath(SimplyMartians.MODID, "martian_shock")
	    );
	
	
	public static final ResourceKey<DamageType> OVERSHOCK = ResourceKey.create(
	        Registries.DAMAGE_TYPE,  ResourceLocation.fromNamespaceAndPath(SimplyMartians.MODID, "overshock")
	    );
}
