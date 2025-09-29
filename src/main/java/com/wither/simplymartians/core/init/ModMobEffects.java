package com.wither.simplymartians.core.init;

import com.wither.simplymartians.SimplyMartians;
import com.wither.simplymartians.entities.OvershockEffect;
import com.wither.simplymartians.entities.PlasmaEffect;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMobEffects {
	public static final DeferredRegister<MobEffect> MOB_EFFECT_DEFERRED_REGISTER = DeferredRegister
			.create(Registries.MOB_EFFECT, SimplyMartians.MODID);

	public static void register(IEventBus eventBus) {
		MOB_EFFECT_DEFERRED_REGISTER.register(eventBus);
	}

	
	
	public static final DeferredHolder<MobEffect, MobEffect> PLASMA = MOB_EFFECT_DEFERRED_REGISTER.register(
			"plasmashock",
			() -> new PlasmaEffect(MobEffectCategory.HARMFUL, 0xFEA2F3).addAttributeModifier(
	                Attributes.ARMOR, ResourceLocation.withDefaultNamespace("effect.plasmashock"), -0.25, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
		            ));

	
	public static final DeferredHolder<MobEffect, MobEffect> OVERSHOCK = MOB_EFFECT_DEFERRED_REGISTER.register(
			"overshock",
			() -> new OvershockEffect(MobEffectCategory.HARMFUL, 0xFFAA3D));
	
	
	
}
