package com.wither.simplymartians.entities.rendering;

import com.wither.simplymartians.SimplyMartians;
import com.wither.simplymartians.entities.TeslaBotEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TeslaBotRenderer extends MobRenderer<TeslaBotEntity, TeslaBotModel<TeslaBotEntity>> {
	   private static final ResourceLocation MARTIAN_LOCATION = ResourceLocation.fromNamespaceAndPath(SimplyMartians.MODID, "textures/entity/martian/martian_engineer/tesla_bot.png");

    public TeslaBotRenderer(EntityRendererProvider.Context p_173933_) {
        super(p_173933_, new TeslaBotModel<>(p_173933_.bakeLayer(TeslaBotModel.LAYER_LOCATION)), 0.5F);
        

    }
    protected int getBlockLightLevel(TeslaBotEntity p_234560_, BlockPos p_234561_) {
        return 15;
    }
  

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getTextureLocation(TeslaBotEntity entity) {
        return MARTIAN_LOCATION;
    }
}
