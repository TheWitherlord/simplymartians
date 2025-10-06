package com.wither.simplymartians.entities.rendering;

import com.wither.simplymartians.SimplyMartians;
import com.wither.simplymartians.entities.MartianEnforcerEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.layers.WitherArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MartianEnforcerRenderer extends MobRenderer<MartianEnforcerEntity, MartianEnforcerModel<MartianEnforcerEntity>> {
	   private static final ResourceLocation MARTIAN_LOCATION = ResourceLocation.fromNamespaceAndPath(SimplyMartians.MODID, "textures/entity/martian/martian_enforcer/martian_enforcer.png");

    public MartianEnforcerRenderer(EntityRendererProvider.Context p_173933_) {
        super(p_173933_, new MartianEnforcerModel<>(p_173933_.bakeLayer(MartianEnforcerModel.LAYER_LOCATION)), 0.5F);
        this.addLayer(new ItemInHandLayer<>(this, p_173933_.getItemInHandRenderer()));
		this.addLayer(new MartianArmorGlowingLayer(this));
        this.addLayer(new MartianForcefieldLayer(this, p_173933_.getModelSet()));

    }

    

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getTextureLocation(MartianEnforcerEntity p_115012_) {
	      return MARTIAN_LOCATION;
	      }
}
