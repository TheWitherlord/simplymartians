package com.wither.simplymartians.items;

import java.util.List;
import java.util.function.Predicate;

import org.jetbrains.annotations.Nullable;

import com.wither.simplymartians.core.init.InitItem;
import com.wither.simplymartians.core.init.InitSoundEvents;
import com.wither.simplymartians.entities.EnergyBall;
import com.wither.simplymartians.entities.MottLaser;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.level.Level;


public class BlasteratorItem extends BowItem {
	
	private static final int COOLDOWN = 35;

	public BlasteratorItem(final Item.Properties properties) {
		super(properties);
	}

	
	protected int getCooldown(final ItemStack launcher) {
		return COOLDOWN;
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
	protected Projectile createProjectile(final Level level, final LivingEntity shooter, final ItemStack projectile, final ItemStack weapon, final boolean isFullPower) {
		return new EnergyBall(level, shooter);
	}

	@Override
	protected void shootProjectile(final LivingEntity shooter, final Projectile projectile, final int projectileNumber, final float p_335337_, final float p_332934_, final float yRot, @Nullable final LivingEntity target) {
		projectile.shootFromRotation(shooter, shooter.getXRot(), shooter.getYRot() + yRot, 0.0F, p_335337_, p_332934_);
	}

	 @Override
	    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
	        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);

	        if (!pLevel.isClientSide) {
	            pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), InitSoundEvents.LASER_SHOOT.get(), SoundSource.NEUTRAL, 1.0F, 0.4F);

	            for (int i = 0; i < 4; i++) {
	            	EnergyBall pellet = new EnergyBall(pLevel, pPlayer);
	                
	                pellet.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 2.5F, 10.0F);
	                
	                // Add a random spread to the velocity
	                float randomSpread = 0.5F; // Adjust this value for wider or tighter spread
	                pellet.setDeltaMovement(pellet.getDeltaMovement().add(
	                    (pLevel.random.nextFloat() - 0.5D) * randomSpread,
	                    (pLevel.random.nextFloat() - 0.5D) * randomSpread,
	                    (pLevel.random.nextFloat() - 0.5D) * randomSpread
	                ));

	                pLevel.addFreshEntity(pellet); // Spawn the pellet
	            }

	            pPlayer.getCooldowns().addCooldown(this, 20); // Add a 1-second cooldown (20 ticks)
	            pPlayer.awardStat(Stats.ITEM_USED.get(this)); // Record the stat
	            itemStack.hurtAndBreak(1, pPlayer, EquipmentSlot.MAINHAND);

	            // Return success
	            return InteractionResultHolder.sidedSuccess(itemStack, pLevel.isClientSide());
	        }

	        // On the client, just play the animation
	        return InteractionResultHolder.pass(itemStack);
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
	
