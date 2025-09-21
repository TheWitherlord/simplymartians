package com.wither.simplymartians.core.util;

import com.wither.simplymartians.SimplyMartians;
import com.wither.simplymartians.core.init.InitItem;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = SimplyMartians.MODID, bus = Bus.MOD, value = Dist.CLIENT)


public class ItemModelProperties {
	
	
	@SubscribeEvent
	public static void setModelPropertiesBlueDrill(FMLClientSetupEvent event) {
		ItemProperties.register(InitItem.FLUXIUM_DRILL.get(), ResourceLocation.parse("activate"),
				(itemStack, world, livingEntity, seed) -> {
					if (livingEntity == null) {
						return 0.0F;
					} else {
						return livingEntity.getUseItem() != itemStack ? 0.0F
								: (float) (itemStack.getUseDuration(livingEntity)
										- livingEntity.getUseItemRemainingTicks()) / 10.0F;
					}
				});

		ItemProperties.register(InitItem.FLUXIUM_DRILL.get(), ResourceLocation.parse("drilling"),
				(itemStack, world, livingEntity, seed) -> livingEntity != null && livingEntity.isUsingItem()
						&& livingEntity.getUseItem() == itemStack ? 1.0F : 0.0F);

	}
	
	@SubscribeEvent
	public static void setModelPropertiesRedDrill(FMLClientSetupEvent event) {
		ItemProperties.register(InitItem.RED_FLUXIUM_DRILL.get(), ResourceLocation.parse("activate"),
				(itemStack, world, livingEntity, seed) -> {
					if (livingEntity == null) {
						return 0.0F;
					} else {
						return livingEntity.getUseItem() != itemStack ? 0.0F
								: (float) (itemStack.getUseDuration(livingEntity)
										- livingEntity.getUseItemRemainingTicks()) / 10.0F;
					}
				});

		ItemProperties.register(InitItem.RED_FLUXIUM_DRILL.get(), ResourceLocation.parse("drilling"),
				(itemStack, world, livingEntity, seed) -> livingEntity != null && livingEntity.isUsingItem()
						&& livingEntity.getUseItem() == itemStack ? 1.0F : 0.0F);

	}
	
	@SubscribeEvent
	public static void setModelPropertiesYellowDrill(FMLClientSetupEvent event) {
		ItemProperties.register(InitItem.YELLOW_FLUXIUM_DRILL.get(), ResourceLocation.parse("activate"),
				(itemStack, world, livingEntity, seed) -> {
					if (livingEntity == null) {
						return 0.0F;
					} else {
						return livingEntity.getUseItem() != itemStack ? 0.0F
								: (float) (itemStack.getUseDuration(livingEntity)
										- livingEntity.getUseItemRemainingTicks()) / 10.0F;
					}
				});

		ItemProperties.register(InitItem.YELLOW_FLUXIUM_DRILL.get(), ResourceLocation.parse("drilling"),
				(itemStack, world, livingEntity, seed) -> livingEntity != null && livingEntity.isUsingItem()
						&& livingEntity.getUseItem() == itemStack ? 1.0F : 0.0F);

	}
	
	@SubscribeEvent
	public static void setModelPropertiesPurpleDrill(FMLClientSetupEvent event) {
		ItemProperties.register(InitItem.PURPLE_FLUXIUM_DRILL.get(), ResourceLocation.parse("activate"),
				(itemStack, world, livingEntity, seed) -> {
					if (livingEntity == null) {
						return 0.0F;
					} else {
						return livingEntity.getUseItem() != itemStack ? 0.0F
								: (float) (itemStack.getUseDuration(livingEntity)
										- livingEntity.getUseItemRemainingTicks()) / 10.0F;
					}
				});

		ItemProperties.register(InitItem.PURPLE_FLUXIUM_DRILL.get(), ResourceLocation.parse("drilling"),
				(itemStack, world, livingEntity, seed) -> livingEntity != null && livingEntity.isUsingItem()
						&& livingEntity.getUseItem() == itemStack ? 1.0F : 0.0F);

	}
	@SubscribeEvent
	public static void setModelPropertiesGreenDrill(FMLClientSetupEvent event) {
		ItemProperties.register(InitItem.GREEN_FLUXIUM_DRILL.get(), ResourceLocation.parse("activate"),
				(itemStack, world, livingEntity, seed) -> {
					if (livingEntity == null) {
						return 0.0F;
					} else {
						return livingEntity.getUseItem() != itemStack ? 0.0F
								: (float) (itemStack.getUseDuration(livingEntity)
										- livingEntity.getUseItemRemainingTicks()) / 10.0F;
					}
				});

		ItemProperties.register(InitItem.GREEN_FLUXIUM_DRILL.get(), ResourceLocation.parse("drilling"),
				(itemStack, world, livingEntity, seed) -> livingEntity != null && livingEntity.isUsingItem()
						&& livingEntity.getUseItem() == itemStack ? 1.0F : 0.0F);

	}
}
