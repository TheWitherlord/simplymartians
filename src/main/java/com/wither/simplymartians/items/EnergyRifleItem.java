package com.wither.simplymartians.items;

import java.util.List;
import java.util.function.Predicate;

import org.jetbrains.annotations.Nullable;

import com.wither.simplymartians.core.init.InitItem;
import com.wither.simplymartians.core.init.InitSoundEvents;
import com.wither.simplymartians.entities.EnergyBall;
import com.wither.simplymartians.entities.PlasmaBall;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;


public class EnergyRifleItem extends BowItem {
	
	private static final int COOLDOWN = 15;

	public EnergyRifleItem(final Item.Properties properties) {
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
	public InteractionResultHolder<ItemStack> use(final Level level, final Player player, final InteractionHand hand) {
		final var heldItem = player.getItemInHand(hand);

		final var projectile = player.getProjectile(heldItem);
		final var hasAmmo = !projectile.isEmpty();

		if (!player.hasInfiniteMaterials() && !hasAmmo) {
			return InteractionResultHolder.fail(heldItem);
		}

		final var cooldown = getCooldown(heldItem);
		if (cooldown > 0) {
			player.getCooldowns().addCooldown(this, cooldown);
		}

		final var ammo = draw(heldItem, projectile, player);
		if (level instanceof final ServerLevel serverLevel && !ammo.isEmpty()) {
			shoot(
					serverLevel,
					player,
					player.getUsedItemHand(),
					heldItem,
					ammo,
					1.0f,
					1.0f,
					true,
					null
			);
		}

		level.playSound(
				null,
				player.getX(),
				player.getY(),
				player.getZ(),
				InitSoundEvents.LASER_SHOOT.get(),
				SoundSource.NEUTRAL,
				0.5f,
				1.0f / (level.getRandom().nextFloat() * 0.4f + 1.2f) + 0.5f
		);

		return InteractionResultHolder.consume(heldItem);
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
	            tooltip.add(Component.translatable("tooltip.simplymartians.plasma_rifle.ammo").withStyle(ChatFormatting.DARK_GREEN) );

		        }
		    
		
		    }
	
