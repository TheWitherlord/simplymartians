package com.wither.simplymartians.entities;

import com.wither.simplymartians.core.init.ModParticleTypes;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

public class OvershockEffect extends MobEffect {

	public OvershockEffect(MobEffectCategory pCategory, int pColor) {
		super(pCategory, pColor);

	}
	
@Override
	public boolean applyEffectTick(LivingEntity entity, int p_294798_) {
			entity.hurt(entity.damageSources().generic(), 0.5F);
			
		return true;
		
}
		@Override
		public ParticleOptions createParticleOptions(MobEffectInstance instance) {
			return ModParticleTypes.OVERSHOCK.get();
		}
		
	

	@Override
	public boolean shouldApplyEffectTickThisTick(int p_295629_, int p_295734_) {
		int i = 5 >> p_295734_;
		return i > 0 ? p_295629_ % i == 0 : true;
	}
}
