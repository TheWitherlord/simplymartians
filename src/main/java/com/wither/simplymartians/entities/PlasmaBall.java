package com.wither.simplymartians.entities;

import java.util.Comparator;
import java.util.List;

import javax.annotation.Nullable;

import com.wither.simplymartians.core.init.InitDamageSources;
import com.wither.simplymartians.core.init.InitEntity;
import com.wither.simplymartians.core.init.ModMobEffects;
import com.wither.simplymartians.core.init.ModParticleTypes;

import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class PlasmaBall extends ModProjectile {
    private int life;

	public PlasmaBall(EntityType<? extends PlasmaBall> p_36892_, Level p_36893_) {
		super(p_36892_, p_36893_);


	}

	@SuppressWarnings("this-escape")
	public PlasmaBall(Level world, LivingEntity thrower) {
		super(InitEntity.PLASMA_BALL.get(), world, thrower);
		this.shootFromRotation(thrower, thrower.getXRot(), thrower.getYRot(), 0, 1.0f, 1.0F);
	}

	public PlasmaBall(Level worldIn, double x, double y, double z) {
		super(InitEntity.PLASMA_BALL.get(), worldIn, x, y, z);
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
	                flag = entity.hurt(damagesource, 5.0F);
	                if (flag) {
	                    if (entity.isAlive()) {
	                        EnchantmentHelper.doPostAttackEffects(serverlevel, entity, damagesource);
	                   
	                    }
	                }
	           

	            if (flag && entity instanceof LivingEntity livingentity1) {

                    livingentity1.addEffect(new MobEffectInstance(ModMobEffects.PLASMA, 120), this.getEffectSource());
                    
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
		        // Find the nearest living entity within a x-block radius
	            LivingEntity target = findNearestEnemy(this.level(), this, 16.0); // Search for enemies within x blocks

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
		        }
		
		            if (!this.level().isClientSide) { // Server-side check is essential
		                if (this.tickCount >= 50) {
		                    this.remove(Entity.RemovalReason.DISCARDED);
		                }
		            }
		        }}
		

	
	
	 private LivingEntity findNearestEnemy(Level level, PlasmaBall source, double range) {
	        List<Mob> enemies = level.getEntitiesOfClass(
	            Mob.class,
	            source.getBoundingBox().inflate(range),
	            (mob) -> mob.isAlive() || !mob.getType().is(EntityTypeTags.DEFLECTS_PROJECTILES) && !mob.is(source.getOwner()) 
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