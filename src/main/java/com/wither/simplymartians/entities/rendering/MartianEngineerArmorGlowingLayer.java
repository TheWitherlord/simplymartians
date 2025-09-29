package com.wither.simplymartians.entities.rendering;

import com.wither.simplymartians.SimplyMartians;
import com.wither.simplymartians.entities.MartianEngineerEntity;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MartianEngineerArmorGlowingLayer<T extends MartianEngineerEntity> extends EyesLayer<T, MartianEngineerModel<T>> {
   private static final RenderType ARMOR_GLOWING = RenderType.eyes(ResourceLocation
			.fromNamespaceAndPath(SimplyMartians.MODID, "textures/entity/martian/martian_engineer/martian_engineer_armor_glow.png"));

   public MartianEngineerArmorGlowingLayer(RenderLayerParent<T, MartianEngineerModel<T>> p_116964_) {
      super(p_116964_);
   }

   public RenderType renderType() {
      return ARMOR_GLOWING;
   }
}