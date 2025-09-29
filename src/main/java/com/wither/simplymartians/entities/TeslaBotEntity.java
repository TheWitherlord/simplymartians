package com.wither.simplymartians.entities;

import com.wither.simplymartians.core.init.InitDamageTypes;
import com.wither.simplymartians.core.init.InitSoundEvents;
import com.wither.simplymartians.core.util.TagInit;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsRestrictionGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class TeslaBotEntity extends Monster {


	

	public TeslaBotEntity(EntityType<? extends TeslaBotEntity> entityType, Level level) {
		super(entityType, level);
        this.xpReward = 1;

	}

	@Override
	protected void registerGoals() {
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0, false));
		this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0, 0.0F));
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));

	}

	 @Override
	    public boolean hurt(DamageSource pSource, float pAmount) {
		 if (this.isInvulnerableTo(pSource)) {
	            return false;
	        } else if (pSource.is(InitDamageTypes.MARTIAN_SHOCK) || pSource.getEntity() instanceof TeslaBotEntity) {
	            return false;
	        }
           return super.hurt(pSource, pAmount);

	        }

	

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.ATTACK_DAMAGE, 3.0).add(Attributes.MAX_HEALTH, 10.0)
				.add(Attributes.ARMOR, 2.0).add(Attributes.MOVEMENT_SPEED, 0.32F).add(Attributes.FOLLOW_RANGE, 55.0);
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
