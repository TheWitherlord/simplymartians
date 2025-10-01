package com.wither.simplymartians.items;

import java.util.List;
import java.util.function.Predicate;

import com.wither.simplymartians.core.init.InitItem;
import com.wither.simplymartians.core.init.InitSoundEvents;
import com.wither.simplymartians.entities.MottLaser;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;


public class DecimatorItem extends BowItem {
	
    private static final int WARMUP_TIME = 20; // 1 second warmup
    private static final int MAX_FIRE_RATE = 2; // Shoot every 2 ticks
    private final RandomSource random = RandomSource.create();

	public DecimatorItem(final Item.Properties properties) {
		super(properties);
	}

	
	

	@Override
	public Predicate<ItemStack> getAllSupportedProjectiles() {
		return stack -> stack.getItem() == InitItem.MARTIAN_POWER_CELL.get();
	}

	@Override
	public int getDefaultProjectileRange() {
		return 15;
	}

	 @Override
	    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
	        // Start using the item when the player right-clicks
	        pPlayer.startUsingItem(pUsedHand);
	        return InteractionResultHolder.consume(pPlayer.getItemInHand(pUsedHand));
	    }

	    @Override
	    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration) {
	        if (pLevel.isClientSide()) return;
	        
	        int usedTicks = this.getUseDuration(pStack, pLivingEntity) - pRemainingUseDuration;

	        if (usedTicks < WARMUP_TIME) {
	            // Minigun is warming up, make a sound and do nothing else.
	            if (usedTicks % 5 == 0) {
	                pLevel.playSound(null, pLivingEntity.blockPosition(), InitSoundEvents.DECIMATOR.get(), SoundSource.PLAYERS, 0.5f, 0.5f);
	            }
	            return;
	        }

	        // Shoot a projectile every 2 ticks (adjust MAX_FIRE_RATE for different speed)
	        if (usedTicks % MAX_FIRE_RATE == 0) {
	            shootProjectile(pLevel, pLivingEntity);
	            pStack.hurtAndBreak(1, pLivingEntity, EquipmentSlot.MAINHAND);
	        }
	    }
	    
	    private void shootProjectile(Level pLevel, LivingEntity pLivingEntity) {
	        // Assuming you have a custom BulletEntity registered (see previous guide).
        	MottLaser bullet = new MottLaser(pLevel, pLivingEntity);

	        // Adjust shooting velocity and add spread for minigun effect
	        float velocity = 2.5f;
	        float inaccuracy = 5.0f; // High inaccuracy for spray effect
	        bullet.shootFromRotation(pLivingEntity, pLivingEntity.getXRot(), pLivingEntity.getYRot() + (random.nextFloat() - 0.5f) * inaccuracy, 0.0f, velocity, 1.0f);

	        pLevel.addFreshEntity(bullet);
	        pLevel.playSound(null, pLivingEntity.blockPosition(), InitSoundEvents.LASER_SHOOT.get(), SoundSource.PLAYERS, 0.5f, 2.0f);
	    }

	    @Override
	    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity, int pTimeCharged) {
	        // The player released the button, stop any further shooting.
	        if (!pLevel.isClientSide()) {
	            pLevel.playSound(null, pLivingEntity.blockPosition(), InitSoundEvents.LASER_SHOOT.get(), SoundSource.PLAYERS, 1.0f, 0.5f);
	        }
	    }

	    @Override
	    public int getUseDuration(ItemStack pStack, LivingEntity pLivingEntity) {
	        return 72000; // A very large number to allow continuous holding
	    }

	    @Override
	    public UseAnim getUseAnimation(ItemStack pStack) {
	        // Use an animation that suits the minigun's continuous use.
	        return UseAnim.BOW;
	    }
	
		
		 @Override
		   public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair)
		   {
			   return repair.getItem() == InitItem.MARTIAN_TECH.get();
		   }
		 
		 @Override
			public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
				super.appendHoverText(stack, context, tooltip, flag);
	            tooltip.add(Component.translatable("tooltip.simplymartians.plasma_rifle.tooltip").withStyle(ChatFormatting.GRAY));
	            tooltip.add(Component.translatable("tooltip.simplymartians.blasterator.ammo").withStyle(ChatFormatting.DARK_GREEN) );

		        }
		
		    
		
		    }
	
