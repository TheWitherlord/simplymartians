package com.wither.simplymartians.core.init;

import java.util.function.Supplier;

import com.wither.simplymartians.SimplyMartians;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModParticleTypes {

	public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister
			.create(BuiltInRegistries.PARTICLE_TYPE, SimplyMartians.MODID);

	public static final Supplier<SimpleParticleType>OVERSHOCK = PARTICLE_TYPES.register("overshock",

			() -> new SimpleParticleType(false));
	
	public static final Supplier<SimpleParticleType>PLASMASHOCK = PARTICLE_TYPES.register("plasmashock",

			() -> new SimpleParticleType(false));
	
	public static final Supplier<SimpleParticleType>ENERGY_TRAIL = PARTICLE_TYPES.register("energy_trail",

			() -> new SimpleParticleType(false));

	
	
}
