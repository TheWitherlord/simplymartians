package com.wither.simplymartians.items;

import java.util.List;

import com.wither.simplymartians.core.init.InitItem;
import com.wither.simplymartians.core.init.ModMobEffects;
import com.wither.simplymartians.core.init.ModParticleTypes;
import com.wither.simplymartians.entities.FluxWave;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

@SuppressWarnings("unused")
public class VibraedgeItem extends SwordItem {

	public VibraedgeItem(Tier p_43269_, Properties p_43272_) {
		super(p_43269_, p_43272_);

	}
	@Override
    public boolean isValidRepairItem(ItemStack pToRepair, ItemStack pRepair) {
        return pRepair.is(InitItem.MARTIAN_TECH) || super.isValidRepairItem(pToRepair, pRepair);
    }

	
	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		 target.addEffect(new MobEffectInstance(ModMobEffects.OVERSHOCK, 55, 0, true, true));

		return super.hurtEnemy(stack, target, attacker);
	}
	
	
		
				           
      	

				    
	}

