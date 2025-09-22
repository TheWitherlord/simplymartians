package com.wither.simplymartians.entities.rendering;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.wither.simplymartians.SimplyMartians;
import com.wither.simplymartians.core.init.InitItem;
import com.wither.simplymartians.entities.SimpleMartianEntity;
import com.wither.simplymartians.entities.ZombieMartianEntity;

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
import net.minecraft.world.entity.HumanoidArm;

public class ZombieMartianModel<T extends ZombieMartianEntity> extends EntityModel<T> implements ArmedModel, HeadedModel{
		// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			 ResourceLocation.fromNamespaceAndPath(SimplyMartians.MODID, "zombie_martian"), "main");	
	private final ModelPart head;
		private final ModelPart body;
		private final ModelPart left_arm;
		private final ModelPart right_arm;
		private final ModelPart left_leg;
		private final ModelPart right_leg;
		private final ModelPart headwear;

		public ZombieMartianModel(ModelPart root) {
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

			PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 18).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
			.texOffs(0, 50).addBox(-5.0F, -2.0F, -5.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(10, 50).addBox(2.0F, -2.0F, -5.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

			PartDefinition head_r1 = head.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(0, 32).addBox(-4.0F, -27.0F, -3.0F, 8.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 13.0F, -3.0F, -0.1309F, 0.0F, 0.0F));

			PartDefinition head_r2 = head.addOrReplaceChild("head_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -32.0F, -6.0F, 10.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 21.0F, 0.0F, -0.0436F, 0.0F, 0.0F));

			PartDefinition headwear = head.addOrReplaceChild("headwear", CubeListBuilder.create().texOffs(0, 62).addBox(-5.0F, -14.0F, -6.0F, 10.0F, 16.0F, 12.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

			PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(32, 18).addBox(-4.0F, 2.0F, -2.0F, 8.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

			PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(46, 40).addBox(-1.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F))
			.texOffs(0, 42).addBox(-1.0F, -1.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 2.0F, 0.0F));

			PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(24, 48).addBox(-3.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F))
			.texOffs(46, 32).addBox(-5.0F, -1.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

			PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(30, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 12.0F, 0.0F));

			PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(40, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

			return LayerDefinition.create(meshdefinition, 128, 128);
		}




		public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
			 this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		      this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
			 this.right_leg.xRot = -1.5F * Mth.triangleWave(pLimbSwing, 13.0F) * pLimbSwingAmount;
		      this.left_leg.xRot = 1.5F * Mth.triangleWave(pLimbSwing, 13.0F) * pLimbSwingAmount;
		      this.right_leg.yRot = 0.0F;
		      this.left_leg.yRot = 0.0F;
		      
		        AnimationUtils.animateZombieArms(this.left_arm, this.right_arm, this.isAggressive(pEntity), this.attackTime, pAgeInTicks);

	      
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
			headwear.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		}
	}

