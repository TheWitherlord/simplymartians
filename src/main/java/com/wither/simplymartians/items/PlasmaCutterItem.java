package com.wither.simplymartians.items;

import com.wither.simplymartians.core.init.InitItem;
import com.wither.simplymartians.core.init.ModMobEffects;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item.Properties;

public class PlasmaCutterItem extends SwordItem {

	public PlasmaCutterItem(Tier p_43269_, Properties p_43272_) {
		super(p_43269_, p_43272_);

	}
	@Override
    public boolean isValidRepairItem(ItemStack pToRepair, ItemStack pRepair) {
        return pRepair.is(InitItem.MARTIAN_TECH) || super.isValidRepairItem(pToRepair, pRepair);
    }

	
	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		 target.addEffect(new MobEffectInstance(ModMobEffects.PLASMA, 120, 0, true, true));

		return super.hurtEnemy(stack, target, attacker);
	}
}