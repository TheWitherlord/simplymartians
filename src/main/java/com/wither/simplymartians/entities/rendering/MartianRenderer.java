package com.wither.simplymartians.entities.rendering;

import java.util.Map;

import com.google.common.collect.Maps;
import com.wither.simplymartians.SimplyMartians;
import com.wither.simplymartians.entities.MartianVariant;
import com.wither.simplymartians.entities.SimpleMartianEntity;
import net.minecraft.Util;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Blaze;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MartianRenderer extends MobRenderer<SimpleMartianEntity, MartianModel<SimpleMartianEntity>> {
	   private static final Map<MartianVariant, ResourceLocation> LOCATION_BY_VARIANT =
	            Util.make(Maps.newEnumMap(MartianVariant.class), map -> {
	                map.put(MartianVariant.GREEN,
	                        ResourceLocation.fromNamespaceAndPath(SimplyMartians.MODID, "textures/entity/martian/simple_martian/green_martian.png"));
	                map.put(MartianVariant.GRAY,
	                        ResourceLocation.fromNamespaceAndPath(SimplyMartians.MODID, "textures/entity/martian/simple_martian/gray_martian.png"));
	                map.put(MartianVariant.RED,
	                        ResourceLocation.fromNamespaceAndPath(SimplyMartians.MODID, "textures/entity/martian/simple_martian/red_martian.png"));
	                map.put(MartianVariant.BLUE,
	                        ResourceLocation.fromNamespaceAndPath(SimplyMartians.MODID, "textures/entity/martian/simple_martian/blue_martian.png"));
	            });
    public MartianRenderer(EntityRendererProvider.Context p_173933_) {
        super(p_173933_, new MartianModel<>(p_173933_.bakeLayer(MartianModel.LAYER_LOCATION)), 0.5F);
        this.addLayer(new ItemInHandLayer<>(this, p_173933_.getItemInHandRenderer()));
		this.addLayer(new MartianArmorGlowingLayer(this));

    }

    

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getTextureLocation(SimpleMartianEntity p_115012_) {
	      return LOCATION_BY_VARIANT.get(p_115012_.getVariant());
	      }
}
