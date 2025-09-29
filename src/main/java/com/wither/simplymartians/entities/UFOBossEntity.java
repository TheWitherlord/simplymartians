package com.wither.simplymartians.entities;

import java.util.EnumSet;

import javax.annotation.Nullable;

import com.wither.simplymartians.core.init.InitDamageTypes;
import com.wither.simplymartians.core.init.InitEntity;
import com.wither.simplymartians.core.init.InitSoundEvents;
import com.wither.simplymartians.core.init.ModMobEffects;
import com.wither.simplymartians.core.util.TagInit;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsRestrictionGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class UFOBossEntity extends PathfinderMob implements RangedAttackMob {
    private static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(UFOBossEntity.class, EntityDataSerializers.BYTE);
    private int summonCooldown = 0;
    
    
    private final ServerBossEvent bossInfo = (ServerBossEvent) (new ServerBossEvent(this.getDisplayName(),
			BossEvent.BossBarColor.PURPLE, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);

    public UFOBossEntity(EntityType<? extends UFOBossEntity> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new FlyingMoveControl(this, 20, true);
        this.xpReward = 35;

    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new StrafingRangedAttackGoal(this, 1.0, 55, 25.0F));
        this.goalSelector.addGoal(1, new UFOBossEntity.UFOAttackGoal(this));
        this.goalSelector.addGoal(4, new MoveTowardsRestrictionGoal(this, 1.0));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0, 0.0F));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, true));

        
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
        		.add(Attributes.ATTACK_DAMAGE, 10.0).add(Attributes.MAX_HEALTH, 250.0).add(Attributes.ARMOR, 15.0).add(Attributes.FLYING_SPEED, 0.1F).add(Attributes.FOLLOW_RANGE, 45.0);
    }
    
    @Override
	public boolean canBeAffected(MobEffectInstance potioneffect) {
		return potioneffect.is(ModMobEffects.OVERSHOCK) ? false : super.canBeAffected(potioneffect);
	}
    
    @Override
    public boolean isAlliedTo(Entity pEntity) {
        if (super.isAlliedTo(pEntity)) {
            return true;
        } else {
            return !pEntity.getType().is(TagInit.MARTIAN) ? false : this.getTeam() == null && pEntity.getTeam() == null;
        }
    }
  
    @Override
    protected PathNavigation createNavigation(Level pLevel) {
        FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, pLevel);
        flyingpathnavigation.setCanOpenDoors(false);
        flyingpathnavigation.setCanFloat(true);
        return flyingpathnavigation;
    }
    
    @Override
    protected boolean shouldDespawnInPeaceful() {
        return true;
    }
    
    
    @Override
    public void checkDespawn() {
        if (net.neoforged.neoforge.event.EventHooks.checkMobDespawn(this)) return;
        if (this.level().getDifficulty() == Difficulty.PEACEFUL && this.shouldDespawnInPeaceful()) {
            this.discard();
        } else {
            this.noActionTime = 0;
        }
    }
    
    @Override
    protected void checkFallDamage(double pY, boolean pOnGround, BlockState pState, BlockPos pPos) {
    }
    
   
   
    @Override
    public boolean onClimbable() {
        return false;
    }
    
    public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);

		if (this.hasCustomName()) {
			this.bossInfo.setName(this.getDisplayName());
		}

	}
    
    
    public void setCustomName(@Nullable Component name) {
		super.setCustomName(name);
		this.bossInfo.setName(this.getDisplayName());
	}
    
    
    public void tick() {
		super.tick();
		
		 if (this.summonCooldown > 0) {
	            this.summonCooldown--;
		 }

		this.bossInfo.setProgress(this.getHealth() / this.getMaxHealth());

		if (!this.isSilent() && !level().isClientSide) {
			this.level().broadcastEntityEvent(this, (byte) 67);
		}
		
			if (this.deathTime > 0) {
				for (int k = 0; k < 5; k++) {
					double d = this.getRandom().nextGaussian() * 0.02D;
					double d1 = this.getRandom().nextGaussian() * 0.02D;
					double d2 = this.getRandom().nextGaussian() * 0.02D;
					this.level().addParticle(this.getRandom().nextBoolean() ? ParticleTypes.CAMPFIRE_SIGNAL_SMOKE : ParticleTypes.POOF,
							(this.getX() + this.getRandom().nextFloat() * this.getBbWidth() * 2.0F) - this.getBbWidth(),
							this.getY() + this.getRandom().nextFloat() * this.getBbHeight(),
							(this.getZ() + this.getRandom().nextFloat() * this.getBbWidth() * 2.0F) - this.getBbWidth(), d, d1, d2);
				}
		}
	}
    
    
    public void startSeenByPlayer(ServerPlayer player) {
		super.startSeenByPlayer(player);
		this.bossInfo.addPlayer(player);
	}

	public void stopSeenByPlayer(ServerPlayer player) {
		super.stopSeenByPlayer(player);
		this.bossInfo.removePlayer(player);
	}

	
    
	
	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty,
			MobSpawnType pSpawnType, @Nullable SpawnGroupData pSpawnGroupData) {
		pSpawnGroupData = super.finalizeSpawn(pLevel, pDifficulty, pSpawnType, pSpawnGroupData);
		RandomSource randomsource = pLevel.getRandom();
		if (randomsource.nextInt(1) == 0) {
			MartianEngineerEntity martian = InitEntity.MARTIAN_ENGINEER.get().create(this.level());
			if (martian != null) {
				martian.positionRider(martian);
				martian.finalizeSpawn(pLevel, pDifficulty, pSpawnType, null);
				martian.startRiding(this, false);
				
				
			}

		}

		return pSpawnGroupData;
	}
	
	 public boolean canBeControlledBy(Entity entity) {
	        return false;
	    }
	 
	 
	 @Override
	    @Nullable
	    public LivingEntity getControllingPassenger() {
	        return null;
	    }
	
	
	@Override
	public void positionRider(Entity passenger, Entity.MoveFunction moveFunction) {
	    if (!this.hasPassenger(passenger)) {
	        return; 
	    }

	    double x = this.getX();
	    double y = this.getY();
	    double z = this.getZ();

	    passenger.shouldRiderSit();
	    
	    double verticalOffset = 1; 

	    moveFunction.accept(passenger, x, y + verticalOffset, z);
	}

	
	
    
    @Override
    public void travel(Vec3 pTravelVector) {
        if (this.isControlledByLocalInstance()) {
            if (this.isInWater()) {
                this.moveRelative(0.02F, pTravelVector);
                this.move(MoverType.SELF, this.getDeltaMovement());
                this.setDeltaMovement(this.getDeltaMovement().scale(0.8F));
            } else if (this.isInLava()) {
                this.moveRelative(0.02F, pTravelVector);
                this.move(MoverType.SELF, this.getDeltaMovement());
                this.setDeltaMovement(this.getDeltaMovement().scale(0.5));
            } else {
                this.moveRelative(this.getSpeed(), pTravelVector);
                this.move(MoverType.SELF, this.getDeltaMovement());
                this.setDeltaMovement(this.getDeltaMovement().scale(0.91F));
            }
        }

        this.calculateEntityAnimation(false);
    }


    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_FLAGS_ID, (byte)0);
    }


  
    
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.METAL_HIT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.DRAGON_FIREBALL_EXPLODE;
    }
    
  

    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {
        this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, item -> item instanceof net.minecraft.world.item.ProjectileWeaponItem));
  
        BigPlasmaBall snowball = new BigPlasmaBall(this.level(), this);
        double d0 = target.getEyeY() - 1.1F;
        double d1 = target.getX() - this.getX();
        double d2 = d0 - snowball.getY();
        double d3 = target.getZ() - this.getZ();
        double d4 = Math.sqrt(d1 * d1 + d3 * d3) * 0.1F;
        snowball.shoot(d1, d2 + d4, d3, 0.05F, 1.0F);
        this.playSound(InitSoundEvents.LASER_SHOOT.get(), 1.0F, 0.4F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level().addFreshEntity(snowball);

    }
    
    
    static class UFOAttackGoal extends Goal {
        private final UFOBossEntity ufo;
        private int attackStep;
        private int attackTime;
        private int lastSeen;

        public UFOAttackGoal(UFOBossEntity ufo) {
            this.ufo = ufo;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            LivingEntity livingentity = this.ufo.getTarget();
            float f = ufo.getMaxHealth();

            return livingentity != null && ufo.getHealth() <= f *50/100 && this.ufo.canAttack(livingentity);
        }

        @Override
        public void start() {
            this.attackStep = 2;
        }

        @Override
        public void stop() {
            this.ufo.isAggressive();
            this.lastSeen = 0;
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            this.attackTime--;
            LivingEntity livingentity = this.ufo.getTarget();
            if (livingentity != null) {
                boolean flag = this.ufo.getSensing().hasLineOfSight(livingentity);
                if (flag) {
                    this.lastSeen = 0;
                } else {
                    this.lastSeen++;
                }

                double d0 = this.ufo.distanceToSqr(livingentity);
                if (d0 < 4.0) {
                    if (!flag) {
                        return;
                    }

                    if (this.attackTime <= 0) {
                        this.attackTime = 20;
                        this.ufo.doHurtTarget(livingentity);
                    }

                    this.ufo.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.0);
                } else if (d0 < this.getFollowDistance() * this.getFollowDistance() && flag) {
                    double d1 = livingentity.getX() - this.ufo.getX();
                    double d2 = livingentity.getY(0.5) - this.ufo.getY(0.5);
                    double d3 = livingentity.getZ() - this.ufo.getZ();
                    if (this.attackTime <= 0) {
                        this.attackStep++;
                        if (this.attackStep == 1) {
                            this.attackTime = 40;
                            this.ufo.setAggressive(true);
                        } else if (this.attackStep <= 4) {
                            this.attackTime = 6;
                        } else {
                            this.attackTime = 60;
                            this.attackStep = 0;
                            this.ufo.setAggressive(false);
                        }

                        if (this.attackStep > 1) {
                            double d4 = Math.sqrt(Math.sqrt(d0)) * 0.5;
                            if (!this.ufo.isSilent()) {
                                this.ufo.level().levelEvent(null, 1018, this.ufo.blockPosition(), 0);
                            }

                            for (int i = 0; i < 1; i++) {
                                Vec3 vec3 = new Vec3(this.ufo.getRandom().triangle(d1, 2.297 * d4), d2, this.ufo.getRandom().triangle(d3, 2.297 * d4));
                                MartianGrenade martian_grenade = new MartianGrenade(this.ufo.level(), this.ufo, vec3.normalize());

                                martian_grenade.setPos(martian_grenade.getX(), this.ufo.getY(0.5) + 0.5, martian_grenade.getZ());
                                this.ufo.level().addFreshEntity(martian_grenade);
                            }
                        }
                    }

                    this.ufo.getLookControl().setLookAt(livingentity, 10.0F, 10.0F);
                } else if (this.lastSeen < 5) {
                    this.ufo.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.0);
                }

                super.tick();
            }
        }

        private double getFollowDistance() {
            return this.ufo.getAttributeValue(Attributes.FOLLOW_RANGE);
        }
    }
 
    @Override
    public boolean hurt(DamageSource source, float amount) {
    	
    	
    	if (this.isInvulnerableTo(source)) {
            return false;
        } else if (source.is(InitDamageTypes.MARTIAN_SHOCK) || source.getEntity() instanceof UFOBossEntity) {
            return false;
        }
    	
        if (super.hurt(source, amount)) {
            Level level = this.level();
            LivingEntity target = this.getTarget();

            if (!level.isClientSide && target != null && this.summonCooldown <= 0) {
                performSummon(level, target);
                this.summonCooldown = 250;
            }
            return true;
        }
        return false;
        
    }
    
   
   

    private void performSummon(Level level, LivingEntity target) {
        for (int i = 0; i < 2; i++) { 
            MartianProbeEntity minion = new MartianProbeEntity(InitEntity.MARTIAN_PROBE.get(), level);

            double spawnX = this.getX() + (level.random.nextDouble() - 0.5) * 4.0;
            double spawnZ = this.getZ() + (level.random.nextDouble() - 0.5) * 4.0;
            double spawnY = this.getY();
            minion.setPos(spawnX, spawnY+ 4, spawnZ);

            minion.setTarget(target);

            level.addFreshEntity(minion);

        }
    }
}
    
    



	

