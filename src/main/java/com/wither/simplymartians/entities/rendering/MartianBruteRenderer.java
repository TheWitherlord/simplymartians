package com.wither.simplymartians.entities.rendering;

import java.util.Map;

import com.google.common.collect.Maps;
import com.wither.simplymartians.SimplyMartians;
import com.wither.simplymartians.entities.MartianBruteEntity;
import com.wither.simplymartians.entities.MartianVariant;
import net.minecraft.Util;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MartianBruteRenderer extends MobRenderer<MartianBruteEntity, MartianBruteModel<MartianBruteEntity>> {
	   private static final Map<MartianVariant, ResourceLocation> LOCATION_BY_VARIANT =
	            Util.make(Maps.newEnumMap(MartianVariant.class), map -> {
	                map.put(MartianVariant.GREEN,
	                        ResourceLocation.fromNamespaceAndPath(SimplyMartians.MODID, "textures/entity/martian/martian_brute/green_martian_brute.png"));
	                map.put(MartianVariant.GRAY,
	                        ResourceLocation.fromNamespaceAndPath(SimplyMartians.MODID, "textures/entity/martian/martian_brute/gray_martian_brute.png"));
	                map.put(MartianVariant.RED,
	                        ResourceLocation.fromNamespaceAndPath(SimplyMartians.MODID, "textures/entity/martian/martian_brute/red_martian_brute.png"));
	                map.put(MartianVariant.BLUE,
	                        ResourceLocation.fromNamespaceAndPath(SimplyMartians.MODID, "textures/entity/martian/martian_brute/blue_martian_brute.png"));
	            });
    public MartianBruteRenderer(EntityRendererProvider.Context p_173933_) {
        super(p_173933_, new MartianBruteModel<>(p_173933_.bakeLayer(MartianBruteModel.LAYER_LOCATION)), 0.5F);
        this.addLayer(new BruteItemInHandLayer<>(this, p_173933_.getItemInHandRenderer())); 
		this.addLayer(new MartianBruteArmorGlowingLayer(this));

    }

    

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getTextureLocation(MartianBruteEntity p_115012_) {
	      return LOCATION_BY_VARIANT.get(p_115012_.getVariant());
	      }
}
