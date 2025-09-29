package com.wither.simplymartians.core.init;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

public class InitDamageSources {
	
	 public static DamageSource martianShock(Level level, Entity attacker) {
	        Holder<DamageType> damageTypeHolder = level.registryAccess()
	            .registryOrThrow(Registries.DAMAGE_TYPE)
	            .getHolderOrThrow(InitDamageTypes.MARTIAN_SHOCK);
	        return new DamageSource(damageTypeHolder, attacker);
	    }
	 
	 public static DamageSource overshock(Entity causer) {
		    return new DamageSource(
		            causer.level().registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(InitDamageTypes.OVERSHOCK),
		            causer);
		}
	 
}