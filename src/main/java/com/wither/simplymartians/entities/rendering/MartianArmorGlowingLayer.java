package com.wither.simplymartians.entities.rendering;

import com.wither.simplymartians.SimplyMartians;
import com.wither.simplymartians.entities.SimpleMartianEntity;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MartianArmorGlowingLayer<T extends SimpleMartianEntity> extends EyesLayer<T, MartianModel<T>> {
   private static final RenderType ARMOR_GLOWING = RenderType.eyes(ResourceLocation
			.fromNamespaceAndPath(SimplyMartians.MODID, "textures/entity/martian/simple_martian/martian_armor_glow.png"));

   public MartianArmorGlowingLayer(RenderLayerParent<T, MartianModel<T>> p_116964_) {
      super(p_116964_);
   }

   public RenderType renderType() {
      return ARMOR_GLOWING;
   }
}