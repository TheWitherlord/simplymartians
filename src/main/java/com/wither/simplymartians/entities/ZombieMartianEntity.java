package com.wither.simplymartians.entities;

import javax.annotation.Nullable;

import com.wither.simplymartians.core.init.InitItem;
import com.wither.simplymartians.core.init.InitSoundEvents;
import com.wither.simplymartians.core.init.ModMobEffects;
import com.wither.simplymartians.core.util.TagInit;
import com.wither.simplymartians.items.MartianZapperItem;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
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
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsRestrictionGoal;
import net.minecraft.world.entity.ai.goal.OpenDoorGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.ZombieAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class ZombieMartianEntity extends Monster {


		

	public ZombieMartianEntity(EntityType<? extends ZombieMartianEntity> entityType, Level level) {
		super(entityType, level);

	}

	@Override
	protected void registerGoals() {
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.4, false));
		this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0, 0.0F));
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		 this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
	        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
	        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
			this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, SimpleMartianEntity.class, true));
			this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, MartianBruteEntity.class, true));

	}
	  protected boolean isSunSensitive() {
	        return true;
	    }
	  
	@Override
    public void aiStep() {
        if (this.isAlive()) {
            boolean flag = this.isSunSensitive() && this.isSunBurnTick();
            if (flag) {
                ItemStack itemstack = this.getItemBySlot(EquipmentSlot.HEAD);
                if (!itemstack.isEmpty()) {
                    if (itemstack.isDamageableItem()) {
                        Item item = itemstack.getItem();
                        itemstack.setDamageValue(itemstack.getDamageValue() + this.random.nextInt(2));
                        if (itemstack.getDamageValue() >= itemstack.getMaxDamage()) {
                            this.onEquippedItemBroken(item, EquipmentSlot.HEAD);
                            this.setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);
                        }
                    }

                    flag = false;
                }

                if (flag) {
                    this.igniteForSeconds(8.0F);
                }
            }
        }

        super.aiStep();
    }

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.ATTACK_DAMAGE, 4.0).add(Attributes.MAX_HEALTH, 30.0)
				.add(Attributes.ARMOR, 2.0).add(Attributes.MOVEMENT_SPEED, 0.24F).add(Attributes.FOLLOW_RANGE, 32.0);
	}

	

	 @Override
	    public boolean killedEntity(ServerLevel level, LivingEntity entity) {
	        boolean flag = super.killedEntity(level, entity);
	        if ((level.getDifficulty() == Difficulty.NORMAL || level.getDifficulty() == Difficulty.HARD) && entity instanceof Villager villager && net.neoforged.neoforge.event.EventHooks.canLivingConvert(entity, EntityType.ZOMBIE_VILLAGER, (timer) -> {})) {
	            if (level.getDifficulty() != Difficulty.HARD && this.random.nextBoolean()) {
	                return flag;
	            }

	            ZombieVillager zombievillager = villager.convertTo(EntityType.ZOMBIE_VILLAGER, false);
	            if (zombievillager != null) {
	                zombievillager.finalizeSpawn(
	                    level,
	                    level.getCurrentDifficultyAt(zombievillager.blockPosition()),
	                    MobSpawnType.CONVERSION,
	                    new Zombie.ZombieGroupData(false, true)
	                );
	                zombievillager.setVillagerData(villager.getVillagerData());
	                zombievillager.setGossips(villager.getGossips().store(NbtOps.INSTANCE));
	                zombievillager.setTradeOffers(villager.getOffers().copy());
	                zombievillager.setVillagerXp(villager.getVillagerXp());
	                net.neoforged.neoforge.event.EventHooks.onLivingConvert(entity, zombievillager);
	                if (!this.isSilent()) {
	                    level.levelEvent(null, 1026, this.blockPosition(), 0);
	                }

	                flag = false;
	            }
	        }

	        return flag;
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

	public float getVoicePitch() {
		return 0.01F;
	}
	

}
