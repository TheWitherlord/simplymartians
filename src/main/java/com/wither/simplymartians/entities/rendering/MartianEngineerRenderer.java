package com.wither.simplymartians.entities.rendering;

import java.util.Map;

import com.google.common.collect.Maps;
import com.wither.simplymartians.SimplyMartians;
import com.wither.simplymartians.entities.MartianEngineerEntity;
import com.wither.simplymartians.entities.MartianVariant;
import com.wither.simplymartians.entities.ZombieMartianEntity;

import net.minecraft.Util;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MartianEngineerRenderer extends MobRenderer<MartianEngineerEntity, MartianEngineerModel<MartianEngineerEntity>> {
	   private static final ResourceLocation MARTIAN_LOCATION = ResourceLocation.fromNamespaceAndPath(SimplyMartians.MODID, "textures/entity/martian/martian_engineer/martian_engineer.png");

    public MartianEngineerRenderer(EntityRendererProvider.Context p_173933_) {
        super(p_173933_, new MartianEngineerModel<>(p_173933_.bakeLayer(MartianEngineerModel.LAYER_LOCATION)), 0.5F);
        this.addLayer(new ItemInHandLayer<>(this, p_173933_.getItemInHandRenderer()));
		this.addLayer(new MartianEngineerArmorGlowingLayer(this));

    }

    

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getTextureLocation(MartianEngineerEntity p_115012_) {
	      return MARTIAN_LOCATION;
	      }
}
