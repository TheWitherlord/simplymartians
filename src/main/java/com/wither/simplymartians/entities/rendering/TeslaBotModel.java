package com.wither.simplymartians.entities.rendering;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.wither.simplymartians.SimplyMartians;
import com.wither.simplymartians.core.init.InitItem;
import com.wither.simplymartians.entities.SimpleMartianEntity;
import com.wither.simplymartians.entities.TeslaBotEntity;

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

public class TeslaBotModel<T extends TeslaBotEntity> extends EntityModel<T> implements ArmedModel, HeadedModel{
		// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			 ResourceLocation.fromNamespaceAndPath(SimplyMartians.MODID, "tesla_bot"), "main");	
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart right_leg;
	private final ModelPart left_leg;
	private final ModelPart right_arm;
	private final ModelPart left_arm;



	public TeslaBotModel(ModelPart root) {
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.right_leg = root.getChild("right_leg");
		this.left_leg = root.getChild("left_leg");
		this.right_arm = root.getChild("right_arm");
		this.left_arm = root.getChild("left_arm");



		}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.4F, -1.6F, -3.6F, 7.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 11).addBox(-2.6F, -4.0F, -2.4F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(24, 17).addBox(-0.8F, -5.6F, -1.2F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(20, 17).addBox(-0.4F, -7.0F, -0.6F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(24, 21).addBox(-0.8F, -8.4F, -1.2F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.8F, 0.0F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(20, 11).addBox(-1.6F, 0.0F, -1.2F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 20.0F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 20).addBox(-0.8F, -0.4F, -1.2F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.2F, 20.4F, 0.0F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 20).addBox(-0.8F, -0.4F, -1.2F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.6F, 17.0F, 0.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(8, 20).addBox(-1.0F, -0.4F, -1.2F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.6F, 17.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}






		public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
			 this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		      this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
			 this.right_leg.xRot = -1.5F * Mth.triangleWave(pLimbSwing, 13.0F) * pLimbSwingAmount;
		      this.left_leg.xRot = 1.5F * Mth.triangleWave(pLimbSwing, 13.0F) * pLimbSwingAmount;
		      this.right_leg.yRot = 0.0F;
		      this.left_leg.yRot = 0.0F;
		      
		        AnimationUtils.animateZombieArms(this.left_arm, this.right_arm, this.isAggressive(pEntity), this.attackTime, pAgeInTicks);

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
		      

	            }
		      }
	      
	        
	       
		

		   public boolean isAggressive(T pEntity) {
		        return pEntity.isAggressive();
		    }
		   
		   
		   private ModelPart getArm(HumanoidArm pArm) {
		        return pArm == HumanoidArm.LEFT ? this.left_arm : this.right_arm;
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

