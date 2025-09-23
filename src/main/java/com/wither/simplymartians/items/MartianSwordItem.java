package com.wither.simplymartians.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class MartianSwordItem extends SwordItem{

	public MartianSwordItem(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_) {
		super(p_43269_, p_43272_);
	}
	
	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		 attacker.addEffect(new MobEffectInstance(MobEffects.JUMP, 120, 4, true, true));

		return super.hurtEnemy(stack, target, attacker);
	}
	
	
}

