package com.wither.simplymartians.entities;

import com.google.common.base.MoreObjects;
import com.wither.simplymartians.core.init.InitDamageSources;
import com.wither.simplymartians.core.init.InitEntity;
import com.wither.simplymartians.core.init.ModMobEffects;

import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
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

public class ZapBolt extends ModArrowLikeProjectile {
    private int life;

	public ZapBolt(EntityType<? extends ZapBolt> p_36892_, Level p_36893_) {
		super(p_36892_, p_36893_);


	}

	@SuppressWarnings("this-escape")
	public ZapBolt(Level world, LivingEntity thrower) {
		super(InitEntity.ZAP_BOLT.get(), world, thrower);
		this.shootFromRotation(thrower, thrower.getXRot(), thrower.getYRot(), 0, 1.8F, 1.0F);
	}

	public ZapBolt(Level worldIn, double x, double y, double z) {
		super(InitEntity.ZAP_BOLT.get(), worldIn, x, y, z);
	}

	
	
	
	 @Override
	    protected void onHitEntity(EntityHitResult result) {
	        super.onHitEntity(result);
	        if (this.level() instanceof ServerLevel serverlevel) {
	            Entity entity = result.getEntity();
	            boolean flag;
	            if (this.getOwner() instanceof LivingEntity livingentity) {
	                DamageSource damagesource = InitDamageSources.overshock(livingentity);
	                flag = entity.hurt(damagesource, 4.0F);
	                if (flag) {
	                    if (entity.isAlive()) {
	                        EnchantmentHelper.doPostAttackEffects(serverlevel, entity, damagesource);
	                   
	                }
	            
	            }

	          
	                if (flag && entity instanceof LivingEntity livingentity1) {
	 	               

	                    livingentity1.addEffect(new MobEffectInstance(ModMobEffects.OVERSHOCK, 30), this.getEffectSource());
	                    livingentity1.addEffect(new MobEffectInstance(MobEffects.GLOWING, 100), this.getEffectSource());
                           this.discard();
	                }
	            }
	            }
	    }
	
	
	
	

	@Override
	protected double getDefaultGravity() {
		return 0F;
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
	    protected void onHitBlock(BlockHitResult pResult) {
	        super.onHitBlock(pResult);
	        if (!this.level().isClientSide) {
	            Vec3i vec3i = pResult.getDirection().getNormal();
	            Vec3 vec3 = Vec3.atLowerCornerOf(vec3i).multiply(0.25, 0.25, 0.25);
	            Vec3 vec31 = pResult.getLocation().add(vec3);
	            ((ServerLevel)this.level()).sendParticles(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 2, 0.2D, 0.2D, 0.2D, 0.0D);
		        // this.level().explode(this, this.getX(), this.getY(), this.getZ(), 1.2f, Level.ExplosionInteraction.NONE);

	            this.discard();
	        }
	    }
	  
			


	    @Override
		public void handleEntityEvent(byte id) {
			if (id == 3) {
				for (int i = 0; i < 8; ++i) {
					this.level().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), this.random.nextGaussian() * 0.05D, random.nextDouble() * 0.2D, random.nextGaussian() * 0.05D);
				}
			} else {
				super.handleEntityEvent(id);
			}
		}
		
	    
}