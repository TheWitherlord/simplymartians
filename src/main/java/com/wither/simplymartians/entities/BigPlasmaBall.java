package com.wither.simplymartians.entities;

import java.util.Comparator;
import java.util.List;

import javax.annotation.Nullable;

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
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class BigPlasmaBall extends ModProjectile {
    private int life;

	public BigPlasmaBall(EntityType<? extends BigPlasmaBall> p_36892_, Level p_36893_) {
		super(p_36892_, p_36893_);


	}

	@SuppressWarnings("this-escape")
	public BigPlasmaBall(Level world, LivingEntity thrower) {
		super(InitEntity.BIG_PLASMA_BALL.get(), world, thrower);
		this.shootFromRotation(thrower, thrower.getXRot(), thrower.getYRot(), 0, 0.05F, 1.0F);
	}

	public BigPlasmaBall(Level worldIn, double x, double y, double z) {
		super(InitEntity.BIG_PLASMA_BALL.get(), worldIn, x, y, z);
	}

	@Nullable
	@Override
    protected ParticleOptions getTrailParticle() {
        return ModParticleTypes.ENERGY_TRAIL.get();
    }
	
	
	 @Override
	    protected void onHitEntity(EntityHitResult result) {
	        super.onHitEntity(result);
	        if (this.level() instanceof ServerLevel serverlevel) {
	            Entity entity = result.getEntity();
	            boolean flag;
	            if (this.getOwner() instanceof LivingEntity livingentity) {
	                DamageSource damagesource = InitDamageSources.overshock( livingentity);
	                flag = entity.hurt(damagesource, 6.0F);
	                if (flag) {
	                    if (entity.isAlive()) {
	                        EnchantmentHelper.doPostAttackEffects(serverlevel, entity, damagesource);
	                   
	                    }
	                }
	           

	            if (flag && entity instanceof LivingEntity livingentity1) {

                    livingentity1.addEffect(new MobEffectInstance(ModMobEffects.PLASMA, 120, 1), this.getEffectSource());
                    
                    this.discard();

                    
	                }
	            }
	            }}
	    
	
	

	

	@Override
	protected double getDefaultGravity() {
		return 0F;
	}
	
	@Override
	public void tick() {
		super.tick();
		
		 if (!this.level().isClientSide) {
		        // Find the nearest living entity within a 16-block radius
	            LivingEntity target = findNearestEnemy(this.level(), this, 16.0); // Search for enemies within 16 blocks

		        if (target != null) {
		            // Get the direction vector to the target
		            Vec3 vectorToTarget = new Vec3(target.getX() - this.getX(), target.getEyeY() - this.getY(), target.getZ() - this.getZ());
		            
		            // Normalize the vector and scale it to your desired speed
		            Vec3 newVelocity = vectorToTarget.normalize().scale(this.getDeltaMovement().length());

		            // Set the projectile's new motion
		            this.setDeltaMovement(newVelocity);
		            
		            // Adjust the rotation to face the target
		            this.setYRot((float)(Math.atan2(newVelocity.x, newVelocity.z) * 180.0 / Math.PI));
		            this.setXRot((float)(Math.atan2(newVelocity.y, newVelocity.horizontalDistance()) * 180.0 / Math.PI));
		
		this.life++;
        if (this.life >= 55) {
            this.discard();
                  }
		        }}
		
}
	 @Override
		public boolean isPickable() {
			return true;
		}
	
	 private LivingEntity findNearestEnemy(Level level, BigPlasmaBall source, double range) {
	        List<Player> enemies = level.getEntitiesOfClass(
	            Player.class,
	            source.getBoundingBox().inflate(range),
	            (mob) -> mob.isAlive() && !mob.is(source.getOwner())
	        );

	        return enemies.stream()
	            .min(Comparator.comparingDouble(source::distanceToSqr))
	            .orElse(null);
	    }
	

	    @Override
	    protected void onHitBlock(BlockHitResult pResult) {
	        super.onHitBlock(pResult);
	        if (!this.level().isClientSide) {
	            Vec3i vec3i = pResult.getDirection().getNormal();
	            Vec3 vec3 = Vec3.atLowerCornerOf(vec3i).multiply(0.25, 0.25, 0.25);
	            Vec3 vec31 = pResult.getLocation().add(vec3);
	            ((ServerLevel)this.level()).sendParticles(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 2, 0.2D, 0.2D, 0.2D, 0.0D);
		      // this.level().explode(this, this.getX(), this.getY(), this.getZ(), 0.4f, Level.ExplosionInteraction.NONE);

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