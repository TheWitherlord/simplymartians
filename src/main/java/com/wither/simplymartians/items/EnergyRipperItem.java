package com.wither.simplymartians.items;

import com.wither.simplymartians.core.init.InitDamageTypes;
import com.wither.simplymartians.core.init.InitSoundEvents;
import com.wither.simplymartians.core.init.ModParticleTypes;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class EnergyRipperItem extends AxeItem{

	
	 public EnergyRipperItem(Tier tier, Properties properties) {
		super(tier, properties);
	}

	@Override
	    public int getUseDuration(ItemStack stack, LivingEntity entity) {
	        return 220;
	    }
	
	 @Override
	    public UseAnim getUseAnimation(ItemStack pStack) {
	        return UseAnim.BOW;
	    }
	    
	    // Begin the use action
	    @Override
	    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
	        ItemStack itemstack = player.getItemInHand(hand);
	        player.startUsingItem(hand);
	        level.playSound(null, player.blockPosition(), InitSoundEvents.DECIMATOR.get(), SoundSource.PLAYERS, 0.5f, 0.5f);

	        return InteractionResultHolder.consume(itemstack);
	    }
	    @Override
	    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {
	        if (!level.isClientSide && livingEntity instanceof Player player) {
	            Vec3 playerPos = player.position();
	            AABB box = new AABB(playerPos.x - 1.5, playerPos.y - 1.5, playerPos.z - 1.5,
	                                playerPos.x + 1.5, playerPos.y + 1.5, playerPos.z + 1.5);

	            // Calculate the damage for this tick
	            float initialDamage = 0.1f; // Starting damage
	            float maxDamage = 4.0f;   // Maximum damage at the end of the duration
	            int totalDuration = getUseDuration(stack, livingEntity);
	            
	            // Calculate the percentage of the duration that has passed
	            float durationElapsed = (float)(totalDuration - remainingUseDuration);
	            float damageProgress = durationElapsed / (float)totalDuration;
	            
	            // Use linear interpolation to scale the damage
	            float damageToApply = initialDamage + (maxDamage - initialDamage) * damageProgress;

	            // Find and damage nearby enemies
	            level.getEntities(player, box, EntitySelector.NO_SPECTATORS.and(e -> e instanceof LivingEntity))
	                 .stream()
	                 .filter(entity -> entity != player)
	                 .forEach(entity -> {
	                     LivingEntity target = (LivingEntity) entity;
	                     DamageSources damageSources = level.damageSources();
	                     DamageSource continuousDamage = damageSources.source(InitDamageTypes.OVERSHOCK, player);
	                	  if (level instanceof ServerLevel serverLevel) {
	                		  stack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);

                         serverLevel.sendParticles(ModParticleTypes.OVERSHOCK.get(), target.getX(), target.getY(), target.getZ(), 5, 0.5, 0.5, 0.5, 0.0);
	                	  }

	                     target.hurt(continuousDamage, damageToApply);
	                 });
	        }
	    }
	    
	  
}
