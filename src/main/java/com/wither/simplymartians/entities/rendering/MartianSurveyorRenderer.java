package com.wither.simplymartians.entities.rendering;

import com.wither.simplymartians.SimplyMartians;
import com.wither.simplymartians.entities.MartianProbeEntity;
import com.wither.simplymartians.entities.UFOBossEntity;

import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.entity.monster.Blaze;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MartianSurveyorRenderer extends MobRenderer<UFOBossEntity, UFOModel<UFOBossEntity>> {
	   private static final ResourceLocation MARTIAN_LOCATION = ResourceLocation.fromNamespaceAndPath(SimplyMartians.MODID, "textures/entity/martian/martian_surveyor/martian_surveyor.png");

    public MartianSurveyorRenderer(EntityRendererProvider.Context p_173933_) {
        super(p_173933_, new UFOModel<>(p_173933_.bakeLayer(UFOModel.LAYER_LOCATION)), 0.5F);
        

    }
    protected int getBlockLightLevel(UFOBossEntity p_234560_, BlockPos p_234561_) {
        return 15;
    }
  

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getTextureLocation(UFOBossEntity entity) {
        return MARTIAN_LOCATION;
    }
}
