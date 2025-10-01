package com.wither.simplymartians.entities;

import java.util.List;

import com.wither.simplymartians.core.init.InitDamageSources;
import com.wither.simplymartians.core.init.InitEntity;
import com.wither.simplymartians.core.init.ModMobEffects;
import com.wither.simplymartians.core.init.ModParticleTypes;

import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class MartianGrenade extends Fireball {

	public MartianGrenade(EntityType<? extends MartianGrenade> p_36892_, Level p_36893_) {
		super(p_36892_, p_36893_);
	}
	
	 public MartianGrenade(Level level, LivingEntity owner, Vec3 movement) {
	        super(InitEntity.MARTIAN_GRENADE.get(), owner, movement, level);
	    }

	    public MartianGrenade(Level level, double x, double y, double z, Vec3 movement) {
	        super(InitEntity.MARTIAN_GRENADE.get(), x, y, z, movement, level);
	    }
	

	@SuppressWarnings("this-escape")
	public MartianGrenade(Level world, LivingEntity thrower) {
		super(InitEntity.MARTIAN_GRENADE.get(), world);
		this.shootFromRotation(thrower, thrower.getXRot(), thrower.getYRot(), 0, 0.5F, 1.0F);
	}

	
	
	@Override
	protected void onHitEntity(EntityHitResult pResult) {
		super.onHitEntity(pResult);
		if (!this.level().isClientSide) {
			
			LivingEntity livingentity = this.getOwner() instanceof LivingEntity livingentity1 ? livingentity1 : null;
			Entity entity = pResult.getEntity();
         

			if (livingentity != null) {
				livingentity.setLastHurtMob(entity);
				((ServerLevel)this.level()).sendParticles(ModParticleTypes.PLASMASHOCK.get(), this.getX(), this.getY(), this.getZ(), 2, 0.2D, 0.2D, 0.2D, 0.0D);
		         this.level().explode(this, this.getX(), this.getY(), this.getZ(), 1.2f, Level.ExplosionInteraction.MOB);
		            this.discard();


			}

			 DamageSource damagesource = InitDamageSources.martianShock(this.level(), livingentity);
				if (entity.hurt(damagesource, 2.0F) && entity instanceof LivingEntity livingentity2) {
					EnchantmentHelper.doPostAttackEffects((ServerLevel) this.level(), livingentity2, damagesource);
				
				

			}

		}
	}

	 
	protected void onHit(HitResult p_36913_) {
		super.onHit(p_36913_);
		if (p_36913_.getType() != HitResult.Type.ENTITY || !this.ownedBy(((EntityHitResult) p_36913_).getEntity())) {
			if (!this.level().isClientSide) {
				List<LivingEntity> list = this.level().getEntitiesOfClass(LivingEntity.class,
						this.getBoundingBox().inflate(4.0D, 2.0D, 4.0D));
				AreaEffectCloud areaeffectcloud = new AreaEffectCloud(this.level(), this.getX(), this.getY(),
						this.getZ());
				Entity entity = this.getOwner();
				if (entity instanceof LivingEntity) {
					areaeffectcloud.setOwner((LivingEntity) entity);
				}

				areaeffectcloud.setParticle(ModParticleTypes.PLASMASHOCK.get());
				areaeffectcloud.setRadius(4.0F);
				areaeffectcloud.setDuration(55);
				areaeffectcloud
						.setRadiusPerTick((2.0F - areaeffectcloud.getRadius()) / (float) areaeffectcloud.getDuration());
				areaeffectcloud.addEffect(new MobEffectInstance(ModMobEffects.PLASMA, 145, 0));

				if (!list.isEmpty()) {
					for (LivingEntity livingentity : list) {
						double d0 = this.distanceToSqr(livingentity);
						if (d0 < 16.0D) {
							areaeffectcloud.setPos(livingentity.getX(), livingentity.getY(), livingentity.getZ());
							break;
						}
					}
				}

				this.level().levelEvent(2006, this.blockPosition(), this.isSilent() ? -1 : 1);
				this.level().addFreshEntity(areaeffectcloud);
				this.discard();
			}

		}
	}

	 @Override
		protected double getDefaultGravity() {
			return 0.002F;
		}

	 @Override
	public boolean isPickable() {
		return true;
	}
	
	@Override
    protected boolean shouldBurn() {
        return false;
    }

	protected ParticleOptions getTrailParticle() {
		return ModParticleTypes.OVERSHOCK.get();
	}
}
