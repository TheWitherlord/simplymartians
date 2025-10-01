package com.wither.simplymartians.entities.rendering;

import com.wither.simplymartians.SimplyMartians;
import com.wither.simplymartians.entities.MartianDistressBeacon;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DistressBeaconRenderer extends MobRenderer<MartianDistressBeacon, DistressBeaconModel<MartianDistressBeacon>> {
	   private static final ResourceLocation MARTIAN_LOCATION = ResourceLocation.fromNamespaceAndPath(SimplyMartians.MODID, "textures/entity/martian/distress_beacon.png");

    public DistressBeaconRenderer(EntityRendererProvider.Context p_173933_) {
        super(p_173933_, new DistressBeaconModel<>(p_173933_.bakeLayer(DistressBeaconModel.LAYER_LOCATION)), 0.5F);
        

    }
    protected int getBlockLightLevel(MartianDistressBeacon p_234560_, BlockPos p_234561_) {
        return 15;
    }
  

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getTextureLocation(MartianDistressBeacon entity) {
        return MARTIAN_LOCATION;
    }
}
