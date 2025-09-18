package com.wither.simplymartians.entities;

import com.wither.simplymartians.core.init.InitEntity;
import com.wither.simplymartians.core.init.ModMobEffects;

import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class FluxWave extends ModProjectile {

    private int life;

	
	public FluxWave(EntityType<? extends FluxWave> p_36892_, Level p_36893_) {
		super(p_36892_, p_36893_);
		
	}

	@SuppressWarnings("this-escape")
	public FluxWave(Level world, LivingEntity thrower) {
		super(InitEntity.FLUX_WAVE.get(), world, thrower);
		this.shootFromRotation(thrower, thrower.getXRot(), thrower.getYRot(), 0, 0.2F, 1.0F);
	}

	@Override
	protected void onHitEntity(EntityHitResult pResult) {
		super.onHitEntity(pResult);
		if (!this.level().isClientSide) {
			
			LivingEntity livingentity = this.getOwner() instanceof LivingEntity livingentity1 ? livingentity1 : null;
			Entity entity = pResult.getEntity();
			if (livingentity != null) {
				livingentity.setLastHurtMob(entity);

			}

			DamageSource damagesource = this.damageSources().indirectMagic(this, livingentity);
			if (entity.hurt(damagesource, 2.0F) && entity instanceof LivingEntity livingentity2) {
				EnchantmentHelper.doPostAttackEffects((ServerLevel) this.level(), livingentity2, damagesource);
				((LivingEntity) entity).addEffect(new MobEffectInstance(ModMobEffects.OVERSHOCK, 85, 0, true, true));


			}

		}
	}
	
	
	

	 @Override
	    protected void onHitBlock(BlockHitResult pResult) {
	        super.onHitBlock(pResult);
	        if (!this.level().isClientSide) {
	            Vec3i vec3i = pResult.getDirection().getNormal();
	            Vec3 vec3 = Vec3.atLowerCornerOf(vec3i).multiply(0.25, 0.25, 0.25);
	            Vec3 vec31 = pResult.getLocation().add(vec3);
	            ((ServerLevel)this.level()).sendParticles(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 2, 0.2D, 0.2D, 0.2D, 0.0D);

	            this.discard();
	        }
	    }

	 @Override
		public void tick() {
			super.tick();
			this.life++;
	        if (this.life >= 55) {
	            this.discard();
	                  }
			
}
	 
	 
	 
	@Override
	protected double getDefaultGravity() {
		return 0F;
	}
	
	

	public boolean isPickable() {
		return false;
	}

	

	public boolean hurt(DamageSource p_37381_, float p_37382_) {
		return true;
	}

	
	

}