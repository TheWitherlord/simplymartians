package com.wither.simplymartians.entities.rendering;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.wither.simplymartians.SimplyMartians;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class UFOModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			 ResourceLocation.fromNamespaceAndPath(SimplyMartians.MODID, "martian_surveyor"), "main");
	
	private final ModelPart ufo_base;

	
	public UFOModel(ModelPart root) {
		this.ufo_base = root.getChild("ufo_base");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition ufo_base = partdefinition.addOrReplaceChild("ufo_base", CubeListBuilder.create().texOffs(0, 0).addBox(-20.0F, -21.0F, -20.0F, 40.0F, 5.0F, 40.0F, new CubeDeformation(0.0F))
		.texOffs(120, 45).addBox(-15.0F, -16.0F, -15.0F, 30.0F, 2.0F, 30.0F, new CubeDeformation(0.0F))
		.texOffs(0, 110).addBox(-16.0F, -23.0F, -16.0F, 32.0F, 2.0F, 32.0F, new CubeDeformation(0.0F))
		.texOffs(170, 186).addBox(-8.0F, -25.0F, -8.0F, 16.0F, 2.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(170, 154).addBox(19.0F, -23.0F, -11.0F, 9.0F, 10.0F, 22.0F, new CubeDeformation(0.0F))
		.texOffs(0, 183).addBox(-28.0F, -23.0F, -11.0F, 9.0F, 10.0F, 22.0F, new CubeDeformation(0.0F))
		.texOffs(0, 45).addBox(-15.0F, -57.0F, -15.0F, 30.0F, 35.0F, 30.0F, new CubeDeformation(0.0F))
		.texOffs(128, 77).addBox(-5.0F, -14.0F, -9.0F, 10.0F, 3.0F, 35.0F, new CubeDeformation(0.0F))
		.texOffs(88, 154).addBox(-16.0F, -14.0F, -21.0F, 10.0F, 8.0F, 31.0F, new CubeDeformation(0.0F))
		.texOffs(160, 0).addBox(6.0F, -14.0F, -21.0F, 10.0F, 8.0F, 31.0F, new CubeDeformation(0.0F))
		.texOffs(128, 115).addBox(19.0F, -27.0F, -9.0F, 9.0F, 4.0F, 35.0F, new CubeDeformation(0.0F))
		.texOffs(0, 144).addBox(-28.0F, -27.0F, -9.0F, 9.0F, 4.0F, 35.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}




	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		 this.ufo_base.yRot = netHeadYaw * ((float)Math.PI / 180F);
	      this.ufo_base.xRot = headPitch * ((float)Math.PI / 180F);
	}
	

	public void setupSpinAnim(float mouthAnimation, float yRot, float xRot) {
        this.ufo_base.yRot = yRot * (float) (Math.PI / 180.0);
        this.ufo_base.xRot = xRot * (float) (Math.PI / 180.0);
    }

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		ufo_base.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}

		
	
}