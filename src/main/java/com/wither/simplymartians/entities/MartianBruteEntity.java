package com.wither.simplymartians.entities;

import javax.annotation.Nullable;

import com.wither.simplymartians.core.init.InitItem;
import com.wither.simplymartians.core.init.ModMobEffects;
import com.wither.simplymartians.core.util.TagInit;
import com.wither.simplymartians.items.MartianZapperItem;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class MartianBruteEntity extends Monster implements RangedAttackMob {
	
	private final ZapperAttackGoal<MartianBruteEntity> bowGoal = new ZapperAttackGoal<>(this, 1.0, 20, 15.0F);
	private final MeleeAttackGoal meleeGoal = new MeleeAttackGoal(this, 1.2, false) {
		
		@Override
		public void stop() {
			super.stop();
			MartianBruteEntity.this.setAggressive(false);
		}

		@Override
		public void start() {
			super.start();
			MartianBruteEntity.this.setAggressive(true);
		}
	};

	public MartianBruteEntity(EntityType<? extends MartianBruteEntity> entityType, Level level) {
		super(entityType, level);
		this.reassessWeaponGoal();

	}
	@Override
	public boolean canBeAffected(MobEffectInstance potioneffect) {
		return potioneffect.is(ModMobEffects.OVERSHOCK) ? false : super.canBeAffected(potioneffect);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0, 0.0F));
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Cow.class, true));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(2, new MartianBruteEntity.MartianOpenDoorGoal(this));

	}

	@Override
	protected PathNavigation createNavigation(Level level) {
		return new WallClimberNavigation(this, level);
	}

	private static final EntityDataAccessor<Integer> VARIANT =
            SynchedEntityData.defineId(MartianBruteEntity.class, EntityDataSerializers.INT);
	
	
	 @Override
	    protected void defineSynchedData(SynchedEntityData.Builder builder) {
	        super.defineSynchedData(builder);
	        builder.define(VARIANT, 0);
	    }

	    private int getTypeVariant() {
	        return this.entityData.get(VARIANT);
	    }

	    public MartianVariant getVariant() {
	        return MartianVariant.byId(this.getTypeVariant() & 255);
	    }

	    public void setVariant(MartianVariant variant) {
	        this.entityData.set(VARIANT, variant.getId() & 255);
	    }

	    @Override
	    public void addAdditionalSaveData(CompoundTag compound) {
	        super.addAdditionalSaveData(compound);
	        compound.putInt("Variant", this.getTypeVariant());
	    }

	    @Override
	    public void readAdditionalSaveData(CompoundTag compound) {
	        super.readAdditionalSaveData(compound);
	        this.entityData.set(VARIANT, compound.getInt("Variant"));
			this.reassessWeaponGoal();

	    }
	
	

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.ATTACK_DAMAGE, 7.0).add(Attributes.KNOCKBACK_RESISTANCE, 0.4).add(Attributes.MAX_HEALTH, 40.0)
				.add(Attributes.ARMOR, 5.0).add(Attributes.MOVEMENT_SPEED, 0.17F).add(Attributes.FOLLOW_RANGE, 25.0);
	}

	protected void populateDefaultEquipmentSlots(RandomSource pRandom, DifficultyInstance p_219150_) {

		this.setDropChance(EquipmentSlot.MAINHAND, 0.0F);

	}

	

	


	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty,
			MobSpawnType pSpawnType, @Nullable SpawnGroupData pSpawnGroupData) {
		
		MartianVariant variant = Util.getRandom(MartianVariant.values(), this.random);
        this.setVariant(variant);
        
		RandomSource randomsource = pLevel.getRandom();
		pSpawnGroupData = super.finalizeSpawn(pLevel, pDifficulty, pSpawnType, pSpawnGroupData);
		float f = pDifficulty.getSpecialMultiplier();
		this.setCanPickUpLoot(randomsource.nextFloat() < 0.55F * f);

		this.setItemSlot(EquipmentSlot.MAINHAND, this.createSpawnWeapon());

		this.populateDefaultEquipmentSlots(randomsource, pDifficulty);
		this.populateDefaultEquipmentEnchantments(pLevel, randomsource, pDifficulty);
		return pSpawnGroupData;
	}
	
	

	private ItemStack createSpawnWeapon() {
		return (double) this.random.nextFloat() < 0.5 ? new ItemStack(InitItem.MARTIAN_ZAPPER.get())
				: new ItemStack(InitItem.FLUXIUM_SWORD.get());
	}

	protected class MartianOpenDoorGoal extends OpenDoorGoal {
		public MartianOpenDoorGoal(MartianBruteEntity snake) {
			super(snake, false);
		}

		@Override
		public boolean canUse() {
			return super.canUse() && MartianBruteEntity.this.isAlive();
		}

	}

	

	public void reassessWeaponGoal() {
		if (this.level() != null && !this.level().isClientSide) {
			this.goalSelector.removeGoal(this.meleeGoal);
			this.goalSelector.removeGoal(this.bowGoal);
			ItemStack itemstack = this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this,
					item -> item instanceof MartianZapperItem));
			if (itemstack.is(InitItem.MARTIAN_ZAPPER.get())) {
				int i = this.getHardAttackInterval();
				if (this.level().getDifficulty() != Difficulty.HARD) {
					i = this.getAttackInterval();
				}

				this.bowGoal.setMinAttackInterval(i);
				this.goalSelector.addGoal(4, this.bowGoal);
			} else {
				this.goalSelector.addGoal(4, this.meleeGoal);
			}
		}
	}

	protected int getHardAttackInterval() {
		return 20;
	}

	protected int getAttackInterval() {
		return 40;
	}

	/**
	 * Attack the specified entity using a ranged attack.
	 */
	
	
	 @Override
	    public void performRangedAttack(LivingEntity target, float distanceFactor) {
	        this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, item -> item instanceof net.minecraft.world.item.ProjectileWeaponItem));

	        ZapBolt snowball = new ZapBolt(this.level(), this);
	        double d0 = target.getEyeY() - 1.1F;
	        double d1 = target.getX() - this.getX();
	        double d2 = d0 - snowball.getY();
	        double d3 = target.getZ() - this.getZ();
	        double d4 = Math.sqrt(d1 * d1 + d3 * d3) * 0.1F;
	        snowball.shoot(d1, d2 + d4, d3, 1.8F, 1.0F);
	        this.playSound(SoundEvents.SNOW_GOLEM_SHOOT, 1.0F, 0.4F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
	        this.level().addFreshEntity(snowball);
	    }

   

	

	@Override
	public boolean canFireProjectileWeapon(ProjectileWeaponItem projectileWeapon) {
		return projectileWeapon == InitItem.MARTIAN_ZAPPER.get();
	}

	
	

	@Override
	public void setItemSlot(EquipmentSlot slot, ItemStack stack) {
		super.setItemSlot(slot, stack);
		if (!this.level().isClientSide) {
			this.reassessWeaponGoal();
		}
	}

	@Override
	public boolean isAlliedTo(Entity pEntity) {
		if (super.isAlliedTo(pEntity)) {
			return true;
		} else {
			return !pEntity.getType().is(TagInit.MARTIAN) ? false
					: this.getTeam() == null && pEntity.getTeam() == null;
		}
	}
	
}
