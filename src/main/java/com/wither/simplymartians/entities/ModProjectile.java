package com.wither.simplymartians.entities;

import javax.annotation.Nullable;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public abstract class ModProjectile extends ThrowableProjectile  {
    public double accelerationPower = 0.010;

	public ModProjectile(EntityType<? extends ModProjectile> type, Level worldIn) {
		super(type, worldIn);
	}

	public ModProjectile(EntityType<? extends ModProjectile> type, Level worldIn, double x, double y, double z) {
		super(type, x, y, z, worldIn);
	}

	public ModProjectile(EntityType<? extends ModProjectile> type, Level worldIn, LivingEntity throwerIn) {
		super(type, throwerIn, worldIn);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {

	}

	public void makeTrail(ParticleOptions particle, int amount) {
		this.makeTrail(particle, 0.0D, 0.0D, 0.0D, amount);
	}

	public void makeTrail(ParticleOptions particle, double r, double g, double b, int amount) {
		for (int i = 0; i < amount; i++) {
			double dx = this.getX() + 0.5 * (this.random.nextDouble() - this.random.nextDouble());
			double dy = this.getY() + 0.5 * (this.random.nextDouble() - this.random.nextDouble());
			double dz = this.getZ() + 0.5 * (this.random.nextDouble() - this.random.nextDouble());
			this.level().addParticle(particle, dx, dy, dz, r, g, b);
		}
	}

	protected void explode(Vec3 pPos) {
		// TODO Auto-generated method stub
		
	}
	

    protected ClipContext.Block getClipType() {
        return ClipContext.Block.COLLIDER;
    }
	
	@Override
    public void tick() {
        Entity entity = this.getOwner();
        if (this.level().isClientSide || (entity == null || !entity.isRemoved()) && this.level().hasChunkAt(this.blockPosition())) {
            super.tick();
            if (this.shouldBurn()) {
                this.igniteForSeconds(1.0F);
            }

            HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity, this.getClipType());
            if (hitresult.getType() != HitResult.Type.MISS && !net.neoforged.neoforge.event.EventHooks.onProjectileImpact(this, hitresult)) {
                this.hitTargetOrDeflectSelf(hitresult);
            }

            this.checkInsideBlocks();
            Vec3 vec3 = this.getDeltaMovement();
            double d0 = this.getX() + vec3.x;
            double d1 = this.getY() + vec3.y;
            double d2 = this.getZ() + vec3.z;
            ProjectileUtil.rotateTowardsMovement(this, 0.2F);
            float f;
            if (this.isInWater()) {
                for (int i = 0; i < 4; i++) {
                    float f1 = 0.25F;
                    this.level().addParticle(ParticleTypes.BUBBLE, d0 - vec3.x * 0.25, d1 - vec3.y * 0.25, d2 - vec3.z * 0.25, vec3.x, vec3.y, vec3.z);
                }

                f = this.getLiquidInertia();
            } else {
                f = this.getInertia();
            }

            this.setDeltaMovement(vec3.add(vec3.normalize().scale(this.accelerationPower)).scale((double)f));
            ParticleOptions particleoptions = this.getTrailParticle();
            if (particleoptions != null) {
                this.level().addParticle(particleoptions, d0, d1 + 0.5, d2, 0.0, 0.0, 0.0);
            }

            this.setPos(d0, d1, d2);
        } else {
            this.discard();
        }
    }
	
	@Nullable
    protected ParticleOptions getTrailParticle() {
        return null;
    }
        
protected boolean shouldBurn() {
	return false;
}

protected float getInertia() {
    return 1.0F;
}

	protected Item getDefaultItem() {
		// TODO Auto-generated method stub
		return null;
	}

	protected float getLiquidInertia() {
		// TODO Auto-generated method stub
        return 0.8F;
	}
}