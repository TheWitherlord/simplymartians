package com.wither.simplymartians.entities.rendering;

import com.wither.simplymartians.SimplyMartians;
import com.wither.simplymartians.entities.ZombieMartianEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZombieMartianRenderer extends MobRenderer<ZombieMartianEntity, ZombieMartianModel<ZombieMartianEntity>> {
	   private static final ResourceLocation MARTIAN_LOCATION = ResourceLocation.fromNamespaceAndPath(SimplyMartians.MODID, "textures/entity/martian/simple_martian/zombie_martian.png");
	             
	          
    public ZombieMartianRenderer(EntityRendererProvider.Context p_173933_) {
        super(p_173933_, new ZombieMartianModel<>(p_173933_.bakeLayer(ZombieMartianModel.LAYER_LOCATION)), 0.5F);
        this.addLayer(new ItemInHandLayer<>(this, p_173933_.getItemInHandRenderer()));
		this.addLayer(new MartianArmorGlowingLayer(this));

    }

    

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getTextureLocation(ZombieMartianEntity p_115012_) {
	      return MARTIAN_LOCATION;
	      }
}
