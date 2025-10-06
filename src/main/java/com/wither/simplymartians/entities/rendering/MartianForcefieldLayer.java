package com.wither.simplymartians.entities.rendering;

import com.wither.simplymartians.SimplyMartians;
import com.wither.simplymartians.entities.MartianEnforcerEntity;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EnergySwirlLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MartianForcefieldLayer extends EnergySwirlLayer<MartianEnforcerEntity, MartianEnforcerModel<MartianEnforcerEntity>> {
	public static final ResourceLocation FORCEFIELD_LOCATION = ResourceLocation.fromNamespaceAndPath(SimplyMartians.MODID, "textures/entity/martian/martian_enforcer/martian_forcefield.png");
    private final MartianEnforcerModel<MartianEnforcerEntity> model;

    public MartianForcefieldLayer(RenderLayerParent<MartianEnforcerEntity, MartianEnforcerModel<MartianEnforcerEntity>> renderer, EntityModelSet modelSet) {
        super(renderer);
        this.model = new MartianEnforcerModel<>(modelSet.bakeLayer(MartianEnforcerModel.MARTIAN_FORCEFIELD));
    }

    @Override
    protected float xOffset(float tickCount) {
        return Mth.cos(tickCount * 0.02F) * 3.0F;
    }

    @Override
    protected ResourceLocation getTextureLocation() {
        return FORCEFIELD_LOCATION;
    }

    @Override
    protected EntityModel<MartianEnforcerEntity> model() {
        return this.model;
    }
}
