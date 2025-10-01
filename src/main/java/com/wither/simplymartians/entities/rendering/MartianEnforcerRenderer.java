package com.wither.simplymartians.entities.rendering;

import com.wither.simplymartians.SimplyMartians;
import com.wither.simplymartians.entities.MartianEnforcerEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MartianEnforcerRenderer extends MobRenderer<MartianEnforcerEntity, MartianModel<MartianEnforcerEntity>> {
	   private static final ResourceLocation MARTIAN_LOCATION = ResourceLocation.fromNamespaceAndPath(SimplyMartians.MODID, "textures/entity/martian/martian_engineer/martian_engineer.png");

    public MartianEnforcerRenderer(EntityRendererProvider.Context p_173933_) {
        super(p_173933_, new MartianModel<>(p_173933_.bakeLayer(MartianModel.LAYER_LOCATION)), 0.5F);
        this.addLayer(new ItemInHandLayer<>(this, p_173933_.getItemInHandRenderer()));
		this.addLayer(new MartianArmorGlowingLayer(this));

    }

    

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getTextureLocation(MartianEnforcerEntity p_115012_) {
	      return MARTIAN_LOCATION;
	      }
}
