package com.wither.simplymartians.entities;

import com.wither.simplymartians.core.init.InitDamageSources;
import com.wither.simplymartians.core.init.ModParticleTypes;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
public class PlasmaEffect extends MobEffect {

	public PlasmaEffect(MobEffectCategory pCategory, int pColor) {
		super(pCategory, pColor);

	}
	
@Override
	public boolean applyEffectTick(LivingEntity entity, int p_294798_) {
    if (entity.level() instanceof ServerLevel serverLevel) {

			entity.hurt(InitDamageSources.overshock(entity), 1.2f);

    }
		return true;
    
		
}
		@Override
		public ParticleOptions createParticleOptions(MobEffectInstance instance) {
			return ModParticleTypes.PLASMASHOCK.get();
		}
		
	

	@Override
	public boolean shouldApplyEffectTickThisTick(int p_295629_, int p_295734_) {
		int i = 60 >> p_295734_;
		return i > 0 ? p_295629_ % i == 0 : true;
	}
}
