package com.wither.simplymartians.entities.rendering;

import com.wither.simplymartians.SimplyMartians;
import com.wither.simplymartians.entities.ZombieMartianBruteEntity;
import com.wither.simplymartians.entities.ZombieMartianEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZombieMartianBruteRenderer extends MobRenderer<ZombieMartianBruteEntity, ZombieMartianBruteModel<ZombieMartianBruteEntity>> {
	   private static final ResourceLocation MARTIAN_LOCATION = ResourceLocation.fromNamespaceAndPath(SimplyMartians.MODID, "textures/entity/martian/martian_brute/zombie_martian_brute.png");
	             
	          
    public ZombieMartianBruteRenderer(EntityRendererProvider.Context p_173933_) {
        super(p_173933_, new ZombieMartianBruteModel<>(p_173933_.bakeLayer(ZombieMartianBruteModel.LAYER_LOCATION)), 0.5F);
        this.addLayer(new ItemInHandLayer<>(this, p_173933_.getItemInHandRenderer()));
		this.addLayer(new MartianBruteArmorGlowingLayer(this));

    }

    

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getTextureLocation(ZombieMartianBruteEntity p_115012_) {
	      return MARTIAN_LOCATION;
	      }
}
