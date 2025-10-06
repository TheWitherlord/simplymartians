package com.wither.simplymartians.entities;

import javax.annotation.Nullable;

import com.wither.simplymartians.core.init.InitDamageTypes;
import com.wither.simplymartians.core.init.InitEntity;
import com.wither.simplymartians.core.init.InitItem;
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
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.BossEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class MartianDistressBeacon extends PathfinderMob {
	private static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData
			.defineId(MartianDistressBeacon.class, EntityDataSerializers.BYTE);
	private int summonCooldown = 0;

	private final ServerBossEvent bossInfo = (ServerBossEvent) (new ServerBossEvent(this.getDisplayName(),
			BossEvent.BossBarColor.GREEN, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(false);

	public MartianDistressBeacon(EntityType<? extends MartianDistressBeacon> entityType, Level level) {
		super(entityType, level);
		this.xpReward = 45;

	}

	@Override
	protected void registerGoals() {
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, true));
		// this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 0.0, false));

	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.ATTACK_DAMAGE, 5.0).add(Attributes.MAX_HEALTH, 1000.0)
				.add(Attributes.MOVEMENT_SPEED, 0.0).add(Attributes.ARMOR, 20.0)
				.add(Attributes.KNOCKBACK_RESISTANCE, 1.0).add(Attributes.FOLLOW_RANGE, 120.0);
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
	public void push(Entity entityIn) {

	}

	@Override
	public void push(double x, double y, double z) {
	}

	@Override
	public void setDeltaMovement(Vec3 motion) {
		super.setDeltaMovement(new Vec3(0, motion.y, 0));
	}

	@Override
	protected boolean shouldDespawnInPeaceful() {
		return true;
	}

	@Override
	public void checkDespawn() {
		if (net.neoforged.neoforge.event.EventHooks.checkMobDespawn(this))
			return;
		if (this.level().getDifficulty() == Difficulty.PEACEFUL && this.shouldDespawnInPeaceful()) {
			this.discard();
		} else {
			this.noActionTime = 0;
		}
	}

	@Override
	protected void checkFallDamage(double pY, boolean pOnGround, BlockState pState, BlockPos pPos) {
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
				this.level().addParticle(
						this.getRandom().nextBoolean() ? ParticleTypes.CAMPFIRE_SIGNAL_SMOKE : ParticleTypes.POOF,
						(this.getX() + this.getRandom().nextFloat() * this.getBbWidth() * 2.0F) - this.getBbWidth(),
						this.getY() + this.getRandom().nextFloat() * this.getBbHeight(),
						(this.getZ() + this.getRandom().nextFloat() * this.getBbWidth() * 2.0F) - this.getBbWidth(), d,
						d1, d2);
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

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(DATA_FLAGS_ID, (byte) 0);
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
	public boolean hurt(DamageSource source, float amount) {

		if (this.isInvulnerableTo(source)) {
			return false;
		} else if (source.is(InitDamageTypes.MARTIAN_SHOCK) || source.getEntity() instanceof MartianDistressBeacon) {
			return false;
		}

		if (super.hurt(source, amount)) {
			Level level = this.level();
			LivingEntity target = this.getTarget();
			float f = this.getMaxHealth();

			if (!level.isClientSide && target != null && this.summonCooldown <= 0) {
				performSummonAnytime(level, target);
				this.summonCooldown = 200;
			}
			return true;
		}
		return false;

	}

	private void performSummonAnytime(Level level, LivingEntity target) {
		for (int i = 0; i < 2; i++) {
			if (this.level() instanceof ServerLevel serverLevel) {

				SimpleMartianEntity martian = InitEntity.MARTIAN.get().spawn(serverLevel, this.blockPosition(),
						MobSpawnType.SPAWNER);
				martian.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(InitItem.MARTIAN_ZAPPER.get()));
				martian.setDropChance(EquipmentSlot.MAINHAND, 0.04F);

				double spawnX = this.getX() + (level.random.nextDouble() - 0.5) * 4.0;
				double spawnZ = this.getZ() + (level.random.nextDouble() - 0.5) * 4.0;
				double spawnY = this.getY();
				martian.setPos(spawnX, spawnY, spawnZ);

				martian.setTarget(target);

				level.addFreshEntity(martian);
			}
		}

		for (int i = 0; i < 1; i++) {
			if (this.level() instanceof ServerLevel serverLevel) {

				MartianBruteEntity martian = InitEntity.MARTIAN_BRUTE.get().spawn(serverLevel, this.blockPosition(),
						MobSpawnType.SPAWNER);
				martian.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(InitItem.FLUXIUM_SWORD.get()));
				martian.setDropChance(EquipmentSlot.MAINHAND, 0.0F);

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
	public void die(DamageSource damageSource) {
		// Ensure this logic only runs on the server side
		if (this.level() instanceof ServerLevel serverLevel) {
			// Spawn a new entity at the current mob's location
			MartianEnforcerEntity martian = InitEntity.MARTIAN_ENFORCER.get().spawn(serverLevel, this.blockPosition(),
					MobSpawnType.SPAWNER);
			if (martian != null) {
				// Optional: set the spawned entity's target to the killer
				if (damageSource.getEntity() instanceof net.minecraft.world.entity.LivingEntity killer) {
					martian.setTarget(killer);
				}
			}
		}

		// Call the super method to execute the vanilla death logic
		// (dropping items, playing sound, etc.).
		super.die(damageSource);
	}

}
