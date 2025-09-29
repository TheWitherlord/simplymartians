package com.wither.simplymartians.entities;

import javax.annotation.Nullable;

import com.wither.simplymartians.core.init.InitDamageTypes;
import com.wither.simplymartians.core.init.InitEntity;
import com.wither.simplymartians.core.init.InitItem;
import com.wither.simplymartians.core.init.InitSoundEvents;
import com.wither.simplymartians.core.init.ModMobEffects;
import com.wither.simplymartians.core.util.TagInit;
import com.wither.simplymartians.items.MartianZapperItem;
import net.minecraft.Util;
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
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
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
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.projectile.windcharge.WindCharge;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class MartianEngineerEntity extends Monster {

    private int summonCooldown = 0;

	  private final ServerBossEvent bossInfo = (ServerBossEvent) (new ServerBossEvent(this.getDisplayName(),
				BossEvent.BossBarColor.BLUE, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);

	public MartianEngineerEntity(EntityType<? extends MartianEngineerEntity> entityType, Level level) {
		super(entityType, level);
        this.xpReward = 55;

	}

	@Override
	protected void registerGoals() {
		
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.3, false));
        this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.5F));
		this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0, 0.0F));
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(2, new MartianEngineerEntity.MartianOpenDoorGoal(this));

	}

	

	

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.ATTACK_DAMAGE, 7.0).add(Attributes.MAX_HEALTH, 300.0)
				.add(Attributes.ARMOR, 8.0).add(Attributes.MOVEMENT_SPEED, 0.27F).add(Attributes.FOLLOW_RANGE, 55.0);
	}

	protected void populateDefaultEquipmentSlots(RandomSource pRandom, DifficultyInstance p_219150_) {
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(InitItem.PLASMACUTTER.get()));
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
		public MartianOpenDoorGoal(MartianEngineerEntity snake) {
			super(snake, false);
		}

		@Override
		public boolean canUse() {
			return super.canUse() && MartianEngineerEntity.this.isAlive();
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
	        } else if (source.is(InitDamageTypes.MARTIAN_SHOCK) || source.getEntity() instanceof MartianEngineerEntity) {
	            return false;
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
	        
	    }
	    
	   
	   

	    private void performSummon(Level level, LivingEntity target) {
	        for (int i = 0; i < 4; i++) { 
	        	TeslaBotEntity minion = new TeslaBotEntity(InitEntity.TESLA_BOT.get(), level);

	            double spawnX = this.getX() + (level.random.nextDouble() - 0.5) * 4.0;
	            double spawnZ = this.getZ() + (level.random.nextDouble() - 0.5) * 4.0;
	            double spawnY = this.getY();
	            minion.setPos(spawnX, spawnY, spawnZ);

	            minion.setTarget(target);

	            level.addFreshEntity(minion);

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

}
