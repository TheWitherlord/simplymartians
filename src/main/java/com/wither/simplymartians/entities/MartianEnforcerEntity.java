package com.wither.simplymartians.entities;

import javax.annotation.Nullable;

import com.wither.simplymartians.core.init.InitDamageTypes;
import com.wither.simplymartians.core.init.InitEntity;
import com.wither.simplymartians.core.init.InitItem;
import com.wither.simplymartians.core.init.InitSoundEvents;
import com.wither.simplymartians.core.util.TagInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PowerableMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsRestrictionGoal;
import net.minecraft.world.entity.ai.goal.OpenDoorGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class MartianEnforcerEntity extends Monster implements RangedAttackMob, PowerableMob {

    private int summonCooldown = 0;

	  private final ServerBossEvent bossInfo = (ServerBossEvent) (new ServerBossEvent(this.getDisplayName(),
				BossEvent.BossBarColor.BLUE, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);

	public MartianEnforcerEntity(EntityType<? extends MartianEnforcerEntity> entityType, Level level) {
		super(entityType, level);
        this.xpReward = 55;

	}

	@Override
	protected void registerGoals() {
		
       this.goalSelector.addGoal(1, new EnforcerAttackGoal(this, 1.1D, 20, 60, 15.0F, 2.0F));
       this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.5F));
		this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0, 0.0F));
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(2, new MartianEnforcerEntity.MartianOpenDoorGoal(this));

	}

	

	

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.ATTACK_DAMAGE, 8.0).add(Attributes.MAX_HEALTH, 400.0)
				.add(Attributes.ARMOR, 8.0).add(Attributes.MOVEMENT_SPEED, 0.27F).add(Attributes.FOLLOW_RANGE, 55.0);
	}

	protected void populateDefaultEquipmentSlots(RandomSource pRandom, DifficultyInstance p_219150_) {
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(InitItem.MOTTGUN.get()));
		this.setDropChance(EquipmentSlot.MAINHAND, 0.0F);

	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty,
			MobSpawnType pSpawnType, @Nullable SpawnGroupData pSpawnGroupData) {


		RandomSource randomsource = pLevel.getRandom();
		pSpawnGroupData = super.finalizeSpawn(pLevel, pDifficulty, pSpawnType, pSpawnGroupData);
		float f = pDifficulty.getSpecialMultiplier();
		this.setCanPickUpLoot(randomsource.nextFloat() < 0.55F * f);


		this.populateDefaultEquipmentSlots(randomsource, pDifficulty);
		this.populateDefaultEquipmentEnchantments(pLevel, randomsource, pDifficulty);
		return pSpawnGroupData;
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

	protected class MartianOpenDoorGoal extends OpenDoorGoal {
		public MartianOpenDoorGoal(MartianEnforcerEntity snake) {
			super(snake, false);
		}

		@Override
		public boolean canUse() {
			return super.canUse() && MartianEnforcerEntity.this.isAlive();
		}

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
		    }
		
	    @Override
	    public boolean isPowered() {
	        return this.getHealth() >= this.getMaxHealth() / 2.0F;
	    }
	    
	    
	    public void startSeenByPlayer(ServerPlayer player) {
			super.startSeenByPlayer(player);
			this.bossInfo.addPlayer(player);
		}

		public void stopSeenByPlayer(ServerPlayer player) {
			super.stopSeenByPlayer(player);
			this.bossInfo.removePlayer(player);
		}
	

		@Override
	    public boolean hurt(DamageSource source, float amount) {
	                
			 if (this.isInvulnerableTo(source)) {
		            return false;
		        } else if (source.is(InitDamageTypes.MARTIAN_SHOCK) || source.getEntity() instanceof MartianEnforcerEntity) {
		            return false;
		      
		        } else {
		            if (this.isPowered()) { 
		            	 if (source.is(DamageTypes.MOB_PROJECTILE) || source.is(DamageTypes.ARROW) || source.is(InitDamageTypes.OVERSHOCK) || source.is(DamageTypes.TRIDENT) || source.is(DamageTypes.THROWN)) {
		            			this.playSound(SoundEvents.BREEZE_DEFLECT, 0.5f, 0.2f / (this.getRandom().nextFloat() * 0.4F + 0.8F));

		                     return false;
		                }
		            }
	    	
	        if (super.hurt(source, amount)) {
	            Level level = this.level();
	            LivingEntity target = this.getTarget();

	            if (!level.isClientSide && target != null && this.summonCooldown <= 0) {
	                performSummon(level, target);
	                this.summonCooldown = 350;
	            }
	            return true;
	        }
	        return false;
	            }}
	        
	    
		
	   
	   

	    private void performSummon(Level level, LivingEntity target) {
	        for (int i = 0; i < 2; i++) { 
	        	if (this.level() instanceof ServerLevel serverLevel) {

					SimpleMartianEntity martian = InitEntity.MARTIAN.get().spawn(serverLevel, this.blockPosition(),
							MobSpawnType.SPAWNER);

	            double spawnX = this.getX() + (level.random.nextDouble() - 0.5) * 4.0;
	            double spawnZ = this.getZ() + (level.random.nextDouble() - 0.5) * 4.0;
	            double spawnY = this.getY();
	            martian.setPos(spawnX, spawnY, spawnZ);

	            martian.setTarget(target);

	            level.addFreshEntity(martian);

	        }
	        }
	    }
	

	@Override
	protected SoundEvent getAmbientSound() {
		return InitSoundEvents.MARTIAN_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource) {
		return InitSoundEvents.MARTIAN_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return InitSoundEvents.MARTIAN_DEATH.get();
	}

	protected SoundEvent getStepSound() {
		return SoundEvents.ZOMBIE_STEP;
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
	public void performRangedAttack(LivingEntity pTarget, float pVelocity) {
	    if (pTarget == null) {
	        return;
	    }

	    for (int i = 0; i < 7; i++) { // bullet count
	        MottLaser pellet = new MottLaser(this.level(), this);
	        double d0 = pTarget.getX() - this.getX();
	        double d1 = pTarget.getEyeY() - this.getEyeY();
	        double d2 = pTarget.getZ() - this.getZ();
	        double d3 = Math.sqrt(d0 * d0 + d2 * d2);

	        // shotgun spread
	        float spread = 15.0F; // Adjust for desired spread
	        double aimX = d0 + this.getRandom().nextGaussian() * spread * 0.0075D * d3;
	        double aimY = d1 + this.getRandom().nextGaussian() * spread * 0.0075D * d3;
	        double aimZ = d2 + this.getRandom().nextGaussian() * spread * 0.0075D * d3;

	        pellet.shoot(aimX, aimY, aimZ, 2.0F, 0.5F);
			this.playSound(InitSoundEvents.LASER_SHOOT.get(), 0.4f, 0.4f / (this.getRandom().nextFloat() * 0.4F + 0.8F));

	        this.level().addFreshEntity(pellet);
	    }
	}

}
