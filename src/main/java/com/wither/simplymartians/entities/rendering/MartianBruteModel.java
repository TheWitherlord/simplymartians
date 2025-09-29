package com.wither.simplymartians.entities.rendering;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.wither.simplymartians.SimplyMartians;
import com.wither.simplymartians.core.init.InitItem;
import com.wither.simplymartians.entities.MartianBruteEntity;
import com.wither.simplymartians.entities.SimpleMartianEntity;

import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;

public class MartianBruteModel<T extends MartianBruteEntity> extends EntityModel<T> implements ArmedModel, HeadedModel {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			 ResourceLocation.fromNamespaceAndPath(SimplyMartians.MODID, "martian_brute"), "main");	
	private final ModelPart head;
	private final ModelPart headwear;
	private final ModelPart body;
	private final ModelPart left_arm;
	private final ModelPart right_arm;
	private final ModelPart left_leg;
	private final ModelPart right_leg;

	public MartianBruteModel(ModelPart root) {
		this.head = root.getChild("head");
		this.headwear = this.head.getChild("headwear");
		this.body = root.getChild("body");
		this.left_arm = root.getChild("left_arm");
		this.right_arm = root.getChild("right_arm");
		this.left_leg = root.getChild("left_leg");
		this.right_leg = root.getChild("right_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(40, 47).addBox(-4.0F, -6.0F, -4.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(68, 36).addBox(-5.0F, -4.0F, -5.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(68, 40).addBox(2.0F, -4.0F, -5.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 0.0F));

		PartDefinition head_r1 = head.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(44, 18).addBox(-4.0F, -27.0F, -3.0F, 8.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.0F, -3.0F, -0.1309F, 0.0F, 0.0F));

		PartDefinition head_r2 = head.addOrReplaceChild("head_r2", CubeListBuilder.create().texOffs(44, 0).addBox(-5.0F, -32.0F, -6.0F, 10.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 19.0F, 0.0F, -0.0436F, 0.0F, 0.0F));

		PartDefinition headwear = head.addOrReplaceChild("headwear", CubeListBuilder.create().texOffs(0, 79).addBox(-7.0F, -14.0F, -7.0F, 14.0F, 16.0F, 14.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, -2.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 28).addBox(-8.0F, -3.0F, -4.0F, 16.0F, 10.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(0, 47).addBox(-7.0F, 7.0F, -2.0F, 14.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(50, 28).addBox(-1.0F, 0.0F, -2.0F, 5.0F, 14.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(50, 61).addBox(-1.0F, -1.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, -1.0F, 0.0F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 58).addBox(-4.0F, 0.0F, -2.0F, 5.0F, 14.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(68, 28).addBox(-5.0F, -1.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-9.0F, -1.0F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(18, 58).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 12.0F, 0.0F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(34, 61).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		 this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
	      this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
		 this.right_leg.xRot = -1.5F * Mth.triangleWave(pLimbSwing, 13.0F) * pLimbSwingAmount;
	      this.left_leg.xRot = 1.5F * Mth.triangleWave(pLimbSwing, 13.0F) * pLimbSwingAmount;
	      this.right_leg.yRot = 0.0F;
	      this.left_leg.yRot = 0.0F;
	      
	      if (this.riding) {
	            this.right_arm.xRot = (float) (-Math.PI / 5);
	            this.right_arm.yRot = 0.0F;
	            this.right_arm.zRot = 0.0F;
	            this.left_arm.xRot = (float) (-Math.PI / 5);
	            this.left_arm.yRot = 0.0F;
	            this.left_arm.zRot = 0.0F;
	            this.right_leg.xRot = -1.4137167F;
	            this.right_leg.yRot = (float) (Math.PI / 10);
	            this.right_leg.zRot = 0.07853982F;
	            this.left_leg.xRot = -1.4137167F;
	            this.left_leg.yRot = (float) (-Math.PI / 10);
	            this.left_leg.zRot = -0.07853982F;
	      
	      if (pEntity.getItemInHand(InteractionHand.MAIN_HAND).getItem() == InitItem.FLUXIUM_SWORD.get()) {
               AnimationUtils.swingWeaponDown(this.right_arm, this.left_arm, pEntity, this.attackTime, pAgeInTicks);
           } else {
 		      if (pEntity.getItemInHand(InteractionHand.MAIN_HAND).getItem() == InitItem.MARTIAN_ZAPPER.get()) {
               AnimationUtils.animateCrossbowHold(this.right_arm, this.left_arm, this.head, !pEntity.isLeftHanded());
           }
           }}
     
       }
      
	

	   public boolean isAggressive(T pEntity) {
	        return pEntity.isAggressive();
	    }
	   
	   
	   private ModelPart getArm(HumanoidArm pArm) {
	        return pArm == HumanoidArm.LEFT ? this.left_arm : this.right_arm;
	    }
	 
	 public ModelPart getHat() {
	        return this.headwear;
	    }

	    @Override
	    public ModelPart getHead() {
	        return this.head;
	    }

	 @Override
	    public void translateToHand(HumanoidArm pSide, PoseStack pPoseStack) {
	        this.getArm(pSide).translateAndRotate(pPoseStack);
	    
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}
}