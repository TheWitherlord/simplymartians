package com.wither.simplymartians.entities.rendering;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.wither.simplymartians.SimplyMartians;
import com.wither.simplymartians.entities.EnergyBall;
import com.wither.simplymartians.entities.PlasmaBall;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EnergyBallRenderer extends EntityRenderer<EnergyBall> {
   private static final ResourceLocation PLASMA_LOCATION = ResourceLocation.fromNamespaceAndPath(SimplyMartians.MODID,"textures/entity/energy_ball.png");
   private static final RenderType RENDER_TYPE = RenderType.entityCutoutNoCull(PLASMA_LOCATION);

   public EnergyBallRenderer(EntityRendererProvider.Context p_173962_) {
      super(p_173962_);
   }

   protected int getBlockLightLevel(EnergyBall p_114087_, BlockPos p_114088_) {
      return 15;
   }

   public void render(EnergyBall pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
       pPoseStack.pushPose();
       pPoseStack.scale(1.0F, 1.0F, 1.0F);
       pPoseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
       PoseStack.Pose posestack$pose = pPoseStack.last();
       VertexConsumer vertexconsumer = pBuffer.getBuffer(RENDER_TYPE);
       vertex(vertexconsumer, posestack$pose, pPackedLight, 0.0F, 0, 0, 1);
       vertex(vertexconsumer, posestack$pose, pPackedLight, 1.0F, 0, 1, 1);
       vertex(vertexconsumer, posestack$pose, pPackedLight, 1.0F, 1, 1, 0);
       vertex(vertexconsumer, posestack$pose, pPackedLight, 0.0F, 1, 0, 0);
       pPoseStack.popPose();
       super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
   }

   private static void vertex(VertexConsumer pConsumer, PoseStack.Pose pPose, int pPackedLight, float pX, int pY, int pU, int pV) {
       pConsumer.addVertex(pPose, pX - 0.5F, (float)pY - 0.25F, 0.0F)
           .setColor(-1)
           .setUv((float)pU, (float)pV)
           .setOverlay(OverlayTexture.NO_OVERLAY)
           .setLight(pPackedLight)
           .setNormal(pPose, 0.0F, 1.0F, 0.0F);
   }

   
   public ResourceLocation getTextureLocation(EnergyBall p_114078_) {
      return PLASMA_LOCATION;
   }
}